package reactive.workshop;

class Step2_PrintingSubscriberWorkshop<T> extends Step1_SubscriberBaseWorkshop<T> {

    @Override
    public void onNext(T item) {
        System.out.println("onNext, item: " + item);
        // request next 1 elem, hint: subscription.request(...)
    }
}
