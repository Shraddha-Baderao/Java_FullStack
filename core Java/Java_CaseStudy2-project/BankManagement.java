import java.util.Arrays;
import java.util.Date;
import java.time.LocalDate;
import java.util.Scanner;


//-------------------------------------------------------------------------------------------------------
//Transaction
//-------------------------------------------------------------------------------------------------------

class Transaction
{
	String transactionId;
	double amount;
	LocalDate date;
	String type;
	String status;
	static int transactionCount = 0;
	
	Transaction(String transactionId, double amount, LocalDate date, String type, String status) 
	{
		super();
		this.transactionId = transactionId;
		this.amount = amount;
		this.date = date;
		this.type = type;
		this.status = status;
	}
	
	void setStatus(String status)
	{
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", amount=" + amount + ", date=" + date + ", type="
				+ type + ", status=" + status + "]";
	}
	
}
//-------------------------------------------------------------------------------------------------------
//Bank Account
//-------------------------------------------------------------------------------------------------------

abstract class BankAccount
{
	 String accHolderName;
	 long accountNumber;
	 String branchName;
	 double balance;
	 String accountType;
	 String mobileNo;
	 String email;
	 Date createDate;
	 String aadharNo;
	 String panNo;
	 double interestRate = 4.0;
	 
	 Transaction [] transactions = new Transaction[100];
	 int transactionCount = 0;
	 
	 BankAccount(String accHolderName, long accountNumber, String branchName, 
			 double balance, String accountType,String mobileNo, 
			 String email, String aadharNo, String panNo) 
	 {
		//super();
		this.accHolderName = accHolderName;
		this.accountNumber = accountNumber;
		this.branchName = branchName;
		this.balance = balance;
		this.accountType = accountType;
		this.mobileNo = mobileNo;
		this.email = email;
		this.createDate = new Date();
		this.aadharNo = aadharNo;
		this.panNo = panNo;
		recordTransaction(new Transaction("Transaction" + (++Transaction.transactionCount), balance, LocalDate.now(),
				"CREDIT", "SUCCESS"), true);
	 }
	 
	 public void setaccHolderName(String name)
	 {
		 this.accHolderName = name;
	 }
	 
	 public void setmobileNo(String mobileNo)
	 {
		 this.mobileNo = mobileNo;
	 }
	 
	 public void setemail(String email)
	 {
		 this.email = email;
	 }
	 
	 public double getBalance()
	 {
		 return balance;
	 }
	 
	 public long getAccountNumber()
	 {
		 return accountNumber;
	 }
	 
	 abstract boolean deposit (double amount);
	 
	 abstract boolean withdraw (double amount);
	 
	 abstract String getAccountDetails();
	 
	 abstract void applyInterest();
	 
	 void closeAccount()
	 {
		 System.out.println("Account" + accountNumber + "Closed Permenantly.");
	 }
	 
	 void recordTransaction(Transaction t,boolean success)
	 {
		 t.setStatus(success ? "SUCCESS" : "FAILED");
		 
		 if(transactionCount < transactions.length)
		 {
			 transactions[transactionCount++] = t;
		 }
	 }	 
	 
	 @Override
	public String toString() {
		return "BankAccount [accHolderName=" + accHolderName + ", accountNumber=" + accountNumber + ", branchName="
				+ branchName + ", balance=" + balance + ", accountType=" + accountType + ", mobileNo=" + mobileNo
				+ ", email=" + email + ", createDate=" + createDate + ", aadharNo=" + aadharNo + ", panNo=" + panNo
				+ ", interestRate=" + interestRate + "]";
	}
	 
	 //TO-DO
}
//-------------------------------------------------------------------------------------------------------
// Saving Account
//-------------------------------------------------------------------------------------------------------


class SavingAccount extends BankAccount
{
	static double minBalance;
	double withdrawLimit;
	double dailyAmtLimit;
	double todayWithdrawn = 0;
	LocalDate lastWithdrawalDay = LocalDate.now();
	
	static
	{
		minBalance = 10000;
	}
	
	SavingAccount(String accHolderName, long accountNumber, String branchName, double balance,
			String mobileNo, String email, String aadharNo, String panNo, double minBalance, double withdrawLimit,
			double dailyAmtLimit) 
	{
		super(accHolderName, accountNumber, branchName, balance, "Saving", mobileNo, email, aadharNo, panNo);
		this.minBalance = minBalance;
		this.withdrawLimit = withdrawLimit;
		this.dailyAmtLimit = dailyAmtLimit;
	}
	
