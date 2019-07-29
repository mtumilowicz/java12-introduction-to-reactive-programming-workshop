package reactive;

import java.util.function.Function;

/**
 * Created by mtumilowicz on 2018-05-21.
 */
public class MappingProcessor<In, Out> extends ProcessorBase<In, Out> {

    private final Function<In, Out> function;

    public MappingProcessor(Function<In, Out> function) {
        this.function = function;
    }

    @Override
    public void onNext(In item) {
        submit(function.apply(item));
        subscription.request(1);
    }
}
