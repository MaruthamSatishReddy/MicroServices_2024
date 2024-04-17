package com.easyToBuy.Order.exception;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@Data
public class ErrorObject {
    private String errorMessage;
    private Integer errorCode;
}
