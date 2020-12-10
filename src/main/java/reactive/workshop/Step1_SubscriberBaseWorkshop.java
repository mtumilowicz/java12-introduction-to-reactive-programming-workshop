package reactive.workshop;

import java.util.concurrent.Flow;

abstract class Step1_SubscriberBaseWorkshop<T> implements Flow.Subscriber<T> {
    Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        // if we already have subscription set - we cancel incoming subscription, hint: cancel()
        // otherwise - set subscription, and request 1 elem, hint: request(1)
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("OnError, message: " + throwable.getLocalizedMessage());
    }

    @Override
    public void onComplete() {
        System.out.println("OnComplete.");
    }
}
