package reactive.answer;

import java.util.function.Function;

class Step9_MappingProcessorAnswer<In, Out> extends Step4_ProcessorBaseAnswer<In, Out>
        implements Step7_StreamAnswer<Out> {
    private final Function<In, Out> mapper;

    Step9_MappingProcessorAnswer(Function<In, Out> mapper) {
        this.mapper = mapper;
    }

    @Override
    public void onNext(In item) {
        submit(mapper.apply(item));
        subscription.request(1);
    }
}
