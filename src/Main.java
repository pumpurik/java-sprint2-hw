import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MonthlyReport monthlyReport = new MonthlyReport();
        YearlyReport yearlyReport = new YearlyReport();
        MonthTotalByYear monthTotalByYear = new MonthTotalByYear();
        while (true) {
            printMenu();
            int command = scanner.nextInt();
            if (command == 1) {
                monthlyReport.saveMonthlyReport();
            } else if (command == 2) {
                yearlyReport.saveYearlyReport();
            } else if (command == 3) {
                monthTotalByYear.check(monthlyReport, yearlyReport);
            } else if (command == 4) {
                monthlyReport.printInformationFromReports();
            } else if (command == 5) {
                yearlyReport.printInformationFromReport();
            } else if (command == 0) {
                System.out.println("Выход");
                break;
            } else {
                System.out.println("Извините, такой команды пока нет.");
            }
        }
    }

    public static void printMenu() {
        System.out.println("Что вы хотите сделать? ");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию обо всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("0 - Выход");
    }

}