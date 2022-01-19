package com.github.sydneytiger.grpc.sum;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class SumClient {
    public static void main(String[] args) {
        System.out.println("Hello, I am a gRPC sum client");

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50051)
                .usePlaintext() // by pass SSL security, do not use it in prod
                .build();

        SumServiceGrpc.SumServiceBlockingStub client = SumServiceGrpc.newBlockingStub(channel);

        SumResponse response =  client.sum(SumRequest.newBuilder().setFirstNumber(100).setSecondNumber(12).build());

        System.out.println(response.getResult());

        System.out.println("Shutting down channel");
        channel.shutdown();
        System.out.println("channel shutdown successfully");
    }
}