	boolean deposit (double amount)
	{
		if(amount <= 0)
		{
			recordTransaction(new Transaction("Transaction" + (++Transaction.transactionCount),amount,
					LocalDate.now(),"CREDIT","FAILED"),false);
			
			return false;
		}
		
		balance = balance + amount;
		recordTransaction(new Transaction("Transaction" + (++Transaction.transactionCount),amount,
				LocalDate.now(),"CREDIT","SUCCESS"),true);
	
		return true;
	}
	
	boolean withdraw(double amount)
	{
		LocalDate today = LocalDate.now();
		
		if(!today.equals(lastWithdrawalDay))
		{
			todayWithdrawn = 0;
			lastWithdrawalDay= today;
		}
		//changed hare
		if(amount <= 0 || amount > withdrawLimit || (todayWithdrawn + amount) > dailyAmtLimit
				||(balance - amount) < minBalance)
		{
			recordTransaction(new Transaction("Transaction" + (++Transaction.transactionCount), amount, LocalDate.now(),
					"DEBIT", "FAILED"), false);
			
			return false;
		}
		
		balance = balance - amount;
		todayWithdrawn = todayWithdrawn + amount;
			
		recordTransaction(new Transaction("Transaction" + (++Transaction.transactionCount), amount, LocalDate.now(),
				"DEBIT", "SUCCESS"), true);
		
			return true;
	}
	
	String getAccountDetails()
	{
		return "[SAVING] AccNo: " + accountNumber + "\nName: " + accHolderName + "\nBalance: " + balance
				+"\nBranch" + branchName + "\nMinBalance: " + minBalance;
	}
	
	void applyInterest()
	{
		double interest = (balance * interestRate) / 100.0;
		balance += interest;
		recordTransaction(new Transaction("Transaction" + (++Transaction.transactionCount), interest, LocalDate.now(),
				"INTEREST", "SUCCESS"), true);
	}

	@Override
	public String toString() {
		return super.toString()+"SavingAccount [minBalance=" + minBalance + ", withdrawLimit=" + withdrawLimit + ", dailyAmtLimit="
				+ dailyAmtLimit + ", todayWithdrawn=" + todayWithdrawn + ", lastWithdrawalDay=" + lastWithdrawalDay
				+ "]";
	}
	
}

//-------------------------------------------------------------------------------------------------------
//Current Account
//-------------------------------------------------------------------------------------------------------

class CurrentAccount extends BankAccount
{
	double overdraftLimit;
	double overdraftInterestRate;
	String businessName; 	
	String gstNumber;
	
	CurrentAccount(String accHolderName, long accountNumber, String branchName, double balance, String accountType,
			String mobileNo, String email, String aadharNo, String panNo, double overdraftLimit,
			double overdraftInterestRate, String businessName, String gstNumber) 
	
	{
		super(accHolderName, accountNumber, branchName, balance, accountType, mobileNo, email, aadharNo, panNo);
		
		this.overdraftLimit = overdraftLimit;
		this.overdraftInterestRate = overdraftInterestRate;
		this.businessName = businessName;
		this.gstNumber = gstNumber;
	}
	
	boolean deposit(double amount)
		{
			if(amount <= 0)
			{
				return false;
			}
		
			balance += amount;
		
			recordTransaction(new Transaction("Transaction" + (++Transaction.transactionCount),amount, LocalDate.now(),
					"DEBIT","SUCCESS"),true);
		
			return true;
		}
	
	boolean withdraw(double amount)
	{
		if(amount <= 0 || amount > (balance + overdraftLimit))
			return false;
		
		balance = balance - amount;
		
		recordTransaction(new Transaction("Transaction" + (++Transaction.transactionCount),amount, LocalDate.now(),
				"DEBIT","SUCCESS"),true);
		
		return true;
	}
	
	String getAccountDetails()
	{
		return "[CURRENT] AccNo:" + accountNumber + "\nName: " + accHolderName + "\nBalance: " + balance 
				+ "\nBusiness: " + businessName + "\nOverdraft Limit: " + overdraftLimit;
	}
	
	void applyInterest()
	{
		if(balance < 0)
		{
			double interest = balance * (-overdraftInterestRate / 100.0);// negative balance × negative rate = positive interest
			balance = balance - interest;
			
			recordTransaction (new Transaction("Transaction" + (++Transaction.transactionCount) ,interest,
					LocalDate.now(),"OD_INTEREST","SUCCESS"),true);
		}
	}
	
