package reactive;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by mtumilowicz on 2018-05-21.
 */
public class FilteringProcessor<T> extends ProcessorBase<T, T> {

    private final Predicate<T> predicate;

    public FilteringProcessor(Predicate<T> predicate) {
        this.predicate = predicate;
    }

    @Override
    public void onNext(T item) {
        if (predicate.test(item)) {
            submit(item);
        }

        subscription.request(1);
    }

    <R> MappingProcessor<T, R> map(Function<T, R> map) {
        MappingProcessor<T, R> processor = new MappingProcessor<>(map);
        this.subscribe(processor);

        return processor;
    }
}
