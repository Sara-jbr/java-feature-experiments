package java15;


import java.util.*;
import java.util.stream.Collectors;

/**
 * TechStore Java 15 Edition
 * Demonstrates Java 15 features in a mini product management app.
 */
public class TechStoreAppJava15 {

    //  Record for Product (Java 15)
    record Product(String name, double price, int stock) {
    }

    //  Sealed class example
    sealed interface Payment permits CreditCard, PayPal {
    }

    static final class CreditCard implements Payment {
    }

    static final class PayPal implements Payment {
    }

    public static void main(String[] args) {

        System.out.println(" Welcome to TechStore (Java 15 Edition)");
        System.out.println("=".repeat(60));

        // ============================================================
        // 1️⃣ Sample Products
        // ============================================================
        var products = List.of(
                new Product("MacBook Pro", 2500.0, 5),
                new Product("Dell XPS", 2200.0, 3),
                new Product("Keyboard", 100.0, 15),
                new Product("Mouse", 40.0, 25)
        );

        // ============================================================
        // 2️⃣ Switch Expression for Product Category
        // ============================================================
        System.out.println("\n Product Categories:");
        for (var p : products) {
            var category = switch (p.name()) {
                case "MacBook Pro", "Dell XPS" -> "Laptop";
                case "Keyboard", "Mouse" -> "Accessory";
                default -> "Other";
            };
            System.out.println(" " + p.name() + " -> " + category);
        }

        // ============================================================
        // 3️⃣ Text Block for Product Catalog
        // ============================================================
        var catalog = """
                 Product Catalog
                ------------------
                %s
                ------------------
                """.formatted(
                products.stream()
                        .map(Product::toString)
                        .collect(Collectors.joining("\n"))
        );
        System.out.println("\n" + catalog);

        // ============================================================
        // 4️⃣ Stream API & Total Stock Calculation
        // ============================================================
        var totalStock = products.stream().mapToInt(Product::stock).sum();
        System.out.println(" Total Stock: " + totalStock);

        // ============================================================
        // 5️⃣ Pattern Matching for instanceof
        // ============================================================
        Object obj = "Java 15 TechStore!";
        if (obj instanceof String s) {
            System.out.println(" Pattern matched string: " + s.toUpperCase());
        }

        // ============================================================
        // 6️⃣ Sealed Classes Example
        // ============================================================
        Payment payment = new CreditCard();
        var paymentType = switch (payment) {
            case CreditCard c -> "Credit Card";
            case PayPal p -> "PayPal";
        };
        System.out.println(" Payment Type: " + paymentType);

        System.out.println("\n TechStore Java 15 demo complete!");
    }
}

