package reactive;

import java.util.concurrent.Flow;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by mtumilowicz on 2019-07-29.
 */
interface XProcessor<In, Out> extends Flow.Publisher<Out> {
    default <R> MappingProcessor<Out, R> map(Function<Out, R> map) {
        MappingProcessor<Out, R> processor = new MappingProcessor<>(map);
        this.subscribe(processor);

        return processor;
    }

    default FilteringProcessor<Out> filter(Predicate<Out> p) {
        FilteringProcessor<Out> processor = new FilteringProcessor<>(p);
        this.subscribe(processor);

        return processor;
    }

    default void forEachPrint() {
        PrintingSubscriber<Out> printingSubscriber = new PrintingSubscriber<>();

        this.subscribe(printingSubscriber);
    }
}
