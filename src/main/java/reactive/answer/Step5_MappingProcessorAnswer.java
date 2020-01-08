package reactive.answer;

import java.util.function.Function;

class Step5_MappingProcessorAnswer<In, Out> extends Step4_ProcessorBaseAnswer<In, Out> {
    private final Function<In, Out> mapper;

    Step5_MappingProcessorAnswer(Function<In, Out> mapper) {
        this.mapper = mapper;
    }

    @Override
    public void onNext(In item) {
        submit(mapper.apply(item));
        subscription.request(1);
    }
}
