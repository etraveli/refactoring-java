package com.junjie.movie.rental.dto.converter;

import com.junjie.movie.rental.dto.MovieRentalDto;
import com.junjie.movie.rental.dto.OrderDto;
import com.junjie.movie.rental.entity.MovieRentalOrder;

import java.util.ArrayList;
import java.util.List;

public class OrderConverter {
    public static OrderDto convertModel2Dto(MovieRentalOrder order) {
        List<MovieRentalDto> movieRentalDtoList = new ArrayList<>();
        order.getRentals().stream().forEach(movieRental ->
                movieRentalDtoList.add(MovieRentalConverter.convertModel2Dto(movieRental)));
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setTotalExpense(order.getTotalExpense());
        orderDto.setOrderFrequentPoints(order.getOrderFrequentPoints());
        orderDto.setCustomerId(order.getCustomer().getId());
        orderDto.setMovieRentalDtoList(movieRentalDtoList);
        return orderDto;
    }
}
