package com.pm.mahfza.repository;

import com.pm.mahfza.entite.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long>{
    Account findByName(String name);
}
