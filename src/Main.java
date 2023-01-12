import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MonthlyReport monthlyReport = new MonthlyReport();
        YearlyReport yearlyReport = new YearlyReport();
        Checker checker = new Checker(monthlyReport, yearlyReport);


        while (true){
            printMenu();
            int command = scanner.nextInt();
            if (command==1){
                String path = null;
                String nameMonth = null;
               for (int i = 1; i< 4; i++){
                   path = "resources/m.20210" + i + ".csv";
                   if (i==1) {
                       nameMonth="Январь";
                       monthlyReport.loadFile(nameMonth, path);
                   }
                   else if (i==2){
                       nameMonth="Февраль";
                       monthlyReport.loadFile(nameMonth, path);
                   }
                   else{
                       nameMonth="Март";
                       monthlyReport.loadFile(nameMonth, path);
                   }
               }
            } else if (command==2) {
                yearlyReport.loadFile("resources/y.2021.csv");
            } else if (command==3) {
                if ((monthlyReport.reportMonth.isEmpty() == true)|| (yearlyReport.reportYear.isEmpty() == true)){
                    System.out.println("Необходимо считать файлы для сверки.");
                }else {
                checker.check("2021");
                }
            } else if (command==4) {
                if (monthlyReport.reportMonth.isEmpty() == false){
                monthlyReport.printMonthlyReport();
                } else{
                    System.out.println("Необходимо сначала считать месячные отчёты.");
                }
            } else if (command==5) {
                if (yearlyReport.reportYear.isEmpty() == false){
                    yearlyReport.printYearlyReport();
                } else{
                    System.out.println("Необходимо сначала считать годовой отчёт.");
                }

            }else if (command==6){
                break;
            }else{
                System.out.println("Такой команды пока не существует.");
            }
        }
    }
    public static void printMenu(){
        System.out.println("Что вы хотите сделать?");
        System.out.println("1 - Считать все месячные отчёты.");
        System.out.println("2 - Считать годовой отчёт.");
        System.out.println("3 - Сверить отчёты.");
        System.out.println("4 - Вывести информацию о всех месячных отчётах.");
        System.out.println("5 - Вывести информацию о годовом отчёте.");
        System.out.println("6 - Выйти из приложения.");
    }
}