	@Override
	public String toString() {
		return super.toString()+"CurrentAccount [overdraftLimit=" + overdraftLimit + ", overdraftInterestRate=" + overdraftInterestRate
				+ ", businessName=" + businessName + ", gstNumber=" + gstNumber + "]";
	}
	
	
}

//-------------------------------------------------------------------------------------------------------
// Salary Account
//-------------------------------------------------------------------------------------------------------

class SalaryAccount extends BankAccount
{
	double salaryAmount;
	Date salaryCreditDate;
	String accountStatus;
	Date lastTransactionDate;
	boolean isFrozen = false;
	
	public SalaryAccount(String accHolderName, long accountNumber, String branchName, double balance, String accountType,
			String mobileNo, String email, String aadharNo, String panNo, double salaryAmount, Date localDate,
			String accountStatus, Date Date2) 
	{
		super(accHolderName, accountNumber, branchName, balance, accountType, mobileNo, email, aadharNo, panNo);
		
		this.salaryAmount = salaryAmount;
		this.salaryCreditDate = localDate;
		this.accountStatus = accountStatus;
		this.lastTransactionDate = Date2;
	}
	
	/*public SalaryAccount(String string, long l, String string2, double d, String string3, String string4,
			String string5, String string6, double e, double f, String string7, String string8, String string9,
			String string10, String string11, String string12, String string13, String string14, String string15,
			String string16, boolean b, String string17) {
		// TODO Auto-generated constructor stub
	}*/

	//Check the last transaction date if no transaction in 60 days account should be frozen due to inactivity
	
	private void checkAndFreezeIfInactive()
	{
		if(isFrozen)
		{
			return;//already frozen
		}
		
		if(lastTransactionDate == null)
		{
			lastTransactionDate = createDate;
		}
		
		long diffInMillis = new Date().getTime() - lastTransactionDate.getTime();
		long daysInactive = diffInMillis / (1000 * 60 * 60 * 24);
		
		if(daysInactive > 60)
		{
			isFrozen = true;
			accountStatus = "Frozen - due to inactivity of 60+ days";
			System.out.println("Salary Account: " + accountNumber + "is Frozen due to inactivity of 60+ days");
		}
	}
	
	void unfreeze()
	{
		if(isFrozen)
		{
			isFrozen = false;
			accountStatus = "ACTIVE";
			System.out.println("Salary Account: " + accountNumber + "has UNFREEZED");
		}
	}
	
	void creditSalary()
	{
		unfreeze();
		deposit(salaryAmount);
		System.out.println("Salary" + salaryAmount + "credited Successfully!!");
	}
	
	boolean deposit(double amount)
	{
		checkAndFreezeIfInactive();
		
		if(isFrozen && !"SALARY_CREDIT".equals(Thread.currentThread().getStackTrace()[2].getMethodName()))
		{
			recordTransaction (new Transaction("Transaction" + (++Transaction.transactionCount),amount, LocalDate.now(),
					"CREDIT","FAILED"),false);
			
			System.out.println("Cannot Deposit. Your account is frozen due to inactivity.");
			
			return false;
		}
		
		if(amount <= 0)
		{
			recordTransaction(new Transaction("Transaction" + (++Transaction.transactionCount),amount,
					LocalDate.now(),"CREDIT","FAILED"),false);
			
			return false;
		}
		
		balance = balance + amount;
		lastTransactionDate = new Date();
		unfreeze();
		recordTransaction(new Transaction("Transaction" + (++Transaction.transactionCount),amount,
				LocalDate.now(),"CREDIT","SUCCESS"),true);
		
		return true;
	}
	
	boolean withdraw(double amount)
	{
		checkAndFreezeIfInactive();
		
		if(isFrozen)
		{
			recordTransaction(new Transaction ("Transacction" + (++Transaction.transactionCount),amount, LocalDate.now(),
					"DEBIT","FAILED"),false);
			
			System.out.println("DEBIT Failed! Account is frozen due to inactivity.");
			return false;	
		}
		if(amount <= 0 || amount > balance)
		{
			System.out.println("coming Inside amount exceed"+amount+"current transaction count"+Transaction.transactionCount);
			
			recordTransaction (new Transaction ("Transaction" + (++Transaction.transactionCount),amount,
					LocalDate.now(),"DEBIT","FAILED"),false);
			
			return false;
		}
		
		balance -= amount;
		lastTransactionDate = new Date();
		recordTransaction(new Transaction ("Transaction" + (++Transaction.transactionCount),amount,
				LocalDate.now(),"DEBIT","SUCCESS"),true);
		
		return true;
	}
	
