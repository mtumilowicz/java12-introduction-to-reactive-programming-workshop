package reactive.workshop;

import java.util.function.Predicate;

/**
 * Created by mtumilowicz on 2018-05-21.
 */
class Step10_FilteringProcessorWorkshop<T> extends Step6_FilteringProcessorWorkshop<T> implements Step7_StreamWorkshop<T> {
    Step10_FilteringProcessorWorkshop(Predicate<T> condition) {
        super(condition);
    }
}
