package java16;


/**
 * Java 16 Features Practice
 * Demonstrates all major Java 16 features with detailed explanations and practical examples.
 */

import java.util.List;

public class Java16FeaturesPractice {

    public static void main(String[] args) throws Exception {
        System.out.println("===== Java 16 Features Practice: TechStore v6.0 =====\n");

        recordDemo();             //  JEP 395: Records
        patternMatchingDemo();    //  JEP 394: Pattern Matching for instanceof
        sealedClassesDemo();      //  JEP 397: Sealed Classes (preview)
        streamEnhancementDemo();  //  Stream.toList()
        unixDomainSocketDemo();   //  JEP 380: Unix-Domain Socket Channels
        packagingToolInfo();      //  JEP 392: Packaging Tool
        foreignLinkerAPINote();   //  JEP 389: Foreign Linker API (Incubator)
        vectorAPINote();          //  JEP 338: Vector API (Incubator)
    }

    // =========================================================
    // JEP 395 — Records (Standard Feature)
    // Records are immutable data carriers — perfect for DTOs or API responses.
    // Automatically generate constructor, getters, equals, hashCode, toString.
    // =========================================================
    static void recordDemo() {
        System.out.println("\n Record Demo — Product Record");
        record Product(String name, double price, String category) {
        }

        Product laptop = new Product("Asus ZenBook", 1299.99, "Laptops");
        Product phone = new Product("iPhone 13", 999.00, "Phones");

        System.out.println("Created product: " + laptop);
        System.out.println("Product category: " + laptop.category());
        System.out.println("Are two products equal? " + laptop.equals(phone));
    }

    // =========================================================
    // JEP 394 — Pattern Matching for 'instanceof'
    // Simplifies type checking and casting.
    // =========================================================
    static void patternMatchingDemo() {
        System.out.println("\n Pattern Matching Demo");
        Object obj = "TechStore Java 16";

        // Old way (before Java 16)
        if (obj instanceof String) {
            String s = (String) obj;
            System.out.println("Old way: length = " + s.length());
        }

        // New way
        if (obj instanceof String s) {
            System.out.println("New way: length = " + s.length());
        }
    }

    // =========================================================
    // JEP 397 — Sealed Classes (Preview)
    // Restrict which classes can extend or implement a superclass/interface.
    // Useful for defining limited, controlled inheritance hierarchies.
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
            System.out.println(name + " → Admin: Can manage inventory & users.");
        }
    }

    static final class Customer implements TechUser {
        String name;

        Customer(String name) {
            this.name = name;
        }

        public void showRole() {
            System.out.println(name + " → Customer: Can browse and purchase items.");
        }
    }

    // =========================================================
    // Stream API Enhancements — Stream.toList()
    // Java 16 adds a built-in method to collect Stream results into an unmodifiable List.
    // =========================================================
    static void streamEnhancementDemo() {
        System.out.println("\n Stream API Enhancement Demo");

        List<String> products = List.of("Laptop", "Mouse", "Keyboard", "Monitor");
        List<String> filtered = products.stream()
                .filter(p -> p.length() > 5)
                .map(String::toUpperCase)
                .toList(); // New method (no need for collectors!)

        System.out.println("Filtered Products: " + filtered);
    }

    // =========================================================
    // JEP 380 — Unix-Domain Socket Channels
    // Enables inter-process communication (IPC) on Unix systems.
    // We'll just simulate it since it requires OS support.
    // =========================================================
    static void unixDomainSocketDemo() {
        System.out.println("\n Unix-Domain Socket Channels Demo (simulation)");
        System.out.println("Unix sockets allow fast IPC for microservices on the same host.");
        System.out.println("(Demo skipped – requires OS-level support)");
    }

    // =========================================================
    // JEP 392 — Packaging Tool
    // A tool to package self-contained Java applications (e.g., jpackage)
    // =========================================================
    static void packagingToolInfo() {
        System.out.println("\n Packaging Tool (jpackage)");
        System.out.println("You can package your TechStore app using:");
        System.out.println("→ jpackage --name TechStoreApp --input target/ --main-class com.techstore.Main");
    }

    // =========================================================
    // JEP 389 — Foreign Linker API (Incubator)
    // Enables calling native code (like C libraries) safely without JNI.
    // =========================================================
    static void foreignLinkerAPINote() {
        System.out.println("\n Foreign Linker API (Incubator)");
        System.out.println("Used for native interop — replacing old JNI for better safety and performance.");
    }

    // =========================================================
    // JEP 338 — Vector API (Incubator)
    // Improves performance for data-parallel computations.
    // =========================================================
    static void vectorAPINote() {
        System.out.println("\n Vector API (Incubator)");
        System.out.println("Used for efficient mathematical computations — good for image, ML, or finance apps.");
    }
}

