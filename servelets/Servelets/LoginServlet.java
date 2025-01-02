@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Dummy validation for the sake of example
        if ("admin".equals(username) && "password".equals(password)) {
            // If valid, create a user object and forward to the dashboard
            User user = new User(username, "admin@example.com");  // Assume User is a model class
            request.setAttribute("user", user);  // Set user as a request attribute
            RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard.jsp");
            dispatcher.forward(request, response);
        } else {
            // If invalid, redirect back to the login page with an error message
            response.sendRedirect("login.jsp?errorMessage=Invalid credentials");
        }
    }
}
