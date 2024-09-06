package com.totty.shoppingcart.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private String message;
    private int statusCode;
    private boolean success;
    private T data;
    private List<String> errors;
    private String path;
    private LocalDateTime timestamp;
}
