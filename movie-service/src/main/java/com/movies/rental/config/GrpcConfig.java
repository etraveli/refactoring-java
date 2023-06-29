package com.movies.rental.config;

import com.movies.rental.grpc.CustomerServiceImpl;
import com.movies.rental.service.CustomerService;
import com.movies.rental.service.MovieService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.protobuf.services.ProtoReflectionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Configuration
public class GrpcConfig {

  private final CustomerService customerService;
  private final MovieService movieService;

  public GrpcConfig(CustomerService customerService, MovieService movieService) {
    this.customerService = customerService;
    this.movieService = movieService;
  }

  @Bean
  public void grpcServer() throws IOException, InterruptedException {
    Server server =
        ServerBuilder.forPort(1234)
            .addService(new CustomerServiceImpl(customerService, movieService))
            .addService(ProtoReflectionService.newInstance())
            .build();
    server.start();
    System.out.println("server started in port: " + server.getPort());
  }
}
