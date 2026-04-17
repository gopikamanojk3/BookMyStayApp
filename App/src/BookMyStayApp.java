import java.io.*;
import java.util.*;

// Booking class must be Serializable
class Booking implements Serializable {
    String reservationId;
    String roomType;
    boolean isCancelled;

    public Booking(String reservationId, String roomType) {
        this.reservationId = reservationId;
        this.roomType = roomType;
        this.isCancelled = false;
    }
}

// Wrapper class for storing system state
class SystemState implements Serializable {
    Map<String, Integer> inventory;
    Map<String, Booking> bookings;

    public SystemState(Map<String, Integer> inventory,
                       Map<String, Booking> bookings) {
        this.inventory = inventory;
        this.bookings = bookings;
    }
}

// Persistence Service
class PersistenceService {

    private static final String FILE_NAME = "hotel_data.ser";

    // SAVE DATA
    public void save(SystemState state) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            oos.writeObject(state);
            System.out.println("Inventory saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }

    // LOAD DATA
    public SystemState load() {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            return (SystemState) ois.readObject();

        } catch (Exception e) {
            return null; // important for recovery handling
        }
    }
}

// MAIN APP
public class BookMyStayApp {

    public static void main(String[] args) {

        PersistenceService service = new PersistenceService();

        Map<String, Integer> inventory;
        Map<String, Booking> bookings;

        System.out.println("System Recovery");

        // Try loading existing data
        SystemState state = service.load();

        if (state == null) {
            // No file / corrupted file → start fresh
            System.out.println("No valid inventory data found. Starting fresh.\n");

            inventory = new HashMap<>();
            bookings = new HashMap<>();

            inventory.put("Single", 5);
            inventory.put("Double", 3);
            inventory.put("Suite", 2);

        } else {
            // Restore from file
            inventory = state.inventory;
            bookings = state.bookings;
            System.out.println("System state restored successfully.\n");
        }

        // Print inventory
        System.out.println("Current Inventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + ": " + inventory.get(type));
        }

        // Save state before exit
        service.save(new SystemState(inventory, bookings));
    }
}