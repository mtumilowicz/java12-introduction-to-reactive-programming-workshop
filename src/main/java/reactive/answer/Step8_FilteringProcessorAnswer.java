package reactive.answer;

import java.util.function.Predicate;

/**
 * Created by mtumilowicz on 2018-05-21.
 */
class Step8_FilteringProcessorAnswer<T> extends Step2_ProcessorBaseAnswer<T, T> implements Step7_XProcessorAnswer<T, T> {
    private final Predicate<T> predicate;

    Step8_FilteringProcessorAnswer(Predicate<T> predicate) {
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