	String getAccountDetails()
	{
		checkAndFreezeIfInactive();
		
		return "[SALARY] AccNo: " + accountNumber + "\nName: " + accHolderName + "\nBalance: " + balance 
				+"\nSalary: " + salaryAmount + "\nStatus: "+ accountStatus + "\nBranch: " + branchName;
	}
	
	void applyInterest()
	{
		checkAndFreezeIfInactive();
		
		System.out.println("Coming inside the apply Interest");
		
		if(isFrozen)
		{
			System.out.println("Account is Frozen. No Interest Rate will be applied!!");
			return;
		}
		
		double interest = (balance * interestRate) / 100.0;
		balance += interest;
		lastTransactionDate = new Date();
		
		recordTransaction(new Transaction("Transaction" + (++Transaction.transactionCount),interest,
				LocalDate.now(),"INTEREST","SUCCESS"),true);
		
	}
	
	@Override
	public String toString() {
		return super.toString()+"SalaryAccount [salaryAmount=" + salaryAmount + ", salaryCreditDate=" + salaryCreditDate
				+ ", accountStatus=" + accountStatus + ", lastTransactionDate=" + lastTransactionDate + ", isFrozen="
				+ isFrozen + "]";
	}
	
}

//-------------------------------------------------------------------------------------------------------
// Loan Account
//-------------------------------------------------------------------------------------------------------

//-------------------------------------------------------------------------------------------------------
//Loan Account
//-------------------------------------------------------------------------------------------------------

class LoanAccount extends BankAccount
{
	int loanId;
	double loanAmount;
	String loanType;
	double loanInterestRate;
	int tenureMonths;
	Date issueDate;
	Date dueDate;
	String status;
	double outStandingAmount;
	Date lastPaymentDate;
	int loanCounter = 0;
	//static int loanCount;
	
	LoanAccount(String accHolderName, long accountNumber, String branchName, String accountType,
            String mobileNo, String email, String aadharNo, String panNo,
            double loanAmount, String loanType,
            double loanInterestRate, int tenureMonths) 
	
	{
		super(accHolderName, accountNumber, branchName,tenureMonths,"LOAN", mobileNo,email,aadharNo,panNo);
		
		this.loanId = ++loanCounter;
		this.loanAmount = loanAmount;
		this.loanType = loanType;
		this.loanInterestRate = loanInterestRate;
		this.tenureMonths = tenureMonths;
		this.outStandingAmount = loanAmount;
		this.issueDate = new Date();
		//LoanAccount.loanCount++;
	}
	
	boolean deposit(double amount)
	{
		if(amount <= 0 || amount > outStandingAmount)
			return false;
		
		outStandingAmount -= amount;
		balance = balance + amount;
		lastPaymentDate = new Date();
		if(outStandingAmount <= 0)
			status = "CLOSED";
		
		recordTransaction (new Transaction("Transaction" + (++Transaction.transactionCount),amount,
				LocalDate.now(),"LOAN_PAYMENT","SUCCESS"),true);
		
		return true;
	}
	
	boolean withdraw(double amount)
	{
		System.out.println("Withdrawal is not allowed for Loan Accounts!!");
		
		return false;
	}
	
	String getAccountDetails()
	{
		return "[LOAN] AccNo:"+ accountNumber + "\nHolder:" + accHolderName+ "\nLoan:" + loanAmount +
				"\nOutstanding:" + outStandingAmount + "\nType:" + loanType; 
	}
	
	void applyInterest()
	{
		double interest = outStandingAmount * (loanInterestRate / 100.0);
		outStandingAmount = outStandingAmount + interest;
		
		recordTransaction (new Transaction("Transaction" + (++Transaction.transactionCount),interest , LocalDate.now(),
				"LOAN_INTEREST","SUCCESS"),true);
	}
	
}


//Bank DAO
//
class BankDAO
{
	
	static BankAccount arr[];
	static int count;
	static double dTotalDeposit;
	static double dTotalWithdraw;
	static int deleteCount;
	static int frozenAccount;
	static int salaryAccCount;
	static int savingAccCount;
	static int currentAccCount;
	static int loanAccCount;
	//static int accountCount;
	
