@WebServlet("/product")
public class ProductServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Show list of products
        List<Product> products = getProducts();  // Fetch products from the database
        request.setAttribute("products", products);
        request.getRequestDispatcher("product_list.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));

        // Add new product to the database
        addProduct(name, price);
        response.sendRedirect("product.jsp");
    }

    private List<Product> getProducts() {
        // Dummy product retrieval (replace with actual logic)
        return new ArrayList<>();
    }

    private void addProduct(String name, double price) {
        // Dummy product addition (replace with actual logic)
    }
}