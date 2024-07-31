import java.util.*;
public class TAXIIBOOKING {
    static int userCounter = 1, driverCounter = 1, bookingCounter = 1;
    static String[] userNames = new String[1000];
    static String[] driverNames = new String[100];
    static String[] driverPasswords = new String[100];
    static String[] bookedTaxis = new String[1000];
    static String[] startPoints = new String[1000];
    static String[] endPoints = new String[1000];
    static int[] bookedByUser = new int[1000];
    static String[] currentLocations = new String[100];
    static String[] locations = {"a", "b", "c", "d", "e", "f"};
    static Map<String, Integer> travelTimes = new HashMap<>();
    static int[] startTime = new int[1000];
    static int[] endTime = new int[1000];
    static int[] driverAvailability = new int[100];
    static double[] driverEarnings = new double[100];
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String choice;
        do {
            System.out.println("WELCOME TO THE TAXI BOOKING SYSTEM");
            System.out.println("1. NEW DRIVER");
            System.out.println("2. DRIVER");
            System.out.println("3. USER");
            System.out.println("4. OWNER");
            System.out.println("0. EXIT");
            choice = in.nextLine();
            switch (choice) {
                case "1":
                    registerNewDriver(in);
                    break;
                case "2":
                    driverLogin(in);
                    break;
                case "3":
                    userSection(in);
                    break;
                case "4":
                    ownerSection(in);
                    break;
                case "0":
                    System.out.println("EXIT");
                    break;
                default:
                    System.out.println("INVALID CHOICE");
                    break;
            }
        } while (!choice.equals("0"));
    }
    private static void registerNewDriver(Scanner in) {
        System.out.println("ENTER DRIVER NAME:");
        driverNames[driverCounter] = in.nextLine();
        System.out.println("ENTER PASSWORD:");
        driverPasswords[driverCounter] = in.nextLine();
        System.out.println("ENTER CURRENT LOCATION (one of " + String.join(", ", locations) + "):");
        currentLocations[driverCounter] = validateLocation(in);
        driverAvailability[driverCounter] = 0;
        driverEarnings[driverCounter] = 0.0;
        driverCounter++;
    }
    private static void driverLogin(Scanner in) {
        System.out.println("DRIVER LOGIN");
        System.out.println("ENTER YOUR NAME:");
        String driverName = in.nextLine();
        System.out.println("ENTER PASSWORD:");
        String driverPassword = in.nextLine();
        int found = 0;
        for (int i = 1; i < driverCounter; i++) {
            if (driverName.equals(driverNames[i]) && driverPassword.equals(driverPasswords[i])) {
                driverSection(in, i);
                found++;
                break;
            }
        }
        if (found == 0) {
            System.out.println("INVALID DRIVER CREDENTIALS");
        }
    }
    private static void driverSection(Scanner in, int driverIndex) {
        String choice;
        do {
            System.out.println("WELCOME DRIVER");
            System.out.println("1. VIEW BOOKINGS");
            System.out.println("0. LOGOUT");
            choice = in.nextLine();
            switch (choice) {
                case "1":
                    viewBookings(driverIndex);
                    break;
                case "0":
                    System.out.println("LOGOUT");
                    break;
                default:
                    System.out.println("INVALID CHOICE");
                    break;
            }
        } while (!choice.equals("0"));
    }
    private static void viewBookings(int driverIndex) {
    System.out.println("BOOKINGS:");
    for (int i = 1; i < bookingCounter; i++) {
        if (bookedTaxis[i].equals(driverNames[driverIndex])) {
            System.out.println("Booking ID: " + (i) +
                    ", User: " + userNames[bookedByUser[i]] +
                    ", Start Point: " + startPoints[i] +
                    ", End Point: " + endPoints[i] +
                    ", Start Time: " + startTime[i] +
                    ", End Time: " + endTime[i] +
                    ", Earnings: " + driverEarnings[i]);
        }
    }
    }
    private static void userSection(Scanner in) {
        String choice;
        do {
            System.out.println("WELCOME USER");
            System.out.println("1. REGISTER");
            System.out.println("2. BOOK TAXI");
            System.out.println("3. VIEW BOOKING");
            System.out.println("0. EXIT");
            choice = in.nextLine();
            switch (choice) {
                case "1":
                    registerNewUser(in);
                    break;
                case "2":
                    bookTaxi(in);
                    break;
                case "3":
                    viewBooking(in);
                    break;
                case "0":
                    System.out.println("EXIT");
                    break;
                default:
                    System.out.println("INVALID CHOICE");
                    break;
            }
        } while (!choice.equals("0"));
    }
    private static void ownerSection(Scanner in) {
        String choice;
        do {
            System.out.println("OWNER SITE");
            System.out.println("1. DRIVERS LIST WITH PLACES");
            System.out.println("2. TOTAL AMOUNT EARNED");
            System.out.println("0. EXIT");
            choice = in.nextLine();
            switch (choice) {
                case "1":
                    OWNERDRIVER(in);
                    break;
                case "2":
                    TOTALEARNINGS(in);
                    break;
                case "0":
                    System.out.println("EXIT");
                    break;
                default:
                    System.out.println("INVALID CHOICE");
                    break;
            }
        } while (!choice.equals("0"));
    }
    private static void bookTaxi(Scanner in) {
        System.out.println("ENTER YOUR USER ID:");
        int userId = in.nextInt();
        in.nextLine();
        if (userId < userCounter) {
            System.out.println("AVAILABLE DRIVERS:");
            boolean driverAvailable = false;
            for (int i = 1; i < driverCounter; i++) {
                if (driverAvailability[i] == 0) {
                    System.out.println((i) + ". Driver: " + driverNames[i] + " - Current Location: " + currentLocations[i] +
                            " - Next Available Time: " + driverAvailability[i]);
                    driverAvailable = true;
                }
            }
            if (!driverAvailable) {
                System.out.println("NO CABS ARE FREE AT THE MOMENT");
                for (int i = 1; i < driverCounter; i++) {
                    System.out.println((i) + ". Driver: " + driverNames[i] + " - Current Location: " + currentLocations[i] +
                            " - Next Available Time: " + driverAvailability[i]);
                }
                return;
            }
            System.out.println("SELECT DRIVER NUMBER:");
            int driverIndex = in.nextInt();
            in.nextLine();
            if (driverIndex < driverCounter) 
            {
                System.out.println("ENTER START POINT (one of " + String.join(", ", locations) + "):");
                String startPoint = validateLocation(in);
                System.out.println("ENTER END POINT (one of " + String.join(", ", locations) + "):");
                String endPoint = validateLocation(in);
                System.out.println("ENTER START TIME (in 24 hours format):");
                int tripStartTime = in.nextInt();
                in.nextLine(); 
                int reachdestination =Math.abs(currentLocations[driverIndex].charAt(0)-startPoint.charAt(0));
                int travelTime = Math.abs(endPoint.charAt(0) - startPoint.charAt(0));
                int tripEndTime = tripStartTime + travelTime;
                if(tripEndTime>24)
                tripEndTime=tripEndTime-24;
                int totaltimetravelforthedriver=reachdestination+travelTime;
                int EARNING=totaltimetravelforthedriver*100;
                System.out.println("PAYMENT TO PAY-->"+EARNING);
                System.out.println("DO YOU WANT TO PROCEED WITH PAYMENT? (yes/no)");
                String confirm = in.next();
                in.nextLine();
                if (confirm.equalsIgnoreCase("yes")) 
                {
                System.out.println("BOOKING SUCCESSFUL! Trip will start at " + tripStartTime + " and end at " + tripEndTime);
                startPoints[bookingCounter] = startPoint;
                endPoints[bookingCounter] = endPoint;
                startTime[bookingCounter] = tripStartTime;
                endTime[bookingCounter] = tripEndTime;
                bookedByUser[bookingCounter] = userId;
                bookedTaxis[bookingCounter] = driverNames[driverIndex];
                currentLocations[driverIndex] = endPoint; 
                driverEarnings[bookingCounter]=EARNING;
                driverAvailability[driverIndex] = tripEndTime;
                bookingCounter++;
                }
                else 
                {
                System.out.println("PAYMENT CANCELLED");
                }
            }
            else 
            {
            System.out.println("INVALID DRIVER ID");
            }
        } 
        else 
        {
            System.out.println("INVALID USER ID");
        }
    }
    private static void registerNewUser(Scanner in) {
        System.out.println("ENTER YOUR NAME:");
        userNames[userCounter] = in.nextLine();
        System.out.println("YOUR USER ID IS: " + userCounter);
        userCounter++;
    }
    private static void viewBooking(Scanner in)
    {
    System.out.println("ENTER YOUR USER ID:");
    int userId = in.nextInt();
    in.nextLine();
    if (userId < userCounter) {
        System.out.println("BOOKINGS FOR USER: " + userNames[userId]);
        boolean bookingFound = false;
        for (int i = 1; i < bookingCounter; i++) {
            if (bookedByUser[i] == userId) {
                int driverIndex = -1;
                for (int j = 1; j < driverCounter; j++) {
                    if (bookedTaxis[i].equals(driverNames[j])) {
                        driverIndex = j;
                        break;
                    }
                }
                if (driverIndex != -1) {
                    System.out.println("Booking ID: " + i +
                            ", Driver: " + bookedTaxis[i] +
                            ", Start Point: " + startPoints[i] +
                            ", End Point: " + endPoints[i] +
                            ", Start Time: " + startTime[i] +
                            ", End Time: " + endTime[i] +
                            ", Driver Earnings: " + driverEarnings[driverIndex]);
                    bookingFound = true;
                }
            }
        }
        if (!bookingFound) {
            System.out.println("NO BOOKINGS FOUND");
        }
    } else {
        System.out.println("INVALID USER ID");
    }
    }
    private static String validateLocation(Scanner in) {
        String location;
        while (true) {
            location = in.nextLine();
            boolean valid = false;
            for (String loc : locations) {
                if (location.equalsIgnoreCase(loc)) {
                    valid = true;
                    break;
                }
            }
            if (valid) {
                break;
            } else {
                System.out.println("INVALID LOCATION. ENTER ONE OF " + String.join(", ", locations));
            }
        }
        return location;
    }
    private static void OWNERDRIVER(Scanner in) {
                for (int i = 1; i < driverCounter; i++)
                {
                    System.out.println("Booking ID: " + i +
                            ", Driver: " + bookedTaxis[i] +
                            ", Start Point: " + startPoints[i] +
                            ", End Point: " + endPoints[i] +
                            ", Start Time: " + startTime[i] +
                            ", End Time: " + endTime[i] +
                            ", Driver Earnings: " + driverEarnings[i]);
                }
    }
    private static void TOTALEARNINGS(Scanner in) {
        double TOTALEARNINGS=0.0;
        for (double i :driverEarnings) 
        {
            TOTALEARNINGS=TOTALEARNINGS+i;
        } 
        System.out.println("TOTAL EARNINGS OF ALL DRIVERS"+TOTALEARNINGS);
    }
}
