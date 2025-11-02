package java16;


/**
 * TechStore Java 16 Edition
 * Demonstrates Java 16 features in a mini product management app.
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class TechStoreAppJava16 {

    public static void main(String[] args) {
        System.out.println("=====  Welcome to TechStore v6.0 (Java 16 Edition) =====\n");

        TechStoreService storeService = new TechStoreService();

        // Add some sample products
        storeService.addProduct(new Product("Laptop", 1500.0, Category.ELECTRONICS));
        storeService.addProduct(new Product("Mouse", 25.5, Category.ACCESSORIES));
        storeService.addProduct(new Product("Keyboard", 45.0, Category.ACCESSORIES));
        storeService.addProduct(new Product("Monitor", 300.0, Category.ELECTRONICS));

        // List all products
        storeService.displayProducts();

        // Search products by name
        System.out.println("\n Searching for 'Laptop':");
        storeService.findProduct("Laptop").ifPresentOrElse(
                p -> System.out.println("Found → " + p),
                () -> System.out.println("Product not found.")
        );

        // Filter expensive products
        System.out.println("\n Products above $100:");
        storeService.filterExpensiveProducts(100).forEach(System.out::println);

        // Show user roles using sealed classes
        System.out.println("\n User Roles:");
        List<User> users = List.of(
                new Admin("Sara"),
                new Customer("Ali"),
                new Guest("Visitor")
        );
        users.forEach(User::describeRole);

        // Pattern matching in action
        System.out.println("\n Pattern Matching Demo:");
        Object obj = new Product("SSD", 120.0, Category.ELECTRONICS);
        if (obj instanceof Product p && p.price() > 100) {
            System.out.println("Matched a product over $100: " + p.name());
        }

        System.out.println("\n TechStore v6.0 running smoothly with Java 16 features!");
    }
}

// =======================================================
//  Record: Product (JEP 395)
// A perfect use-case for immutable product data in an e-commerce system.
// =======================================================
record Product(String name, double price, Category category) {
}

// =======================================================
//  Enum: Category — product categories
// =======================================================
enum Category {
    ELECTRONICS, ACCESSORIES, SOFTWARE, OTHER
}

// =======================================================
// Sealed Classes (JEP 397)
// Control inheritance and define clear app roles.
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
        System.out.println(name + " → Admin: Manages inventory and users.");
    }
}

final class Customer extends User {
    public Customer(String name) {
        super(name);
    }

    @Override
    void describeRole() {
        System.out.println(name + " → Customer: Can browse and buy products.");
    }
}

final class Guest extends User {
    public Guest(String name) {
        super(name);
    }

    @Override
    void describeRole() {
        System.out.println(name + " → Guest: Can only view limited product info.");
    }
}

// =======================================================
// TechStoreService — uses Stream API and Records
// =======================================================
class TechStoreService {
    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
        System.out.println(" Added product: " + product.name());
    }

    public void displayProducts() {
        System.out.println("\n Product List:");
        products.forEach(System.out::println);
    }

    public Optional<Product> findProduct(String name) {
        return products.stream()
                .filter(p -> p.name().equalsIgnoreCase(name))
                .findFirst();
    }

    public List<Product> filterExpensiveProducts(double minPrice) {
        // New Stream.toList() (Java 16)
        return products.stream()
                .filter(p -> p.price() > minPrice)
                .sorted(Comparator.comparing(Product::price).reversed())
                .toList();
    }
}
