package reactive.answer;

import java.util.function.Predicate;

class Step10_FilteringProcessorAnswer<T> extends Step6_FilteringProcessorAnswer<T> implements Step7_StreamAnswer<T> {

    Step10_FilteringProcessorAnswer(Predicate<T> condition) {
        super(condition);
    }
}
