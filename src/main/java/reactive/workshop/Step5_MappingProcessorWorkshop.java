package reactive.workshop;

import java.util.function.Function;

/**
 * Created by mtumilowicz on 2018-05-21.
 */
class Step5_MappingProcessorWorkshop<In, Out> extends Step3_ProcessorBaseWorkshop<In, Out> {
    private final Function<In, Out> function;

    Step5_MappingProcessorWorkshop(Function<In, Out> function) {
        this.function = function;
    }

    @Override
    public void onNext(In item) {
        // submit transformed item by function and request next
        // hint: submit(...), subscription.request(...)
    }
}
