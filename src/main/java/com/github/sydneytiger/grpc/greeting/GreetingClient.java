package com.github.sydneytiger.grpc.greeting;

import com.github.sydneytiger.grpc.greet.GreetRequest;
import com.github.sydneytiger.grpc.greet.GreetResponse;
import com.github.sydneytiger.grpc.greet.GreetServiceGrpc;
import com.github.sydneytiger.grpc.greet.Greeting;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

// gRPC client (stub) boilerplate
public class GreetingClient {
    public static void main(String[] args) {
        System.out.println("Hello, I am a gRPC greeting client");

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50051)
                .usePlaintext() // by pass SSL security, do not use it in prod
                .build();

        System.out.println("Creating stub");
        // DummyServiceGrpc.DummyServiceBlockingStub syncClient = DummyServiceGrpc.newBlockingStub(channel);
        // DummyServiceGrpc.DummyServiceFutureStub asyncClient = DummyServiceGrpc.newFutureStub(channel);

        GreetServiceGrpc.GreetServiceBlockingStub greetClient = GreetServiceGrpc.newBlockingStub(channel);
        Greeting greetMessage = Greeting.newBuilder()
                                .setFirstName("Tiger")
                                .setLastName("Chen")
                                .build();

        GreetResponse response = greetClient.greet(GreetRequest.newBuilder().setGreeting(greetMessage).build());

        System.out.println(response.getResult());

        // do something here
        System.out.println("Shutting down channel");
        channel.shutdown();
        System.out.println("channel shutdown successfully");

    }
}
