package locker;

/**
 * //TODO work comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 25.05.2018
 */
class CountThread implements Runnable {
    private Locker locker;
    private int a;

    CountThread(Locker lock) {
        this.locker = lock;
    }

    @Override
    public void run() {
        locker.lock(); // устанавливаем блокировку
        try {
            for (int i = 1; i < 5; i++) {
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName() + " " + a++);
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            locker.unlock(); // снимаем блокировку
        }
    }

    public static void main(String[] args) {
        Locker locker = new Locker();
        CountThread countThread = new CountThread(locker);
        for (int i = 0; i < 3; i++) {
            Thread t = new Thread(countThread);
            t.setName("Thread " + i);
            t.start();
        }
    }
}