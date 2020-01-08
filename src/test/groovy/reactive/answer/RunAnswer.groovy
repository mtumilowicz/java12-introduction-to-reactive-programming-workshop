package reactive.answer

class RunAnswer {

    static void main(String[] args) {
        chaining()
//        imperative()
    }

    static def chaining() {
        def numberPublisher = new Step8_NumberPublisherAnswer()
        numberPublisher
                .filter { it % 2 == 0 }
                .map { "new mapping: ${it}" }
                .forEachPrint()

        numberPublisher.start()
    }

    static def imperative() {
        Step3_NumberPublisherAnswer numberPublisher = new Step3_NumberPublisherAnswer()

        Step2_PrintingSubscriberAnswer<String> printingSubscriber = new Step2_PrintingSubscriberAnswer<>()
        Step5_MappingProcessorAnswer<Integer, String> mappingProcessor = new Step5_MappingProcessorAnswer<>({
            "new mapping: ${it} "
        })
        Step6_FilteringProcessorAnswer<Integer> filteringProcessor = new Step6_FilteringProcessorAnswer<>({
            it % 2 == 0
        })

        numberPublisher.subscribe(filteringProcessor)
        filteringProcessor.subscribe(mappingProcessor)
        mappingProcessor.subscribe(printingSubscriber)

        numberPublisher.start()
    }
}
