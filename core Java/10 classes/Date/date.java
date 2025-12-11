import java.util.Scanner;

class Date
{
    int day;
    int month;
    int year;
    String dow;   // day of week

    // Default Constructor
    Date() {
        System.out.println("Default Constructor Called");
        day = 0;
        month = 0;
        year = 0;
        dow = "";
    }

    // Parameterized Constructor
    Date(int day, int month, int year, String dow) {
        System.out.println("Parameterized Constructor Called");
        this.day = day;
        this.month = month;
        this.year = year;
        this.dow = dow;
    }

    void setDay(int day) {
        this.day = day;
    }
    int getDay() {
        return day;
    }

    void setMonth(int month) {
        this.month = month;
    }
    int getMonth() {
        return month;
    }

    void setYear(int year) {
        this.year = year;
    }
    int getYear() {
        return year;
    }

    void setDow(String dow) {
        this.dow = dow;
    }
    String getDow() {
        return dow;
    }

    void display() {
        System.out.println("Date: " + day + "/" + month + "/" + year);
        System.out.println("Day of Week: " + dow);
    }
} // Date class ends here


class TestDate
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        // Using Default Constructor
        Date d1 = new Date();

        System.out.print("Enter Day: ");
        d1.setDay(sc.nextInt());

        System.out.print("Enter Month: ");
        d1.setMonth(sc.nextInt());

        System.out.print("Enter Year: ");
        d1.setYear(sc.nextInt());
        sc.nextLine();  // consume newline

        System.out.print("Enter Day of Week: ");
        d1.setDow(sc.nextLine());

        System.out.println("\n--- Date Details (User Input) ---");
        d1.display();

        // Using Parameterized Constructor
        Date d2 = new Date(9, 12, 2025, "Tuesday");

        System.out.println("\n--- Date Details (Parameterized Constructor) ---");
        d2.display();
    }
} // main class ends here
