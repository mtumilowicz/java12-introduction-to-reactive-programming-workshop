package reactive.workshop;

import java.util.function.Predicate;

/**
 * Created by mtumilowicz on 2018-05-21.
 */
class Step6_FilteringProcessorWorkshop<T> extends Step3_ProcessorBaseWorkshop<T, T> {
    private final Predicate<T> predicate;

    Step6_FilteringProcessorWorkshop(Predicate<T> predicate) {
        this.predicate = predicate;
    }

    @Override
    public void onNext(T item) {
        // if predicate is hold - submit item, hint: submit(...)
        // request next, hint: subscription.request(...)
    }
}
