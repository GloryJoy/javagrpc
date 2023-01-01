package com.joyful.java.grpc.client;

import com.joyful.java.grpc.simpleserv.GreetServiceImpl;
import com.proto.greet.*;
import io.grpc.*;
import io.grpc.netty.shaded.io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import io.grpc.stub.StreamObserver;

import javax.net.ssl.SSLException;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


public class GreetingClient {

    ManagedChannel managedChannel;

    public void run() {
//        managedChannel = ManagedChannelBuilder.forAddress("localhost", 50051)
//                .usePlaintext()
//                .build();

        try {
            managedChannel = NettyChannelBuilder.forAddress("localhost", 50051)
                    .sslContext(
                            GrpcSslContexts
                                    .forClient()
                                    .trustManager(new File("ssl/ca.crt"))
                                    .build())
                    .build();
        } catch (SSLException e) {
            e.printStackTrace();
        }

        doUnaryCall(managedChannel);
//        doServerStreamingCall(managedChannel);
//        doClientStreamingCall(managedChannel);
//        doBiDiStreamCall(managedChannel);
        doUnaryCallWithDeathline(managedChannel);

        System.out.println("gRPC was run");


        managedChannel.shutdown();


    }

    private void doUnaryCallWithDeathline(ManagedChannel managedChannel) {
        //create a greet service client (blocking)
        GreetServiceGrpc.GreetServiceBlockingStub greetServiceBlockingStub = GreetServiceGrpc.newBlockingStub(managedChannel);

        GreetWithDeathlineRequest greetWithDeathlineRequest =
                GreetWithDeathlineRequest.newBuilder()
                        .setGreeting(
                                Greeting
                                        .newBuilder()
                                        .setFirstName("Joy")
                                        .setLastName("Glory")
                                        .build()

                        )
                        .build();

        try {
            System.out.println("Sending a request ...with 3000ms deathline");
            GreetWithDeathlineResponse greetWithDeathlineResponse = greetServiceBlockingStub
                    .withDeadline(Deadline.after(3000L, TimeUnit.MILLISECONDS))
                    .greetWithDeathline(greetWithDeathlineRequest);
            System.out.println("The result is " + greetWithDeathlineResponse.getResult());

        } catch (StatusRuntimeException sre) {
            if (sre.getStatus() == Status.DEADLINE_EXCEEDED) {
                System.out.println("The deathline has been exceeded, and the client does not wait for the server to complete " + sre.getMessage());
            } else {
                sre.printStackTrace();
            }
        }


        try {
            System.out.println("Sending a request ...with 100ms deathline");
            GreetWithDeathlineResponse greetWithDeathlineResponse = greetServiceBlockingStub
                    .withDeadline(Deadline.after(100L, TimeUnit.MILLISECONDS))
                    .greetWithDeathline(greetWithDeathlineRequest);
            System.out.println("The result is " + greetWithDeathlineResponse.getResult());

        } catch (StatusRuntimeException sre) {
            if (sre.getStatus().getCode() == Status.Code.DEADLINE_EXCEEDED) {
                System.out.println("The deathline has been exceeded, and the client does not wait for the server to complete " + sre.getMessage());
            } else {
                System.out.println("status = " + sre.getStatus() + " and message = " + sre.getMessage());

                sre.printStackTrace();
            }
        }


    }

