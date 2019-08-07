package reactive.answer;

import java.util.function.Predicate;

/**
 * Created by mtumilowicz on 2018-05-21.
 */
class Step10_FilteringProcessorAnswer<T> extends Step4_ProcessorBaseAnswer<T, T> implements Step7_StreamAnswer<T> {
    private final Predicate<T> predicate;

    Step10_FilteringProcessorAnswer(Predicate<T> predicate) {
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
