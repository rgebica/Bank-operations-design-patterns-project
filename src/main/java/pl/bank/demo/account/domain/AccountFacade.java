package pl.bank.demo.account.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.bank.demo.account.dto.*;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import pl.bank.demo.account.visitor.Visitor;
import pl.bank.demo.account.validation.PaymentValidator;

@Service
@Slf4j
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class AccountFacade implements Visitor {

    AccountRepository accountRepository;
    OperationHistoryService operationHistoryService;
    OperationHistoryRepository operationHistoryRepository;
    AccountTypeSingleton accountType = AccountTypeSingleton.getInstance();

    public void createIndividualAccount(IndividualAccountCreateDto individualAccountCreate) {
        Account account = new Account.Builder()
                .buildFirstName(individualAccountCreate.getFirstName())
                .buildLastName(individualAccountCreate.getLastName())
                .buildUserId(individualAccountCreate.getUserId())
                .buildTaxNumber(individualAccountCreate.getTaxNumber())
                .buildEmail(individualAccountCreate.getEmail())
                .buildPhoneNumber(individualAccountCreate.getPhoneNumber())
                .buildLogin(individualAccountCreate.getLogin())
                .buildPassword(individualAccountCreate.getPassword())
                .buildAccountType(visitIndividualAccount(individualAccountCreate))
                .buildBalance(0L)
                .buildAccountNumber(generateAccountNumber())
                .buildCode(individualAccountCreate.getCode())
                .build();
        accountRepository.save(account);
    }

    public void createCompanyAccount(CompanyAccountCreateDto companyAccountCreate) {
        Account account = new Account.Builder()
                .buildFirstName(companyAccountCreate.getFirstName())
                .buildLastName(companyAccountCreate.getLastName())
                .buildUserId(companyAccountCreate.getUserId())
                .buildTaxNumber(companyAccountCreate.getTaxNumber())
                .buildEmail(companyAccountCreate.getEmail())
                .buildPhoneNumber(companyAccountCreate.getPhoneNumber())
                .buildLogin(companyAccountCreate.getLogin())
                .buildPassword(companyAccountCreate.getPassword())
                .buildAccountType(visitCompanyAccount(companyAccountCreate))
                .buildBalance(0L)
                .buildAccountNumber(generateAccountNumber())
                .buildCode(companyAccountCreate.getCode())
                .build();
        accountRepository.save(account);
    }

    public void withdrawal(UpdateBalanceDto updateBalance) {
        if (checkLoginCredentials(updateBalance.getAccountNumber(), updateBalance.getCode())) {
            Account account = accountRepository.findAccountByAccountNumber(updateBalance.getAccountNumber());
            if (account.getBalance() >= updateBalance.getAmountToWithdrawal()) {
                account.setBalance(account.getBalance() - updateBalance.getAmountToWithdrawal());
                operationHistoryService.setOperationHistoryWithdrawal(account, updateBalance);
                accountRepository.save(account);
            }
        }
    }

    public void payment(UpdateBalanceDto updateBalance) {
        if (checkLoginCredentials(updateBalance.getAccountNumber(), updateBalance.getCode())) {
            Account account = accountRepository.findAccountByAccountNumber(updateBalance.getAccountNumber());
            final PaymentValidator paymentValidator = account.getAccountType().equals("INDIVIDUAL") ?
                    new PaymentIndividualValidate() : new PaymentCompanyValidate();
            paymentValidator.validate(updateBalance);
            account.setBalance(account.getBalance() + updateBalance.getAmountToWithdrawal());
            accountRepository.save(account);
            operationHistoryService.setOperationHistoryPayment(account, updateBalance);
        }
    }

    public CheckBalanceDto getBalance(String accountNumber) {
        Account account = accountRepository.findAccountByAccountNumber(accountNumber);
        return CheckBalanceDto.builder()
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .accountNumber(account.getAccountNumber())
                .balance(account.getBalance())
                .build();
    }

    public void makeTransaction(AccountTransactionDto accountTransactionDto) {
        Account accountFrom = accountRepository.findAccountByAccountNumber(accountTransactionDto.getAccountNumberFrom());
        Account accountTo = accountRepository.findAccountByAccountNumber(accountTransactionDto.getAccountNumberTo());
        if (accountFrom.getBalance() >= accountTransactionDto.getAmount()) {
            accountFrom.setBalance(accountFrom.getBalance() - accountTransactionDto.getAmount());
            accountRepository.save(accountFrom);
            operationHistoryService.setTransactionHistoryFrom(accountFrom, accountTransactionDto);
        }
        accountTo.setBalance(accountFrom.getBalance() + accountTransactionDto.getAmount());
        accountRepository.save(accountTo);
        operationHistoryService.setTransactionHistoryTo(accountTo, accountTransactionDto);
    }

    public AccountOperationHistoryDto getOperationHistory(String accountNumber) {
        Account account = accountRepository.findAccountByAccountNumber(accountNumber);
        List<OperationHistoryDto> operationHistory = operationHistoryRepository.findAllByAccountNumber(accountNumber).stream()
                .map(OperationHistory::dto)
                .collect(Collectors.toList());

        return AccountOperationHistoryDto.builder()
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .accountNumber(account.getLastName())
                .operationHistory(operationHistory)
                .build();
    }

    private String generateAccountNumber() {
        String start = "BE";
        Random value = new Random();

        int r1 = value.nextInt(10);
        int r2 = value.nextInt(10);
        start += Integer.toString(r1) + Integer.toString(r2) + " ";

        int count = 0;
        int n = 0;
        for(int i =0; i < 12;i++)
        {
            if(count == 4)
            {
                start += " ";
                count =0;
            }
            else
                n = value.nextInt(10);
            start += Integer.toString(n);
            count++;

        }
        return start;
    }

    private boolean checkLoginCredentials(String accountNumber, String code) {
        if (accountRepository.existsByAccountNumberAndCode(accountNumber, code)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String visitCompanyAccount(CompanyAccountCreateDto companyAccountCreate) {
        return companyAccountCreate.getAccountTypeSingleton().toString();
    }

    @Override
    public String visitIndividualAccount(IndividualAccountCreateDto individualAccountCreateDto) {
        return individualAccountCreateDto.getAccountTypeSingleton().toString();
    }
}
