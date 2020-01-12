[![Build Status](https://travis-ci.com/mtumilowicz/java12-introduction-to-functional-programming-workshop.svg?branch=master)](https://travis-ci.com/mtumilowicz/java12-introduction-to-functional-programming-workshop)
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)

# java12-introduction-to-reactive-programming-workshop
* References
    * [Out-of-the-box Reactive Streams with Java 9](https://www.youtube.com/watch?v=COgktgJmP_k)
    * [Java Streams vs Reactive Streams: Which, When, How, and Why? by Venkat Subramaniam](https://www.youtube.com/watch?v=kG2SEcl1aMM)
    * [Reactive Programming by Venkat Subramaniam](https://www.youtube.com/watch?v=weWSYIUdX6chttps://www.youtube.com/watch?v=weWSYIUdX6c)
    * [From Functional to Reactive Programming, Venkat Subramaniam](https://www.youtube.com/watch?v=U_NgcAg7jyY)
    * [WJUG #239 - Jacek Kunicki: Jak (nie) używać Reactive Streams w Javie 9+](https://www.youtube.com/watch?v=8zVcpjSxT1o)
    * https://github.com/rucek/reactive-streams-java9
    * https://github.com/reactive-streams/reactive-streams-jvm
    * https://docs.oracle.com/javase/9/docs/api/java/util/concurrent/Flow.html  
    * https://blog.softwaremill.com/how-not-to-use-reactive-streams-in-java-9-7a39ea9c2cb3
    * https://thepracticaldeveloper.com/2018/01/31/reactive-programming-java-9-flow/
    * https://www.quora.com/Whats-the-difference-between-push-and-pull-protocols
    * http://blog.amitinside.com/Java-Iterator-Pattern-vs-Stream-vs-RxObservable/
    * http://reactivex.io/RxJava/2.x/javadoc/io/reactivex/BackpressureStrategy.html

## preface
The main goal of this project is to explore basic features of 
`reactive streams` introduced in `Java 9`:
* **Publisher**
* **Subscriber**
* **Subscription** (**Backpressure**)
* **Processor** (**SubmissionPublisher**)

## introduction
* the main objective of the reactive programming is NOT to be as fast as possible 
but to use resources (CPU, memory, ...) in the most efficient manner
* before java 8: completely imperative + object oriented
    * please refer: https://github.com/mtumilowicz/java12-introduction-to-functional-programming-workshop
    * imperative: tell me what tell me how
    * declarative: tell me what and NOT how
* Michael Feather: OO makes code understandable by encapsulating moving parts. FP makes code understandable by 
minimizing moving parts.
    * I moving part: immutability
    * II moving part: control flow (in imperative - we are going up and down to follow the flow)
    * functional (style): declarative + higher-order function
        * functional programming: function composition + lazy evaluation
* shared mutability
    ```
    Thread th = new Thread(new Runnable() {
        public void run() { // we don't produce anything and don't consume anything 
            // it could be useful only by shared mutability
            // how to work with threads if shared mutability is dangerous 
            // and thread API forces us to use it
        }
    })
    ```
* in the past the structure of sequential code was very different from the structure 
of concurrent code
    * with stream: the structure of sequential code is the same as the structure of 
    concurrent code
* stream is not a data structure it is an abstraction of functions (with a data source: network, file, etc.)
    * Martin Fowler: [Collection Pipeline Pattern](https://martinfowler.com/articles/collection-pipeline/)
    * it is actually a collection of functions
* limitations
    * stream cannot be reused
    * single pipeline (a single terminal operation)
        * cannot split into two
    * no exceptions handling
* reactive programming (idea formulated by Eric Meijer)
    * the applications we developer, the programs we create must
    be really responsive and be able to react to stimuli in a 
    system
* times when you have to go to bank and talk with a person,
times when you have to go to travel agency to buy tickets
    * in the past companies made products for their employees to use
    and make those employees (nobody cares what they think) available to us the customers
        * now companies build product for real-users, IOT
* In push protocols, the client opens a connection to the server and keeps it constantly active. The server will 
send (push) all new events to the client using that single always-on connection. In other words, the server PUSHes 
the new events to the client.
* In pull protocols, the client periodically connects to the server, checks for and gets (pulls) recent events and 
then closes the connection and disconnects from the server. The client repeats this whole procedure to get updated 
about new events. In this mode, the clients periodically PULLs the new events from the server.
```
private static void pullExample() {
	final List<String> list = Lists.newArrayList("Java", "C", "C++", "PHP", "Go");

	final Iterator<String> iterator = list.iterator();

    while (iterator.hasNext()) {
    	System.out.println(iterator.next());
    }
}

private static void pushExample() {
	final List<String> list = Lists.newArrayList("Java", "C", "C++", "PHP", "Go");

	final Observable<String> observable = Observable.from(list);

	observable.subscribe(System.out::println, System.out::println, () -> System.out.println("We are done!"));
}
```
* https://www.reactivemanifesto.org/
    * OOP four pillars: abstraction, encapsulation, inheritance, polymorphism
    * reactive four pillars 
        * responsive
            * infinite-scrolling
            * providing responsiveness
                * efficiency is attained not by doing tasks faster, but
                by avoiding those that shouldn't be done in the first place
        * resilient: make failure first-class citizen (it is okay
        to fail)
        * elastic - the only reasonable direction to scale is horizontally
        * message driven - do not expose your database instead
        export your data
* how many threads should you create?
    * computation intensive <= # of cores
    * IO intensive = ...
    * http://reactivex.io/RxJava/2.x/javadoc/io/reactivex/schedulers/Schedulers.html
        * Schedulers.io(), Schedulers.computation()
    * https://github.com/mtumilowicz/java12-nio-non-blocking-polling-server-workshop
    * number of threads is therefore strictly limited
        * by memory also
    * example of reactive application
        * excel: if you modify one cell, it propagates to other cells
        * google docs - hundreds of people use it simultaneously
        
|java streams   |reactive streams   |
|---|---|
|pipeline   |pipeline   |
|push data   |push/pull data   |
|lazy   |lazy   |
|0, 1, oo   |0, 1, oo   |
|data only   |3 channels: data, error, complete   |
|exceptions: good luck   |deal with it downstream (error is just another form of data)  |
|sequential vs parallel   |synch vs async   |
|single pipeline (one terminal operation)   |multiple subscribers   |

|reactive streams   |CompletableFuture/Promises   |
|---|---|
|0, 1, oo   |0, 1   |
|3 channels |2 channels (data, error) |

* nonblocking backpressure
    * BUFFER - Buffers all onNext values until the downstream consumes it.
    * DROP - Drops the most recent onNext value if the downstream can't keep up.
    * ERROR - Signals a MissingBackpressureException in case the downstream can't keep up.
    * LATEST - Keeps only the latest onNext value, overwriting any previous value if the downstream can't keep up.
    * MISSING - OnNext events are written without any buffering or dropping.

## definitions
* **[Flow.Publisher](https://docs.oracle.com/javase/9/docs/api/java/util/concurrent/Flow.Publisher.html)** - 
source of data  
* **[Flow.Subscriber](https://docs.oracle.com/javase/9/docs/api/java/util/concurrent/Flow.Subscriber.html)** - 
destination of data  
* **[Flow.Subscription](https://docs.oracle.com/javase/9/docs/api/java/util/concurrent/Flow.Subscription.html)** - 
message control linking a `Flow.Publisher` and `Flow.Subscriber` 
(`Subscriber` signal demand to `Publisher` using `Subscription`)  
* **[Flow.Processor](https://docs.oracle.com/javase/9/docs/api/java/util/concurrent/Flow.Processor.html)** - 
a component that acts as both a `Subscriber` and `Publisher` (can 
consume input and produce output).  
* **[Flow.SubmissionPublisher](https://docs.oracle.com/javase/9/docs/api/java/util/concurrent/SubmissionPublisher.html)** - 
the only one implementation (in `JDK`) of `Flow.Publisher`; has ability to asynchronously issue submitted (non-null) 
items to current subscribers until it is closed.

## data flow
```
PUBLISHER -> PROCESSOR -> PROCESSOR -> SUBSCRIBER
```
We have two scenarios:  
* `Publisher` is slow, `Subscriber` is fast (best scenario)
* `Publisher` is fast, `Subscriber` is slow (the `Subscriber` must deal 
with excessive data - the most naive approach is just to drop all 
excessive data - so the data will be irrevitable)

Note that if we have multiple `subscribers` and one `publisher` - they 
are receiving elements in the same order.

## interaction steps
1. implement `Flow.Publisher` (using, for example `SubmissionPublisher<T>`) and `Flow.Subscriber`
1. the subscriber attempts to subscribe to the publisher by calling the 
`subscribe(Flow.Subscriber<? super T> subscriber)`
method of the publisher
    * success: the publisher asynchronously calls the `onSubscribe(Flow.Subscription subscription)` 
    method of the subscriber
    * failure: `onError(Throwable throwable)` method of the subscriber is called 
    with an `IllegalStateException`, and the interaction ends
1. the subscriber sends a request to the publisher for `N` items calling the `request(N)` 
on the `Subscription`
1. multiple requests are send regardless if earlier are already fulfilled (non-blocking)
1. the publisher calls the `onNext(T item)` method of the subscriber and sends an item in each call
    * if there is no more items to send the publisher calls the `onComplete()` method of the subscriber to signal
the end of stream, and interaction ends
    * note that if subscriber requests `Long.MAX_VALUE` items, the stream becomes not reactive - it is
    effectively a push stream
1. if the publisher encounters an error - calls `onError(Throwable throwable)` on subscriber
1. the subscriber can cancel its subscription by calling the `cancel()` method on its subscription
    * if a subscription is cancelled, the interaction ends 
    * it is possible for the subscriber to receive items after
    cancellation if there were pending requests before

## additional remarks
Correctly `@Override` method `onSubscribe` looks as below:
```
@Override
public void onSubscribe(Flow.Subscription subscription) {
    if (this.subscription == null) {
        this.subscription = subscription;
        this.subscription.request(1); // we handle backpressure through subscription
    }
    else {
        subscription.cancel(); // we handle cancellation through subscription
    }
}
```
because we want our `Subscriber` talk to only one `Publisher` - 
`Subscription` represents a link between single `Subscriber` and single 
`Publisher` so you have to cancel the incoming one (if we already have 
one, we don't accept any furthers).

## tests
We test it by running:
* `RunAnswer`
* `RunWorkshop`
the output should be:
```
onNext, item: new mapping: 2
onNext, item: new mapping: 4
onNext, item: new mapping: 6
onNext, item: new mapping: 8
onNext, item: new mapping: 10
onNext, item: new mapping: 12
...
```