	static
	{
		dTotalDeposit = 0;
		dTotalWithdraw = 0;
		
		arr = new BankAccount[25];
		
		arr[count++] = new LoanAccount("Sneha Patil",100000011L,"Mumbai Branch","LOAN","9876543220","sneha@gmail.com","2345-6789-0123","BCDEA1234F",500000.00,"CAR",7.5,120);
		arr[count++] = new SavingAccount("Rohan Joshi",100000003L,"Delhi Branch",55000.00,"9876543212","rohan@gmail.com","3456-7890-1234","CDEAB1234F",8000.00,20000.00,40000.00);
		arr[count++] = new SalaryAccount("Ananya Kulkarni",100000007L,"Pune Branch",50000.00,"SALARY","9876543216","ananya@gmail.com","7890-1234-5678","ABCDE5678F",45000.00,null, "ACTIVE", null);
		arr[count++] = new CurrentAccount("Vikram Reddy",100000006L,"Delhi Branch",70000.00,"CURRENT","9876543215","vikram@gmail.com","6789-0123-4567","FABCD1234E",150000.00,11.0,"Reddy Traders","27FABCD1234E1Z5");
		arr[count++] = new LoanAccount("Rohan Joshi",100000012L,"Delhi Branch","LOAN","9876543221","rohan@gmail.com","3456-7890-1234","CDEAB1234F",400000.00,"EDUCATION",6.5,60);
		arr[count++] = new CurrentAccount("Priya Deshmukh",100000005L,"Mumbai Branch",95000.00,"CURRENT","9876543214","priya@gmail.com","5678-9012-3456","EABCD1234F",250000.00,10.0,"Deshmukh Enterprises","27EABCD1234F1Z5");
		arr[count++] = new SalaryAccount("Meera Joshi",100000009L,"Delhi Branch",45000.00,"SALARY","9876543218","meera@gmail.com","9012-3456-7890","CDEAB5678F",40000.00, null, null, null);
		arr[count++] = new SavingAccount("Sneha Patil",100000002L,"Mumbai Branch",60000.00,"9876543211","sneha@gmail.com","2345-6789-0123","BCDEA1234F",10000.00,30000.00,60000.00);
	
		dTotalDeposit = 375000;
		loanAccCount += 2;
		savingAccCount += 2;
		currentAccCount += 2;
		salaryAccCount += 2;
}
	
    //Bank bobj = new Bank();

    //1.Add the Account
    boolean addAccount(BankAccount acc)
    {
        boolean bRet = false;
        arr[BankDAO.count++] = acc;
        bRet = true;
        
        if(acc instanceof LoanAccount)
        	BankDAO.loanAccCount++;
        
        if(acc instanceof SavingAccount)
        	BankDAO.savingAccCount++;
        
        if(acc instanceof SalaryAccount)
        	BankDAO.salaryAccCount++;
        
        if(acc instanceof CurrentAccount)
        	BankDAO.currentAccCount++;
        
        return bRet;
    }
    //2.Search the Account
    boolean searchAccount(long accountNumber)
    {
        boolean bRet = false;
        for(int i = 0; i < BankDAO.count; i++)
        {
            if(BankDAO.arr[i].accountNumber == accountNumber)
            {
                bRet = true;
                break;
            }
        }
        return bRet;
    } 
    //3.Display Specific Account
    void displayAccount(long accountNumber)
    {
        for(int i = 0; i <= BankDAO.count; i++)
        {
            if(BankDAO.arr[i].accountNumber == accountNumber)
            {
            	System.out.println(BankDAO.arr[i]);
            	break;
            }
            
        }
    }

    //4.Update method
    //starts here
    boolean updateAccountName(long accountNumber,String name)//completed
    {
        boolean bRet = false;
        for(int i = 0; i < BankDAO.count; i++)
        {
            if(BankDAO.arr[i].accountNumber == accountNumber)
            {
            	BankDAO.arr[i].setaccHolderName(name);
            	bRet = true;
            	break;
            }
        }
        return bRet;
    }//ends here
    
    boolean updateAccountEmail(long accountNumber,String email)
    {
        boolean bRet = false;
        for(int i = 0; i < BankDAO.count; i++)
        {
            if(BankDAO.arr[i].accountNumber == accountNumber)
            {
            	BankDAO.arr[i].setemail(email);
            	bRet = true;
            	break;
            }
        }
        return bRet;
    }
    
    boolean updateAccountMobileNo(long accountNumber,String mobileNo)
    {
        boolean bRet = false;
        for(int i = 0; i < BankDAO.count; i++)
        {
            if(BankDAO.arr[i].accountNumber == accountNumber)
            {
            	BankDAO.arr[i].setmobileNo(mobileNo);
            	bRet = true;
            	break;
            }
        }
        return bRet;
    }

