package budgetracker;
import java.sql.*;
import java.util.*;
public class Login extends BudgetTracker {
String user;
Login()
{
	
}
	Login(String username)
	{
		user=username;
		super.log(user);
	}

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/budgettracker";

    
    static final String USER = "root";
    static final String PASS = "Jasmita2002@";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BudgetTracker bt=new BudgetTracker();
       
      int choice;
        System.out.println("Welcome to the Budget Tracker!");
        while(true) {
        System.out.println("Do you want to continue? (1) for yes and (2) for no");
        choice=scanner.nextInt();
        scanner.nextLine();
        if(choice==2)
        {
        	System.out.println("EXITING BUDGET TRACKER");
        	System.exit(0);
        }
        else {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        Connection conn = null;
        Statement stmt = null;
        
        try {
            
            Class.forName(JDBC_DRIVER);

            
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

           
            stmt = conn.createStatement();

            // Check if the user exists
            String checkUser = "SELECT * FROM Users WHERE username='" + username + "'";
            ResultSet rs = stmt.executeQuery(checkUser);

            if (rs.next()) {
                // Existing user
                
                if (rs.getString("password").equals(password)) {
                    System.out.println("Login successful!");
                   
                    System.out.println("Your budget information:");
                   
                    
                    Login login=new Login(username);
                    
                } else {
                    System.out.println("Incorrect password. Please try again.");
                }
            } else {
                // New user
                // table create karna hai
            	  
//                String createUserTable = "CREATE TABLE " + username +
//                                         "(id INTEGER not NULL AUTO_INCREMENT, " +
//                                         " date DATE, " +
//                                         " description VARCHAR(255), " +
//                                         " amount DOUBLE, " +
//                                         " PRIMARY KEY ( id ))";
//
//                stmt.executeUpdate(createUserTable);
                String insertUser = "INSERT INTO Users (username, password) VALUES ('" +
                        username + "', '" + password + "')";

                	stmt.executeUpdate(insertUser);
               	 String createUser = "CREATE TABLE " +username+"(amount double,description VARCHAR(100),type VARCHAR(10))";
                 

             	 stmt.executeUpdate(createUser);


                
             	
                
               
                System.out.println("Account created! You can now login.");
                
                Login login=new Login(username);

               
            }

          
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //jdbc excp
            se.printStackTrace();
        } catch (Exception e) {
            // class ka exception
            e.printStackTrace();
        } finally {
            // resource close WHY NOT WORKING  
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            	se2.printStackTrace();
            } 
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } 
        } 

       
    }
        }
}
}

