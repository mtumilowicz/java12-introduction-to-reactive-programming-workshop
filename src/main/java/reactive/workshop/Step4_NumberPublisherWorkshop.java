package reactive.workshop;

import java.util.concurrent.SubmissionPublisher;
import java.util.stream.IntStream;

/**
 * Created by mtumilowicz on 2018-05-21.
 */
class Step4_NumberPublisherWorkshop extends SubmissionPublisher<Integer> {

    void start() {
        // generate infinite stream of numbers, submit and sleep for each number
        // hint: IntStream, iterate, submit, sleep
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
