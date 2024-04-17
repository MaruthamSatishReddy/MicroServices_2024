package com.easyToBuy.Order.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException (String message){
       super(message);
    }
}
