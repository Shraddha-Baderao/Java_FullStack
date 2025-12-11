import java.util.Scanner;

class Student
{
    private int frn;
    private String name;
    private double distance;

    // Default Constructor
    Student() {
        System.out.println("Default Constructor Called");
        frn = 0;
        name = "";
        distance = 0.0;
    }

    // Parameterized Constructor
    Student(int frn, String name, double distance) {
        System.out.println("Parameterized Constructor Called");
        this.frn = frn;
        this.name = name;
        this.distance = distance;
    }

    void setFrn(int frn) {
        this.frn = frn;
    }
    int getFrn() {
        return this.frn;
    }

    void setName(String name) {
        this.name = name;
    }
    String getName() {
        return this.name;
    }

    void setDistance(double distance) {
        this.distance = distance;
    }
    double getDistance() {
        return this.distance;
    }

    void display() {
        System.out.println("FRN: " + frn);
        System.out.println("Name: " + name);
        System.out.println("Distance: " + distance + " km");
    }
}

class TestStudent
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        Student s1 = new Student();  // default constructor

        System.out.print("Enter FRN (int): ");
        s1.setFrn(sc.nextInt());
        sc.nextLine(); // used for buffer

        System.out.print("Enter Name: ");
        s1.setName(sc.nextLine());

        System.out.print("Enter Distance: ");
        s1.setDistance(sc.nextDouble());

        System.out.println("\n--- Student Details ---");
        s1.display();

        Student s2 = new Student(101, "Shraddha", 12.5); 
        System.out.println("\n--- Student 2 Details (Parameterized Constructor) ---");
        s2.display();
    }
}
