package reactive.answer;

import java.util.concurrent.Flow;

abstract class Step1_SubscriberBaseAnswer<T> implements Flow.Subscriber<T> {
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
