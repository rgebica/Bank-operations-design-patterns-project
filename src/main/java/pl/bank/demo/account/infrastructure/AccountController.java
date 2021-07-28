package pl.bank.demo.account.infrastructure;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bank.demo.account.domain.AccountFacade;
import pl.bank.demo.account.dto.*;

import static org.springframework.http.HttpStatus.*;

@RequestMapping("/account")
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

class AccountController {

    AccountFacade accountFacade;

    @Autowired
    public AccountController(AccountFacade accountFacade) {
        this.accountFacade = accountFacade;
    }

    @CrossOrigin
    @PostMapping("/create-account-individual")
    public ResponseEntity<String> accountCreateIndividual(@RequestBody IndividualAccountCreateDto individualAccountCreate) {
        accountFacade.createIndividualAccount(individualAccountCreate);
        return new ResponseEntity<>("Account individual created", CREATED);
    }

    @CrossOrigin
    @PostMapping("/create-account-company")
    public ResponseEntity<String> accountCreateCompany(@RequestBody CompanyAccountCreateDto companyAccountCreate) {
        accountFacade.createCompanyAccount(companyAccountCreate);
        return new ResponseEntity<>("Account company created", CREATED);
    }

    @CrossOrigin
    @PutMapping("/withdrawal")
    public ResponseEntity<String> withdrawal(@RequestBody UpdateBalanceDto updateBalance) {
        accountFacade.withdrawal(updateBalance);
        return new ResponseEntity<>("Money paid out", CREATED);
    }

    @CrossOrigin
    @PutMapping("/payment")
    public ResponseEntity<String> payment(@RequestBody UpdateBalanceDto updateBalance) {
        accountFacade.payment(updateBalance);
        return new ResponseEntity<>("Money paid in", CREATED);
    }

    @CrossOrigin
    @GetMapping("/balance/{accountNumber}")
    public ResponseEntity<CheckBalanceDto> payment(@PathVariable String accountNumber) {
        CheckBalanceDto balance = accountFacade.getBalance(accountNumber);
        return ResponseEntity.ok(balance);
    }

    @CrossOrigin
    @PutMapping("/transaction")
    public ResponseEntity<String> transaction(@RequestBody AccountTransactionDto accountTransactionDto) {
        accountFacade.makeTransaction(accountTransactionDto);
        return new ResponseEntity<>("Transcaction completed", OK);
    }

    @CrossOrigin
    @GetMapping("/operation-history/{accountNumber}")
    public ResponseEntity<AccountOperationHistoryDto> operationHistory(@PathVariable String accountNumber) {
        AccountOperationHistoryDto operationHistory = accountFacade.getOperationHistory(accountNumber);
        return ResponseEntity.ok(operationHistory);
    }
}
