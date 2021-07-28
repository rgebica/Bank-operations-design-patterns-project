package pl.bank.demo.account.validation;

import pl.bank.demo.account.domain.Account;
import pl.bank.demo.account.dto.UpdateBalanceDto;

public interface UpdateBalanceValidator {
  void validate(UpdateBalanceDto updateBalanceDto, Account account);
}
