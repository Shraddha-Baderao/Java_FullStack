import java.util.Scanner;

class Product
{
    int pid;
    String pname;
    int quantity;

    // Default Constructor
    Product() {
        System.out.println("Default Constructor Called");
        pid = 0;
        pname = "";
        quantity = 0;
    }

    // Parameterized Constructor
    Product(int pid, String pname, int quantity) {
        System.out.println("Parameterized Constructor Called");
        this.pid = pid;
        this.pname = pname;
        this.quantity = quantity;
    }

    void setPid(int pid) {
        this.pid = pid;
    }
    int getPid() {
        return pid;
    }

    void setPname(String pname) {
        this.pname = pname;
    }
    String getPname() {
        return pname;
    }

    void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    int getQuantity() {
        return quantity;
    }

    void display() {
        System.out.println("Product ID: " + pid);
        System.out.println("Product Name: " + pname);
        System.out.println("Quantity: " + quantity);
    }
} // class Product ends here


class TestProduct
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        // Using Default Constructor
        Product p1 = new Product();

        System.out.print("Enter Product ID: ");
        p1.setPid(sc.nextInt());
        sc.nextLine(); // consume leftover newline

        System.out.print("Enter Product Name: ");
        p1.setPname(sc.nextLine());

        System.out.print("Enter Quantity: ");
        p1.setQuantity(sc.nextInt());

        System.out.println("\n--- Product Details (User Input) ---");
        p1.display();

        // Using Parameterized Constructor
        Product p2 = new Product(101, "Laptop", 5);

        System.out.println("\n--- Product Details (Parameterized Constructor) ---");
        p2.display();
    }
} // main class ends here
