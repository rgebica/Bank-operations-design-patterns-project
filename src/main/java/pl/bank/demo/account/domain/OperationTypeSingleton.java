package pl.bank.demo.account.domain;

public class OperationTypeSingleton {

    private static OperationTypeSingleton operationTypeSingleInstance = null;

    String payment;
    String withdrawal;
    String transaction;

    public OperationTypeSingleton() {
        payment = "PAYMENT";
        withdrawal = "WITHDRAWAL";
        transaction = "TRANSACTION";
    }

    public static OperationTypeSingleton getInstance()
    {
        if (operationTypeSingleInstance == null)
            operationTypeSingleInstance = new OperationTypeSingleton();

        return operationTypeSingleInstance;
    }
}
