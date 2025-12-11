import java.util.Scanner;

class Admin
{
    int id;
    String name;
    double salary;
    double allowance;

    // Default Constructor
    Admin() {
        System.out.println("Default Constructor Called");
        id = 0;
        name = "";
        salary = 0.0;
        allowance = 0.0;
    }

    // Parameterized Constructor
    Admin(int id, String name, double salary, double allowance) {
        System.out.println("Parameterized Constructor Called");
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.allowance = allowance;
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

    void setAllowance(double allowance) {
        this.allowance = allowance;
    }
    double getAllowance() {
        return allowance;
    }

    void display() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Salary: " + salary);
        System.out.println("Allowance: " + allowance);
    }
} // class Admin ends here


class TestAdmin
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        // Using Default Constructor
        Admin a1 = new Admin();

        System.out.print("Enter Admin ID: ");
        a1.setId(sc.nextInt());
        sc.nextLine();  // consume leftover newline

        System.out.print("Enter Name: ");
        a1.setName(sc.nextLine());

        System.out.print("Enter Salary: ");
        a1.setSalary(sc.nextDouble());

        System.out.print("Enter Allowance: ");
        a1.setAllowance(sc.nextDouble());

        System.out.println("\n--- Admin Details (User Input) ---");
        a1.display();

        // Using Parameterized Constructor
        Admin a2 = new Admin(201, "Shraddha", 60000.0, 8000.0);

        System.out.println("\n--- Admin Details (Parameterized Constructor) ---");
        a2.display();
    }
} // main class ends here
