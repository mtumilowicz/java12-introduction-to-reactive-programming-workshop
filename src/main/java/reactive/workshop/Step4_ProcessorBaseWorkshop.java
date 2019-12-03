package reactive.workshop;

import java.util.concurrent.Flow;

abstract class Step4_ProcessorBaseWorkshop<In, Out> extends Step1_SubscriberBaseWorkshop<In>
        implements Flow.Processor<In, Out> {
    // create SubmissionPublisher

    @Override
    public void subscribe(Flow.Subscriber<? super Out> subscriber) {
        // subscribe to submissionPublisher with subscriber, hint: subscribe(...)
    }

    @Override
    public void onError(Throwable throwable) {
        // push exception to the channel and close submissionPublisher, hint: closeExceptionally(...)
    }

    @Override
    public void onComplete() {
        // close submissionPublisher, hint: close()
    }

    void submit(Out item) {
        // submit item to submissionPublisher, hint: submit(...)
    }
}
