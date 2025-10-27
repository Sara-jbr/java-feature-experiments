package java10;


import java.util.*;
import java.util.stream.Collectors;

/**
 *  TechStore Java 10 Demo
 * A simple product management system demonstrating Java 10 features in action.
 */
public class TechStoreAppJava10 {

    static class Product {
        String name;
        double price;

        Product(String name, double price) {
            this.name = name;
            this.price = price;
        }

        @Override
        public String toString() {
            return name + " ($" + price + ")";
        }
    }

    public static void main(String[] args) {

        System.out.println(" Welcome to TechStore (Java 10 Edition)");
        System.out.println("=".repeat(50));

        // 1️⃣ Use var for cleaner code
        var products = new ArrayList<Product>();
        products.add(new Product("Laptop", 999.99));
        products.add(new Product("Mouse", 25.50));
        products.add(new Product("Keyboard", 45.00));
        products.add(new Product("Monitor", 189.00));

        // 2️⃣ Create an immutable copy of the product list
        var immutableProducts = List.copyOf(products);

        System.out.println("\n All Products:");
        immutableProducts.forEach(p -> System.out.println(" - " + p));

        // 3️⃣ Find a product using Optional.orElseThrow()
        var foundProduct = products.stream()
                .filter(p -> p.name.equalsIgnoreCase("Laptop"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found!"));
        System.out.println("\n Found product: " + foundProduct);

        // 4️⃣ Create an unmodifiable list of product names using Collectors
        var productNames = products.stream()
                .map(p -> p.name)
                .collect(Collectors.toUnmodifiableList());
        System.out.println("\n Product Names: " + productNames);

        // 5️⃣ var in for-each loops
        System.out.println("\n Prices above $100:");
        for (var p : products) {
            if (p.price > 100)
                System.out.println("  " + p);
        }

        // 6️⃣ JVM Improvements — shown as concept
        System.out.println("""
             JVM Enhancements in Java 10:
            - AppCDS for faster startup
            - G1 GC parallel Full GC
            - Container resource limits awareness
        """);

        System.out.println("\n TechStore Java 10 demo complete!");
    }
}

