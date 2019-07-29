package reactive;

import java.util.concurrent.SubmissionPublisher;
import java.util.function.Predicate;
import java.util.stream.IntStream;

/**
 * Created by mtumilowicz on 2018-05-21.
 */
public class NumberPublisher extends SubmissionPublisher<Integer> {
    
    public void start() {
        IntStream.iterate(1, i->i+1).forEach(i -> {
            submit(i);
            sleep();
        });
    }
    
    FilteringProcessor<Integer> filter(Predicate<Integer> p) {
        FilteringProcessor<Integer> processor = new FilteringProcessor<>(i -> i % 2 == 0);
        this.subscribe(processor);
        
        return processor;
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
