package pl.bank.demo.account.dto;

import lombok.Builder;
import lombok.Getter;
import pl.bank.demo.account.domain.OperationHistory;

import java.util.List;

@Getter
@Builder
public class AccountOperationHistoryDto {
    String firstName;
    String lastName;
    String accountNumber;
    List<OperationHistoryDto> operationHistory;

    public static AccountOperationHistoryDto from(String firstName, String lastName, String accountNumber, List<OperationHistoryDto> operationHistory) {
        return AccountOperationHistoryDto.builder()
                .firstName(firstName)
                .lastName(lastName)
                .accountNumber(accountNumber)
                .operationHistory(operationHistory)
                .build();
    }
}
