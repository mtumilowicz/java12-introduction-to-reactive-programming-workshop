package reactive.workshop;

import java.util.function.Function;

class Step5_MappingProcessorWorkshop<In, Out> extends Step4_ProcessorBaseWorkshop<In, Out> {
    private final Function<In, Out> mapper;

    Step5_MappingProcessorWorkshop(Function<In, Out> mapper) {
        this.mapper = mapper;
    }

    @Override
    public void onNext(In item) {
        // submit transformed item by function and request next
        // hint: submit(...), subscription.request(...)
    }
}
