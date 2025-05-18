import java.util.ArrayList;
import java.util.HashMap;

public class MonthTotalByYear {
    /* Для сверки данных программа должна делать следующее:
        Проверить, что месячные и годовой отчёты были считаны из файлов. В случае если этого не было сделано, нужно предложить сначала считать данные.
        Подсчитать суммы доходов и расходов по каждому из месяцев.
        Сверить полученные суммы с суммой доходов и расходов в отчёте по году.
        При обнаружении несоответствия программа должна вывести месяц, где оно обнаружено.
    Если несоответствий не обнаружено, приложение должно вывести только информацию об успешном завершении операции.*/
    //отчет за 1й должен иметь income - 1_593_150, expense - 350_000
    //отчет за 2й должен иметь income - 810_000, expense - 14_000
    //отчет за 3й должен иметь income - 2_430_000, expense - 90000

    void check(MonthlyReport monthlyReport, YearlyReport yearlyReport) {
        if (!validationReports(monthlyReport.monthlyReportsHashmap, yearlyReport.yearlyReportsHashmap)){
            return;
        };
        ArrayList<Year> yearReport = yearlyReport.yearlyReportsHashmap.get("y.2021.csv");
        for (Integer key : monthlyReport.monthlyReportsHashmap.keySet()){
            for (Year year : yearReport){
                if (year.month == key){
                    if (year.isExpense) {
                        if (year.amount != monthlyReport.sumExpense(key)){
                            System.out.println("Несоответствие расходов в месяце " + key);
                            System.out.println("Expense month" + monthlyReport.sumExpense(key));
                            System.out.println("Expense year " + year.amount);
                            return;
                        }
                    } else {
                        if (year.amount != monthlyReport.sumIncome(key)){
                            System.out.println("Несоответствие доходов в месяце " + key);
                            System.out.println("Income month " + monthlyReport.sumIncome(key));
                            System.out.println("Income year" + year.amount);
                            return;
                        }
                    }
                }
            }
        }
        System.out.println("Успешный успех");
    }

    boolean validationReports(HashMap<Integer, ArrayList<Month>> monthlyReports, HashMap<String, ArrayList<Year>> yearlyReports) {
        if (monthlyReports.isEmpty() || yearlyReports.isEmpty()){
            System.out.println("Отчеты не были считаны, пожалуйста, считайте файлы перед сверкой");
            return false;
        }
        return true;
    }
}
