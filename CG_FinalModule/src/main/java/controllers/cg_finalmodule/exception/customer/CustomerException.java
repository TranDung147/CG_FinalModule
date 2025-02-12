package controllers.cg_finalmodule.exception.customer;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerException extends RuntimeException {

    private ErrorCustomer errorCustomer;
    public CustomerException(ErrorCustomer errorCustomer) {
        super(errorCustomer.getMessage());
        this.errorCustomer = errorCustomer;
    }
}
