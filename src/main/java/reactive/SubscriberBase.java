package reactive;

import java.util.concurrent.Flow;

/**
 * Created by mtumilowicz on 2018-05-21.
 */
abstract class SubscriberBase<T> implements Flow.Subscriber<T> {
    Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        if (this.subscription == null) {
            this.subscription = subscription;
            this.subscription.request(1);
        } else {
            subscription.cancel();
        }
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
