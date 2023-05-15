package com.example.reservations.service;

import com.example.reservations.model.Reservation;
import com.example.reservations.repository.ReservationRepository;
import com.reservation.Request;
import com.reservation.ReservationGrpcDto;
import com.reservation.Response;
import com.reservation.SuiteServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@GrpcService
@RequiredArgsConstructor

public class ReservationGrpcService extends SuiteServiceGrpc.SuiteServiceImplBase {

    private final ReservationRepository reservationRepository;



    @Override
    public void getAllReservations(Request request, StreamObserver<Response> responseObserver) {
   /*     List<Reservation> reservations = reservationRepository.findAll();
        List<ReservationGrpcDto> response = new ArrayList<>();

        for(Reservation reservation : reservations){
            System.out.println(reservation.getId());
            ReservationGrpcDto reservationGrpcDto = ReservationGrpcDto.newBuilder()
                    .setId(reservation.getId())
                    .setSuiteId(reservation.getSuiteId())
                    .setStartDate(reservation.getStartDate().toString())
                    .setEndDate(reservation.getEndDate().toString())
                    .setNumber(reservation.getNumber())
                    .setStatus(reservation.getStatus())
                    .setUserId(reservation.getUserId())
                    .setHostId(reservation.getHostId())
                    .build();
            response.add(reservationGrpcDto);
        }
        Response res = Response.newBuilder().addAllReservations(response).build();
        responseObserver.onNext(res);
        responseObserver.onCompleted();*/

        var response = Response.newBuilder()
                .addAllReservations(reservationRepository.findAll().stream().map(this::convert).collect(Collectors.toSet()))
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

    private ReservationGrpcDto convert(Reservation reservation){
        return ReservationGrpcDto.newBuilder()
                .setId(reservation.getId())
                .setSuiteId(reservation.getSuiteId())
                .setStartDate(reservation.getStartDate().toString())
                .setEndDate(reservation.getEndDate().toString())
                .setNumber(reservation.getNumber())
                .setStatus(reservation.getStatus())
                .setUserId(reservation.getUserId())
                .setHostId(reservation.getHostId())
                .build();
    }
}
