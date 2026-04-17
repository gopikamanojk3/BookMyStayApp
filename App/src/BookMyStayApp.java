// Custom Exception for Invalid Booking
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// Booking Validator Class
class BookingValidator {
    private static final String[] VALID_ROOM_TYPES = {"Single", "Double", "Suite"};

    public static void validateBooking(String guestName, String roomType) throws InvalidBookingException {
        if (guestName == null || guestName.trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty.");
        }

        boolean validRoom = false;
        for (String validType : VALID_ROOM_TYPES) {
            if (validType.equalsIgnoreCase(roomType)) {
                validRoom = true;
                break;
            }
        }

        if (!validRoom) {
            throw new InvalidBookingException("Invalid room type selected: " + roomType);
        }
    }
}

// Main Application Class
public class BookMyStayApp {
    public static void main(String[] args) {
        String guestName = "Abhisheak";
        String roomType = "single"; // intentionally lowercase to test validation

        try {
            BookingValidator.validateBooking(guestName, roomType);
            System.out.println("Booking successful for " + guestName + " in " + roomType + " room.");
        } catch (InvalidBookingException e) {
            System.out.println("Booking failed: " + e.getMessage());
        }
    }
}
