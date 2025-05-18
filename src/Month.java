public class Month {
    String name;
    boolean isExpense;
    int quantity;
    int unitPrice;

    Month(String [] lines){
        name = lines[0];
        isExpense = Boolean.parseBoolean(lines[1]);
        quantity = Integer.parseInt(lines[2]);
        unitPrice = Integer.parseInt(lines[3]);
    }
}
