package pl.bank.demo.account.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class OperationNotAllowedAdvice {
    @ResponseBody
    @ExceptionHandler(OperationNotAllowedException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public String behaviourNotFoundHandler (OperationNotAllowedException ex) {
        return ex.getMessage();
    }
}