    //5.Delete method
    //starts here
    boolean deleteAccount( long accountNumber)
    {
        boolean bRet = false;
        for(int i = 0; i < BankDAO.count; i++)
        {
            if(BankDAO.arr[i].accountNumber == accountNumber)
            {
                bRet = true;
                while(i < BankDAO.count-1)
                {
                	BankDAO.arr[i] = BankDAO.arr[i+1];
                	i++;
                }
                BankDAO.count--;
                break;
            }
        }
        return bRet;
    }//ends here

    //6.Add the transaction
    //starts here
    boolean addTransaction(long accountNumber,String transactionId, double amount, LocalDate date, String type, String status)
    {
        boolean bRet = false;
        for(int i = 0; i < BankDAO.count; i++)
        {
            if(BankDAO.arr[i].accountNumber == accountNumber)
            {
            	BankDAO.arr[i].transactions[BankDAO.arr[i].transactionCount++] = new Transaction( transactionId,  amount,  date,  type,  status);
                bRet = true;
                break;
            }
        }

        return bRet;

    }//ends here


    //7.Display all records
    //starts here
    void displayAllRecords()
    {
        for(int i = 0; i < BankDAO.count; i++)
        {
        	System.out.println(BankDAO.arr[i]);
        	System.out.println();
        }
    }//ends here


    //8.Deposit method
    //starts here
    boolean deposit(long accountNumber,double amount)
    {
        boolean bRet = false;
        for(int i = 0; i < BankDAO.count; i++)
        {
            if(BankDAO.arr[i].accountNumber == accountNumber)
            {
            	bRet = BankDAO.arr[i].deposit(amount);
            	if(bRet == true)
            		BankDAO.dTotalDeposit = BankDAO.dTotalDeposit + amount;
            		
            }
        }
        return bRet;
    }

    //9.Withdraw methods
    boolean withdraw(long accountNumber,double amount)
    {
        boolean bRet = false;
        for(int i = 0; i < BankDAO.count; i++)
        {
            if(BankDAO.arr[i].accountNumber == accountNumber)
            {
             
            	bRet = BankDAO.arr[i].withdraw(amount);
            	if(bRet == true)
            		BankDAO.dTotalWithdraw = BankDAO.dTotalWithdraw + amount;
                
            }
        }

        return bRet;
    }
    
    //10.checkIntrest Rate
    void applyIntrestRate(long accountNumber)
    {
    	//double checkedIntrest = 0;
    	for(int i = 0; i < BankDAO.count; i++)
    	{
    		if(BankDAO.arr[i].accountNumber == accountNumber)
    		{
    			BankDAO.arr[i].applyInterest();
    			break;
    		}
    	}
    	//return checkedIntrest;
    }
    //11.Display all transaction for specific record
    void DisplayTransaction(long accountno)
    {
    	for(int i = 0; i < BankDAO.count; i++)
    	{
    		if(BankDAO.arr[i].accountNumber == accountno)
    		{
    			System.out.println(BankDAO.arr[i].transactionCount);
    			for(int j = 0; j < BankDAO.arr[i].transactionCount; j++)
    			{
    				System.out.println(BankDAO.arr[i].transactions[j]);
    			}
    			break;
    		}
    	
    	}
    }
    
    static //12.generate eod
    String   generateeod()
    {
    	//System.out.println("Transaction summary for the day:");
    	//System.out.println("Opening balance:"+BankDAO.dTotalDeposit);
    	//System.out.println("Closing balance:"+BankDAO.dTotalWithdraw);
    	//System.out.println("Total acount open:"+BankDAO.count);
    	//System.out.println("Total acount Frozen:");
    	//System.out.println("Total salary account:"+BankDAO.salaryAccCount);
    	//System.out.println("Total saving account:"+BankDAO.savingAccCount);
    	//System.out.println("Total current account:"+BankDAO.currentAccCount);
    	//System.out.println("Total Loan account:"+BankDAO.loanAccCount);
    	//System.out.println("!!!Thank You!!!");
    	
    	return "Transaction summary for the day:"+"\nOpening balance:"+BankDAO.dTotalDeposit+
    			"\nClosing balance:"+BankDAO.dTotalWithdraw+"\nTotal acount open:"+BankDAO.count+
    			"\nTotal salary account:"+BankDAO.salaryAccCount+"\nTotal saving account:"+BankDAO.savingAccCount+
    			"\nTotal current account:"+BankDAO.currentAccCount+"\nTotal Loan account:"+BankDAO.loanAccCount;
    	
    }
	@Override
	public String toString() {
		return "BankDAO []";
	}
}// class  * BankDAO*ends here


