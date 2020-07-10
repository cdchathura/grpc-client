package com.chathura.edu.grpcsamplekubernetes;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.internal.DnsNameResolverProvider;
import io.grpc.util.RoundRobinLoadBalancerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.UnknownHostException;


@RestController
public class HelloWorldController {





    @GetMapping(path = "/client")
    public String helloWorld() throws UnknownHostException {
        System.out.println("\n Calling======");
         final ManagedChannel channel = ManagedChannelBuilder.forTarget(System.getenv("ECHO_SERVICE_TARGET"))
                .nameResolverFactory(new DnsNameResolverProvider())  // this is on by default
                .loadBalancerFactory(RoundRobinLoadBalancerFactory.getInstance())
                .usePlaintext(true)
                .build();
        HelloServiceGrpc.HelloServiceBlockingStub stub
                = HelloServiceGrpc.newBlockingStub(channel);
        HelloResponse helloResponse = stub.hello(HelloRequest.newBuilder()
                .setFirstName("Chathura")
                .setLastName("Dissanayake")
                .build());
        channel.shutdown();
        System.out.println("\n got a response======");
        return helloResponse.getGreeting() ;
    }


}
