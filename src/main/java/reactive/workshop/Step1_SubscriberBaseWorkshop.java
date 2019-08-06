package reactive.workshop;

import java.util.concurrent.Flow;

/**
 * Created by mtumilowicz on 2018-05-21.
 */
abstract class Step1_SubscriberBaseWorkshop<T> implements Flow.Subscriber<T> {
    Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        // if we already has subscription set - we cancel incoming subscription, hint: cancel()
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
