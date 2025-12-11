import java.util.Scanner;

class SalesManager
{
    int id;
    String name;
     double salary;
    double incentive;
    double target;

    // Default Constructor
    SalesManager() {
        System.out.println("Default Constructor Called");
        id = 0;
        name = "";
        salary = 0.0;
        incentive = 0.0;
        target = 0.0;
    }

    // Parameterized Constructor
    SalesManager(int id, String name, double salary, double incentive, double target) {
        System.out.println("Parameterized Constructor Called");
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.incentive = incentive;
        this.target = target;
    }

        void setId(int id) {
        this.id = id;
    }
    int getId() {
        return id;
    }

    void setName(String name) {
        this.name = name;
    }
    String getName() {
        return name;
    }

    void setSalary(double salary) {
        this.salary = salary;
    }
    double getSalary() {
        return salary;
    }

    void setIncentive(double incentive) {
        this.incentive = incentive;
    }
    double getIncentive() {
        return incentive;
    }

    void setTarget(double target) {
        this.target = target;
    }
    double getTarget() {
        return target;
    }

    void display() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Salary: " + salary);
        System.out.println("Incentive: " + incentive);
        System.out.println("Target: " + target);
    }
}//class salesmanager ends here

class TestSalesManager
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        // Using Default Constructor
        SalesManager sm1 = new SalesManager();

        System.out.print("Enter SalesManager ID: ");
        sm1.setId(sc.nextInt());
        sc.nextLine(); // consume leftover newline

        System.out.print("Enter Name: ");
        sm1.setName(sc.nextLine());

        System.out.print("Enter Salary: ");
        sm1.setSalary(sc.nextDouble());

        System.out.print("Enter Incentive: ");
        sm1.setIncentive(sc.nextDouble());

        System.out.print("Enter Target: ");
        sm1.setTarget(sc.nextDouble());

        System.out.println("\n--- SalesManager Details (User Input) ---");
        sm1.display();

        // Using Parameterized Constructor
        SalesManager sm2 = new SalesManager(102, "Shraddha", 60000.0, 5000.0, 100000.0);
        System.out.println("\n--- SalesManager Details (Parameterized Constructor) ---");
        sm2.display();
    }
}
