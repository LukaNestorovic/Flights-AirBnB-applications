package com.example.profile.service;


import com.example.profile.UserServiceGrpc;
import com.example.profile.Username;
import com.example.profile.exception.UserNotFoundException;
import com.example.profile.model.dtogrpc.UserDto;
import com.example.profile.model.dtogrpc.UserNameDto;
import com.example.profile.repository.UserRepository;
import com.example.profile.userUsername;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.Optional;


@GrpcService
public class UserGrpcServiceImpl extends UserServiceGrpc.UserServiceImplBase {
    private final UserRepository userRepository;

    public UserGrpcServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void getUserByUsername(Username username, StreamObserver<userUsername> responseObserver){
        UserNameDto dto = userRepository.findByUsername(username.getUsername())
                .map(user -> new UserNameDto(user.getId(), user.getUsername()))
                        .orElseThrow(() -> new UserNotFoundException("Nije pronadjen user"));
        responseObserver.onNext(
                userUsername.newBuilder()
                        .setUserId(dto.getId())
                        .setUsername(dto.getUserUsername())
                        .build());
        responseObserver.onCompleted();
    }
}
