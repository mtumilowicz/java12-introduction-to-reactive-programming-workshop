package reactive.answer;

import java.util.concurrent.Flow;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by mtumilowicz on 2019-07-29.
 */
interface Step7_StreamAnswer<In> extends Flow.Publisher<In> {
    default <R> Step9_MappingProcessorAnswer<In, R> map(Function<In, R> map) {
        Step9_MappingProcessorAnswer<In, R> processor = new Step9_MappingProcessorAnswer<>(map);
        this.subscribe(processor);

        return processor;
    }

    default Step8_FilteringProcessorAnswer<In> filter(Predicate<In> p) {
        Step8_FilteringProcessorAnswer<In> processor = new Step8_FilteringProcessorAnswer<>(p);
        this.subscribe(processor);

        return processor;
    }

    default void forEachPrint() {
        Step3_PrintingSubscriberAnswer<In> printingSubscriber = new Step3_PrintingSubscriberAnswer<>();

        this.subscribe(printingSubscriber);
    }
}
