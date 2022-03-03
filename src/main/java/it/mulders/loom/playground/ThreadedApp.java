package it.mulders.loom.playground;

import java.util.stream.IntStream;
import static java.util.stream.Collectors.toList;

public class ThreadedApp {
    public static void main(final String... args) {
        System.out.printf("Starting conventional threading app...%n");
        var count = args.length > 0 ? Integer.parseInt(args[0], 10) : Constants.JOB_COUNT;
        var start = System.currentTimeMillis();

        var threads = IntStream.rangeClosed(0, count)
            .mapToObj(id -> new PrintThreadNameJob())
            .map(job -> Thread.ofPlatform().start(job))
            .collect(toList());

        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException ie) {
            }
        });
        
        var duration = System.currentTimeMillis() - start;
        System.out.printf("  ... took %d milliseconds%n", duration);
    }
}
