import java.util.Scanner;

class Book
{
    int ISBH;
    String BName;
    String author;
    String category;
    double price;

    // Default Constructor
    Book() {
        System.out.println("Default Constructor Called");
        ISBH = 0;
        BName = "";
        author = "";
        category = "";
        price = 0.0;
    }

    // Parameterized Constructor
    Book(int ISBH, String BName, String author, String category, double price) {
        System.out.println("Parameterized Constructor Called");
        this.ISBH = ISBH;
        this.BName = BName;
        this.author = author;
        this.category = category;
        this.price = price;
    }

    void setISBH(int ISBH) {
        this.ISBH = ISBH;
    }
    int getISBH() {
        return ISBH;
    }

    void setBName(String BName) {
        this.BName = BName;
    }
    String getBName() {
        return BName;
    }

    void setAuthor(String author) {
        this.author = author;
    }
    String getAuthor() {
        return author;
    }

    void setCategory(String category) {
        this.category = category;
    }
    String getCategory() {
        return category;
    }

    void setPrice(double price) {
        this.price = price;
    }
    double getPrice() {
        return price;
    }

    void display() {
        System.out.println("ISBH: " + ISBH);
        System.out.println("Book Name: " + BName);
        System.out.println("Author: " + author);
        System.out.println("Category: " + category);
        System.out.println("Price: Rs. " + price);
    }
} // Book class ends here


class TestBook
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        // Using Default Constructor
        Book b1 = new Book();

        System.out.print("Enter ISBH: ");
        b1.setISBH(sc.nextInt());
        sc.nextLine(); // consume leftover newline

        System.out.print("Enter Book Name: ");
        b1.setBName(sc.nextLine());

        System.out.print("Enter Author Name: ");
        b1.setAuthor(sc.nextLine());

        System.out.print("Enter Category: ");
        b1.setCategory(sc.nextLine());

        System.out.print("Enter Price: ");
        b1.setPrice(sc.nextDouble());

        System.out.println("\n--- Book Details (User Input) ---");
        b1.display();

        // Using Parameterized Constructor
        Book b2 = new Book(101, "Java Programming", "James Gosling", "Education", 499.0);

        System.out.println("\n--- Book Details (Parameterized Constructor) ---");
        b2.display();
    }
} // main class ends here
