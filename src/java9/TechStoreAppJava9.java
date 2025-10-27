package java9;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TechStoreAppJava9 {

    public static void main(String[] args) {

        // =====================================================
        // 1️⃣ IMMUTABLE COLLECTION FACTORY METHODS
        // =====================================================
        List<Product> products = List.of(
                new Product(1, "MacBook Pro", "Laptop", 2500.0, 10),
                new Product(2, "Logitech Mouse", "Accessory", 50.0, 30),
                new Product(3, "Samsung Monitor", "Monitor", 300.0, 8)
        );

        System.out.println(" Immutable product list:");
        products.forEach(System.out::println);

        // =====================================================
        // 2️⃣ STREAM API IMPROVEMENTS (takeWhile, dropWhile)
        // =====================================================
        System.out.println("\n Products under $1000 (using takeWhile):");
        products.stream()
                .sorted(Comparator.comparing(Product::getPrice))
                .takeWhile(p -> p.getPrice() < 1000)
                .forEach(System.out::println);

        System.out.println("\n Products above $1000 (using dropWhile):");
        products.stream()
                .sorted(Comparator.comparing(Product::getPrice))
                .dropWhile(p -> p.getPrice() < 1000)
                .forEach(System.out::println);

        // =====================================================
        // 3️⃣ OPTIONAL ENHANCEMENTS
        // =====================================================
        Optional<Product> optional = products.stream()
                .filter(p -> p.getName().equalsIgnoreCase("Dell XPS 15"))
                .findFirst();

        optional.ifPresentOrElse(
                p -> System.out.println("\n Found: " + p),
                () -> System.out.println("\n Dell XPS 15 not found.")
        );

        // or()
        Product fallback = optional.or(() -> Optional.of(new Product(0, "Default", "N/A", 0.0, 0))).get();
        System.out.println("Fallback product: " + fallback);

        // stream() from Optional
        products = Stream.concat(products.stream(), optional.stream()).collect(Collectors.toList());

        // =====================================================
        // 4️⃣ PRIVATE INTERFACE METHODS + DEFAULT METHOD
        // =====================================================
        StoreOperations ops = new StoreManager();
        ops.generateDetailedReport(products);

        // =====================================================
        // 5️⃣ PROCESS API
        // =====================================================
        ProcessHandle.current().info().user().ifPresent(u -> System.out.println("\n Running as: " + u));

        // =====================================================
        // 6️⃣ TRY-WITH-RESOURCES IMPROVEMENT
        // =====================================================
        System.out.println("\n Try-with-resources:");
        var resource = new Resource9();
        try (resource) {
            resource.process();
        }

        // =====================================================
        // 7️⃣ CONTINUE USING LAMBDA + STREAMS + COLLECTORS
        // =====================================================
        Map<String, Double> avgByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.averagingDouble(Product::getPrice)));
        System.out.println("\n Average price by category: " + avgByCategory);
    }
}

// ===============================================
// PRODUCT CLASS
// ===============================================
class Product {
    private int id;
    private String name;
    private String category;
    private double price;
    private int stock;

    public Product(int id, String name, String category, double price, int stock) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }

    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return String.format("%s (%s) - $%.2f [Stock: %d]", name, category, price, stock);
    }
}

// ===============================================
// INTERFACE WITH PRIVATE METHOD (Java 9)
// ===============================================
interface StoreOperations {
    default void generateDetailedReport(List<Product> products) {
        printHeader();
        products.forEach(p -> System.out.println(" " + p.getName() + " - $" + p.getPrice()));
        printFooter();
    }

    private void printHeader() {
        System.out.println("\n===== STORE REPORT START =====");
    }

    private void printFooter() {
        System.out.println("===== STORE REPORT END =====");
    }
}

// ===============================================
// CLASS IMPLEMENTATION
// ===============================================
class StoreManager implements StoreOperations { }

// ===============================================
// AUTO-CLOSEABLE RESOURCE (Java 9 try-with-resources demo)
// ===============================================
class Resource9 implements AutoCloseable {
    void process() {
        System.out.println("Processing resource safely...");
    }

    @Override
    public void close() {
        System.out.println("Resource closed automatically.");
    }
}

