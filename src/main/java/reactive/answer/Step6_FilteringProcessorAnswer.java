package reactive.answer;

import java.util.function.Predicate;

/**
 * Created by mtumilowicz on 2018-05-21.
 */
class Step6_FilteringProcessorAnswer<T> extends Step3_ProcessorBaseAnswer<T, T> {
    private final Predicate<T> predicate;

    Step6_FilteringProcessorAnswer(Predicate<T> predicate) {
        this.predicate = predicate;
    }

    @Override
    public void onNext(T item) {
        if (predicate.test(item)) {
            submit(item);
        }

        subscription.request(1);
    }
}
