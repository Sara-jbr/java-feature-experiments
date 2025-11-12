package java21;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static java.lang.StringTemplate.STR;

/**
 * Java 21 Features Practice
 * Demonstrates major Java 21 features with realistic examples for a TechStore mini app.
 */
public class Java21FeaturesPractice {

    public static void main(String[] args) throws Exception {
        System.out.println("===== Java 21 Features Practice: TechStore v11.0 =====\n");

        recordPatternsDemo();
        patternMatchingSwitchDemo();
        virtualThreadsDemo();
        scopedValuesDemo();
        stringTemplatesDemo();
        sequencedCollectionsDemo();
    }

    // =========================================================
    // Record Patterns (first introduction in Java 21)
    // =========================================================
    static void recordPatternsDemo() {
        System.out.println("\nRecord Patterns Demo (Java 21)");
        record Product(String name, double price, String category) {}
        Object obj = new Product("MacBook Air", 1400.0, "Laptop");

        if (obj instanceof Product p) { // Record pattern simplified
            System.out.println("Matched product: " + p.name() + " - $" + p.price());
        }
    }

    // =========================================================
    // Pattern Matching Switch
    // =========================================================
    static void patternMatchingSwitchDemo() {
        System.out.println("\nPattern Matching Switch Demo (Java 21)");

        Object product = new Product("Logitech Mouse", 45.0, "Accessory");

        String category = switch (product) {
            case Product(String n, double price, String cat) -> cat;
            default -> "Unknown";
        };

        System.out.println("Product category via switch: " + category);
    }

    // =========================================================
    // Virtual Threads
    // =========================================================
    static void virtualThreadsDemo() throws Exception {
        System.out.println("\nVirtual Threads Demo (Java 21)");
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        List<Callable<String>> tasks = List.of(
                () -> "Load products",
                () -> "Process orders",
                () -> "Update inventory"
        );

        List<Future<String>> results = executor.invokeAll(tasks);
        for (Future<String> f : results) System.out.println(f.get());

        executor.shutdown();
    }

    // =========================================================
    // Scoped Values (Preview)
    // =========================================================
    static void scopedValuesDemo() throws Exception {
        System.out.println("\nScoped Values Demo (Java 21 Preview)");
        ScopedValue<String> storeName = ScopedValue.newInstance("TechStore v11.0");
        try (ScopedValue.Scope scope = ScopedValue.where(storeName, "Scoped Store Demo")) {
            System.out.println("Scoped store name inside scope: " + storeName.get());
        }
        System.out.println("Scoped store name outside scope: " + storeName.get());
    }

    // =========================================================
    // String Templates (Preview)
    // =========================================================
    static void stringTemplatesDemo() {
        System.out.println("\nString Templates Demo (Java 21 Preview)");
        String productName = "MacBook Pro";
        double price = 2500.0;

        String template = STR."Product: \{productName} | Price: $\{price}";
        System.out.println(template);
    }

    // =========================================================
    // Sequenced Collections (JEP 431)
    // =========================================================
    static void sequencedCollectionsDemo() {
        System.out.println("\nSequenced Collections Demo (Java 21)");
        Map<String, Double> products = Map.of(
                "Laptop", 2000.0,
                "Monitor", 320.0,
                "Mouse", 45.0
        );

        Map<String, Double> sequenced = Map.copyOf(products); // preserves insertion order
        sequenced.forEach((k, v) -> System.out.println(k + " -> $" + v));
    }

    // Product record
    record Product(String name, double price, String category) {}
}
