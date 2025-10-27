package java10;

import java.util.*;
import java.util.stream.Collectors;

/**
 *  Java 10 Full Features Practice
 * Demonstrates all major Java 10 features with realistic examples and detailed comments.
 */
public class Java10FeaturesPractice {

    public static void main(String[] args) {

        System.out.println(" Java 10 Features Practice Demo");
        System.out.println("=".repeat(50));

        // ============================================================
        // 1️⃣ Local Variable Type Inference (var)
        // ============================================================
        // Java 10 introduced 'var' to infer types of local variables automatically.
        // It improves readability but keeps static typing.
        var name = "Sara";
        var age = 25;
        var languages = List.of("Java", "Spring Boot", "Docker");
        System.out.println(" " + name + " (" + age + ") knows: " + languages);

        //  Note: 'var' works only for local variables, not for fields or parameters.

        // ============================================================
        // 2️⃣ Immutable Collections with copyOf()
        // ============================================================
        var modifiableList = new ArrayList<>(List.of("Laptop", "Keyboard", "Mouse"));
        var unmodifiableList = List.copyOf(modifiableList);

        System.out.println("\n Modifiable list: " + modifiableList);
        System.out.println(" Unmodifiable list: " + unmodifiableList);

        // Trying to modify unmodifiableList will throw UnsupportedOperationException
        try {
            unmodifiableList.add("Monitor");
        } catch (UnsupportedOperationException e) {
            System.out.println(" Cannot modify unmodifiable list!");
        }

        // ============================================================
        // 3️⃣ Optional.orElseThrow()
        // ============================================================
        // Replaces Optional.get() with a safer default version.
        Optional<String> maybeProduct = Optional.of("Laptop");
        System.out.println("\n Product found: " + maybeProduct.orElseThrow());

        Optional<String> emptyProduct = Optional.empty();
        try {
            emptyProduct.orElseThrow(); // throws NoSuchElementException
        } catch (Exception e) {
            System.out.println(" No product found: " + e.getClass().getSimpleName());
        }

        // ============================================================
        // 4️⃣ Collectors.toUnmodifiableList/Set/Map()
        // ============================================================
        var upperNames = languages.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toUnmodifiableList());
        System.out.println("\n Unmodifiable collected list: " + upperNames);

        // ============================================================
        // 5️⃣ var in Loops and Try-with-resources
        // ============================================================
        for (var item : languages) {
            System.out.println(" Language: " + item);
        }

        try (var scanner = new java.util.Scanner(System.in)) {
            System.out.println(" Try-with-resources using var (scanner auto-closed)");
        } catch (Exception e) {
            System.out.println(" Scanner closed.");
        }

        // ============================================================
        // 6️⃣ Performance & JVM Improvements (conceptual)
        // ============================================================
        System.out.println("""
             JVM Improvements in Java 10:
            - Application Class-Data Sharing (AppCDS)
            - G1 GC Parallel Full GC improvements
            - Root Certificates added by default
            - Container-awareness (detects CPU/memory limits)
        """);

        // ============================================================
        // 7️⃣ API Changes and Additions
        // ============================================================
        // New methods in Collectors, Optional, and unmodifiable collections
        var map = Map.of("Java", 10, "Spring", 5, "Docker", 3);
        System.out.println(" Unmodifiable Map: " + map);

        // ============================================================
        // 8️⃣ Clean-up of APIs
        // ============================================================
        // Removed deprecated APIs and unified local variable inference
        System.out.println(" All Java 10 features demonstrated successfully!");
    }
}

