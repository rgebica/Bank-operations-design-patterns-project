package pl.bank.demo.account.domain;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import pl.bank.demo.account.dto.AccountOperationHistoryDto;
import pl.bank.demo.account.dto.OperationHistoryDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "operation_history")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OperationHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long operationHistoryId;
    String accountNumber;
    String firstName;
    String lastName;
    String amount;
    Long currentBalance;
    String operationType;

    public OperationHistory() {
    }

    private OperationHistory(Builder builder) {
        operationHistoryId = builder.operationHistoryId;
        accountNumber = builder.accountNumber;
        firstName = builder.firstName;
        lastName = builder.lastName;
        amount = builder.amount;
        currentBalance = builder.currentBalance;
        operationType = builder.operationType;
    }

    public Long getOperationHistoryId() {
        return operationHistoryId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAmount() {
        return amount;
    }

    public Long getCurrentBalance() {
        return currentBalance;
    }

    public String getOperationType() {
        return operationType;
    }


    public static final class Builder {
        Long operationHistoryId;
        String firstName;
        String lastName;
        String amount;
        String operationType;
        String accountNumber;
        Long currentBalance;

        public Builder() {

        }

        public Builder buildOperationHistoryId(Long operationHistoryId) {
            this.operationHistoryId = operationHistoryId;
            return this;
        }

        public Builder buildFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder buildLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder buildOperationType(String operationType) {
            this.operationType = operationType;
            return this;
        }

        public Builder buildCurrentBalance(Long currentBalance) {
            this.currentBalance = currentBalance;
            return this;
        }

        public Builder buildAmount(String amount) {
            this.amount = amount;
            return this;
        }

        public Builder buildAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public OperationHistory build() {
            return new OperationHistory(this);
        }
    }

    public OperationHistoryDto dto() {
        return OperationHistoryDto.builder()
                .amount(amount)
                .currentBalance(currentBalance)
                .operationType(operationType)
                .build();
    }
}
