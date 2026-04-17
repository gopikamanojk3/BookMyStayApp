import java.util.*;

/**
 * Book My Stay - Hotel Booking Management System
 *
 * Use Case 11: Concurrent Booking Simulation (Thread Safety)
 */
public class BookMyStayApp {

    // Reservation class
    static class Reservation {
        String guestName;
        String roomType;

        Reservation(String guestName, String roomType) {
            this.guestName = guestName;
            this.roomType = roomType;
        }
    }

    // Shared queue (FIFO)
    private Queue<Reservation> bookingQueue = new LinkedList<>();

    // Shared inventory
    private Map<String, Integer> inventory = new HashMap<>();

    // Room counters (for unique IDs)
    private Map<String, Integer> roomCounters = new HashMap<>();

    public BookMyStayApp() {
        inventory.put("Single", 2);
        inventory.put("Double", 1);

        roomCounters.put("Single", 0);
        roomCounters.put("Double", 0);
    }

    // Add booking request (synchronized for safety)
    public synchronized void addBooking(String guestName, String roomType) {
        bookingQueue.add(new Reservation(guestName, roomType));
        System.out.println(guestName + " added booking request for " + roomType);
    }

    // Generate room ID
    private String generateRoomId(String roomType) {
        int count = roomCounters.get(roomType) + 1;
        roomCounters.put(roomType, count);
        return roomType + "-" + count;
    }

    // Critical section: allocation must be synchronized
    public synchronized void processBooking() {

        if (bookingQueue.isEmpty()) return;

        Reservation r = bookingQueue.poll();

        if (inventory.getOrDefault(r.roomType, 0) > 0) {

            String roomId = generateRoomId(r.roomType);

            // Update inventory safely
            inventory.put(r.roomType, inventory.get(r.roomType) - 1);

            System.out.println("Booking confirmed for " + r.guestName +
                    " | Room ID: " + roomId);

        } else {
            System.out.println("Booking failed for " + r.guestName +
                    " | No rooms available");
        }
    }

    public static void main(String[] args) {

        BookMyStayApp system = new BookMyStayApp();

        // Create threads (simulating multiple users)
        Thread t1 = new Thread(() -> {
            system.addBooking("Alice", "Single");
            system.processBooking();
        });

        Thread t2 = new Thread(() -> {
            system.addBooking("Bob", "Single");
            system.processBooking();
        });

        Thread t3 = new Thread(() -> {
            system.addBooking("Charlie", "Single");
            system.processBooking();
        });

        // Start threads (concurrent execution)
        t1.start();
        t2.start();
        t3.start();
    }
}