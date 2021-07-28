package pl.bank.demo.account.validation;

import pl.bank.demo.account.dto.UpdateBalanceDto;

public interface PaymentValidator {
    void validate(UpdateBalanceDto updateBalanceDto);
}
