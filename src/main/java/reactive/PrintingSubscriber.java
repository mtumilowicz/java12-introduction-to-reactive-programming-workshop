package reactive;

/**
 * Created by mtumilowicz on 2018-05-21.
 */
class PrintingSubscriber<T> extends SubscriberBase<T> {

    @Override
    public void onNext(T item) {
        System.out.println("onNext, item: " + item);
        subscription.request(1);
    }
}
