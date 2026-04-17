import java.util.*;

/**
 * Book My Stay - Hotel Booking Management System
 *
 * Use Case 6: Room Allocation Processing
 */
public class BookMyStayApp {

    // Reservation class (same as your previous code)
    static class Reservation {
        String guestName;
        String roomType;

        Reservation(String guestName, String roomType) {
            this.guestName = guestName;
            this.roomType = roomType;
        }
    }

    // FIFO Queue
    private Queue<Reservation> bookingQueue = new LinkedList<>();

    // Inventory
    private Map<String, Integer> inventory = new HashMap<>();

    // Track room numbers per type (for sequential IDs)
    private Map<String, Integer> roomCounters = new HashMap<>();

    public BookMyStayApp() {
        inventory.put("Single", 2);
        inventory.put("Double", 2);
        inventory.put("Suite", 1);

        roomCounters.put("Single", 0);
        roomCounters.put("Double", 0);
        roomCounters.put("Suite", 0);
    }

    // Add booking request
    public void addBookingRequest(String guestName, String roomType) {
        bookingQueue.add(new Reservation(guestName, roomType));
    }

    // Generate sequential Room ID (Single-1, Single-2, etc.)
    private String generateRoomId(String roomType) {
        int count = roomCounters.get(roomType) + 1;
        roomCounters.put(roomType, count);
        return roomType + "-" + count;
    }

    // Process bookings
    public void processBookings() {
        System.out.println("Room Allocation Processing\n");

        while (!bookingQueue.isEmpty()) {
            Reservation r = bookingQueue.poll();

            if (inventory.getOrDefault(r.roomType, 0) > 0) {

                String roomId = generateRoomId(r.roomType);

                // Update inventory
                inventory.put(r.roomType, inventory.get(r.roomType) - 1);

                System.out.println("Booking confirmed for Guest: "
                        + r.guestName + ", Room ID: " + roomId);

            } else {
                System.out.println("Booking failed for Guest: "
                        + r.guestName + " (No rooms available)");
            }
        }
    }

    public static void main(String[] args) {

        BookMyStayApp system = new BookMyStayApp();

        // Sample input (same style)
        system.addBookingRequest("Abhi", "Single");
        system.addBookingRequest("Subha", "Single");
        system.addBookingRequest("Vanmathi", "Suite");

        // Process bookings
        system.processBookings();
    }
}