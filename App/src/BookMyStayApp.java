import java.util.HashMap;
import java.util.Map;

/**
 * Book My Stay - Hotel Booking Management System
 *
 * Use Case 4: Room Search & Availability Check
 *
 * Demonstrates how guests can search available rooms
 * without modifying the inventory state.
 *
 * @author Student
 * @version 4.0
 */
public class BookMyStayApp {

    // Room Domain Model
    static class Room {
        String type;
        double price;
        String amenities;

        Room(String type, double price, String amenities) {
            this.type = type;
            this.price = price;
            this.amenities = amenities;
        }
    }

    // Centralized Inventory
    private Map<String, Integer> inventory = new HashMap<>();

    // Room Details
    private Map<String, Room> roomCatalog = new HashMap<>();

    // Constructor to initialize data
    public BookMyStayApp() {

        // Inventory setup
        inventory.put("Single", 5);
        inventory.put("Double", 2);
        inventory.put("Suite", 0); // unavailable example

        // Room catalog
        roomCatalog.put("Single", new Room("Single", 100.0, "1 Bed, Free WiFi"));
        roomCatalog.put("Double", new Room("Double", 180.0, "2 Beds, Free WiFi, TV"));
        roomCatalog.put("Suite", new Room("Suite", 350.0, "King Bed, Living Area, Ocean View"));
    }

    // Read-only search method
    public void searchAvailableRooms() {

        System.out.println("\nAvailable Rooms:\n");

        for (String roomType : inventory.keySet()) {

            int available = inventory.get(roomType);

            // Defensive check (only show rooms with availability > 0)
            if (available > 0) {

                Room room = roomCatalog.get(roomType);

                System.out.println("Room Type: " + room.type);
                System.out.println("Price per night: $" + room.price);
                System.out.println("Amenities: " + room.amenities);
                System.out.println("Rooms Available: " + available);
                System.out.println("-----------------------------------");
            }
        }
    }

    // Application Entry Point
    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("Book My Stay - Room Search");
        System.out.println("Version 4.0");
        System.out.println("=================================");

        BookMyStayApp app = new BookMyStayApp();

        // Guest searches available rooms
        app.searchAvailableRooms();
    }
}