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

    default Step10_FilteringProcessorAnswer<In> filter(Predicate<In> p) {
        Step10_FilteringProcessorAnswer<In> processor = new Step10_FilteringProcessorAnswer<>(p);
        this.subscribe(processor);

        return processor;
    }

    default void forEachPrint() {
        Step2_PrintingSubscriberAnswer<In> printingSubscriber = new Step2_PrintingSubscriberAnswer<>();

        this.subscribe(printingSubscriber);
    }
}
