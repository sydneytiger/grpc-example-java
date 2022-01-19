package com.github.sydneytiger.grpc.sum;

import com.github.sydneytiger.grpc.sum.SumServiceGrpc.SumServiceImplBase;
import io.grpc.stub.StreamObserver;

public class SumServiceImpl extends SumServiceImplBase {
    @Override
    public void sum(SumRequest request, StreamObserver<SumResponse> responseObserver) {
        float first_number = request.getFirstNumber();
        float second_number = request.getSecondNumber();

        SumResponse response = SumResponse.newBuilder()
                .setResult(first_number + second_number)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
