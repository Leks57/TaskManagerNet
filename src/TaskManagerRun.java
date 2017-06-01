import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TaskManagerRun {




/*

    public static class MyMenu extends Thread {
        @Override
        public void run(){
            int action = 6;
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            while (action!=0) {
                View.MyView.printLine();
                System.out.println("Выберите действие:");
                System.out.println("1 - Exit");
                System.out.println("2 - PrintTasks");
                System.out.println("3 - CreateTask");
                System.out.println("4 - EditTask");
                System.out.println("5 - SaveTaskList");

                String line = null;


                try {
                    line = reader.readLine();
                    action = Integer.parseInt(line);
                } catch (NumberFormatException e) {}
                catch (IOException e) {
                    e.printStackTrace();
                }



                switch(action) {
                    case 1: Task.Xml.saveXml(); Server.ScheduledTask.myTimer.cancel(); TaskManagerRun.timerThread.interrupt(); break; //Выход
                    case 2:
                        System.out.println("Выберите требуемый список:");
                        System.out.println("1 - Список всех задач");
                        System.out.println("2 - Список актуальных задач");
                        int act = 0;
                        try {
                            line = reader.readLine();
                            act = Integer.parseInt(line);
                        } catch (NumberFormatException e) {}
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                        switch (act) {
                            case 1: tasks.printTasks(); break;
                            case 2: tasks.printActualTasks(); break;
                            default:
                                System.out.println("Выбрано неверное действие!"); break;
                        }
                        break; //PrintTasks
                    case 3: tasks.createTask(); Task.Xml.saveXml(); Server.ScheduledTask.updateTimer(); break; //CreateTask
                    case 4: tasks.editTask(); Server.ScheduledTask.updateTimer(); break; //editTask
                    case 5: Task.Xml.saveXml(); System.out.println("Файл сохранен"); break; //saveTaskList

                    default:
                        System.out.println("Выбрано неверное действие! Повторите ввод!");
                        break;
                }


            }
        }
    }

    public static MyMenu myMenu = new MyMenu();

    static Task.TaskList tasks = new Task.TaskList();

    public static void main(String[] args) throws IOException {

        Task.Xml.defineXml(); // создание файла xml или определение текущего


        Server.ScheduledTask.printMissedTasks();
        Server.ScheduledTask.updateTimer(); // Обновление даты первого срабатывания таймера и запуск таймера

        //Основное МЕНЮ
        myMenu.start();

    }*/
}