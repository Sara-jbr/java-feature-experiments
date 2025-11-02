package java18;

/**
 * TechStore Java 18 Edition
 * Demonstrates Java 18 features in a mini product management app.
 */

import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class TechStoreAppJava18 {

    public static void main(String[] args) throws Exception {
        System.out.println("=====  TechStore v8.0 (Java 18) =====\n");

        StoreService store = new StoreService();

        // Add products
        store.addProduct(new Product("MacBook Pro", 2500.0, Category.LAPTOP));
        store.addProduct(new Product("Logitech Mouse", 45.0, Category.ACCESSORY));
        store.addProduct(new Product("Samsung Monitor", 320.0, Category.MONITOR));
        store.addProduct(new Product("Mechanical Keyboard", 120.0, Category.ACCESSORY));
        store.addProduct(new Product("SSD", 150.0, Category.STORAGE));

        // Display products
        store.displayProducts();

        // Filter products with price >= 100 using Stream.dropWhile
        List<Product> filtered = store.getProducts().stream()
                .dropWhile(p -> p.price() < 100)
                .toList();
        System.out.println("\n Products with price >= 100:");
        filtered.forEach(p -> System.out.println(p.name() + " - $" + p.price()));

        // Start simple web server (if permitted)
        try {
            var server = com.sun.net.httpserver.HttpServer.create(new InetSocketAddress(8081), 0);
            server.createContext("/techstore", exchange -> {
                String response = "Welcome to TechStore v8.0 Java 18 Demo!";
                exchange.sendResponseHeaders(200, response.getBytes().length);
                try (var os = exchange.getResponseBody()) {
                    os.write(response.getBytes());
                }
            });
            server.start();
            System.out.println("\nServer started at http://localhost:8081/techstore");
        } catch (Exception e) {
            System.out.println("\nSimple Web Server demo skipped (permissions).");
        }

        // HTTP Client demo
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://api.github.com"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("\nGitHub API status code: " + response.statusCode());

        System.out.println("\n TechStore v8.0 running smoothly with Java 18 features!");
    }
}

// =======================================================
//  Product Record
// =======================================================
record Product(String name, double price, Category category) {
}

// =======================================================
//  Category Enum
// =======================================================
enum Category {
    LAPTOP, ACCESSORY, MONITOR, STORAGE
}

// =======================================================
//  StoreService — product operations using Streams
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

    public List<Product> getProducts() {
        return products;
    }
}

