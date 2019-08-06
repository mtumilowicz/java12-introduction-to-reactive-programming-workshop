package reactive.answer;

import java.util.concurrent.Flow;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by mtumilowicz on 2019-07-29.
 */
interface Step7_StreamAnswer<In, Out> extends Flow.Publisher<Out> {
    default <R> Step9_MappingProcessorAnswer<Out, R> map(Function<Out, R> map) {
        Step9_MappingProcessorAnswer<Out, R> processor = new Step9_MappingProcessorAnswer<>(map);
        this.subscribe(processor);

        return processor;
    }

    default Step8_FilteringProcessorAnswer<Out> filter(Predicate<Out> p) {
        Step8_FilteringProcessorAnswer<Out> processor = new Step8_FilteringProcessorAnswer<>(p);
        this.subscribe(processor);

        return processor;
    }

    default void forEachPrint() {
        Step3_PrintingSubscriberAnswer<Out> printingSubscriber = new Step3_PrintingSubscriberAnswer<>();

        this.subscribe(printingSubscriber);
    }
}
