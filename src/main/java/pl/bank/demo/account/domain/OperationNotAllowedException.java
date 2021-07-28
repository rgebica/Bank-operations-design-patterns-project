package pl.bank.demo.account.domain;

public class OperationNotAllowedException extends RuntimeException {
    public OperationNotAllowedException(String errorMessage) {
        super(errorMessage);
    }
}
