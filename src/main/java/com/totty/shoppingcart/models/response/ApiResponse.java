package com.totty.shoppingcart.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private String message;
    private Object data;

    /*    private boolean success;
    private int statusCode;
    private T data;
    private List<String> errors;
    private String path;
    private LocalDateTime timestamp;*/
}
