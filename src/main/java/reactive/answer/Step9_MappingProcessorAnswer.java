package reactive.answer;

import java.util.function.Function;

/**
 * Created by mtumilowicz on 2018-05-21.
 */
class Step9_MappingProcessorAnswer<In, Out> extends Step2_ProcessorBaseAnswer<In, Out>
        implements Step7_StreamAnswer<In, Out> {
    private final Function<In, Out> function;

    Step9_MappingProcessorAnswer(Function<In, Out> function) {
        this.function = function;
    }

    @Override
    public void onNext(In item) {
        submit(function.apply(item));
        subscription.request(1);
    }
}
