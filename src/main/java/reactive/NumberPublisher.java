package reactive;

import java.util.concurrent.SubmissionPublisher;
import java.util.stream.IntStream;

/**
 * Created by mtumilowicz on 2018-05-21.
 */
public class NumberPublisher extends SubmissionPublisher<Integer> implements XProcessor<Integer, Integer> {
    
    public void start() {
        IntStream.iterate(1, i->i+1).forEach(i -> {
            submit(i);
            sleep();
        });
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
