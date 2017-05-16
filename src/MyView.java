public class MyView {
    public void printMainPage() {
        System.out.println("Выберите действие:");
        System.out.println("1 - PrintTasks");
        System.out.println("2 - CreateTask");
        System.out.println("3 - EditTask");
        System.out.println("4 - SaveTaskList");
        System.out.println("0 - Exit");
    }

    public void printEditTaskMenu() {
        System.out.println("Введите номер действия: ");
        System.out.println("1 - Изменить название задачи");
        System.out.println("2 - Изменить описание задачи");
        System.out.println("3 - Изменить дату события");
        System.out.println("4 - Удалить задачу");
        System.out.println("0 - Выход");
    }

    public static void printLine() {
        System.out.println("-------------------------------------------------------------");
    };
}