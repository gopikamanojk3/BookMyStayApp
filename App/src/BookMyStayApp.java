import java.util.*;
import java.util.stream.Collectors;

/**
 * Train Consist Management App
 *
 * UC9: Group Bogies by Type using Collectors.groupingBy()
 */
public class BookMyStayApp {

    // Bogie class
    static class Bogie {
        String name;   // e.g., Sleeper, AC Chair, First Class, Goods
        String type;   // Passenger / Goods

        Bogie(String name, String type) {
            this.name = name;
            this.type = type;
        }

        public String toString() {
            return name + " (" + type + ")";
        }
    }

    public static void main(String[] args) {

        // Step 1: Create list of bogies
        List<Bogie> bogies = new ArrayList<>();

        bogies.add(new Bogie("Sleeper", "Passenger"));
        bogies.add(new Bogie("AC Chair", "Passenger"));
        bogies.add(new Bogie("First Class", "Passenger"));
        bogies.add(new Bogie("Sleeper", "Passenger")); // duplicate for grouping
        bogies.add(new Bogie("Cylindrical", "Goods"));
        bogies.add(new Bogie("Rectangular", "Goods"));

        // Step 2: Convert to stream and group by bogie name
        Map<String, List<Bogie>> groupedBogies =
                bogies.stream()
                        .collect(Collectors.groupingBy(b -> b.name));

        // Step 3: Display grouped result
        System.out.println("Grouped Bogies by Type:\n");

        for (String key : groupedBogies.keySet()) {
            System.out.println(key + " -> " + groupedBogies.get(key));
        }
    }
}