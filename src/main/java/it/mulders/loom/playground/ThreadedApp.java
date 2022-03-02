package it.mulders.loom.playground;

import java.util.stream.IntStream;
import static java.util.stream.Collectors.toList;

public class ThreadedApp {
    public static void main(final String... args) {
        System.out.printf("Starting conventional threading app...%n");
        var start = System.currentTimeMillis();

        var threads = IntStream.rangeClosed(0, 1_000)
            .mapToObj(id -> new PrintThreadNameJob())
            .map(job -> Thread.ofPlatform().start(job))
            .collect(toList());

        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException ie) {
            }
        });

        // try {
        //     var thread = Thread.ofPlatform().start(new PrintThreadNameJob());
        //     thread.join();
        // } catch (InterruptedException ie) {
        // }
        
        var duration = System.currentTimeMillis() - start;
        System.out.printf("  ... took %d milliseconds%n", duration);
    }
}