//class Bankcontroller 
//starts here
class BankController 
{
    
    BankDAO bao = new BankDAO();
    
    
    //1.open account controller  COMPLETED
    boolean openAccount(BankAccount acc)
    {
        boolean bRet = false;
        bao.addAccount(acc);
        bRet = true;
        
        return bRet;

    }

    //2.Search Account     COMPLETED
    boolean searchBankAccountbyNo(long accountNo)
    {
        boolean bRet = false;
        bRet = bao.searchAccount(accountNo);
        return bRet;
    }

    //3.Display Account		//COMPLETED		
    void displayAccout(long accountNo)
    {
    	bao.displayAccount(accountNo);
    	//System.out.println(accountNo);
    }

    //4.Update the Account  //COMPLETED
    boolean updateAccountName(long accountNo,String name)
    {
        boolean bRet = false;
        bRet = bao.updateAccountName(accountNo,name);
        return bRet;
    }
    
    boolean updateAccountMobileNo(long accountNo,String mobno)//COMPLETED
    {
        boolean bRet = false;
        bRet = bao.updateAccountMobileNo(accountNo,mobno);
        return bRet;
    }
    
    boolean updateAccountEmail(long accountNo,String email)//COMPLETED
    {
        boolean bRet = false;
        bRet = bao.updateAccountEmail(accountNo,email);
        return bRet;
    }

    //5.Delete the Account
    boolean deleteAccount(long accountNo) // COMPLETED
    {
        boolean bRet = false;
        bRet = bao.deleteAccount(accountNo);
        return bRet;
    }

   

    //6.Display all records   //completed
    void displayAllRecords()
    {
    	bao.displayAllRecords();
    }

    //8.deposit method controller // successfull transaction is considering only
    boolean deposit(long accountNo,double amount)
    {
        boolean bRet = false;
        bRet = bao.deposit(accountNo,amount);
        return bRet;
    }


    //9.generate end of the day changes are required
    String generateEndOfRecords()
    {
        //count of total transaction,total deposit,total withdraw,total account opened
    	return BankDAO.generateeod();
    	
    }
    //10.withdraw money      												Completed
    boolean withdrawCash(long accountNumber,double amount)
    {
        boolean bRet = false;
        bRet = bao.withdraw(accountNumber, amount);
        return bRet;
    }
    
  
    //11.Display all transaction for specific record			//completed
    void DisplayTransaction(long accountNumber)
    {
    	bao.DisplayTransaction(accountNumber);
    }
    //12.Apply interest
    void applyIntrest(long accountNumber)
    {
    	bao.applyIntrestRate(accountNumber);
    }
    
    



}
//ends here




class BankView {

    private BankController controller;
    private Scanner sc = new Scanner(System.in);

    public BankView(BankController controller) {
        this.controller = controller;
    }

