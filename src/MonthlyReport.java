import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {
    HashMap <String,ArrayList<Month>> reportMonth = new HashMap();
    public void loadFile (String nameMonth ,String path){
        String content = readFileContents(path);
        String[] lines = content.split("\r?\n");
        ArrayList <Month> objMonth = new ArrayList<>();
        for (int i=1; i< lines.length; i++){
            String line= lines[i];
            String [] parts = line.split(",");
            String itemName = parts[0];
            boolean isExpense = Boolean.parseBoolean(parts[1]);
            int quantity = Integer.parseInt(parts[2]);
            int sumOfOne = Integer.parseInt(parts[3]);
            Month month = new Month(itemName, isExpense, quantity, sumOfOne);
            objMonth.add(month);
        }
        reportMonth.put(nameMonth, objMonth);
    }
    public String readFileContents(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            return null;
        }
    }

    public void topSale (String nameMonth){
        ArrayList <Month> objMonth = reportMonth.get(nameMonth);
        HashMap <String,Integer> sales = new HashMap<>();
        for (Month month : objMonth){
            if (month.isExpense == false){
                sales.put(month.itemName, sales.getOrDefault(month.itemName, 0) + (month.quantity*month.sumOfOne));
            }
        }
        String maxItemName = null;
        int maxItemSum = 0;
        for (String itemName : sales.keySet()) {
            if (maxItemName == null) {
                maxItemName = itemName;
                maxItemSum = sales.get(itemName);
                continue;
            }
            if (sales.get(maxItemName) < sales.get(itemName)) {
                maxItemName = itemName;
                maxItemSum = sales.get(itemName);
            }
        }
        System.out.println("Самый прибыльный товар - " + maxItemName);
        System.out.println("Сумма продаж данного товара - " + maxItemSum);
    }

    public void topSpending (String nameMonth){
        ArrayList <Month> objMonth = reportMonth.get(nameMonth);
        HashMap <String,Integer> spendings = new HashMap<>();
        for (Month month : objMonth){
            if (month.isExpense == true){
                spendings.put(month.itemName, spendings.getOrDefault(month.itemName, 0) + (month.quantity*month.sumOfOne));
            }
        }
        String maxItemName = null;
        int maxItemSum = 0;
        for (String itemName : spendings.keySet()) {
            if (maxItemName == null) {
                maxItemName = itemName;
                maxItemSum = spendings.get(itemName);
                continue;
            }
            if (spendings.get(maxItemName) < spendings.get(itemName)) {
                maxItemName = itemName;
                maxItemSum = spendings.get(itemName);
            }
        }
        System.out.println("Самая большая трата на товар - " + maxItemName);
        System.out.println("Сумма данной траты - " + maxItemSum);
    }
    public void printMonthlyReport (){
        for (String nameMonth : reportMonth.keySet()){
            System.out.println(nameMonth);
            topSale(nameMonth);
            topSpending(nameMonth);
        }
    }
    public int totalMonthlyIncome (String nameMonth){
        int sumSales = 0;
        ArrayList <Month> objMonth = reportMonth.get(nameMonth);
        HashMap <String,Integer> sales = new HashMap<>();
        for (Month month : objMonth){
            if (month.isExpense == false){
                sales.put(month.itemName, sales.getOrDefault(month.itemName, 0) + (month.quantity*month.sumOfOne));
            }
        }
        for (String itemName : sales.keySet()){
            sumSales+=sales.get(itemName);
        }
        return sumSales;
    }
    public int totalMonthlyExpenses (String nameMonth){
        int sumExpenses = 0;
        ArrayList <Month> objMonth = reportMonth.get(nameMonth);
        HashMap <String,Integer> expenses = new HashMap<>();
        for (Month month : objMonth){
            if (month.isExpense == true){
                expenses.put(month.itemName, expenses.getOrDefault(month.itemName, 0) + (month.quantity*month.sumOfOne));
            }
        }
        for (String itemName : expenses.keySet()){
            sumExpenses+=expenses.get(itemName);
        }
        return sumExpenses;
    }
}
