package com.github.sydneytiger.grpc.server;

import com.github.sydneytiger.grpc.greeting.GreetServiceImpl;
import com.github.sydneytiger.grpc.sum.SumServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class MyServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Hello, I am a gRPC server");

        Server server = ServerBuilder
                .forPort(50051)
                .addService(new GreetServiceImpl()) // add service implementationi
                .addService(new SumServiceImpl())
                .build();

        server.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Received Shutdown Request");
            server.shutdown();
            System.out.println("Successfully stopped the server");
        }));

        server.awaitTermination();
    }
}