    public void start() {

        int choice;

        do {
            System.out.println("\n========= XYZ BANK MENU =========");
            System.out.println("1. Open New Account");
            System.out.println("2. Search Account");
            System.out.println("3. Deposit Money");
            System.out.println("4. Withdraw Money");
            System.out.println("5. Display Account Details");
            System.out.println("6. Display All Accounts");
            System.out.println("7. Apply Interest");
            System.out.println("8. Display Transactions");
            System.out.println("9. Delete Account");
            System.out.println("10. End Of Day Report");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {

                /* ================= OPEN ACCOUNT ================= */
                case 1:
                    openAccount();
                    break;

                /* ================= SEARCH ACCOUNT ================= */
                case 2:
                    searchAccount();
                    break;

                /* ================= DEPOSIT ================= */
                case 3:
                    depositMoney();
                    break;

                /* ================= WITHDRAW ================= */
                case 4:
                    withdrawMoney();
                    break;

                /* ================= DISPLAY ACCOUNT ================= */
                case 5:
                    displayAccount();
                    break;

                /* ================= DISPLAY ALL ================= */
                case 6:
                    controller.displayAllRecords();
                    break;

                /* ================= APPLY INTEREST ================= */
                case 7:
                    applyInterest();
                    break;

                /* ================= TRANSACTIONS ================= */
                case 8:
                    displayTransactions();
                    break;

                /* ================= DELETE ================= */
                case 9:
                    deleteAccount();
                    break;

                /* ================= EOD ================= */
                case 10:
                    generateEndOfRecords();
                    break;

                case 0:
                    System.out.println("Thank you for banking with XYZ Bank!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);
    }

    /* ===================================================== */

    private void openAccount() {

        System.out.println("\nSelect Account Type:");
        System.out.println("1. Savings Account");
        System.out.println("2. Salary Account");
        System.out.println("3. Current Account");
        System.out.println("4. Loan Account");
        System.out.print("Choice: ");

        int type = sc.nextInt();
        sc.nextLine();

        System.out.print("Account Number: ");
        long accNo = sc.nextLong();
        sc.nextLine();

        System.out.print("Account Holder Name: ");
        String name = sc.nextLine();

        System.out.print("Branch Name: ");
        String branch = sc.nextLine();

        System.out.print("Mobile Number: ");
        String mobile = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Aadhar Number: ");
        String aadhar = sc.nextLine();

        System.out.print("PAN Number: ");
        String pan = sc.nextLine();

        BankAccount account = null;

        switch (type) {

            case 1: // SAVINGS
                System.out.print("Initial Balance (>=10000): ");
                double sbal = sc.nextDouble();
                account = new SavingAccount(
                        name, accNo, branch, sbal,
                        mobile, email, aadhar, pan,
                        10000, 25000, 50000
                );
                break;

            case 2: // SALARY
                System.out.print("Initial Balance: ");
                double salBal = sc.nextDouble();
                System.out.print("Monthly Salary: ");
                double salary = sc.nextDouble();
                account = new SalaryAccount(
                        name, accNo, branch, salBal, "SALARY",
                        mobile, email, aadhar, pan,
                        salary, null, "ACTIVE", null
                );
                break;

            case 3: // CURRENT
                System.out.print("Initial Balance: ");
                double cbal = sc.nextDouble();
                System.out.print("Overdraft Limit: ");
                double od = sc.nextDouble();
                account = new CurrentAccount(
                        name, accNo, branch, cbal, "CURRENT",
                        mobile, email, aadhar, pan,
                        od, 10.0, "Business", "GST123"
                );
                break;

            case 4: // LOAN
                System.out.print("Loan Amount: ");
                double loanAmt = sc.nextDouble();
                account = new LoanAccount(
                        name, accNo, branch, "LOAN",
                        mobile, email, aadhar, pan,
                        loanAmt, "GENERAL", 8.0, 60
                );
                break;

            default:
                System.out.println("Invalid Account Type!");
                return;
        }

        if (controller.openAccount(account))
            System.out.println("Account opened successfully!");
        else
            System.out.println("Account creation failed!");
    }

    void searchAccount() {
        System.out.print("Enter Account Number: ");
        long accNo = sc.nextLong();
        if (controller.searchBankAccountbyNo(accNo))
            System.out.println("Account exists.");
        else
            System.out.println("Account not found.");
    }

    void depositMoney() {
        System.out.print("Account Number: ");
        long accNo = sc.nextLong();
        System.out.print("Amount: ");
        double amt = sc.nextDouble();

        if (controller.deposit(accNo, amt))
            System.out.println("Deposit successful.");
        else
            System.out.println("Deposit failed.");
    }

    void withdrawMoney() {
        System.out.print("Account Number: ");
        long accNo = sc.nextLong();
        System.out.print("Amount: ");
        double amt = sc.nextDouble();

        if (controller.withdrawCash(accNo, amt))
            System.out.println("Withdrawal successful.");
        else
            System.out.println("Withdrawal failed.");
    }

    void displayAccount() {
        System.out.print("Account Number: ");
        long accNo = sc.nextLong();
        controller.displayAccout(accNo);
    }

    void applyInterest() {
        System.out.print("Account Number: ");
        long accNo = sc.nextLong();
        controller.applyIntrest(accNo);
        System.out.println("Interest applied.");
    }

    void displayTransactions() {
        System.out.print("Account Number: ");
        long accNo = sc.nextLong();
        controller.DisplayTransaction(accNo);
    }
    

    void deleteAccount() {
        System.out.print("Account Number: ");
        long accNo = sc.nextLong();
        if (controller.deleteAccount(accNo))
            System.out.println("Account deleted.");
        else
            System.out.println("Account not found.");
    }
    
    void generateEndOfRecords()
    {
    	//String sobj = controller.generateEndOfRecords();
    	//String h = "Hello";
    	//System.out.println(h);
    	System.out.println(controller.generateEndOfRecords());
    }
}
class BankMaain {
    public static void main(String[] args) {

        // Controller
        BankController controller = new BankController();

        // View
        BankView view = new BankView(controller);

        // Start Application
        view.start();
    }
}