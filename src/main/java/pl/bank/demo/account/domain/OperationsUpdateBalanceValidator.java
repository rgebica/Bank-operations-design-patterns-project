package pl.bank.demo.account.domain;

import org.springframework.stereotype.Component;
import pl.bank.demo.account.dto.UpdateBalanceDto;
import pl.bank.demo.account.validation.UpdateBalanceValidator;

@Component
class OperationsUpdateBalanceValidator implements UpdateBalanceValidator {

  public static final int MAX_AMOUNT_TO_WITHDRAW_FOR_INDIVIDUAL = 100000;
  AccountTypeSingleton accountType = AccountTypeSingleton.getInstance();

  @Override
  public void validate(UpdateBalanceDto updateBalanceDto, Account account) {
    if(account.getAccountType().equals(accountType.accountTypeIndividual)){
      if(updateBalanceDto.getAmountToWithdrawal() > MAX_AMOUNT_TO_WITHDRAW_FOR_INDIVIDUAL){
          throw new OperationNotAllowedException("Amount exception");
      }
    }
  }
}
