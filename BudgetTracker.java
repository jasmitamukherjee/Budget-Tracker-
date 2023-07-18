package budgetracker;

	import java.sql.*;
import java.util.Scanner;
	
	
	public class BudgetTracker 
	{
		
	    public void log(String User) {
	    	
	        Scanner scanner = new Scanner(System.in);
	      String user=User;

	        // Initialize database connection
	        Connection connection = null;
	        
	         
	        try {
	        	
	        	//String sql = "CREATE DATABASE transactions";
	        	connection = DriverManager.getConnection("jdbc:mysql://localhost/budgettracker", "root", "Jasmita2002@");
	            
	        	//Statement stmt = connection.createStatement();
	           // stmt.executeUpdate(sql);
	            System.out.println("Database created successfully...");   
	            
	            Statement stmt = connection.createStatement();
	        	//String sql = "CREATE TABLE transactions (amount integer,description varchar(20),type varchar(20)) " ;
	                    

	          //stmt.executeUpdate(sql);
	          System.out.println("Created table in given database...");
	            
	            	
	            
	        } catch (SQLException e) {
	            System.out.println("Failed to connect to database: " + e.getMessage());
	            System.exit(1);
	        }

	        
	        BudgetTrackerImpl budgetTracker = new BudgetTrackerImpl(connection);
	        
	        

	       
	       while(true) {
	            System.out.println("\nMenu:");
	            System.out.println("1. Add income");
	            System.out.println("2. Add expense");
	            System.out.println("3. View balance");
	            System.out.println("4. View transaction history");
	            System.out.println("5. Delete an entry");
	            System.out.println("6. Exit");
	            System.out.print("Please select an option: ");

	            int option = scanner.nextInt();
	            scanner.nextLine();

	            switch (option) {
	                case 1:
	                    System.out.print("Please enter the amount of income: ");
	                    double incomeAmount = scanner.nextDouble();
	                    scanner.nextLine(); 
	                    System.out.print("Please enter a description for the income: ");
	                    String incomeDescription = scanner.nextLine();
	                    budgetTracker.addIncome(incomeAmount, incomeDescription,user);
	                    break;
	                case 2:
	                    System.out.print("Please enter the amount of expense: ");
	                    double expenseAmount = scanner.nextDouble();
	                    scanner.nextLine(); 
	                    System.out.print("Please enter a description for the expense: ");
	                    String expenseDescription = scanner.nextLine();
	                    if(budgetTracker.getBalance(user)<expenseAmount)
	                    	System.out.println("Cannot add expense, balance is low !!");
	                    else
	                    budgetTracker.addExpense(expenseAmount, expenseDescription,user);
	                    break;
	                case 3:
	                    double balance = budgetTracker.getBalance(user);
	                    
	                    System.out.println("Current balance: Rs "+ balance);
	                    break;
	                case 4:
	                    Transaction[] transactions = budgetTracker.getTransactionHistory(user);
	                    System.out.println("Transaction history:");
	                    for (Transaction transaction : transactions) {
	                        System.out.println(transaction.getDescription()+"-Rs. "+ transaction.getAmount()+"->"+transaction.getType());
	                    }
	                    break;
	                case 5 :
	                	System.out.println("Transaction history:");
	                	 Transaction[] T = budgetTracker.getTransactionHistory(user);
	                	System.out.println("Transaction history:");
	                    for (Transaction transaction : T) {
	                    	 System.out.println(transaction.getDescription()+"-Rs. "+ transaction.getAmount()+"->"+transaction.getType());
	                    }
	                	System.out.print("\nEnter the description of the record you want to remove : ");
	                	
	                	String del=scanner.next();
	                	budgetTracker.deleteTransaction(del,user);
	                	
	                	
	                	break;
	                	
	                case 6:
	                    budgetTracker.close();
	                    System.out.println("EXITING ... ");
	                   new Login().main(null);
	                    break;
	                    
	                   // System.exit(0);
	                default:
	                    System.out.println("Invalid option selected.");
	            }
	        }
	    }
	}

	
