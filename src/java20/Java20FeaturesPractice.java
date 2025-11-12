package java20;


/**
 * Java 20 Features Practice
 * Demonstrates all major Java 20 features with detailed explanations and practical examples.
 */

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Java20FeaturesPractice {

    public static void main(String[] args) throws Exception {
        System.out.println("===== Java 20 Features Practice: TechStore v10.0 =====\n");

        recordPatternsDemo();
        patternMatchingSwitchDemo();
        virtualThreadsDemo();
        scopedValuesDemo();
    }

    // =========================================================
    // Record Patterns (finalized in Java 20)
    // =========================================================
    static void recordPatternsDemo() {
        System.out.println("\n🔹 Record Patterns Demo");

        record Product(String name, double price, String category) {
        }
        Object obj = new Product("MacBook Air", 1400.0, "Laptop");

        if (obj instanceof Product p) { // Record pattern simplified in Java 20
            System.out.println("Matched product: " + p.name() + " - $" + p.price());
        }
    }

    // =========================================================
    //  Pattern Matching for Switch (Enhanced)
    // =========================================================
    static void patternMatchingSwitchDemo() {
        System.out.println("\n Pattern Matching Switch Demo");
        Object product = new Product("Logitech Mouse", 45.0, "Accessory");

        String category = switch (product) {
            case Product(String n, double price, String cat) -> cat;
            default -> "Unknown";
        };
        System.out.println("Product category via switch pattern: " + category);
    }

    // =========================================================
    // Virtual Threads (Improved)
    // =========================================================
    static void virtualThreadsDemo() throws Exception {
        System.out.println("\n Virtual Threads Demo");

        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        List<Callable<String>> tasks = List.of(
                () -> {
                    Thread.sleep(500);
                    return "Load products";
                },
                () -> {
                    Thread.sleep(300);
                    return "Process orders";
                },
                () -> {
                    Thread.sleep(200);
                    return "Update inventory";
                }
        );

        List<Future<String>> results = executor.invokeAll(tasks);
        for (Future<String> f : results) {
            System.out.println(f.get());
        }

        executor.shutdown();
    }

    // =========================================================
    // Scoped Values (Preview)
    // =========================================================
    static void scopedValuesDemo() throws Exception {
        System.out.println("\n Scoped Values Demo (Preview)");

        var storeName = ScopedValue.newInstance("TechStore v10.0");
        try (var scope = ScopedValue.where(storeName, "TechStore Scoped Demo")) {
            System.out.println("Scoped store name inside scope: " + storeName.get());
        }
        // Outside scope, accessing storeName would throw IllegalStateException if used
    }

    // Product record
    record Product(String name, double price, String category) {
    }
}

