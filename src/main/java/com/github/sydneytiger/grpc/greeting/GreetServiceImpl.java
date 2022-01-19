package com.github.sydneytiger.grpc.greeting;

import com.github.sydneytiger.grpc.greet.GreetRequest;
import com.github.sydneytiger.grpc.greet.GreetResponse;
import com.github.sydneytiger.grpc.greet.GreetServiceGrpc;
import com.github.sydneytiger.grpc.greet.Greeting;
import io.grpc.stub.StreamObserver;

public class GreetServiceImpl extends GreetServiceGrpc.GreetServiceImplBase {

    @Override
    public void greet(GreetRequest request, StreamObserver<GreetResponse> responseObserver) {
        // extract data from request
        Greeting greeting = request.getGreeting();
        String firstName = greeting.getFirstName();
        String lastName = greeting.getLastName();

        // build response
        String result = "Hello " + firstName + " " + lastName;
        GreetResponse response = GreetResponse.newBuilder()
                .setResult(result)
                .build();

        // send the response
        responseObserver.onNext(response);

        // complete the RPC call
        responseObserver.onCompleted();
    }
}
