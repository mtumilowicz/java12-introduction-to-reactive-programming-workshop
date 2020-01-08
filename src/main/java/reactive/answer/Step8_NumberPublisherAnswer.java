package reactive.answer;

import java.util.concurrent.SubmissionPublisher;
import java.util.stream.IntStream;

class Step8_NumberPublisherAnswer extends SubmissionPublisher<Integer>
        implements Step7_StreamAnswer<Integer> {

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
