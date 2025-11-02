package java18;

/**
 * Java 18 Features Practice
 * Demonstrates all major Java 18 features with detailed explanations and practical examples.
 */

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class Java18FeaturesPractice {

    public static void main(String[] args) throws Exception {
        System.out.println("===== Java 18 Features Practice: TechStore v8.0 =====\n");

        recordDemo();             // Records (from earlier LTS) + preview features
        simpleWebServerDemo();    // JEP 408: Simple Web Server
        streamEnhancementsDemo(); // Stream.toList() & dropWhile/takeWhile
        httpClientDemo();         // JEP 418: HTTP Client standardization
        unicodeUTF8Demo();        // UTF-8 by default
    }

    // =========================================================
    // Record Demo (JEP 395) — immutable product DTO
    // =========================================================
    static void recordDemo() {
        System.out.println("\n Record Demo");
        record Product(String name, double price, String category) {}
        Product laptop = new Product("HP Spectre", 1800.0, "Laptop");
        System.out.println("Product: " + laptop);
    }

    // =========================================================
    // Simple Web Server (JEP 408)
    // Lightweight HTTP server built into Java 18
    // =========================================================
    static void simpleWebServerDemo() {
        System.out.println("\n Simple Web Server Demo (JEP 408)");

        try {
            // Java 18: Simple HTTP server on port 8080
            var server = com.sun.net.httpserver.HttpServer.create(new InetSocketAddress(8080), 0);
            server.createContext("/techstore", exchange -> {
                String response = "Welcome to TechStore v8.0!";
                exchange.sendResponseHeaders(200, response.getBytes().length);
                try (var os = exchange.getResponseBody()) {
                    os.write(response.getBytes());
                }
            });
            server.start();
            System.out.println("Server started at http://localhost:8080/techstore");
        } catch (IOException e) {
            System.out.println("Simple Web Server demo skipped (requires permissions).");
        }
    }

    // =========================================================
    // Stream Enhancements
    // Methods: dropWhile, takeWhile (already previewed)
    // =========================================================
    static void streamEnhancementsDemo() {
        System.out.println("\n Stream Enhancements Demo");

        List<Double> prices = List.of(50.0, 100.0, 150.0, 200.0);

        List<Double> expensive = prices.stream()
                .dropWhile(p -> p < 100) // drop elements while condition is true
                .toList();
        System.out.println("Prices >= 100: " + expensive);

        List<Double> cheap = prices.stream()
                .takeWhile(p -> p <= 150) // take elements while condition is true
                .toList();
        System.out.println("Prices <= 150: " + cheap);
    }

    // =========================================================
    // HTTP Client Demo (Standardized in Java 18)
    // =========================================================
    static void httpClientDemo() throws Exception {
        System.out.println("\n HTTP Client Demo");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new java.net.URI("https://api.github.com"))
                .GET()
                .build();

        // synchronous request
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("HTTP GET to GitHub API status: " + response.statusCode());
    }

    // =========================================================
    // UTF-8 by default (JEP 400)
    // =========================================================
    static void unicodeUTF8Demo() {
        System.out.println("\n UTF-8 by default (JEP 400)");
        String utf8Text = "TechStore – Modern e-Commerce ";
        System.out.println(utf8Text);
    }
}

