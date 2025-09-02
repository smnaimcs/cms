import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

public class RememberMeFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);

        // Only check remember-me if session does not exist
        if (session == null || session.getAttribute("username") == null) {

            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if ("rememberMe".equals(c.getName())) {
                        String token = c.getValue();

                        try (Connection con = DriverManager.getConnection(
                                "jdbc:mysql://localhost:3306/testdb", "webapp_user", "My$trongP@ss123")) {

                            // Query token table + join Users to get username
                            PreparedStatement ps = con.prepareStatement(
                                "SELECT t.user_id, t.role, t.expiry, u.username " +
                                "FROM remember_me_tokens t " +
                                "JOIN Users u ON u.id = t.user_id " +
                                "WHERE t.token = ?");
                            ps.setString(1, token);
                            ResultSet rs = ps.executeQuery();

                            if (rs.next()) {
                                java.sql.Timestamp expiry = rs.getTimestamp("expiry");

                                if (expiry.after(new java.util.Date())) {
                                    // Token valid → restore session
                                    int userId = rs.getInt("user_id");
                                    String role = rs.getString("role");
                                    String username = rs.getString("username");

                                    session = request.getSession();
                                    session.setAttribute("userId", userId);
                                    session.setAttribute("username", username);
                                    session.setAttribute("role", role);

                                    // Auto-redirect to home page based on role
                                    if ("student".equalsIgnoreCase(role)) {
                                        response.sendRedirect("studentHome.jsp");
                                        return;
                                    } else if ("teacher".equalsIgnoreCase(role)) {
                                        response.sendRedirect("teacherHome.jsp");
                                        return;
                                    } else if ("admin".equalsIgnoreCase(role)) {
                                        response.sendRedirect("adminHome.jsp");
                                        return;
                                    }

                                } else {
                                    // Token expired → delete from DB
                                    PreparedStatement del = con.prepareStatement(
                                        "DELETE FROM remember_me_tokens WHERE token=?");
                                    del.setString(1, token);
                                    del.executeUpdate();
                                }
                            }

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        break; // only process the first rememberMe cookie
                    }
                }
            }
        }

        chain.doFilter(req, res);
    }
}
