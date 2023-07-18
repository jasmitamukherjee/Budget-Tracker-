package budgetracker;



public interface BudgetTrackerInterface {

    void addIncome(double amount, String description,String user);

    void addExpense(double amount, String description,String user);
    
    void deleteTransaction(String description,String user);

    double getBalance(String user);

    Transaction[] getTransactionHistory(String user);

}
