package it.mulders.loom.playground;

import java.time.Duration;

public class PrintThreadNameJob implements Runnable {
    public PrintThreadNameJob() {
    }
    
    @Override
    public void run() {
        var name = Thread.currentThread().getName();
        var type = Thread.currentThread().isVirtual() ? "virtual" : "conventional";
        // System.out.printf("Job runs in %s thread %s%n", type, name);
        // try {
        //     Thread.sleep(Duration.ofSeconds(2));
        // } catch (InterruptedException e) {
        // }
    }
}
