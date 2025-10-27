package java8;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import java.util.Base64;

public class TechStoreAppJava8 {

    public static void main(String[] args) {

        // ========================================================
        //  CREATE SAMPLE PRODUCT LIST
        // ========================================================
        List<Product> products = Arrays.asList(
                new Product(1, "MacBook Pro", "Laptop", 2500.0, 10),
                new Product(2, "Dell XPS 15", "Laptop", 2000.0, 5),
                new Product(3, "Logitech Mouse", "Accessory", 50.0, 30),
                new Product(4, "Samsung Monitor", "Monitor", 300.0, 8),
                new Product(5, "Mechanical Keyboard", "Accessory", 120.0, 15),
                new Product(6, "ASUS ROG", "Laptop", 2200.0, 3)
        );

        // ========================================================
        // 1️⃣ LAMBDA EXPRESSIONS + FUNCTIONAL INTERFACES
        // ========================================================
        Predicate<Product> expensive = p -> p.getPrice() > 1000;
        Consumer<Product> printProduct = p -> System.out.println(p.getName() + " - $" + p.getPrice());
        Supplier<String> storeName = () -> "TechNest Store";

        System.out.println("Welcome to " + storeName.get() + "!");
        System.out.println("Expensive products:");
        products.stream().filter(expensive).forEach(printProduct);

        // ========================================================
        // 2️⃣ METHOD REFERENCES
        // ========================================================
        System.out.println("\n java8.Product Names:");
        products.forEach(System.out::println); // toString method reference

        // ========================================================
        // 3️⃣ STREAM OPERATIONS (filter, map, collect)
        // ========================================================
        List<String> laptopNames = products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase("Laptop"))
                .map(Product::getName)
                .collect(Collectors.toList());
        System.out.println("\n Laptop names: " + laptopNames);

        // ========================================================
        // 4️⃣ COLLECTORS (groupingBy, averaging, joining)
        // ========================================================
        Map<String, Double> avgPriceByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory,
                        Collectors.averagingDouble(Product::getPrice)));
        System.out.println("\n Average price by category: " + avgPriceByCategory);

        String allNames = products.stream()
                .map(Product::getName)
                .collect(Collectors.joining(", "));
        System.out.println("\n All product names: " + allNames);

        // ========================================================
        // 5️⃣ REDUCE — Total inventory value
        // ========================================================
        double totalValue = products.stream()
                .map(p -> p.getPrice() * p.getStock())
                .reduce(0.0, Double::sum);
        System.out.println("\n Total inventory value: $" + totalValue);

        // ========================================================
        // 6️⃣ OPTIONAL — Find a product safely
        // ========================================================
        Optional<Product> optionalProduct = products.stream()
                .filter(p -> p.getName().equalsIgnoreCase("iPhone"))
                .findFirst();
        System.out.println("\n Found product: " + optionalProduct.orElse(new Product(0, "Unknown", "N/A", 0.0, 0)));

        // ========================================================
        // 7️⃣ PARALLEL STREAMS — Performance for large data
        // ========================================================
        double totalLaptopPrice = products.parallelStream()
                .filter(p -> p.getCategory().equals("Laptop"))
                .mapToDouble(Product::getPrice)
                .sum();
        System.out.println("\n⚡ Total laptop price (parallel): $" + totalLaptopPrice);

        // ========================================================
        // 8️⃣ DEFAULT INTERFACE METHOD
        // ========================================================
        StoreOperations ops = new StoreManager();
        ops.generateReport(products);

        // ========================================================
        // 9️⃣ DATE & TIME API
        // ========================================================
        LocalDate today = LocalDate.now();
        LocalDate deliveryDate = today.plusDays(5);
        System.out.println("\n Today: " + today + " | Estimated Delivery: " + deliveryDate);

        // Format date
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd MMM yyyy");
        System.out.println("Formatted delivery date: " + deliveryDate.format(fmt));

        // ========================================================
        // 🔟 BASE64 Encoding/Decoding
        // ========================================================
        String secretKey = "TechNestSecretKey";
        String encodedKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        String decodedKey = new String(Base64.getDecoder().decode(encodedKey));
        System.out.println("\n Encoded key: " + encodedKey);
        System.out.println("Decoded key: " + decodedKey);

        // ========================================================
        // 1️⃣1️⃣ MAP Enhancements (forEach, replaceAll)
        // ========================================================
        Map<String, Double> discounts = new HashMap<>();
        discounts.put("Laptop", 10.0);
        discounts.put("Accessory", 5.0);
        discounts.put("Monitor", 7.0);

        discounts.replaceAll((cat, disc) -> disc + 2); // increase all discounts by 2%
        discounts.forEach((cat, disc) ->
                System.out.println("New discount for " + cat + ": " + disc + "%"));
    }
}

// ========================================================
//  PRODUCT CLASS
// ========================================================
class Product {
    private int id;
    private String name;
    private String category;
    private double price;
    private int stock;

    public Product(int id, String name, String category, double price, int stock) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }

    @Override
    public String toString() {
        return String.format("%s (%s) - $%.2f [Stock: %d]", name, category, price, stock);
    }
}

// ========================================================
//  INTERFACE WITH DEFAULT METHOD
// ========================================================
interface StoreOperations {
    default void generateReport(List<Product> products) {
        System.out.println("\n Generating java8.Product Report...");
        products.stream()
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .forEach(p -> System.out.println(p.getName() + " - $" + p.getPrice()));
    }
}

// ========================================================
//  IMPLEMENTATION CLASS
// ========================================================
class StoreManager implements StoreOperations {
    // can override default if needed
}

