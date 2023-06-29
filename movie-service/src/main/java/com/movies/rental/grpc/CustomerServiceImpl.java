package com.movies.rental.grpc;

import com.movies.rental.*;
import com.movies.rental.handler.RentalInfo;
import com.movies.rental.service.CustomerService;
import com.movies.rental.service.MovieService;
import com.movies.rental.service.repository.entity.Customer;
import io.grpc.stub.StreamObserver;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@AllArgsConstructor
public class CustomerServiceImpl extends CustomerServiceGrpc.CustomerServiceImplBase {

  private final CustomerService customerService;

  private final MovieService movieService;

  @Override
  public void getCustomer(
      GetCustomerRequest request, StreamObserver<GetCustomerResponse> responseObserver) {
    try {
      Customer customer = customerService.fetchAll(request.getCustomerId());
      responseObserver.onNext(
          GetCustomerResponse.newBuilder().setCustomer(extracted(customer)).build());
    } finally {
      responseObserver.onCompleted();
    }
  }

  @Override
  public void postCustomer(
      PostCustomerRequest request, StreamObserver<PostCustomerResponse> responseObserver) {
    try {
      Customer customer = customerService.fetchAll(request.getCustomerId());
      try {
        new RentalInfo(customerService, movieService)
            .statement(customer, request.getMovieRentalList());
        responseObserver.onNext(
            PostCustomerResponse.newBuilder()
                .setMessage("Updated with new movie rental data")
                .build());
      } catch (RuntimeException e) {
        e.printStackTrace();
      }
    } finally {
      responseObserver.onCompleted();
    }
  }

  public com.movies.rental.Customer extracted(Customer customer) {
    List<com.movies.rental.MovieRental> movieRentalsProtobuf = new ArrayList<>();

    for (var mr : customer.getMovieRentals()) {
      com.movies.rental.MovieRental movieRental =
          com.movies.rental.MovieRental.newBuilder()
              .setMovieId(mr.getMovieId())
              .setTitle(movieService.fetch(mr.getMovieId()).getTitle())
              .setAmountPerMovie(mr.getAmountPerMovie())
              .setDaysOfRental(mr.getDaysOfRental())
              .setGenre(movieService.fetch(mr.getMovieId()).getGenre().name())
              .build();

      movieRentalsProtobuf.add(movieRental);
    }

    return com.movies.rental.Customer.newBuilder()
        .setCustomerId(customer.getCustomerId())
        .setName(customer.getName())
        .addAllMovieRental(movieRentalsProtobuf)
        .setTotalFrequentRenterPoints(customer.getBonusPoints())
        .setTotalAmount(customer.getTotalAmount())
        .build();
  }
}
