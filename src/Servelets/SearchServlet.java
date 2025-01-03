@WebServlet("/search")
public class SearchServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");
        // Search logic
        List<Item> results = searchItems(query);
        request.setAttribute("results", results);
        request.getRequestDispatcher("search_results.jsp").forward(request, response);
    }

    private List<Item> searchItems(String query) {
        // Dummy search logic (replace with actual logic)
        return new ArrayList<>();
    }
}