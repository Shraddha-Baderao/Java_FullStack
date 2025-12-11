import java.util.Scanner;

class HR
{
    int id;
    String name;
    double salary;
    double commission;

    // Default Constructor
    HR() {
        System.out.println("Default Constructor Called");
        id = 0;
        name = "";
        salary = 0.0;
        commission = 0.0;
    }

    // Parameterized Constructor
    HR(int id, String name, double salary, double commission) {
        System.out.println("Parameterized Constructor Called");
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.commission = commission;
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

    void setCommission(double commission) {
        this.commission = commission;
    }
    double getCommission() {
        return commission;
    }

    void display() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Salary: " + salary);
        System.out.println("Commission: " + commission);
    }
}// class HR ends here

class TestHR
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        // Using Default Constructor
        HR hr1 = new HR();

        System.out.print("Enter HR ID: ");
        hr1.setId(sc.nextInt());
        sc.nextLine(); // consume leftover newline

        System.out.print("Enter Name: ");
        hr1.setName(sc.nextLine());

        System.out.print("Enter Salary: ");
        hr1.setSalary(sc.nextDouble());

        System.out.print("Enter Commission: ");
        hr1.setCommission(sc.nextDouble());

        System.out.println("\n--- HR Details (User Input) ---");
        hr1.display();

        // Using Parameterized Constructor
        HR hr2 = new HR(102, "Shraddha", 50000.0, 4000.0);
        System.out.println("\n--- HR Details (Parameterized Constructor) ---");
        hr2.display();
    }
}//main class ends here
