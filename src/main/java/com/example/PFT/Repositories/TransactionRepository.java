package com.example.PFT.Repositories;

import com.example.PFT.Models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {


    @Query(value = "SELECT * FROM Transaction  WHERE account_id = :accountid",nativeQuery = true)
    List<Transaction> findTransactionsByAccount(Long accountid);

    //Optional<Transaction> findTopByAccountIdOrderByDateDesc(Long accountId);
}
