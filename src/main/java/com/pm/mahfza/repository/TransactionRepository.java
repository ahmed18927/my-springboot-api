package com.pm.mahfza.repository;

import com.pm.mahfza.entite.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByWalletNameOrderByDateDesc(String walletName);
}
