package reactive.answer;

import java.util.function.Predicate;

class Step6_FilteringProcessorAnswer<T> extends Step4_ProcessorBaseAnswer<T, T> {
    private final Predicate<T> condition;

    Step6_FilteringProcessorAnswer(Predicate<T> condition) {
        this.condition = condition;
    }

    @Override
    public void onNext(T item) {
        if (condition.test(item)) {
            submit(item);
        }

        subscription.request(1);
    }
}