    private void doBiDiStreamCall(ManagedChannel managedChannel) {
        GreetServiceGrpc.GreetServiceStub asyncClient = GreetServiceGrpc.newStub(managedChannel);
        CountDownLatch countDownLatch = new CountDownLatch(1);

        StreamObserver<GreetEveryoneRequest> requestStreamObserver = asyncClient.greetEveryone(
                new StreamObserver<GreetEveryoneResponse>() {
                    @Override
                    public void onNext(GreetEveryoneResponse greetEveryoneResponse) {
                        // Getting something back from server therefore it is handling server responses here.
                        System.out.println("Response from server " + greetEveryoneResponse.getResult());
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        countDownLatch.countDown();

                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("Server is done sending data");
                        countDownLatch.countDown();

                    }
                }
        );

        //Sending messages to server -- therefore, it is sending request here.
        Arrays.asList("John", "Joy", "Marc", "April")
                .forEach(
                        name -> requestStreamObserver.onNext(
                                GreetEveryoneRequest
                                        .newBuilder()
                                        .setGreeting(
                                                Greeting
                                                        .newBuilder()
                                                        .setFirstName(name)
                                                        .build()
                                        )
                                        .build()
                        )
                );
        requestStreamObserver.onCompleted();

        try {
            countDownLatch.await(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void doClientStreamingCall(ManagedChannel managedChannel) {
        GreetServiceGrpc.GreetServiceStub asyncClient = GreetServiceGrpc.newStub(managedChannel);
        CountDownLatch countDownLatch = new CountDownLatch(1);

        StreamObserver<LongGreetRequest> longGreetRequestStreamObserver = asyncClient.longGreet(new StreamObserver<LongGreetResponse>() {
            @Override
            public void onNext(LongGreetResponse longGreetResponse) {
                // we a response from the server
                // onNext will be called only once
                System.out.println("Received a response from the server");
                System.out.println(longGreetResponse.getResult());

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                //The server is done sending us the data
                // onCompleted will be called right after onNext()
                countDownLatch.countDown();
                System.out.println("Server has completed sending us something");

            }
        });

        List<LongGreetRequest> greetingList = List.of(
                LongGreetRequest
                        .newBuilder()
                        .setGreeting(
                                Greeting
                                        .newBuilder()
                                        .setFirstName("Glory")
                                        .build()
                        )
                        .build(),
                LongGreetRequest
                        .newBuilder()
                        .setGreeting(
                                Greeting
                                        .newBuilder()
                                        .setFirstName("John")
                                        .build())
                        .build(),
                LongGreetRequest
                        .newBuilder()
                        .setGreeting(
                                Greeting
                                        .newBuilder()
                                        .setFirstName("Biden")
                                        .build())
                        .build()
        );

        greetingList.stream().forEach(
                greeting -> {
                    System.out.println("Sending message...");
                    longGreetRequestStreamObserver.onNext(greeting);
                }
        );

        longGreetRequestStreamObserver.onCompleted();

        try {
            countDownLatch.await(3L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doServerStreamingCall(ManagedChannel managedChannel) {


        GreetManytimesRequest greetManytimesRequest = GreetManytimesRequest
                .newBuilder()
                .setGreeting(Greeting.newBuilder()
                        .setFirstName("Glory Joy")
                        .setLastName("Joy")
                        .build())
                .build();


        GreetServiceGrpc.GreetServiceBlockingStub greetServiceBlockingStub = GreetServiceGrpc.newBlockingStub(managedChannel);
        greetServiceBlockingStub.greetManyTimes(greetManytimesRequest)
                .forEachRemaining(greetManytimesResponse -> System.out.println(greetManytimesResponse.getResult()));

    }

    private void doUnaryCall(ManagedChannel managedChannel) {

        //create a greet service client (blocking)
        GreetServiceGrpc.GreetServiceBlockingStub greetServiceBlockingStub = GreetServiceGrpc.newBlockingStub(managedChannel);

        //create a protocol buffer greeting message
        Greeting greeting = Greeting.newBuilder()
                .setFirstName("Joy")
                .setLastName("Joy")
                .build();
        // do the same for a gree request
        GreetRequest greetRequest = GreetRequest.newBuilder()
                .setGreeting(greeting)
                .build();

        // call the RPC and get back a GreetResponse
        GreetResponse greetResponse = greetServiceBlockingStub.greet(greetRequest);

        System.out.println(greetResponse.getResult());

    }


    public static void main(String[] args) {
        System.out.println("Hello I'm a gRPC client");

        GreetingClient greetingClientMain = new GreetingClient();

        greetingClientMain.run();

    }

}
