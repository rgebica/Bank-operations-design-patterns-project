package pl.bank.demo.account.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bank.demo.account.dto.AccountOperationHistoryDto;

import java.util.List;

interface OperationHistoryRepository extends JpaRepository<OperationHistory, Long> {
    List<OperationHistory> findAllByAccountNumber(String accountNumber);
}
