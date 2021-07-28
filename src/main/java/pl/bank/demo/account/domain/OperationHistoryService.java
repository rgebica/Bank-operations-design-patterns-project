package pl.bank.demo.account.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bank.demo.account.dto.AccountTransactionDto;
import pl.bank.demo.account.dto.UpdateBalanceDto;

@Service
public class OperationHistoryService {

    OperationHistoryRepository operationHistoryRepository;
    AccountRepository accountRepository;
    OperationTypeSingleton operationType = OperationTypeSingleton.getInstance();

    @Autowired
    public OperationHistoryService(OperationHistoryRepository operationHistoryRepository, AccountRepository accountRepository) {
        this.operationHistoryRepository = operationHistoryRepository;
        this.accountRepository = accountRepository;
    }

    public void setOperationHistoryWithdrawal(Account account, UpdateBalanceDto updateBalance) {
        OperationHistory operationHistory = new OperationHistory.Builder()
                .buildAccountNumber(account.getAccountNumber())
                .buildFirstName(account.getFirstName())
                .buildLastName(account.getLastName())
                .buildAmount("-" + updateBalance.getAmountToWithdrawal())
                .buildCurrentBalance(account.getBalance())
                .buildOperationType(operationType.withdrawal)
                .build();
        operationHistoryRepository.save(operationHistory);
    }

    public void setOperationHistoryPayment(Account account, UpdateBalanceDto updateBalance) {
        OperationHistory operationHistory = new OperationHistory.Builder()
                .buildAccountNumber(account.getAccountNumber())
                .buildFirstName(account.getFirstName())
                .buildLastName(account.getLastName())
                .buildAmount("+" + updateBalance.getAmountToWithdrawal())
                .buildCurrentBalance(account.getBalance())
                .buildOperationType(operationType.payment)
                .build();
        operationHistoryRepository.save(operationHistory);
    }

    public void setTransactionHistoryFrom(Account account, AccountTransactionDto accountTransactionDto) {
        OperationHistory operationHistory = new OperationHistory.Builder()
                .buildAccountNumber(account.getAccountNumber())
                .buildFirstName(account.getFirstName())
                .buildLastName(account.getLastName())
                .buildAmount("+ " + accountTransactionDto.getAmount())
                .buildCurrentBalance(account.getBalance())
                .buildOperationType(operationType.transaction + "TO" + accountTransactionDto.getAccountNumberFrom())
                .build();
        operationHistoryRepository.save(operationHistory);
    }

    public void setTransactionHistoryTo(Account account, AccountTransactionDto accountTransactionDto) {
        OperationHistory operationHistory = new OperationHistory.Builder()
                .buildAccountNumber(account.getAccountNumber())
                .buildFirstName(account.getFirstName())
                .buildLastName(account.getLastName())
                .buildAmount("- " + accountTransactionDto.getAccountNumberTo())
                .buildCurrentBalance(account.getBalance())
                .buildOperationType(operationType.transaction + "FROM" + accountTransactionDto.getAccountNumberTo())
                .build();
        operationHistoryRepository.save(operationHistory);
    }
}
