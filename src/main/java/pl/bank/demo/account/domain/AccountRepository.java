package pl.bank.demo.account.domain;

import org.springframework.data.jpa.repository.JpaRepository;

interface AccountRepository extends JpaRepository<Account, Long> {
    Account findAccountByAccountNumber(String accountNumber);
    boolean existsByAccountNumberAndCode(String accountNumber, String code);
}
