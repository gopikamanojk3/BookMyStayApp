import java.util.*;
import java.util.stream.*;

/**
 * Train Consist Management App
 *
 * UC10: Count Total Seats in Train using reduce()
 */
public class BookMyStayApp {

    // Bogie class with seating capacity
    static class Bogie {
        String name;
        int capacity;

        Bogie(String name, int capacity) {
            this.name = name;
            this.capacity = capacity;
        }
    }

    public static void main(String[] args) {

        // Step 1: Create list of bogies
        List<Bogie> bogies = new ArrayList<>();

        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 60));
        bogies.add(new Bogie("First Class", 40));
        bogies.add(new Bogie("Sleeper", 72));

        // Step 2: Stream → map → reduce
        int totalSeats = bogies.stream()
                .map(b -> b.capacity)          // extract capacity
                .reduce(0, Integer::sum);      // sum all values

        // Step 3: Display result
        System.out.println("Total Seating Capacity: " + totalSeats);
    }
}