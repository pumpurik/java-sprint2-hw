import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReport {
    HashMap<String,ArrayList<Year>> reportYear = new HashMap<>();
    public void loadFile (String path){
        String content = readFileContents(path);
        String[] lines = content.split("\r?\n");
        String nameYear = path.substring(12,16);
        ArrayList<Year> years = new ArrayList<>();
        for (int i=1; i< lines.length; i++){
            String line= lines[i];
            String [] parts = line.split(",");
            int month = Integer.parseInt(parts[0]);
            int amount = Integer.parseInt(parts[1]);
            boolean isExpense = Boolean.parseBoolean(parts[2]);
            Year year = new Year(month,amount,isExpense);
            years.add(year);
        }
        reportYear.put(nameYear,years);
    }
    public String readFileContents(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            return null;
        }
    }
    public void profitByMonth (String nameYear){
        ArrayList <Year> objYear = reportYear.get(nameYear);
        HashMap<Integer, Integer> profitByMonth = new HashMap<>();
        for (Year year: objYear) {
            if (year.isExpense == false){
                profitByMonth.put(year.month, year.amount );
            }
        }
        for (Year year: objYear) {
            if (year.isExpense==true){
                profitByMonth.put(year.month, profitByMonth.get(year.month) - year.amount);
            }
        }
        for (Integer numberMonth: profitByMonth.keySet()){
            System.out.println("Номер месяца - " + numberMonth + "; прибыль - " + profitByMonth.get(numberMonth));
        }
    }
    public double averageConsumption (String nameYear){
        ArrayList <Year> objYear = reportYear.get(nameYear);
        HashMap<Integer, Integer> profitByMonth = new HashMap<>();
        for (Year year: objYear) {
            if (year.isExpense == true){
                profitByMonth.put(year.month, year.amount );
            }
        }
        double sum = 0;
        int count = 0;
        for (Integer numberMonth : profitByMonth.keySet()){
            sum+=profitByMonth.get(numberMonth);
            count++;
        }
        double averageSum = sum/count;
        return averageSum;
    }
    public double averageIncome (String nameYear){
        ArrayList <Year> objYear = reportYear.get(nameYear);
        HashMap<Integer, Integer> profitByMonth = new HashMap<>();
        for (Year year: objYear) {
            if (year.isExpense == false){
                profitByMonth.put(year.month, year.amount );
            }
        }
        double sum = 0;
        int count = 0;
        for (Integer numberMonth : profitByMonth.keySet()){
            sum+=profitByMonth.get(numberMonth);
            count++;
        }
        double averageSum = sum/count;
        return averageSum;
    }
    public void printYearlyReport (){
        for (String nameYear : reportYear.keySet()){
            System.out.println("Год - " + nameYear);
            profitByMonth(nameYear);
            System.out.printf("Средний расход за все месяцы в году - " + "%.2f", averageConsumption(nameYear));
            System.out.println("");
            System.out.printf("Средний доход за все месяцы в году - " + "%.2f", averageIncome(nameYear));
            System.out.println("");
        }
    }
}
