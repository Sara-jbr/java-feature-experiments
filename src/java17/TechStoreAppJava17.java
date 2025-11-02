package java17;

/**
 * TechStore Java 17 Edition
 * Demonstrates Java 17 features in a mini product management app.
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TechStoreAppJava17 {

    public static void main(String[] args) {
        System.out.println("===== TechStore v7.0 (Java 17) =====\n");

        StoreService store = new StoreService();

        // Add products
        store.addProduct(new Product("MacBook Pro", 2500.0, Category.LAPTOP));
        store.addProduct(new Product("Logitech Mouse", 45.0, Category.ACCESSORY));
        store.addProduct(new Product("Samsung Monitor", 320.0, Category.MONITOR));
        store.addProduct(new Product("Mechanical Keyboard", 120.0, Category.ACCESSORY));

        // Display products
        store.displayProducts();

        // Pattern Matching: search by object type
        Object obj = new Product("SSD", 150.0, Category.STORAGE);
        if (obj instanceof Product p && p.price() > 100) {
            System.out.println("\nMatched Product over $100: " + p.name());
        }

        // Switch Expression demo
        String categoryName = "ACCESSORY";
        double discount = switch (Category.valueOf(categoryName)) {
            case LAPTOP -> 10.0;
            case ACCESSORY -> 5.0;
            case MONITOR -> 7.0;
            default -> 0.0;
        };
        System.out.println("\nDiscount for " + categoryName + ": " + discount + "%");

        // User roles using sealed classes
        List<User> users = List.of(
                new Admin("Sara"),
                new Customer("Ali"),
                new Guest("Visitor")
        );
        System.out.println("\n👥 User Roles:");
        users.forEach(User::describeRole);

        System.out.println("\n TechStore v7.0 running smoothly with Java 17 features!");
    }
}

// =======================================================
//  Product Record
// =======================================================
record Product(String name, double price, Category category) {
}

// =======================================================
//  Product Category Enum
// =======================================================
enum Category {
    LAPTOP, ACCESSORY, MONITOR, STORAGE
}

// =======================================================
//  Sealed User Classes
// =======================================================
sealed abstract class User permits Admin, Customer, Guest {
    protected String name;

    public User(String name) {
        this.name = name;
    }

    abstract void describeRole();
}

final class Admin extends User {
    public Admin(String name) {
        super(name);
    }

    @Override
    void describeRole() {
        System.out.println(name + " → Admin: Manages inventory & users.");
    }
}

final class Customer extends User {
    public Customer(String name) {
        super(name);
    }

    @Override
    void describeRole() {
        System.out.println(name + " → Customer: Can browse & purchase products.");
    }
}

final class Guest extends User {
    public Guest(String name) {
        super(name);
    }

    @Override
    void describeRole() {
        System.out.println(name + " → Guest: Limited access to store.");
    }
}

// =======================================================
//  StoreService — product operations using Stream API
// =======================================================
class StoreService {
    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product p) {
        products.add(p);
        System.out.println("Added: " + p.name());
    }

    public void displayProducts() {
        System.out.println("\n Product List:");
        products.forEach(System.out::println);
    }

    public List<Product> filterExpensiveProducts(double minPrice) {
        return products.stream()
                .filter(p -> p.price() > minPrice)
                .sorted(Comparator.comparing(Product::price).reversed())
                .toList();
    }
}

