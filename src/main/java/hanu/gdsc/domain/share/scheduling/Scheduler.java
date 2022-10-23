package hanu.gdsc.domain.share.scheduling;

import java.util.concurrent.atomic.AtomicBoolean;

public class Scheduler {
    public static abstract class Runner {
        private AtomicBoolean stop;

        protected Runner() {
            this.stop = new AtomicBoolean(false);
        }

        protected void stop() {
            stop.set(true);
        }

        protected abstract void run() throws Throwable;
    }

    private Thread thread;
    private Runner runner;

    public Scheduler(long millis, Runner runner) {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!runner.stop.get()) {
                    try {
                        runner.run();
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                    try {
                        Thread.sleep(millis);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void start() {
        thread.start();
    }

    public void stop() {
        runner.stop();
    }
}
