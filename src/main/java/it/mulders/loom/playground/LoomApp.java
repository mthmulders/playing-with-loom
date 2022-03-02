package it.mulders.loom.playground;

import java.util.stream.IntStream;
import static java.util.stream.Collectors.toList;

public class LoomApp {
    public static void main(final String... args) {
        System.out.printf("Starting virtual threading app with Project Loom...%n");
        var start = System.currentTimeMillis();

        var threads = IntStream.rangeClosed(0, 1_000_000)
        .mapToObj(id -> new PrintThreadNameJob())
            .map(job -> Thread.ofVirtual().start(job))
            .collect(toList());

        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException ie) {
            }
        });

        // try {
        //     var thread = Thread.ofVirtual().start(new PrintThreadNameJob());
        //     thread.join();
        // } catch (InterruptedException ie) {
        // }
        
        var duration = System.currentTimeMillis() - start;
        System.out.printf("  ... took %d milliseconds%n", duration);
    }
}
