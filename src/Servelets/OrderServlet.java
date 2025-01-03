@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Show order page (e.g., products, cart)
        request.getRequestDispatcher("order.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String productId = request.getParameter("productId");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        // Process order (e.g., store order in database, update stock)
        createOrder(userId, productId, quantity);
        response.sendRedirect("order_confirmation.jsp");
    }

    private void createOrder(String userId, String productId, int quantity) {
        // Dummy order processing (replace with actual logic)
    }
}