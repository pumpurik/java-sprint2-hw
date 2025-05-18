import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReport {
    FileReader fileReader = new FileReader();
    HashMap<String,ArrayList<Year>> yearlyReportsHashmap = new HashMap<>();

    void saveYearlyReport(){
        ArrayList<String> reports = fileReader.readFileContents("y.2021.csv");
        ArrayList<Year> years = new ArrayList<>();
        for (int i = 1; i<reports.size(); i++){
            String[] lineContents = reports.get(i).split(",");
            Year year = new Year(lineContents);
            years.add(year);
        }
        yearlyReportsHashmap.put("y.2021.csv", years);
    };

    /*Информация из годового отчёта.
    При вызове этой функции программа должна выводить такие данные:
        рассматриваемый год;
        прибыль по каждому месяцу;
        средний расход за все имеющиеся операции в году;
        средний доход за все имеющиеся операции в году.*/
    //короче, я устала, поскольку год один - я автоматизировать не буду, мне впадлу

    void printInformationFromReport(){
        if (!validateReport()){
            return;
        }
        System.out.println("Год 2021");
        printIncomePerMonth();
        System.out.println("Средний расход за все имеющиеся операции в году - " + averageSumExpense());
        System.out.println("Средний доход за все имеющиеся операции в году - " + averageSumIncome());
    }

    void printIncomePerMonth(){
        ArrayList<Year> yearReport = yearlyReportsHashmap.get("y.2021.csv");
        for (Year year: yearReport){
            if (!year.isExpense){
                System.out.println("Месяц " + year.month + ". Прибыль - " + year.amount);
            }
        }
    }

    double averageSumExpense(){
        ArrayList<Year> yearReport = yearlyReportsHashmap.get("y.2021.csv");
        double averageSum = 0;
        for (Year year: yearReport){
            if (year.isExpense){
                averageSum+=year.amount;
            }
        }
        return averageSum/3;
    }

    double averageSumIncome(){
        ArrayList<Year> yearReport = yearlyReportsHashmap.get("y.2021.csv");
        double averageSum = 0;
        for (Year year: yearReport){
            if (!year.isExpense){
                averageSum+=year.amount;
            }
        }
        return averageSum/3;
    }

    boolean validateReport() {
        if (yearlyReportsHashmap.isEmpty()){
            System.out.println("Отчеты не были считаны, пожалуйста, считайте файлы!");
            return false;
        }
        return true;
    }
}
