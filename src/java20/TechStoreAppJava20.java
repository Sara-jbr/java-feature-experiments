package java20;

/**
 * TechStore Java 20 Edition
 * Demonstrates Java 20 features in a mini product management app.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TechStoreAppJava20 {

    public static void main(String[] args) throws Exception {
        System.out.println("===== TechStore v10.0 (Java 20 Preview) =====\n");

        StoreService store = new StoreService();
        store.addProduct(new Product("MacBook Pro", 2500.0, Category.LAPTOP));
        store.addProduct(new Product("Logitech Mouse", 45.0, Category.ACCESSORY));
        store.addProduct(new Product("Samsung Monitor", 320.0, Category.MONITOR));

        // Display products
        store.displayProducts();

        // Pattern Matching for Switch demo
        Object obj = new Product("Mechanical Keyboard", 120.0, Category.ACCESSORY);
        String cat = switch (obj) {
            case Product(String n, double price, Category c) -> c.name();
            default -> "UNKNOWN";
        };
        System.out.println("\nPattern matching switch category: " + cat);

        // Virtual Threads: concurrent product processing
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        List<Callable<String>> tasks = store.getProducts().stream()
                .map(p -> (Callable<String>) () -> "Processed: " + p.name())
                .toList();

        List<Future<String>> results = executor.invokeAll(tasks);
        results.forEach(r -> {
            try {
                System.out.println(r.get());
            } catch (Exception ignored) {
            }
        });
        executor.shutdown();

        // Scoped Values (Preview)
        var scopedStoreName = ScopedValue.newInstance("TechStore v10.0");
        try (var scope = ScopedValue.where(scopedStoreName, "Scoped Store Demo")) {
            System.out.println("\nScoped store name inside scope: " + scopedStoreName.get());
        }

        System.out.println("\n TechStore v10.0 running smoothly with Java 20 features!");
    }
}

// =======================================================
//  Product record
// =======================================================
record Product(String name, double price, Category category) {
}

// =======================================================
//  Category Enum
// =======================================================
enum Category {
    LAPTOP, ACCESSORY, MONITOR
}

// =======================================================
//  StoreService — product operations
// =======================================================
class StoreService {
    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product p) {
        products.add(p);
        System.out.println("Added: " + p.name());
    }

    public void displayProducts() {
        System.out.println("\n Product List:");
        products.forEach(System.out::println);
    }

    public List<Product> getProducts() {
        return products;
    }
}

