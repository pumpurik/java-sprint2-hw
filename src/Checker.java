import java.util.ArrayList;
import java.util.HashMap;

public class Checker {
   MonthlyReport monthlyReport;
   YearlyReport yearlyReport;

    public Checker(MonthlyReport monthlyReport, YearlyReport yearlyReport) {
        this.monthlyReport = monthlyReport;
        this.yearlyReport = yearlyReport;
    }
    public void check(String nameYear){
        boolean checkIncome = checkIncome(nameYear);
        boolean checkExpenses = checkExpenses(nameYear);
        if ((checkIncome == true)&&(checkExpenses==true)) {
            System.out.println("Сверка данных прошла успешно! Ошибок не обнаружено.");
        }
    }
    public boolean checkExpenses (String nameYear){
        String nameMonth=null;
        boolean flag = true;
        ArrayList<Year> objYear = yearlyReport.reportYear.get(nameYear);
        HashMap<Integer, Integer> expensesByMonth = new HashMap<>();
        for (Year year: objYear) {
            if (year.isExpense == true){
                expensesByMonth.put(year.month, year.amount );
            }
        }
        for (Integer numberMonth : expensesByMonth.keySet()){
            switch (numberMonth){
                case 1:
                    nameMonth="Январь";
                    break;
                case 2:
                    nameMonth="Февраль";
                    break;
                default:
                    nameMonth="Март";
                    break;
            }
            int sumExpenses = monthlyReport.totalMonthlyExpenses(nameMonth);
            if (sumExpenses!= expensesByMonth.get(numberMonth)){
                System.out.println("Обнаружено несоответствие по расходам с месяцем " + nameMonth);
                flag = false;
            }
        }
        return flag;
    }
    public boolean checkIncome (String nameYear){
        String nameMonth=null;
        boolean flag = true;
        ArrayList<Year> objYear = yearlyReport.reportYear.get(nameYear);
        HashMap<Integer, Integer> incomeByMonth = new HashMap<>();
        for (Year year: objYear) {
            if (year.isExpense == false){
                incomeByMonth.put(year.month, year.amount );
            }
        }
        for (Integer numberMonth : incomeByMonth.keySet()){
            switch (numberMonth){
                case 1:
                    nameMonth="Январь";
                    break;
                case 2:
                    nameMonth="Февраль";
                    break;
                default:
                    nameMonth="Март";
                    break;
            }
            int sumExpenses = monthlyReport.totalMonthlyIncome(nameMonth);
            if (sumExpenses!= incomeByMonth.get(numberMonth)){
                System.out.println("Обнаружено несоответствие по доходам с месяцем " + nameMonth);
               flag = false;
            }
        }
        return flag;
    }
}
