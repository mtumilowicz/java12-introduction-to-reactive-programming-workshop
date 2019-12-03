package reactive.workshop;

import java.util.concurrent.Flow;
import java.util.function.Function;
import java.util.function.Predicate;

interface Step7_StreamWorkshop<In> extends Flow.Publisher<In> {
    default <R> Step9_MappingProcessorWorkshop<In, R> map(Function<In, R> map) {
        // create mapping processor, subscribe with it and return it
        // hint: Step9_MappingProcessorWorkshop, this.subscribe(...)

        return null;
    }

    default Step10_FilteringProcessorWorkshop<In> filter(Predicate<In> p) {
        // create filtering processor, subscribe with it and return it
        // hint: Step10_FilteringProcessorWorkshop, this.subscribe(...)

        return null;
    }

    default void forEachPrint() {
        // create printing subscriber and subscribe with it
        // hint: Step2_PrintingSubscriberWorkshop, this.subscribe(...)
    }
}
