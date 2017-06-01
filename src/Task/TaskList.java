package Task;

import View.*;

import java.io.Serializable;
import java.util.*;

public class TaskList implements Serializable {

    private static TaskList instance;
    private List<Task> tasks = new ArrayList<Task>();

    public static TaskList getInstance() {
        if(instance == null){
            instance = new TaskList();
        }
        return instance;
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public void addTask(Task task) {
        this.getTasks().add(task);
    }

    public void printTasks() {
        if (this.getTasks().isEmpty() == false) {
            for(Task t: this.getTasks()) {
                t.printTask();
            }
        }
        else {System.out.println("Список задач пуст!");}
    }

    public void printActualTasks() {
        if (this.getTasks().isEmpty() == false) {
            int i = 0;
            for(Task t: this.getTasks()) {
                if (t.isCompleted() == false) {
                    t.printTask();
                    i++;
                }
            }
            if (i == 0) {
                System.out.println("Список актуальных задач пуст!");
            }
        }
        else {System.out.println("Список задач пуст!");}
    }

    public void createTask() {
        Task task = new Task();
        task.setTaskName();
        task.setTaskDescription();
        task.setTaskDate();

        Contact contact = new Contact();
        contact.setContactName();
        contact.setContactSurname();
        contact.setContactPhone();

        task.addContact(contact);
        this.addTask(task);
    }

    public int editTask() {
        MyView.printLine();
        System.out.println("Текущие задачи:");
        for(Task t: this.getTasks()){
            System.out.println(this.getTasks().indexOf(t) + 1 + " - " + t.getName());
        }

        Scanner scInt = new Scanner(System.in);
        Scanner scLine = new Scanner(System.in);

        int taskNumber = 0;
        try {
            System.out.println("Введите номер выбранной задачи: ");
            taskNumber = scInt.nextInt() - 1;
            MyView.printLine();
            this.getTasks().get(taskNumber).printTask();


            System.out.println("Введите номер действия: ");
            System.out.println("1 - Изменить название задачи");
            System.out.println("2 - Изменить описание задачи");
            System.out.println("3 - Изменить дату события");
            System.out.println("4 - Удалить задачу");
            System.out.println("0 - Выход");

            int userChoice = scInt.nextInt();
            MyView.printLine();
            switch(userChoice) {
                case 1: this.getTasks().get(taskNumber).setTaskName(); break;
                case 2: System.out.println("Введите новое описание:"); this.getTasks().get(taskNumber).setDescription(scLine.nextLine()); break;
                case 3: this.getTasks().get(taskNumber).setTaskDate(); break;
                case 4: this.getTasks().remove(taskNumber); System.out.println("Задача № " + (taskNumber + 1) + " успешно удалена"); break;
                case 0: break;
                default: System.out.println("Неверное действие!");
            }
        } catch (IndexOutOfBoundsException e) {System.out.println("Неправильный номер задачи!"); editTask();}
        return taskNumber;
    }

    public int getDeletedTask() {
        MyView.printLine();
        System.out.println("Текущие задачи:");
        for (Task t : this.getTasks()) {
            System.out.println(this.getTasks().indexOf(t) + 1 + " - " + t.getName());
        }

        Scanner scInt = new Scanner(System.in);
        int taskNumber = 0;
        try {
            System.out.println("Введите номер выбранной задачи: ");
            taskNumber = scInt.nextInt() - 1;
        } catch (IndexOutOfBoundsException e) {System.out.println("Неправильный номер задачи!"); getDeletedTask();}
        return taskNumber;
    }

}