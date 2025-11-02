package java19;

/**
 * Java 19 Features Practice
 * Demonstrates all major Java 18 features with detailed explanations and practical examples.
 */

import java.util.List;
import java.util.concurrent.*;

public class Java19FeaturesPractice {

    public static void main(String[] args) throws Exception {
        System.out.println("===== Java 19 Features Practice: TechStore v9.0 =====\n");

        recordPatternsDemo();
        patternMatchingSwitchDemo();
        virtualThreadsDemo();
        structuredConcurrencyDemo();
    }

    // =========================================================
    // Record Patterns (Preview)
    // =========================================================
    static void recordPatternsDemo() {
        System.out.println("\n Record Patterns Demo");
        record Product(String name, double price, String category) {}
        Object obj = new Product("MacBook Air", 1400.0, "Laptop");

        if (obj instanceof Product(String name, double price, String category) p) { // Record pattern
            System.out.println("Matched product via record pattern: " + p.name() + " - $" + p.price());
        }
    }

    // =========================================================
    // Pattern Matching for Switch (Preview)
    // =========================================================
    static void patternMatchingSwitchDemo() {
        System.out.println("\n Pattern Matching Switch Demo");
        Object product = new Product("Logitech Mouse", 45.0, "Accessory");

        String category = switch (product) {
            case Product(String n, double price, String cat) -> cat; // pattern matching in switch
            default -> "Unknown";
        };
        System.out.println("Product category via switch pattern: " + category);
    }

    // =========================================================
    // Virtual Threads (Preview)
    // =========================================================
    static void virtualThreadsDemo() throws Exception {
        System.out.println("\n Virtual Threads Demo (Preview)");

        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        List<Callable<String>> tasks = List.of(
                () -> { Thread.sleep(500); return "Load products"; },
                () -> { Thread.sleep(300); return "Process orders"; },
                () -> { Thread.sleep(200); return "Update inventory"; }
        );

        List<Future<String>> results = executor.invokeAll(tasks);
        for (Future<String> f : results) {
            System.out.println(f.get());
        }

        executor.shutdown();
    }

    // =========================================================
    // Structured Concurrency (Preview)
    // =========================================================
    static void structuredConcurrencyDemo() throws Exception {
        System.out.println("\n Structured Concurrency Demo (Preview)");

        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) { // preview API
            Future<String> task1 = scope.fork(() -> "Fetch users");
            Future<String> task2 = scope.fork(() -> "Fetch products");

            scope.join(); // waits for all
            scope.throwIfFailed();

            System.out.println("Task1 result: " + task1.resultNow());
            System.out.println("Task2 result: " + task2.resultNow());
        } catch (Exception e) {
            System.out.println("Structured concurrency error: " + e.getMessage());
        }
    }

    // Product record reused
    record Product(String name, double price, String category) {}
}

