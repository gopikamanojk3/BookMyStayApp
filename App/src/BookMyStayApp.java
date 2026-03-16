import java.util.HashMap;
import java.util.Map;

/**
 * Book My Stay - Hotel Booking Management System
 *
 * Use Case 3: Centralized Room Inventory Management
 *
 * This program demonstrates how room availability can be managed
 * using a centralized HashMap instead of scattered variables.
 *
 * @author Student
 * @version 3.1
 */
public class BookMyStayApp {

    // HashMap storing room type and available count
    private Map<String, Integer> roomInventory;

    /**
     * Constructor initializes room inventory
     */
    public BookMyStayApp() {
        roomInventory = new HashMap<>();

        // Initialize room availability
        roomInventory.put("Single", 10);
        roomInventory.put("Double", 6);
        roomInventory.put("Suite", 3);
    }

    /**
     * Get availability of a room type
     */
    public int getAvailability(String roomType) {
        return roomInventory.getOrDefault(roomType, 0);
    }

    /**
     * Update room availability
     */
    public void updateAvailability(String roomType, int change) {
        if (roomInventory.containsKey(roomType)) {
            int updated = roomInventory.get(roomType) + change;
            roomInventory.put(roomType, updated);
        }
    }

    /**
     * Display current inventory
     */
    public void displayInventory() {
        System.out.println("\nCurrent Room Inventory:");

        for (Map.Entry<String, Integer> entry : roomInventory.entrySet()) {
            System.out.println(entry.getKey() + " Rooms Available: " + entry.getValue());
        }
    }

    /**
     * Application entry point
     */
    public static void main(String[] args) {

        System.out.println("===================================");
        System.out.println("Book My Stay - Hotel Booking App");
        System.out.println("Version 3.1");
        System.out.println("Centralized Room Inventory System");
        System.out.println("===================================");

        // Initialize inventory
        BookMyStayApp inventory = new BookMyStayApp();

        // Display inventory
        inventory.displayInventory();

        // Simulate booking
        System.out.println("\nBooking 1 Single Room...");
        inventory.updateAvailability("Single", -1);

        // Display updated inventory
        inventory.displayInventory();
    }
}