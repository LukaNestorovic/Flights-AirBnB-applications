package com.example.suites.config;

import com.reservation.SuiteServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcConfiguration {
    @Value("${grpc.server.host}")
    private String grpcServerHost;

    @Value("${grpc.server.port}")
    private int grpcServerPort;

    @Bean
    public ManagedChannel managedChannel() {
        return ManagedChannelBuilder.forAddress(grpcServerHost, grpcServerPort)
                .usePlaintext()
                .build();
    }

    @Bean
    public SuiteServiceGrpc.SuiteServiceBlockingStub suiteServiceBlockingStub(ManagedChannel managedChannel) {
        return SuiteServiceGrpc.newBlockingStub(managedChannel);
    }
}
