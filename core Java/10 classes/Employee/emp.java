import java.util.Scanner;

class Employee
{
    int id;
    String name;
    double salary;

    // Default Constructor
    Employee() {
        System.out.println("Default Constructor Called");
        id = 0;
        name = "";
        salary = 0.0;
    }

    // Parameterized Constructor
    Employee(int id, String name, double salary) {
        System.out.println("Parameterized Constructor Called");
        this.id = id;
        this.name = name;
        this.salary = salary;
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

        void display() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Salary: " + salary);
    }
}

class TestEmployee
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        // Using Default Constructor
        Employee e1 = new Employee();

        System.out.print("Enter Employee ID: ");
        e1.setId(sc.nextInt());
        sc.nextLine(); // consume leftover newline

        System.out.print("Enter Name: ");
        e1.setName(sc.nextLine());

        System.out.print("Enter Salary: ");
        e1.setSalary(sc.nextDouble());

        System.out.println("\n--- Employee Details (User Input) ---");
        e1.display();

        // Using Parameterized Constructor
        Employee e2 = new Employee(102, "Shraddha", 50000.0);
        System.out.println("\n--- Employee Details (Parameterized Constructor) ---");
        e2.display();
    }
}
