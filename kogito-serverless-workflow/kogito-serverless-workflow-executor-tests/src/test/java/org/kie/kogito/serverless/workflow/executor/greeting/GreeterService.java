package org.kie.kogito.serverless.workflow.executor.greeting;

import java.util.stream.Collectors;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class GreeterService extends GreeterGrpc.GreeterImplBase {

    public static Server buildServer(int port) {
        return ServerBuilder.forPort(port).addService(new GreeterService()).build();
    }

    @Override
    public void sayHello(HelloRequest request,
            StreamObserver<HelloReply> responseObserver) {
        responseObserver.onNext(buildReply(request));
        responseObserver.onCompleted();
    }

    @Override
    public void sayHelloArray(HelloArrayRequest requests,
            StreamObserver<HelloArrayReply> responseObserver) {
        responseObserver.onNext(HelloArrayReply.newBuilder().addAllReplies(requests.getRequestsList().stream().map(request -> buildReply(request)).collect(Collectors.toList())).build());
        responseObserver.onCompleted();
    }

    private HelloReply buildReply(HelloRequest request) {
        String message;
        switch (request.getLanguage().toLowerCase()) {
            case "spanish":
                message = "Saludos desde gRPC service " + request.getName();
                break;
            case "italian":
                message = "Boungiorno " + request.getName();
                break;
            case "catalan":
                message = "Bon dia" + request.getName();
                break;
            case "english":
            default:
                message = "Hello from gRPC service " + request.getName();
        }
        return HelloReply.newBuilder().setMessage(message).setState(request.getInnerHello().getUnknown() ? State.UNKNOWN : State.SUCCESS)
                .setInnerMessage(InnerMessage.newBuilder().setNumber(23).build()).build();
    }
}
