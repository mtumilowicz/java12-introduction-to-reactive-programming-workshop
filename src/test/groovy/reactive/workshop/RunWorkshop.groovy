package reactive.workshop

class RunWorkshop {

    static void main(String[] args) {
        chaining()
//        imperative()
    }

    static def chaining() {
        def numberPublisher = new Step8_NumberPublisherWorkshop()
        numberPublisher
                .filter({ it % 2 == 0 })
                .map({ "new mapping: ${it}" })
                .forEachPrint()

        numberPublisher.start()
    }

    static def imperative() {
        Step3_NumberPublisherWorkshop numberPublisher = new Step3_NumberPublisherWorkshop()

        Step2_PrintingSubscriberWorkshop<String> printingSubscriber = new Step2_PrintingSubscriberWorkshop<>()
        Step5_MappingProcessorWorkshop<Integer, String> mappingProcessor = new Step5_MappingProcessorWorkshop<>({
            "new mapping: ${it} "
        })
        Step6_FilteringProcessorWorkshop<Integer> filteringProcessor = new Step6_FilteringProcessorWorkshop<>({
            it % 2 == 0
        })

        numberPublisher.subscribe(filteringProcessor)
        filteringProcessor.subscribe(mappingProcessor)
        mappingProcessor.subscribe(printingSubscriber)

        numberPublisher.start()
    }
}
