package com.junjie.movie.rental.service;

import com.google.common.collect.Lists;
import com.junjie.movie.rental.dto.OrderDto;
import com.junjie.movie.rental.entity.*;
import com.junjie.movie.rental.entity.price.strategy.PricerContext;
import com.junjie.movie.rental.exception.ResourceNotFoundException;
import com.junjie.movie.rental.repository.CustomerRepository;
import com.junjie.movie.rental.repository.MovieRepository;
import com.junjie.movie.rental.repository.MovieRentalOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieRentalOrderService {
    @Autowired
    private MovieRentalOrderRepository orderRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public MovieRentalOrder createOrder(OrderDto orderDto) {
        var order = new MovieRentalOrder();
        var rentals = new ArrayList<MovieRental>();
        double totalExpense = 0.0;
        int orderFreequencyEnterPoints = 0;
        var movieRentalDtos = orderDto.getMovieRentalDtoList();
        for (int index = 0; index < movieRentalDtos.size(); index++) {
            var movieId = movieRentalDtos.get(index).getMovieId();
            var movie = movieRepository.findById(movieId).orElseThrow(() ->
                            new ResourceNotFoundException(String.format("the movieId %s does not exist!", movieId)));
            var days = movieRentalDtos.get(index).getRentDays();
            var pricerContext = new PricerContext(movie.getType());
            double expense = pricerContext.getRentExpense(movieRentalDtos.get(index).getRentDays());
            totalExpense += expense;
            int frequencyPoint = pricerContext.getFrequencyEnterPoints(movieRentalDtos.get(index).getRentDays());
            orderFreequencyEnterPoints += frequencyPoint;
            var movieRental = new MovieRental(days, expense, movie, order);
            rentals.add(movieRental);
        }
        var customer = customerRepository.findById(
                orderDto.getCustomerId()).orElseThrow(() ->
                new ResourceNotFoundException(String.format("the customerId %s does not exist!", orderDto.getCustomerId())));
        customer.setFrequentEnterPoints(customer.getFrequentEnterPoints() + orderFreequencyEnterPoints);
        order.setCustomer(customer);
        order.setRentals(rentals);
        order.setTotalExpense(totalExpense);
        order.setOrderFrequentPoints(orderFreequencyEnterPoints);
        return orderRepository.save(order);
    }

    @Transactional(readOnly = true)
    public MovieRentalOrder findOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() ->
                new ResourceNotFoundException(String.format("the orderID %s does not exist!", orderId)));
    }

    @Transactional(readOnly = true)
    public List<MovieRentalOrder> findAllOrder() {
        return Lists.newArrayList(orderRepository.findAll());
    }

    @Transactional(readOnly = true)
    public void deleteOrderById(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
