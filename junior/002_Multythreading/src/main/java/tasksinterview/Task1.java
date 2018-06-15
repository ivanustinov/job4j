package tasksinterview;

/**
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 14.06.2018
 */
public class Task1 {
    private Object obj = new Object();
    private String numbers = "";


    public void addNumbers(int number) {
        numbers += String.valueOf(number);
    }

    public String getNumbers() {
        return numbers;
    }

    class MyThread extends Thread {
        private int count;

        public MyThread(int count) {
            this.count = count;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                synchronized (obj) {
                    for (int i = 0; i < 10; i++) {
                        addNumbers(count);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Task1 task = new Task1();
        MyThread first = task.new MyThread(1);
        MyThread second = task.new MyThread(2);
        first.start();
        second.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        first.interrupt();
        second.interrupt();
        System.out.println(task.getNumbers());
    }
}
