package reactive.answer;

import java.util.function.Predicate;

class Step10_FilteringProcessorAnswer<T> extends Step4_ProcessorBaseAnswer<T, T> implements Step7_StreamAnswer<T> {
    private final Predicate<T> condition;

    Step10_FilteringProcessorAnswer(Predicate<T> condition) {
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
