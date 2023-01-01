package com.joyful.java.grpc.blog.server;

import com.joyful.java.grpc.calculator.server.CalculatorServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.protobuf.services.ProtoReflectionService;

import java.io.IOException;

public class BlogServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(50052)
                .addService(new BlogServiceImpl())
                .addService(ProtoReflectionService.newInstance())
                .build();

        server.start();
        System.out.println("Server is started");

        Runtime.getRuntime()
                .addShutdownHook(new Thread(() -> {
                            System.out.println("Received Shutdown Request");
                            server.shutdown();
                            System.out.println("Successfully stopped the server");
                        })
                );

        server.awaitTermination();
    }
}
