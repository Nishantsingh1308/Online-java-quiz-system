@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Admin functionalities (e.g., viewing users, managing content)
        request.getRequestDispatcher("admin_dashboard.jsp").forward(request, response);
    }
}
