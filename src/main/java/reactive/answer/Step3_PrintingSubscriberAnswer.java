package reactive.answer;

/**
 * Created by mtumilowicz on 2018-05-21.
 */
class Step3_PrintingSubscriberAnswer<T> extends Step1_SubscriberBaseAnswer<T> {

    @Override
    public void onNext(T item) {
        System.out.println("onNext, item: " + item);
        subscription.request(1);
    }
}
