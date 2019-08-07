package reactive.workshop;

import java.util.function.Predicate;

/**
 * Created by mtumilowicz on 2018-05-21.
 */
class Step6_FilteringProcessorWorkshop<T> extends Step4_ProcessorBaseWorkshop<T, T> {
    private final Predicate<T> condition;

    Step6_FilteringProcessorWorkshop(Predicate<T> condition) {
        this.condition = condition;
    }

    @Override
    public void onNext(T item) {
        // if predicate is hold - submit item, hint: submit(...)
        // request next, hint: subscription.request(...)
    }
}
