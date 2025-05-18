import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {
    //про правила названий методов тож вспомнила тока под конец, я усталаааааа
    FileReader fileReader = new FileReader();
    HashMap<Integer,ArrayList<Month>> monthlyReportsHashmap = new HashMap<>();

    void saveMonthlyReport(){
        for (int i = 1; i <4; i++){
            ArrayList<String> reports = fileReader.readFileContents("m.20210" + i + ".csv");
            ArrayList<Month> months = new ArrayList<>();
            for (int j = 1; j<reports.size(); j++){
                String[] lineContents = reports.get(j).split(",");
                Month month = new Month(lineContents);
                months.add(month);
            }
            monthlyReportsHashmap.put(i, months);
        }
    };
    void maxIncome(int monthName){
        int max = 0;
        String name = "";
        ArrayList<Month> lines = monthlyReportsHashmap.get(monthName);
        for (Month month: lines){
            if (!month.isExpense){
                if ((month.unitPrice*month.quantity) > max){
                    max = month.unitPrice*month.quantity;
                    name = month.name;
                }
            }
        }
        System.out.println("Самый прибыльный товар - " + name + " Сумма - " + max);
    };

    void maxExpense(int monthName){
        int max = 0;
        String name = "";
        ArrayList<Month> lines = monthlyReportsHashmap.get(monthName);
        for (Month month: lines){
            if (month.isExpense){
                if ((month.unitPrice*month.quantity) > max){
                    max = month.unitPrice*month.quantity;
                    name = month.name;
                }
            }
        }
        System.out.println("Самая большая трата - " + name + " Сумма - " + max);
    };

    int sumIncome(int monthName){
        int sum = 0;
        ArrayList<Month> lines = monthlyReportsHashmap.get(monthName);
        for (Month month: lines){
            if (!month.isExpense) {
                sum += month.unitPrice*month.quantity;
            }
        }
        return sum;
    }

    int sumExpense(int monthName){
        int sum = 0;
        ArrayList<Month> lines = monthlyReportsHashmap.get(monthName);
        for (Month month: lines){
            if (month.isExpense) {
                sum += month.unitPrice*month.quantity;
            }
        }
        return sum;
    }

    void nameMonth(int monthName) {
        if (monthName == 1){
            System.out.println("Январь");
        } else if (monthName == 2){
            System.out.println("Февраль");
        } else {
            System.out.println("Март");
        }
    }

    void printInformationFromReports(){
        if (!validateReports()){
            return;
        }
        for (Integer key: monthlyReportsHashmap.keySet()){
            nameMonth(key);
            maxIncome(key);
            maxExpense(key);
        }
    }

    boolean validateReports() {
        if (monthlyReportsHashmap.isEmpty()){
            System.out.println("Отчеты не были считаны, пожалуйста, считайте файлы!");
            return false;
        }
        return true;
    }
}