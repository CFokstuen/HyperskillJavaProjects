import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        InterfaceManager interfaceManager = new InterfaceManager();
        CinemaRoom cinemaRoom = interfaceManager.createRoom();
        interfaceManager.menuManager(cinemaRoom);
    }
}

class InterfaceManager {
    Scanner scanner = new Scanner(System.in);

    public CinemaRoom createRoom() {
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        System.out.println();
        return new CinemaRoom(rows, seats);
    }

    public void menuManager(CinemaRoom cr) {
        for (;;) {
            System.out.println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");
            int input = scanner.nextInt();
            switch (input) {
                case 0:
                    scanner.close();
                    return;
                case 1:
                    showRoom(cr);
                    break;
                case 2:
                    sellTicket(cr);
                    showRoom(cr);
                    break;
                case 3:
                    showStatistics(cr);
            }
        }
    }

    public void showStatistics(CinemaRoom cr) {
        System.out.printf("\nNumber of purchased tickets: %d\n" +
                "Percentage: %.2f%%\n" +
                "Current income: $%d\n" +
                "Total income: $%d\n",
                cr.getPurchasedTickets(), cr.getPurchasedTickets() / (double) cr.getTotalSeats() * 100,
                cr.getCurrentIncome(), cr.getTotalIncome());
        System.out.println();
    }

    public void showRoom(CinemaRoom cr) {
        SeatStatus[][] planOfRoom = cr.getPlanOfRoom();
        System.out.println("\nCinema:");
        System.out.print("  ");
        for (int i = 1; i <= planOfRoom[0].length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        int rowNumber = 1;
        for (SeatStatus[] arr : planOfRoom) {
            System.out.print(rowNumber++ + " ");
            for (SeatStatus seatStatus : arr) {
                System.out.print(seatStatus.equals(SeatStatus.VACANT) ? "S " : "B ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void sellTicket(CinemaRoom cr) {
        System.out.println("\nEnter a row number:");
        int row = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seat = scanner.nextInt();
        System.out.println();
        try {
            if (cr.getPlanOfRoom()[row - 1][seat - 1] == SeatStatus.VACANT) {
                System.out.println("Ticket price: $" + cr.getPrice(row));
                cr.setSoldSeats(row, seat);
                cr.setPurchasedTickets();
                cr.sendMoneyInCurrentIncome(row);
            } else {
                System.out.println("That ticket has already been purchased!");
                sellTicket(cr);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Wrong input!");
            sellTicket(cr);
        }
    }
}

class CinemaRoom {
    private final int rows;
    private final int seats;
    private final int lessPrice = 8;
    private final int biggerPrice = 10;
    private final int totalSeats;
    private SeatStatus[][] planOfRoom;
    private int currentIncome = 0;
    private int purchasedTickets = 0;

    public CinemaRoom(int rows, int seats) {
        this.rows = rows;
        this.seats = seats;
        this.totalSeats = rows * seats;
        initializeRoom();
    }

    private void initializeRoom() {
        planOfRoom = new SeatStatus[rows][seats];
        for (SeatStatus[] seatStatuses : planOfRoom) {
            Arrays.fill(seatStatuses, SeatStatus.VACANT);
        }
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getPurchasedTickets() {
        return purchasedTickets;
    }

    public void setPurchasedTickets() {
        purchasedTickets++;
    }

    public int getCurrentIncome() {
        return currentIncome;
    }

    public SeatStatus[][] getPlanOfRoom() {
        return planOfRoom;
    }

    public void setSoldSeats(int row, int seat) {
        planOfRoom[row - 1][seat - 1] = SeatStatus.OCCUPIED;
    }

    public void sendMoneyInCurrentIncome(int row) {
        currentIncome += getPrice(row);
    }

    public int getTotalIncome() {
        if (totalSeats >= 60) {
            int incomeOfForwardSeats = (rows / 2) * seats * biggerPrice;
            int incomeOfBackSeats = (rows - (rows / 2)) * seats * lessPrice;
            return incomeOfBackSeats + incomeOfForwardSeats;
        } else {
            return totalSeats * biggerPrice;
        }
    }

    public int getPrice(int row) {
        return (totalSeats >= 60 && rows / 2 < row) ? lessPrice : biggerPrice;
    }
}

enum SeatStatus {
    VACANT, OCCUPIED
}
