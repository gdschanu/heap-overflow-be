package hanu.gdsc.share.scheduling;

public class ScheduledThread {
    public static interface Runner {
        public void run() throws Exception;
    }

    private final long millis;
    private final Thread thread;

    public ScheduledThread(long millis, Runner runner) {
        this.millis = millis;
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(millis);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        runner.run();
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    public void start() {
        thread.start();
    }
}
