package com.junjie.movie.rental.controller;

import com.junjie.movie.rental.dto.OrderDto;
import com.junjie.movie.rental.dto.converter.OrderConverter;
import com.junjie.movie.rental.service.MovieRentalOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/order")
@RestController
public class OrderController {
    @Autowired
    private MovieRentalOrderService orderService;

    @GetMapping("/{orderId}")
    public OrderDto getOrderById(@PathVariable("orderId") Long orderId) {
        return OrderConverter.convertModel2Dto(orderService.findOrderById(orderId));
    }

    @GetMapping
    public List<OrderDto> getAllOrders() {
        var list = new ArrayList<OrderDto>();
        orderService.findAllOrder().stream().forEach(movieRentalOrder ->
                list.add(OrderConverter.convertModel2Dto(movieRentalOrder)));
        return list;
    }

    @PostMapping
    public OrderDto createOrder(@Valid @RequestBody OrderDto orderDto) {
        return OrderConverter.convertModel2Dto(
                orderService.createOrder(orderDto));
    }

    @DeleteMapping("/{orderId}")
    public void deleteOrder(@PathVariable("orderId") Long orderId) {
        orderService.deleteOrderById(orderId);
    }


}
