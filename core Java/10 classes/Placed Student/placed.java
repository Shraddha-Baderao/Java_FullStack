import java.util.Scanner;

class PlacedStudent
{
    private int frn;
    private String name;
    private double distance;
    private String companyName;
    private String designation;

        PlacedStudent() {
        System.out.println("Default Constructor Called");
        frn = 0;
        name = "";
        distance = 0.0;
        companyName = "";
        designation = "";
    }

      PlacedStudent(int frn, String name, double distance, String companyName, String designation) {
        System.out.println("Parameterized Constructor Called");
        this.frn = frn;
        this.name = name;
        this.distance = distance;
        this.companyName = companyName;
        this.designation = designation;
    }

        void setFrn(int frn) {
        this.frn = frn;
    }
    int getFrn() {
        return frn;
    }

        void setName(String name) {
        this.name = name;
    }
    String getName() {
        return name;
    }


    void setDistance(double distance) {
        this.distance = distance;
    }
    double getDistance() {
        return distance;
    }

  
    void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    String getCompanyName() {
        return companyName;
    }

        void setDesignation(String designation) {
        this.designation = designation;
    }
    String getDesignation() {
        return designation;
    }

       void display() {
        System.out.println("FRN: " + frn);
        System.out.println("Name: " + name);
        System.out.println("Distance: " + distance + " km");
        System.out.println("Company Name: " + companyName);
        System.out.println("Designation: " + designation);
    }
}//class PlasedStudent ends here

class TestPlacedStudent
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        // Using Default Constructor
        PlacedStudent ps1 = new PlacedStudent();

        System.out.print("Enter FRN: ");
        ps1.setFrn(sc.nextInt());
        sc.nextLine(); // consume leftover newline

        System.out.print("Enter Name: ");
        ps1.setName(sc.nextLine());

        System.out.print("Enter Distance: ");
        ps1.setDistance(sc.nextDouble());
        sc.nextLine(); // consume leftover newline

        System.out.print("Enter Company Name: ");
        ps1.setCompanyName(sc.nextLine());

        System.out.print("Enter Designation: ");
        ps1.setDesignation(sc.nextLine());

        System.out.println("\n--- Placed Student Details (User Input) ---");
        ps1.display();

        // Using Parameterized Constructor
        PlacedStudent ps2 = new PlacedStudent(102, "Shraddha", 15.5, "TCS", "Software Engineer");
        System.out.println("\n--- Placed Student Details (Parameterized Constructor) ---");
        ps2.display();
    }
}
