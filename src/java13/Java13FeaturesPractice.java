package java13;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Java 13 Features Practice
 * Demonstrates all major Java 13 features with detailed explanations and practical examples.
 */
public class Java13FeaturesPractice {

    public static void main(String[] args) throws IOException {

        System.out.println(" Java 13 Features Practice Demo");
        System.out.println("=".repeat(60));

        // ============================================================
        // 1️⃣ Text Blocks (JEP 355)
        // ============================================================
        // Multiline string literals, easier than concatenation
        var jsonTemplate = """
                {
                    "name": "Laptop",
                    "price": 2500,
                    "stock": 5
                }
                """;
        System.out.println("\n Text Block Example:\n" + jsonTemplate);

        // ============================================================
        // 2️⃣ Switch Expressions Updates
        // ============================================================
        // Java 12 introduced switch expressions; Java 13 finalized improvements
        var day = "FRIDAY";
        var typeOfDay = switch (day) {
            case "SATURDAY", "SUNDAY" -> "Weekend";
            case "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY" -> "Weekday";
            default -> throw new IllegalStateException("Invalid day: " + day);
        };
        System.out.println("\n " + day + " is a " + typeOfDay);

        // ============================================================
        // 3️⃣ Dynamic CDS Archives (JEP 350)
        // ============================================================
        // Conceptual: improves startup by sharing pre-processed classes
        System.out.println("""
                ⚡ Dynamic CDS (Class Data Sharing):
                - Improves JVM startup and memory footprint
                - Automatically creates CDS archive for application classes
                - Conceptual example (no code needed)
                """);

        // ============================================================
        // 4️⃣ Reimplement Legacy Socket API (JEP 353)
        // ============================================================
        // Conceptual: socket API is now internally updated for performance and maintainability
        System.out.println("""
                ️ Legacy Socket API Reimplementation:
                - Modernized internal implementation
                - Backward compatible
                - No direct user-facing change
                """);

        // ============================================================
        // 5️⃣ FileSystems API Updates (JEP 352)
        // ============================================================
        // Enhanced Windows: improved handling of symbolic links and errors
        var tempFile = Files.createTempFile("java13demo", ".txt");
        System.out.println("\n Created temp file: " + tempFile);
        System.out.println("Is regular file? " + Files.isRegularFile(tempFile));
        Files.deleteIfExists(tempFile);

        // ============================================================
        // 6️⃣ Stream API + Collectors Enhancements
        // ============================================================
        var products = List.of("Laptop", "Mouse", "Keyboard", "Monitor", "Laptop");
        // Collect distinct products
        var distinctProducts = products.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println("\n Distinct Products: " + distinctProducts);

        // ============================================================
        // 7️⃣ String API Enhancements (continued from Java 12)
        // ============================================================
        var text = "java13features";
        System.out.println("\n Uppercase using transform(): " + text.transform(String::toUpperCase));

        // ============================================================
        // 8️⃣ Helpful NullPointerExceptions (conceptual)
        // ============================================================
        // Java 13 enhances exceptions with more descriptive messages
        System.out.println("""
                 Helpful NullPointerException:
                - NullPointerExceptions now show exactly which variable was null
                - Improves debugging
                """);

        // ============================================================
        // 9️⃣ Local Variable Type Inference (var) in loops and try-with-resources
        // ============================================================
        System.out.println("\n Local variable type inference using var:");
        for (var p : distinctProducts) {
            System.out.println("Product: " + p);
        }

        try (var scanner = new java.util.Scanner(System.in)) {
            System.out.println(" Scanner ready (auto-close using var)");
        } catch (Exception e) {
            System.out.println(" Scanner closed");
        }

        System.out.println("\n All major Java 13 features demonstrated!");
    }
}

