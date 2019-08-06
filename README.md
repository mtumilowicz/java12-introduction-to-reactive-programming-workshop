# java9-reactive-streams
The main goal of this project is to explore basic features of 
`reactive streams` in `Java 9`:
* **Publisher**
* **Subscriber**
* **Subscription** (**Backpressure**)
* **Processor** (**SubmissionPublisher**)

_Reference_: [Out-of-the-box Reactive Streams with Java 9](https://www.youtube.com/watch?v=COgktgJmP_k)  
_Reference_: https://github.com/reactive-streams/reactive-streams-jvm  
_Reference_: https://docs.oracle.com/javase/9/docs/api/java/util/concurrent/Flow.html

## definitions
In `Java 9` naming conventions:  
**[Flow.Publisher](https://docs.oracle.com/javase/9/docs/api/java/util/concurrent/Flow.Publisher.html)** - 
source of data.  
**[Flow.Subscriber](https://docs.oracle.com/javase/9/docs/api/java/util/concurrent/Flow.Subscriber.html)** - 
destination of data.  
**[Flow.Subscription](https://docs.oracle.com/javase/9/docs/api/java/util/concurrent/Flow.Subscription.html)** - 
message control linking a `Flow.Publisher` and `Flow.Subscriber` 
(`Subscriber` signal demand to `Publisher`).  
**[Flow.Processor](https://docs.oracle.com/javase/9/docs/api/java/util/concurrent/Flow.Processor.html)** - 
a component that acts as both a `Subscriber` and `Publisher` (can 
consume input and produce output).  
**[Flow.SubmissionPublisher](https://docs.oracle.com/javase/9/docs/api/java/util/concurrent/SubmissionPublisher.html)** - 
It's the only  one implementation (in `JDK`) of `Flow.Publisher`. 
Moreover - has ability to asynchronously issue submitted (non-null) 
items to current subscribers until it is closed.

## data flow
```
PUBLISHER -> PROCESSOR -> PROCESSOR -> SUBSCRIBER
```
We have two scenarios:  
* `Publisher` is slow, `Subscriber` is fast (best scenario)
* `Publisher` is fast, `Subscriber` is slow (the `Subscriber` must deal 
with excessive data - the most naive approach is just to drop all 
excessive data - so the data will be irrevitable).

Note that if we have multiple `subscribers` and one `publisher` - they 
are receiving elements in the same order.

## interaction steps
1. Implement `Flow.Publisher` (useful implementation, can he extended: `SubmissionPublisher<T>`) and `Flow.Subscriber`
1. The subscriber attempts to subscribe to the publisher by calling the 
`subscribe(Flow.Subscriber<? super T> subscriber)`
method of the publisher
    * success: the publisher asynchronously calls the `onSubscribe(Flow.Subscription subscription)` 
    method of the subscriber
    * failure: `onError(Throwable throwable)` method of the subscriber is called 
    with an `IllegalStateException`, and the interaction ends
1. The subscriber sends a request to the publisher for `N` items calling the `request(N)` 
on the `Subscription`
1. Multiple requests are send regardless earlier are already fulfilled (non-blocking)
1. The publisher calls the `onNext(T item)` method of the subscriber and sends an item in each call
    * if there is no more items to send the publisher calls the `onComplete()` method of the subscriber to signal
the end of stream, and interaction ends
    * note that if subscriber requested `Long.MAX_VALUE` items, the stream becomes not reactive - it is
    effectively a push stream
1. if the publisher encounters an error - calls `onError(Throwable throwable)` on subscriber
1. the subscriber can cancel its subscription by calling the `cancel()` method on its subscription
    * if a subscription is cancelled, the interaction ends 
    * it is possible for the subscriber to receive items after
    cancellation if there were pending requests before

## project content

### SubscriberBase
Correctly `@Override` method `onSubscribe` looks as below:
```
@Override
public void onSubscribe(Flow.Subscription subscription) {
    if (this.subscription == null) {
        this.subscription = subscription;
        this.subscription.request(1);
    }
    else {
        subscription.cancel();
    }
}
```
because we want our `Subscriber` talk to only one `Publisher` - 
`Subscription` represents a link between single `Subscriber` and single 
`Publisher` so you have to cancel the incoming one (if we already have 
one, we don't accept any furthers).

### Main
In the main method we conctruct essentially something like this:
```
IntStream.iterate(1, i->i+1).filter(i->i%2 == 0).mapToObj(i -> "new mapping: " + i).forEach(i -> System.out.println("onNext, item: " + i));
```
but using reactive steam api.

### NumberPublisher
`SubmissionPublisher` with additional method `start()` to run
the whole pipeline.
```
public void start() {
    IntStream.iterate(1,i->i+1).forEach(i -> {
        submit(i);
        sleep();
    });
}
```
We `sleep()` our `Thread` for a second - to make the whole process
clearer and more readable.

### Processors: FilteringProcessor, MappingProcessor

### Subscriber: PrintingSubscriber