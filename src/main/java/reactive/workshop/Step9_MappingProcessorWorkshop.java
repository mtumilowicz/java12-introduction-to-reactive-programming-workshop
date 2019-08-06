package reactive.workshop;

import java.util.function.Function;

/**
 * Created by mtumilowicz on 2018-05-21.
 */
class Step9_MappingProcessorWorkshop<In, Out> extends Step5_MappingProcessorWorkshop<In, Out>
        implements Step7_StreamWorkshop<Out> {
    Step9_MappingProcessorWorkshop(Function<In, Out> function) {
        super(function);
    }
}
