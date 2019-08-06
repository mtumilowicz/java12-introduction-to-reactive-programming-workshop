package reactive.answer;

import java.util.concurrent.SubmissionPublisher;
import java.util.stream.IntStream;

/**
 * Created by mtumilowicz on 2018-05-21.
 */
class Step10_NumberPublisherAnswer extends SubmissionPublisher<Integer>
        implements Step7_XProcessorAnswer<Integer, Integer> {

    void start() {
        IntStream.iterate(1, i -> i + 1).forEach(i -> {
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
