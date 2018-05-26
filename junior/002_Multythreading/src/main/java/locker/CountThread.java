package locker;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 25.05.2018
 */
class CountThread implements Runnable {
    private Count count;
    private Locker locker;

    CountThread(Count count, Locker lock) {
        this.count = count;
        this.locker = lock;
    }

    @Override
    public void run() {
        locker.lock(); // устанавливаем блокировку
        try {
            for (int i = 1; i < 5; i++) {
                count.increment();
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName() + " " + count);
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            locker.unlock(); // снимаем блокировку
        }
    }

    public static void main(String[] args) {
        Count count = new Count();
        Locker locker = new Locker();
        for (int i = 0; i < 3; i++) {
            Thread t = new Thread(new CountThread(count, locker));
            t.setName("Thread " + i);
            t.start();
        }
    }
}