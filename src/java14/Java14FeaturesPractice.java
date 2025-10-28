package java14;

import java.util.*;

/**
 * Java 14 Features Practice
 * Demonstrates all major Java 14 features with detailed explanations and practical examples.
 */
public class Java14FeaturesPractice {

    public static void main(String[] args) {

        System.out.println(" Java 14 Features Practice Demo");
        System.out.println("=".repeat(60));

        // ============================================================
        // 1️⃣ Switch Expressions (Finalized)
        // ============================================================
        var day = "SATURDAY";
        var typeOfDay = switch (day) {
            case "SATURDAY", "SUNDAY" -> "Weekend";
            default -> "Weekday";
        };
        System.out.println("\n " + day + " is a " + typeOfDay);

        // ============================================================
        // 2️⃣ Records (JEP 359) — compact data classes
        // ============================================================
        // Records automatically create constructor, getters, equals, hashCode, toString
        record Product(String name, double price, int stock) {
        }
        var p1 = new Product("MacBook Pro", 2500.0, 5);
        var p2 = new Product("Dell XPS", 2200.0, 3);
        System.out.println("\n Record Example: " + p1);

        // ============================================================
        // 3️⃣ Pattern Matching for instanceof (JEP 305)
        // ============================================================
        Object obj = "Hello Java 14!";
        if (obj instanceof String s) { // no need to cast
            System.out.println(" Pattern matching detected string: " + s.toUpperCase());
        }

        // ============================================================
        // 4️⃣ Helpful NullPointerExceptions (enabled by JVM flag)
        // ============================================================
        System.out.println("""
                ⚠️ Helpful NullPointerException:
                - Now JVM can show exactly which variable was null
                - Improves debugging
                """);

        // ============================================================
        // 5️⃣ Text Blocks (continued from Java 13)
        // ============================================================
        var jsonText = """
                {
                    "name": "Keyboard",
                    "price": 100,
                    "stock": 15
                }
                """;
        System.out.println("\n Text Block Example:\n" + jsonText);

        // ============================================================
        // 6️⃣ Switch expressions with yield
        // ============================================================
        int month = 8;
        var quarter = switch (month) {
            case 1, 2, 3 -> "Q1";
            case 4, 5, 6 -> "Q2";
            case 7, 8, 9 -> "Q3";
            case 10, 11, 12 -> "Q4";
            default -> throw new IllegalArgumentException("Invalid month: " + month);
        };
        System.out.println("\n Month " + month + " is in " + quarter);

        // ============================================================
        // 7️⃣ Local-Variable Type Inference (var) — loops, streams, try-with-resources
        // ============================================================
        var products = List.of(p1, p2);
        System.out.println("\n Loop with var:");
        for (var p : products) {
            System.out.println(p.name() + " - $" + p.price());
        }

        // ============================================================
        // 8️⃣ Stream API usage
        // ============================================================
        var totalStock = products.stream()
                .mapToInt(Product::stock)
                .sum();
        System.out.println("\n Total stock: " + totalStock);

        // ============================================================
        // 9️⃣ Switch expressions with multiple statements (using yield)
        // ============================================================
        var dayType = switch (day) {
            case "SATURDAY", "SUNDAY" -> {
                System.out.println("Processing weekend...");
                yield "Weekend";
            }
            default -> {
                System.out.println("Processing weekday...");
                yield "Weekday";
            }
        };
        System.out.println("Day Type: " + dayType);

        System.out.println("\n All major Java 14 features demonstrated!");
    }
}

