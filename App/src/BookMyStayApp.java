import java.util.*;

class Booking {
    String reservationId;
    String roomType;
    boolean isCancelled;

    public Booking(String reservationId, String roomType) {
        this.reservationId = reservationId;
        this.roomType = roomType;
        this.isCancelled = false;
    }
}

class CancellationService {

    // Inventory map (Room Type → Available Count)
    private Map<String, Integer> inventory;

    // Active bookings
    private Map<String, Booking> bookings;

    // Rollback stack (LIFO)
    private Stack<String> rollbackStack;

    public CancellationService(Map<String, Integer> inventory,
                               Map<String, Booking> bookings) {
        this.inventory = inventory;
        this.bookings = bookings;
        this.rollbackStack = new Stack<>();
    }

    public void cancelBooking(String reservationId) {

        System.out.println("\nBooking Cancellation");

        // Step 1: Validate booking exists
        if (!bookings.containsKey(reservationId)) {
            System.out.println("Error: Booking does not exist.");
            return;
        }

        Booking booking = bookings.get(reservationId);

        // Step 2: Check already cancelled
        if (booking.isCancelled) {
            System.out.println("Error: Booking already cancelled.");
            return;
        }

        // Step 3: Push to rollback stack
        rollbackStack.push(reservationId);

        // Step 4: Restore inventory
        String roomType = booking.roomType;
        inventory.put(roomType, inventory.get(roomType) + 1);

        // Step 5: Mark cancelled
        booking.isCancelled = true;

        // Step 6: Print output
        System.out.println("Booking cancelled successfully. Inventory restored for room type: " + roomType);

        System.out.println("\nRollback History (Most Recent First):");
        for (int i = rollbackStack.size() - 1; i >= 0; i--) {
            System.out.println("Released Reservation ID: " + rollbackStack.get(i));
        }

        System.out.println("\nUpdated " + roomType + " Room Availability: " + inventory.get(roomType));
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        // Step 1: Initialize inventory
        Map<String, Integer> inventory = new HashMap<>();
        inventory.put("Single", 5);
        inventory.put("Double", 3);

        // Step 2: Create bookings
        Map<String, Booking> bookings = new HashMap<>();

        Booking b1 = new Booking("Single-1", "Single");
        bookings.put(b1.reservationId, b1);

        // Simulate booking (reduce inventory)
        inventory.put("Single", inventory.get("Single") - 1);

        // Step 3: Cancellation Service
        CancellationService service = new CancellationService(inventory, bookings);

        // Step 4: Perform cancellation
        service.cancelBooking("Single-1");
    }
}
