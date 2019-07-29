package main;

import reactive.SubscriberBase;

/**
 * Created by mtumilowicz on 2018-05-21.
 */
public class PrintingSubscriber<T> extends SubscriberBase<T> {
    @Override
    public void onNext(T item) {
        System.out.println("onNext, item: " + item);
        subscription.request(1);
    }
}
