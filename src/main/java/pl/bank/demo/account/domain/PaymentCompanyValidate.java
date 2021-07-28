package pl.bank.demo.account.domain;

import pl.bank.demo.account.dto.UpdateBalanceDto;
import pl.bank.demo.account.validation.PaymentValidator;

public class PaymentCompanyValidate implements PaymentValidator {

    public static final int MAX_AMOUNT_TO_WITHDRAW_FOR_INDIVIDUAL = 100000000;

    @Override
    public void validate(UpdateBalanceDto updateBalanceDto) {
        if (updateBalanceDto.getAmountToWithdrawal() > MAX_AMOUNT_TO_WITHDRAW_FOR_INDIVIDUAL) {
            throw new OperationNotAllowedException("Company exception");
        }
    }
}