package com.movie.rental.app.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The BaseResponse class is used to transfer response data
 *
 * @author pabasara8857@gmail.com
 */
@NoArgsConstructor
@Data
public class BaseResponse<T> {

  /** actual result */
  private T result;

  public BaseResponse(T result) {
    this.result = result;
  }
}
