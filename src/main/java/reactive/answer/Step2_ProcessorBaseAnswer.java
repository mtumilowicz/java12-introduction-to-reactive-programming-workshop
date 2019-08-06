package reactive.answer;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

/**
 * Created by mtumilowicz on 2018-05-21.
 */
abstract class Step2_ProcessorBaseAnswer<In, Out> extends Step1_SubscriberBaseAnswer<In>
        implements Flow.Publisher<Out>, Flow.Processor<In, Out> {
    private final SubmissionPublisher<Out> submissionPublisher = new SubmissionPublisher<>();

    @Override
    public void subscribe(Flow.Subscriber<? super Out> subscriber) {
        submissionPublisher.subscribe(subscriber);
    }

    @Override
    public void onError(Throwable throwable) {
        submissionPublisher.closeExceptionally(throwable);
    }

    @Override
    public void onComplete() {
        submissionPublisher.close();
    }

    void submit(Out item) {
        submissionPublisher.submit(item);
    }
}
