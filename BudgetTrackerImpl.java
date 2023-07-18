package budgetracker;

import java.sql.*;
import java.sql.Statement; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BudgetTrackerImpl implements BudgetTrackerInterface{

    private Connection connection;

    public BudgetTrackerImpl(Connection connection) {
        this.connection = connection;
      
        
        
    }

    @Override
    public void addIncome(double amount, String description,String User) {
        try {
        	String user=User;
        	String username=User;
        	Statement stmt=connection.createStatement();


            PreparedStatement statement = connection.prepareStatement("insert into "+ user +"(amount, description, type) VALUES (?, ?, 'income')");
            statement.setDouble(1, amount);
            statement.setString(2, description);
            
            statement.executeUpdate();
            System.out.println("Income added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addExpense(double amount, String description,String user) {
        try {
        	
        	
            PreparedStatement statement = connection.prepareStatement("INSERT INTO "+ user +"(amount, description, type) VALUES (?, ?, 'expense')");
            statement.setDouble(1, amount);
            statement.setString(2, description);
            statement.executeUpdate();
            System.out.println("Expense added successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to add expense: " + e.getMessage());
        }
    }

    @Override
    public double getBalance(String user) {
        double incomeTotal = 0;
        double expenseTotal = 0;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT amount, type FROM "+ user);

            while (resultSet.next()) {
                double amount = resultSet.getDouble("amount");
                String type = resultSet.getString("type");
                if (type.equals("income")) {
                    incomeTotal += amount;
                } else if (type.equals("expense")) {
                    expenseTotal += amount;
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to get balance: " + e.getMessage());
        }

        return incomeTotal - expenseTotal;
    }
    public void deleteTransaction(String del,String user)
    {
    	try
    	{
    		  Statement s = connection.createStatement();
    	 
    		String booleanString = "SELECT description  FROM "+ user+" WHERE (description ='" +del + "')";
                 
                s.execute(booleanString);
                ResultSet resultSet = s.getResultSet();
                boolean recordFound = resultSet.next();
                if (recordFound) {
       String  t = "DELETE FROM "+user+" WHERE (description='" + del + "')";
         s.executeUpdate(t);
         System.out.println("Deleted record sucessfully ... ");
        
    }
    	
    	else
    	{
    		System.out.println("Sorry,no such transaction found !!!");
    	}
    	}
    	catch(Exception e)
    	{
    		System.out.println("Failed to delete the record : "+e.getMessage());
    	}
    }

    @Override
    public Transaction[] getTransactionHistory(String user) {
        List<Transaction> transactions = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM "+ user);

            while (resultSet.next()) {
                double amount = resultSet.getDouble("amount");
                String description = resultSet.getString("description");
                String type = resultSet.getString("type");
                Transaction transaction = new Transaction(amount, description, type);
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get transaction history: " + e.getMessage());
        }

        return transactions.toArray(new Transaction[0]);
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Failed to close database connection: " + e.getMessage());
        }
    }

}
