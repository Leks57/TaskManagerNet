import java.util.Date;
import java.util.TimerTask;
import java.util.Timer;

public class ScheduledTask extends TimerTask {
    private Date alarm = null;
    private static Date firstAlarmDate = null;
    private static Date currentAlarmDate = null;
    private static int operatedTask;

    public static void setOperatedTask(int taskNumber) {
        ScheduledTask.operatedTask = taskNumber;
    }
    public static int getOperatedTask() {
        return ScheduledTask.operatedTask;
    }

    public void setAlarm(Date alarm) {
        this.alarm = alarm;
    }
    public Date getAlarm() {
        return alarm;
    }



    // Добавляем таск
    @Override
    public void run() {
        Date now;
        try {
            while(true) {
                now = new Date();
                Thread.sleep(1000);
                if (now.compareTo(alarm) >= 0 && TaskList.getInstance().getTasks().get(ScheduledTask.getOperatedTask()).isCompleted() == false) {
                    System.out.println("НАПОМИНАНИЕ!");

                    TaskList.getInstance().getTasks().get(ScheduledTask.getOperatedTask()).printTask();    // вывод задачи на экран
                    TaskList.getInstance().getTasks().get(ScheduledTask.getOperatedTask()).setCompleted(true);

                    ScheduledTask.updateTimer();
                }
            }
        } catch(Exception e) {
            Server.myTimer.cancel();
        }
    }

    public void start() {
        Server.myTimer.schedule(Server.myTask, Server.myTask.getAlarm()); // void schedule (TimerTask task, Date when)
    }


    public static void updateTimer() {

        for(Task z: TaskList.getInstance().getTasks()) {

            Date now = new Date();
            if (now.compareTo(z.getDate()) <= 0) {      // срабатывает при now <= z.getDate()
                currentAlarmDate = z.getDate();

                try {
                    if (firstAlarmDate.compareTo(currentAlarmDate) >= 0) {      // срабатывает при firstAlarmDate >= currentAlarmDate
                        firstAlarmDate = currentAlarmDate;
                        ScheduledTask.setOperatedTask(TaskList.getInstance().getTasks().indexOf(z));
                    }
                } catch (NullPointerException ex) {
                    firstAlarmDate = currentAlarmDate;
                    ScheduledTask.setOperatedTask(TaskList.getInstance().getTasks().indexOf(z));
                }

            }

            if (currentAlarmDate != null) {
                Server.myTask.setAlarm(firstAlarmDate);
                if (Server.timerThread.getState() == Thread.State.NEW) {
                    Server.timerThread.start();
                }
                currentAlarmDate = null;
            }

        }

    }


    public static void printMissedTasks() {
        int i = 0;
        for(Task z: TaskList.getInstance().getTasks()) {
            Date now = new Date();
            if (z.isCompleted() == false && now.compareTo(z.getDate()) >= 0) {   // Вывод на экран пропущенных задач
                System.out.println("");
                System.out.println("Пропущенная задача:");
                z.printTask();  //вывести напоминание
                z.setCompleted(true);
                i++;
            }
        }

        if (i == 0) {System.out.println("Пропущенные задачи отсутствуют");}
        else {
            System.out.println("");
            System.out.println("Кол-во пропущенных задач: " + i);
        }
        firstAlarmDate = null;

    }

}