@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        // Dummy registration logic for the sake of example
        if (username != null && password != null && email != null) {
            // Registration successful
            response.sendRedirect("registration.jsp?successMessage=Registration successful!");
        } else {
            // Registration failed
            response.sendRedirect("registration.jsp?errorMessage=Please fill all fields!");
        }
    }
}