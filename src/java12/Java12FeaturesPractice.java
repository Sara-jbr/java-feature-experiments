package java12;

import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Java 12 Features Practice
 * Covers all major Java 12 features with detailed explanations and real examples.
 * package com.example.java12features;
 * <p>
 * import java.text.NumberFormat;
 * import java.util.*;
 * import java.util.stream.Collectors;
 * <p>
 * /**
 * Java 12 Features Practice
 * Covers all major Java 12 features with detailed explanations and real examples.
 */
public class Java12FeaturesPractice {

    public static void main(String[] args) {

        System.out.println(" Java 12 Features Practice Demo");
        System.out.println("=".repeat(60));

        // ============================================================
        // 1️⃣ Switch Expressions (Preview Feature in Java 12)
        // ============================================================
        // Switch expressions simplify syntax and return values.
        var day = "MONDAY";

        var typeOfDay = switch (day) {
            case "SATURDAY", "SUNDAY" -> "Weekend";
            case "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY" -> "Weekday";
            default -> throw new IllegalStateException("Invalid day: " + day);
        };

        System.out.println(" " + day + " is a " + typeOfDay);

        // ============================================================
        // 2️⃣ String API Enhancements
        // ============================================================
        var text = "  Hello Java 12!  \n";

        // indent() — add indentation to each line
        var indented = text.indent(4);
        System.out.println("\n Indented text:\n" + indented);

        // transform() — apply a function directly to a string
        var upper = text.transform(String::toUpperCase);
        System.out.println(" Transformed (upper): " + upper);

        // ============================================================
        // 3️⃣ Compact Number Formatting
        // ============================================================
        var shortFormatter = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.SHORT);
        var longFormatter = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.LONG);
        shortFormatter.setMaximumFractionDigits(1);
        longFormatter.setMaximumFractionDigits(1);

        System.out.println("\n Compact Number Formatting:");
        System.out.println("Short: " + shortFormatter.format(1200)); // 1.2K
        System.out.println("Long: " + longFormatter.format(1200));   // 1.2 thousand

        // ============================================================
        // 4️⃣ Files.mismatch() — Compare Files Efficiently
        // ============================================================
        // Skipping file creation for demo, but explanation:
        System.out.println("""
                     Files.mismatch(path1, path2):
                    Returns the index of the first mismatched byte between two files,
                    or -1L if there is no mismatch.
                    Useful for verifying identical files quickly.
                """);

        // ============================================================
        // 5️⃣ Collectors.teeing()
        // ============================================================
        // Combines two collectors and merges results using a BiFunction.
        var numbers = List.of(1, 2, 3, 4, 5);
        var result = numbers.stream()
                .collect(Collectors.teeing(
                        Collectors.summingInt(Integer::intValue),
                        Collectors.averagingInt(Integer::intValue),
                        (sum, avg) -> "Sum = " + sum + ", Average = " + avg
                ));
        System.out.println("\n Teeing Collector Result: " + result);

        // ============================================================
        // 6️⃣ New String Methods: indent() and transform()
        // ============================================================
        var info = "java 12".transform(s -> s.substring(0, 1).toUpperCase() + s.substring(1));
        System.out.println(" Capitalized: " + info);

        // ============================================================
        // 7️⃣ JVM & Performance Improvements
        // ============================================================
        System.out.println("""
                     JVM Enhancements in Java 12:
                    - Shenandoah GC (low-pause-time GC)
                    - Microbenchmark Suite
                    - Switch Expressions (preview)
                """);

        System.out.println(" All Java 12 features demonstrated successfully!");
    }
}
