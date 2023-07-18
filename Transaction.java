package budgetracker;

public class Transaction {

private double amount;

private String description;

private String type;

public Transaction(double amount, String description, String type) {

this.amount = amount;

this.description = description;

this.type = type;

}

public double getAmount() {

return amount;

}

public void setAmount(double amount) {

this.amount = amount;

}

public String getDescription() {

return description;

}

public void setDescription(String description) {

this.description = description;

}

public String getType() {

return type;

}

public void setType(String type) {

this.type = type;

}

}
