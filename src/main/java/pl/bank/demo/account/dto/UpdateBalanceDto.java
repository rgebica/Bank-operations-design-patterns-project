package pl.bank.demo.account.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateBalanceDto {
    String accountNumber;
    String code;
    Long amountToWithdrawal;
}
