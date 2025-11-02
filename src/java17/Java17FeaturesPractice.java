package java17;

/**
 * Java 17 Features Practice
 * Demonstrates all major Java 17 features with detailed explanations and practical examples.
 */

import java.util.*;


public class Java17FeaturesPractice {

    public static void main(String[] args) {
        System.out.println("===== Java 17 Features Practice: TechStore v7.0 =====\n");

        recordDemo();             // JEP 395 (Records)
        sealedClassesDemo();      // JEP 409 (Sealed Classes finalized)
        patternMatchingDemo();    // JEP 394 (Pattern Matching for instanceof)
        switchExpressionDemo();   // JEP 361 (Switch Expressions finalized)
        textBlocksDemo();         // JEP 378 (Text Blocks)
        streamToListDemo();       // Stream.toList() enhancement
    }

    // =========================================================
    // Records — immutable DTOs
    // =========================================================
    static void recordDemo() {
        System.out.println("\n Record Demo");
        record Product(String name, double price, String category) {
        }
        Product laptop = new Product("Dell XPS 17", 2200.0, "Laptops");
        System.out.println("Product record: " + laptop);
    }

    // =========================================================
    //  Sealed Classes — finalized in Java 17
    // =========================================================
    static void sealedClassesDemo() {
        System.out.println("\n Sealed Classes Demo");
        TechUser admin = new Admin("Sara");
        TechUser customer = new Customer("Ali");

        admin.showRole();
        customer.showRole();
    }

    sealed interface TechUser {
        void showRole();
    }

    static final class Admin implements TechUser {
        String name;

        Admin(String name) {
            this.name = name;
        }

        public void showRole() {
            System.out.println(name + " → Admin: Manages inventory");
        }
    }

    static final class Customer implements TechUser {
        String name;

        Customer(String name) {
            this.name = name;
        }

        public void showRole() {
            System.out.println(name + " → Customer: Can browse products");
        }
    }

    // =========================================================
    //  Pattern Matching for instanceof
    // =========================================================
    static void patternMatchingDemo() {
        System.out.println("\n Pattern Matching Demo");
        Object obj = "Java 17 TechStore";
        if (obj instanceof String s) {
            System.out.println("Matched string length: " + s.length());
        }
    }

    // =========================================================
    //  Switch Expressions — can return a value
    // =========================================================
    static void switchExpressionDemo() {
        System.out.println("\n Switch Expressions Demo");
        String category = "Laptops";

        double discount = switch (category) {
            case "Laptops" -> 15.0;
            case "Accessories" -> 5.0;
            default -> 0.0;
        };
        System.out.println("Discount for " + category + ": " + discount + "%");
    }

    // =========================================================
    //  Text Blocks — multi-line strings
    // =========================================================
    static void textBlocksDemo() {
        System.out.println("\n Text Blocks Demo");
        String html = """
                <html>
                    <head><title>TechStore</title></head>
                    <body>
                        <h1>Welcome to TechStore v7.0!</h1>
                    </body>
                </html>
                """;
        System.out.println(html);
    }

    // =========================================================
    //  Stream.toList() — built-in collector
    // =========================================================
    static void streamToListDemo() {
        System.out.println("\n Stream.toList() Demo");
        List<String> products = List.of("Laptop", "Mouse", "Keyboard", "Monitor");
        List<String> filtered = products.stream()
                .filter(p -> p.length() > 5)
                .map(String::toUpperCase)
                .toList(); // Java 16+ method
        System.out.println("Filtered Products: " + filtered);
    }
}

