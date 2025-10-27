package java9;


import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java9FeaturesPractice {

    public static void main(String[] args) throws Exception {

        // =====================================================
        // 1️⃣ FACTORY METHODS FOR COLLECTIONS (List.of, Set.of, Map.of)
        // =====================================================
        // Immutable collections (cannot be modified)
        List<String> devices = List.of("Laptop", "Phone", "Tablet");
        Set<String> brands = Set.of("Apple", "Samsung", "Dell");
        Map<String, Integer> stock = Map.of("Laptop", 5, "Phone", 10, "Tablet", 8);

        System.out.println("Devices: " + devices);
        System.out.println("Brands: " + brands);
        System.out.println("Stock: " + stock);

        // =====================================================
        // 2️⃣ STREAM API ENHANCEMENTS
        // =====================================================
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

        // takeWhile → takes elements until condition fails
        List<Integer> firstHalf = numbers.stream()
                .takeWhile(n -> n < 5)
                .collect(Collectors.toList());
        System.out.println("takeWhile (<5): " + firstHalf);

        // dropWhile → skips elements while condition true
        List<Integer> afterFive = numbers.stream()
                .dropWhile(n -> n < 5)
                .collect(Collectors.toList());
        System.out.println("dropWhile (<5): " + afterFive);

        // New iterate() overload (with condition)
        Stream.iterate(1, n -> n <= 10, n -> n + 2)
                .forEach(System.out::print);
        System.out.println();

        // =====================================================
        // 3️⃣ OPTIONAL ENHANCEMENTS
        // =====================================================
        Optional<String> product = Optional.of("MacBook Pro");

        // ifPresentOrElse
        product.ifPresentOrElse(
                p -> System.out.println("Product found: " + p),
                () -> System.out.println("No product found.")
        );

        // or() - fallback Optional
        Optional<String> empty = Optional.empty();
        empty.or(() -> Optional.of("Default Product"))
                .ifPresent(System.out::println);

        // stream() - Optional to Stream
        List<String> optionals = product.stream().collect(Collectors.toList());
        System.out.println("Optional as List: " + optionals);

        // =====================================================
        // 4️⃣ PRIVATE METHODS IN INTERFACES
        // =====================================================
        SmartDevice device = new SmartPhone();
        device.showDeviceInfo(); // default + private methods

        // =====================================================
        // 5️⃣ TRY-WITH-RESOURCES IMPROVEMENT
        // =====================================================
        System.out.println("\nTry-with-resources demo:");
        AutoCloseableResource res = new AutoCloseableResource();
        try (res) { // reuses already-declared variable (new in Java 9)
            res.doWork();
        }

        // =====================================================
        // 6️⃣ PROCESS API UPDATE
        // =====================================================
        ProcessHandle current = ProcessHandle.current();
        System.out.println("\nProcess Info:");
        System.out.println("PID: " + current.pid());
        System.out.println("User: " + current.info().user().orElse("Unknown"));
        System.out.println("Start Time: " + current.info().startInstant().orElse(Instant.now()));

        // =====================================================
        // 7️⃣ JShell (REPL)
        // =====================================================
        // You can test any expression in JShell like:
        // jshell> List.of("Java", "9").forEach(System.out::println);

        // =====================================================
        // 8️⃣ STILL CAN USE ALL JAVA 8 FEATURES
        // =====================================================
        Predicate<String> isLong = s -> s.length() > 4;
        devices.stream().filter(isLong).forEach(System.out::println);
    }
}

// ===============================================
// INTERFACE WITH PRIVATE METHODS (Java 9)
// ===============================================
interface SmartDevice {
    default void showDeviceInfo() {
        logStart();
        System.out.println("Showing smart device info...");
        logEnd();
    }

    private void logStart() {
        System.out.println("[Start SmartDevice]");
    }

    private void logEnd() {
        System.out.println("[End SmartDevice]");
    }
}

class SmartPhone implements SmartDevice { }

// ===============================================
// AutoCloseable Example (Try-with-Resources)
// ===============================================
class AutoCloseableResource implements AutoCloseable {
    void doWork() {
        System.out.println("Working with resource...");
    }

    @Override
    public void close() {
        System.out.println("Resource closed automatically.");
    }
}

