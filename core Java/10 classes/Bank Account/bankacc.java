import java.util.Scanner;

class BankAccount
{
    int accNo;
    String holderName;
    double currentBalance;
    double interestRate;

    // Default Constructor
    BankAccount() {
        System.out.println("Default Constructor Called");
        accNo = 0;
        holderName = "";
        currentBalance = 0.0;
        interestRate = 0.0;
    }

    // Parameterized Constructor
    BankAccount(int accNo, String holderName, double currentBalance, double interestRate) {
        System.out.println("Parameterized Constructor Called");
        this.accNo = accNo;
        this.holderName = holderName;
        this.currentBalance = currentBalance;
        this.interestRate = interestRate;
    }

    void setAccNo(int accNo) {
        this.accNo = accNo;
    }
    int getAccNo() {
        return accNo;
    }

    void setHolderName(String holderName) {
        this.holderName = holderName;
    }
    String getHolderName() {
        return holderName;
    }

    void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }
    double getCurrentBalance() {
        return currentBalance;
    }

    void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
    double getInterestRate() {
        return interestRate;
    }

    void display() {
        System.out.println("Account Number: " + accNo);
        System.out.println("Holder Name: " + holderName);
        System.out.println("Current Balance: " + currentBalance);
        System.out.println("Interest Rate: " + interestRate + "%");
    }
} // BankAccount class ends here


class TestBankAccount
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        // Using Default Constructor
        BankAccount b1 = new BankAccount();

        System.out.print("Enter Account Number: ");
        b1.setAccNo(sc.nextInt());
        sc.nextLine(); // consume newline

        System.out.print("Enter Holder Name: ");
        b1.setHolderName(sc.nextLine());

        System.out.print("Enter Current Balance: ");
        b1.setCurrentBalance(sc.nextDouble());

        System.out.print("Enter Interest Rate (%): ");
        b1.setInterestRate(sc.nextDouble());

        System.out.println("\n--- Bank Account Details (User Input) ---");
        b1.display();

        // Using Parameterized Constructor
        BankAccount b2 = new BankAccount(12345, "Shraddha", 50000.0, 6.5);

        System.out.println("\n--- Bank Account Details (Parameterized Constructor) ---");
        b2.display();
    }
} // main class ends here
