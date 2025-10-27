package java11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Java11FeaturesPractice {

    public static void main(String[] args) throws IOException, InterruptedException {

        // =============================================================
        // 1️⃣ LOCAL VARIABLE TYPE INFERENCE for lambda parameters (var)
        // =============================================================
        // Introduced in Java 10, but Java 11 extends it to lambda parameters
        List<String> words = List.of("Java", "Spring", "Docker");
        words.forEach((var w) -> System.out.println("Word: " + w));

        // =============================================================
        // 2️⃣ NEW STRING METHODS
        // =============================================================
        String text = "   Hello Java 11!   ";
        System.out.println("isBlank(): " + text.isBlank());       // false
        System.out.println("strip(): '" + text.strip() + "'");     // removes all kinds of whitespace
        System.out.println("stripLeading(): '" + text.stripLeading() + "'");
        System.out.println("stripTrailing(): '" + text.stripTrailing() + "'");
        System.out.println("repeat(2): " + "Hi ".repeat(2));

        // lines() - converts text to Stream of lines
        String multiLine = "Java 11\nis awesome\nfor developers!";
        System.out.println("lines():");
        multiLine.lines().forEach(System.out::println);

        // =============================================================
        // 3️⃣ FILE METHODS: Files.readString() & writeString()
        // =============================================================
        Path path = Paths.get("java11_sample.txt");
        Files.writeString(path, "Hello from Java 11 File API!");
        String fileContent = Files.readString(path);
        System.out.println("\n File content: " + fileContent);

        // =============================================================
        // 4️⃣ COLLECTIONS: toArray(IntFunction)
        // =============================================================
        var list = List.of("A", "B", "C");
        String[] arr = list.toArray(String[]::new);
        System.out.println("\nArray from List: " + Arrays.toString(arr));

        // =============================================================
        // 5️⃣ Optional.isEmpty()
        // =============================================================
        Optional<String> maybeValue = Optional.empty();
        System.out.println("\nOptional is empty? " + maybeValue.isEmpty());

        // =============================================================
        // 6️⃣ HTTP CLIENT (Standardized in Java 11)
        // =============================================================
        // New, modern, asynchronous HTTP Client replaces HttpURLConnection.
        // (Example uses httpbin.org for demo)
        var client = java.net.http.HttpClient.newHttpClient();
        var request = java.net.http.HttpRequest.newBuilder()
                .uri(java.net.URI.create("https://httpbin.org/get"))
                .build();

        var response = client.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());
        System.out.println("\n HTTP GET Response Status: " + response.statusCode());
        System.out.println("Response Body (short preview): " + response.body().substring(0, 60) + "...");

        // =============================================================
        // 7️⃣ NEST-BASED ACCESS CONTROL
        // =============================================================
        // Improves access between nested classes (no reflection needed).
        // (Demonstrated below)
        OuterClass outer = new OuterClass();
        outer.accessInner();

        // =============================================================
        // 8️⃣ Launch single-file source-code programs
        // =============================================================
        // Example: Run directly with `java Hello.java`
        // (no compilation step required) — cannot be shown inside code here.

        // =============================================================
        // 9️⃣ Deprecations / Removals
        // =============================================================
        // - Java EE and CORBA modules removed
        // - Nashorn JavaScript engine deprecated
        // - "javac --release 11" replaces complex bootclasspath setups

        // =============================================================
        // 🔟 Stream API small enhancement
        // =============================================================
        List<String> items = List.of("pen", "notebook", "laptop", "phone");
        var result = items.stream().dropWhile(s -> s.length() < 6).collect(Collectors.toList());
        System.out.println("\nDropWhile result: " + result);

        System.out.println("\nAll Java 11 features demonstrated successfully!");
    }
}

// =============================================================
// 📦 Nested class demo for "Nest-based access control"
// =============================================================
class OuterClass {
    private String secret = "Java 11 Nest Access";

    void accessInner() {
        InnerClass inner = new InnerClass();
        inner.printSecret();
    }

    class InnerClass {
        void printSecret() {
            // Can access private field of OuterClass without reflection
            System.out.println("InnerClass reads secret: " + secret);
        }
    }
}
