package pl.bank.demo.account.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccountTransactionDto {
    String accountNumberFrom;
    String accountNumberTo;
    Long amount;
}
