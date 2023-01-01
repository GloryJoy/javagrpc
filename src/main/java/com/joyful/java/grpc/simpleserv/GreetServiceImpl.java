package com.joyful.java.grpc.simpleserv;

import com.proto.greet.*;
import io.grpc.Context;
import io.grpc.stub.StreamObserver;

public class GreetServiceImpl extends GreetServiceGrpc.GreetServiceImplBase {
    @Override
    public void greet(GreetRequest request, StreamObserver<GreetResponse> responseObserver) {
//        super.greet(request, responseObserver);

        //extract the fields we need
        Greeting greeting = request.getGreeting();
        String firstName = greeting.getFirstName();
        String result = "Hello " + firstName;

        //create the response
        GreetResponse greetResponse = GreetResponse.newBuilder()
                .setResult(result)
                .build();

        //send the response
        responseObserver.onNext(greetResponse);

        //complete the RPC call
        responseObserver.onCompleted();
    }

    @Override
    public void greetManyTimes(GreetManytimesRequest request, StreamObserver<GreetManytimesResponse> responseObserver) {
        String firstName = request.getGreeting().getFirstName();

        try {

            for (int i = 0; i < 10; i++) {
                String result = "Hello " + firstName + ", response number: " + i;
                GreetManytimesResponse greetManytimesResponse = GreetManytimesResponse.newBuilder()
                        .setResult(result)
                        .build();

                responseObserver.onNext(greetManytimesResponse);
                Thread.sleep(1000L);
            }
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        } finally {
            responseObserver.onCompleted();
        }

    }

    @Override
    public StreamObserver<LongGreetRequest> longGreet(StreamObserver<LongGreetResponse> responseObserver) {
        StreamObserver<LongGreetRequest> streamObserverRequest =
                new StreamObserver<LongGreetRequest>() {
                    String result = "";

                    @Override
                    public void onNext(LongGreetRequest longGreetRequest) {
                        result += "Hello " + longGreetRequest.getGreeting().getFirstName() + "! ";

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onCompleted() {
                        //this is when we want to return a response
                        responseObserver.onNext(LongGreetResponse.newBuilder()
                                .setResult(result)
                                .build());
                        responseObserver.onCompleted();
                    }
                };
        return streamObserverRequest;
    }

    @Override
    public StreamObserver<GreetEveryoneRequest> greetEveryone(StreamObserver<GreetEveryoneResponse> responseObserver) {
        StreamObserver<GreetEveryoneRequest> requestStreamObserver = new StreamObserver<GreetEveryoneRequest>() {
            @Override
            public void onNext(GreetEveryoneRequest greetEveryoneRequest) {
                // Server is doing something here.. then send back response
                String response = "Hello " + greetEveryoneRequest.getGreeting().getFirstName();
                GreetEveryoneResponse greetEveryoneResponse = GreetEveryoneResponse
                        .newBuilder()
                        .setResult(response)
                        .build();

                responseObserver.onNext(greetEveryoneResponse);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {

                responseObserver.onCompleted();
                ;

            }
        };
        return requestStreamObserver;

    }

    @Override
    public void greetWithDeathline(GreetWithDeathlineRequest request, StreamObserver<GreetWithDeathlineResponse> responseObserver) {

        Context context = Context.current();

        try {
            for (int i = 0; i < 3; i++) {
                if (!context.isCancelled()) {
                    System.out.println("sleeping for 100ms");
                    Thread.sleep(100L);
                } else {
                    return;
                }
                responseObserver.onNext(
                        GreetWithDeathlineResponse
                                .newBuilder()
                                .setResult(" Hello, " + request.getGreeting().getFirstName() + "")
                                .build());
                responseObserver.onCompleted();

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
