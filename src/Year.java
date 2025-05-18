public class Year {
    int month;
    int amount;
    boolean isExpense;

    Year(String[] line){
        month = Integer.parseInt(line[0]);
        amount = Integer.parseInt(line[1]);
        isExpense = Boolean.parseBoolean(line[2]);
    }
}
