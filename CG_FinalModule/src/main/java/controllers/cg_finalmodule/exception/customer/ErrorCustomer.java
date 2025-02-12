package controllers.cg_finalmodule.exception.customer;


import lombok.Getter;
import lombok.Setter;

@Getter
public enum ErrorCustomer {

    CUSTOMER_NOT_FOUND("Khách hàng không tồn tại !") ;
    private final String message;
    ErrorCustomer(String message) {
        this.message = message;
    }

}
