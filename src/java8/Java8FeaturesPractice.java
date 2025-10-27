package java8;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

public class Java8FeaturesPractice {

    public static void main(String[] args) {

        // =============================================
        // 1️⃣ LAMBDA EXPRESSIONS
        // =============================================
        // Lambda = short form for anonymous inner classes used for functional interfaces.
        Runnable task = () -> System.out.println("Running a task using Lambda!");
        task.run();

        List<String> products = Arrays.asList("Laptop", "Mouse", "Keyboard", "Monitor");
        products.forEach(p -> System.out.println("java8.Product: " + p));

        // =============================================
        // 2️⃣ FUNCTIONAL INTERFACES
        // =============================================
        // Java 8 provides common functional interfaces in java.util.function
        Predicate<String> isLongName = name -> name.length() > 5;
        System.out.println("Is 'Laptop' long name? " + isLongName.test("Laptop"));

        Function<String, Integer> nameLength = name -> name.length();
        System.out.println("Length of 'Keyboard' = " + nameLength.apply("Keyboard"));

        Supplier<Double> randomPrice = () -> Math.random() * 100;
        System.out.println("Generated random price: $" + randomPrice.get());

        Consumer<String> printer = msg -> System.out.println("Message: " + msg);
        printer.accept("Welcome to Java 8 features!");

        // =============================================
        // 3️⃣ METHOD REFERENCES
        // =============================================
        // Instead of a lambda, directly refer to an existing method
        products.forEach(System.out::println); // System.out.println is a method reference

        // Constructor reference
        Supplier<List<String>> listSupplier = ArrayList::new;
        List<String> newList = listSupplier.get();
        newList.add("java8.Smartphone");
        System.out.println("New list created using constructor reference: " + newList);

        // =============================================
        // 4️⃣ STREAM API
        // =============================================
        // Stream API allows functional-style processing of collections
        List<String> upperCaseProducts = products.stream()
                .filter(p -> p.length() > 5)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("Filtered & uppercased: " + upperCaseProducts);

        // Reduce example (sum total length of all names)
        int totalLength = products.stream()
                .map(String::length)
                .reduce(0, Integer::sum);
        System.out.println("Total length of product names: " + totalLength);

        // =============================================
        // 5️⃣ OPTIONAL
        // =============================================
        // Optional helps avoid NullPointerException
        Optional<String> optionalName = Optional.of("Monitor");
        optionalName.ifPresent(System.out::println);

        Optional<String> emptyName = Optional.empty();
        System.out.println("Name or default: " + emptyName.orElse("Unknown"));

        // =============================================
        // 6️⃣ DEFAULT & STATIC METHODS IN INTERFACE
        // =============================================
        Device phone = new Smartphone();
        phone.start(); // default method
        Device.showDeviceType(); // static method

        // =============================================
        // 7️⃣ NEW DATE AND TIME API (java.time)
        // =============================================
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println("Today: " + today + ", Time: " + now);

        // Formatting date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        System.out.println("Formatted date/time: " + dateTime.format(formatter));

        // Adding days
        LocalDate futureDate = today.plusDays(10);
        System.out.println("10 days later: " + futureDate);

        // =============================================
        // 8️⃣ BASE64 ENCODING & DECODING
        // =============================================
        String original = "Java8Rocks!";
        String encoded = Base64.getEncoder().encodeToString(original.getBytes());
        String decoded = new String(Base64.getDecoder().decode(encoded));
        System.out.println("Encoded: " + encoded + ", Decoded: " + decoded);

        // =============================================
        // 9️⃣ PARALLEL STREAMS
        // =============================================
        // Run operations in parallel (multi-core usage)
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int sum = numbers.parallelStream().mapToInt(i -> i).sum();
        System.out.println("Sum using parallel stream: " + sum);

        // =============================================
        // 🔟 COLLECTORS (groupingBy, partitioningBy, joining, summarizing)
        // =============================================
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Bob");

        // groupingBy example
        Map<Integer, List<String>> groupedByLength = names.stream()
                .collect(Collectors.groupingBy(String::length));
        System.out.println("Grouped by name length: " + groupedByLength);

        // partitioningBy example
        Map<Boolean, List<String>> partitioned = names.stream()
                .collect(Collectors.partitioningBy(n -> n.startsWith("A")));
        System.out.println("Partitioned (starts with A): " + partitioned);

        // joining
        String joinedNames = names.stream().collect(Collectors.joining(", "));
        System.out.println("Joined names: " + joinedNames);

        // summarizing
        IntSummaryStatistics stats = numbers.stream().collect(Collectors.summarizingInt(Integer::intValue));
        System.out.println("Stats: " + stats);

        // =============================================
        // 1️⃣1️⃣ NASHORN JAVASCRIPT ENGINE (deprecated in later versions)
        // =============================================
        // Allows running JavaScript code inside Java
        // Commented out since newer Java versions may remove Nashorn
        /*
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("nashorn");
        try {
            engine.eval("print('Hello from JavaScript in Java 8!')");
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        */

        // =============================================
        // 1️⃣2️⃣ forEach + new methods in Collection API
        // =============================================
        List<String> items = new ArrayList<>(Arrays.asList("pen", "book", "pencil", "book"));
        items.removeIf(item -> item.equals("book"));
        System.out.println("After removeIf: " + items);

        items.replaceAll(String::toUpperCase);
        System.out.println("After replaceAll: " + items);
    }
}

// =============================================
// INTERFACE WITH DEFAULT AND STATIC METHODS
// =============================================
interface Device {
    default void start() {
        System.out.println("java8.Device starting...");
    }

    static void showDeviceType() {
        System.out.println("java8.Device type: Electronic");
    }
}

// =============================================
// CLASS IMPLEMENTING INTERFACE
// =============================================
class Smartphone implements Device {
    @Override
    public void start() {
        System.out.println("java8.Smartphone booting up...");
    }
}
