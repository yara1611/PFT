package com.example.PFT.Repositories;

import com.example.PFT.Models.Account;
import com.example.PFT.Models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    @Query(value = "SELECT * FROM Account  WHERE user_id = :userid",nativeQuery = true)
    List<Account> findAccountsByUser(Long userid);
}
