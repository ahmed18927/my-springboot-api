package com.pm.mahfza.service;

import com.pm.mahfza.entite.Account;
import com.pm.mahfza.entite.Transaction;
import com.pm.mahfza.entite.TransactionType;
import com.pm.mahfza.repository.AccountRepository;
import com.pm.mahfza.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FinanceService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    private Transaction recordTransaction(String walletName, BigDecimal amount, String type, String description) {
        Account acc = accountRepository.findByName(walletName);
        if (acc == null) throw new RuntimeException("Wallet not found: " + walletName);

        BigDecimal newBalance = acc.getBalance();
        if (type.equals("ADD")) newBalance = newBalance.add(amount);
        else if (type.equals("SPEND")) newBalance = newBalance.subtract(amount);

        acc.setBalance(newBalance);
        accountRepository.save(acc);

        TransactionType txType = type.equals("ADD") ? TransactionType.ADD : TransactionType.SPEND;

        Transaction tx = Transaction.builder()
                .walletName(walletName)
                .type(txType)
                .amount(amount)
                .description(description)
                .date(LocalDateTime.now())
                .build();
        return transactionRepository.save(tx);
    }

    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

    public Transaction addFunds(String walletName, BigDecimal amount, String description) {
        return recordTransaction(walletName, amount, "ADD", description);
    }

    public Transaction spendFunds(String walletName, BigDecimal amount, String description) {
        return recordTransaction(walletName, amount, "SPEND", description);
    }

    public List<Transaction> getTransactions(String walletName) {
        return transactionRepository.findByWalletNameOrderByDateDesc(walletName);
    }
    public BigDecimal getTotalBalance() {
        return accountRepository.findAll().stream()
                .map(Account::getBalance)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
