package pl.bank.demo.account.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CheckBalanceDto {
    String firstName;
    String lastName;
    String accountNumber;
    Long balance;

    public static CheckBalanceDto from(String firstName, String lastName, String accountNumber, Long balance) {
        return CheckBalanceDto.builder()
                .firstName(firstName)
                .lastName(lastName)
                .accountNumber(accountNumber)
                .balance(balance)
                .build();
    }
}
