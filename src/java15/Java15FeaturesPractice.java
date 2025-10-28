package java15;


import java.util.*;

/**
 * Java 15 Features Practice
 * Demonstrates all major Java 15 features with detailed explanations and practical examples.
 */
public class Java15FeaturesPractice {

    public static void main(String[] args) {

        System.out.println(" Java 15 Features Practice Demo");
        System.out.println("=".repeat(60));

        // ============================================================
        // 1️⃣ Text Blocks (JEP 378 — Standardized)
        // ============================================================
        var jsonText = """
                {
                    "name": "Laptop",
                    "price": 2500,
                    "stock": 5
                }
                """;
        System.out.println("\n Text Block Example:\n" + jsonText);

        // ============================================================
        // 2️⃣ Records (JEP 360) — continued
        // ============================================================
        record Product(String name, double price, int stock) {
        }
        var p1 = new Product("MacBook Pro", 2500.0, 5);
        System.out.println("\n Record Example: " + p1);

        // ============================================================
        // 3️⃣ Pattern Matching for instanceof (JEP 375 — preview)
        // ============================================================
        Object obj = "Java 15 is awesome!";
        if (obj instanceof String s) { // automatically casts
            System.out.println(" Pattern matching string: " + s.toUpperCase());
        }

        // ============================================================
        // 4️⃣ Sealed Classes (JEP 360 — preview)
        // ============================================================
        System.out.println("\n Sealed Classes Demo:");
        // Conceptual: restricts which classes can extend or implement
        System.out.println("Sealed classes allow controlled inheritance. Example: Vehicle -> Car, Bike");

        // ============================================================
        // 5️⃣ Helpful NullPointerExceptions
        // ============================================================
        System.out.println("""
                 Helpful NullPointerException:
                - JVM now points exactly which variable was null
                - Makes debugging easier
                """);

        // ============================================================
        // 6️⃣ Hidden Classes (JEP 371 — advanced)
        // ============================================================
        System.out.println("""
                 Hidden Classes:
                - JVM can create classes not discoverable by classloader
                - Used in frameworks & dynamic code generation
                """);

        // ============================================================
        // 7️⃣ Local-variable type inference (var) enhancements
        // ============================================================
        System.out.println("\n Using var in loops and streams:");
        var products = List.of(p1, new Product("Dell XPS", 2200.0, 3));
        for (var p : products) {
            System.out.println("Product: " + p.name());
        }

        // ============================================================
        // 8️⃣ Stream API — advanced usage
        // ============================================================
        var totalStock = products.stream().mapToInt(Product::stock).sum();
        System.out.println("\n Total stock: " + totalStock);

        // ============================================================
        // 9️⃣ Switch Expressions (finalized)
        // ============================================================
        var day = "MONDAY";
        var typeOfDay = switch (day) {
            case "SATURDAY", "SUNDAY" -> "Weekend";
            default -> "Weekday";
        };
        System.out.println(" " + day + " is a " + typeOfDay);

        System.out.println("\n All major Java 15 features demonstrated!");
    }
}

