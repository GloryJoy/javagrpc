package com.joyful.java.grpc.calculator.client;

import com.joyful.java.proto.calculator.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CalculatorClient {

    ManagedChannel managedChannel;

    public void run() {

        ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress("localhost", 50052)
                .usePlaintext()
                .build();


//        doUnaryCall(managedChannel);
//        doServerStreamingCall(managedChannel);
//        doClientStreamingCall(managedChannel);
//        doBiDiStreamingCall(managedChannel);
        doSquareRootCall(managedChannel);

        managedChannel.shutdown();

    }

    private void doSquareRootCall(ManagedChannel managedChannel) {
        CalculatorServiceGrpc.CalculatorServiceBlockingStub calculatorServiceBlockingStub = CalculatorServiceGrpc.newBlockingStub(managedChannel);

        Integer number = 15;

        SquareRootRequest squareRootRequest = SquareRootRequest
                .newBuilder()
                .setNumber(number)
                .build();

        SquareRootResponse squareRootResponse = calculatorServiceBlockingStub.sqareRoot(squareRootRequest);
        System.out.println("The server response is " + squareRootResponse.getRootedNumber());

        number = -1;

        squareRootRequest = SquareRootRequest
                .newBuilder()
                .setNumber(number)
                .build();

        try {
            squareRootResponse = calculatorServiceBlockingStub.sqareRoot(squareRootRequest);

        } catch (StatusRuntimeException sre){
            System.out.println(sre.getMessage());
            System.out.println("The error status is -> " + sre.getStatus());
            System.out.println("The trailer is -> " + sre.getTrailers());
        }


    }

    private void doBiDiStreamingCall(ManagedChannel managedChannel) {

        CalculatorServiceGrpc.CalculatorServiceStub asyncClient =
                CalculatorServiceGrpc.newStub(managedChannel);
        CountDownLatch countDownLatch = new CountDownLatch(1);

        StreamObserver<FindMaximumRequest> requestStreamObserver = asyncClient.findMaximum(
                new StreamObserver<FindMaximumResponse>() {
                    @Override
                    public void onNext(FindMaximumResponse findMaximumResponse) {
                        //Server is responding back, handled in this section

                        System.out.println("Server is sending maximum number = " + findMaximumResponse.getMaximum());
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("Server has completed.");
                        countDownLatch.countDown();

                    }
                }
        );

        Arrays.asList(2, 3, 4, 5, 6, 7, 3, 4, 5, 9).forEach(
                number -> {
                    System.out.println("Sending " + number + " to server...");
                    requestStreamObserver.onNext(
                            FindMaximumRequest
                                    .newBuilder()
                                    .setNumber(number)
                                    .build()
                    );
                    try {
                        Thread.sleep(
                                100L
                        );
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );

        requestStreamObserver.onCompleted();
        try {
            countDownLatch.await(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void doClientStreamingCall(ManagedChannel managedChannel) {

        CalculatorServiceGrpc.CalculatorServiceStub asyncClient =
                CalculatorServiceGrpc.newStub(managedChannel);
        CountDownLatch countDownLatch = new CountDownLatch(1);

        StreamObserver<ComputeAverageRequest> requestStreamObserver = asyncClient.computeAverage(
                new StreamObserver<ComputeAverageResponse>() {


                    @Override
                    public void onNext(ComputeAverageResponse computeAverageResponse) {
                        System.out.println("Received a response from server");
                        System.out.println(computeAverageResponse.getAverage());

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("Server has completed sending us data");
                        countDownLatch.countDown();

                    }
                }
        );

        List<ComputeAverageRequest> averageRequestList =
                List.of(
                        ComputeAverageRequest.newBuilder()
                                .setNumber(2).build(),
                        ComputeAverageRequest.newBuilder()
                                .setNumber(3).build(),
                        ComputeAverageRequest.newBuilder()
                                .setNumber(4).build(),
                        ComputeAverageRequest.newBuilder()
                                .setNumber(5).build()
                );

        averageRequestList.stream().forEach(
                computeAverageRequest -> {
                    System.out.println("Sending number for computing...");
                    requestStreamObserver.onNext(computeAverageRequest);
                }
        );

        requestStreamObserver.onCompleted();
        try {
            countDownLatch.await(3000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    private void doServerStreamingCall(ManagedChannel managedChannel) {

        CalculatorServiceGrpc.CalculatorServiceBlockingStub calculatorServiceBlockingStub = CalculatorServiceGrpc.newBlockingStub(managedChannel);
        System.out.println("gRPC start");
        Integer number = 567890890;
        calculatorServiceBlockingStub.primeNumberDecomposition(
                PrimeNumberDecomositionRequest.newBuilder()
                        .setNumber(number)
                        .build()
        ).forEachRemaining(primeNumberDecompositionResponse -> {
            System.out.println(primeNumberDecompositionResponse.getPrimeFactor());
        });
    }

    private void doUnaryCall(ManagedChannel managedChannel) {
        CalculatorServiceGrpc.CalculatorServiceBlockingStub calculatorServiceBlockingStub = CalculatorServiceGrpc.newBlockingStub(managedChannel);
        SumRequest sumRequest = SumRequest.newBuilder()
                .setFirstNumber(10)
                .setSecondNumber(25)
                .build();

        SumResponse sumResponse = calculatorServiceBlockingStub.sum(sumRequest);

        System.out.println(sumRequest.getFirstNumber() + " + " + sumRequest.getSecondNumber() + " = " + sumResponse.getSumResult());
    }

    public static void main(String[] args) {

        CalculatorClient calculatorClient = new CalculatorClient();
        calculatorClient.run();
    }
}
