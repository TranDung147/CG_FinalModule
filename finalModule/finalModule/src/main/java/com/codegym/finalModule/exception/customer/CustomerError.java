package com.codegym.finalModule.exception.customer;


import lombok.Getter;

@Getter
public enum CustomerError {

    CUSTOMER_NOTFOUND("Khách hàng không tồn tại !") ,
    ID_NOTFOUND("ID khách hàng không tồn tại !") ;

    private final String message;
    CustomerError(String message) {
        this.message = message;
    }
}
