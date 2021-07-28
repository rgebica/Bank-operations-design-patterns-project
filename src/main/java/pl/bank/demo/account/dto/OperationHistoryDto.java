package pl.bank.demo.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OperationHistoryDto {
    String amount;
    Long currentBalance;
    String operationType;
}
