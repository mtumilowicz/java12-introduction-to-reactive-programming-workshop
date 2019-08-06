package reactive.answer

import reactive.Step10_NumberPublisherAnswer
import reactive.Step3_PrintingSubscriberAnswer
import reactive.Step4_FilteringProcessorAnswer
import reactive.Step5_MappingProcessorAnswer
import reactive.Step6_NumberPublisherAnswer

class Run {

    static void main(String[] args) {

    }

    static def chaining() {
        def numberPublisher = new Step10_NumberPublisherAnswer()
        numberPublisher
                .filter({ it % 2 == 0 })
                .map({ "new mapping: ${it}" })
                .forEachPrint()

        numberPublisher.start()
    }

    static def imperative() {
        Step6_NumberPublisherAnswer numberPublisher = new Step6_NumberPublisherAnswer()

        Step3_PrintingSubscriberAnswer<String> printingSubscriber = new Step3_PrintingSubscriberAnswer<>()
        Step5_MappingProcessorAnswer<Integer, String> mappingProcessor = new Step5_MappingProcessorAnswer<>({
            "new mapping: ${it} "
        })
        Step4_FilteringProcessorAnswer<Integer> filteringProcessor = new Step4_FilteringProcessorAnswer<>({
            it % 2 == 0
        })

        numberPublisher.subscribe(filteringProcessor)
        filteringProcessor.subscribe(mappingProcessor)
        mappingProcessor.subscribe(printingSubscriber)

        numberPublisher.start()
    }
}
