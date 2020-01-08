package reactive.answer;

class Step2_PrintingSubscriberAnswer<T> extends Step1_SubscriberBaseAnswer<T> {

    @Override
    public void onNext(T item) {
        System.out.println("onNext, item: " + item);
        subscription.request(1);
    }
}
