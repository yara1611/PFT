package com.example.PFT.Repositories;

import com.example.PFT.Models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {


    @Query(value = "SELECT * FROM Transaction  WHERE account_id = :accountid",nativeQuery = true)
    List<Transaction> findTransactionsByAccount(Long accountid);

    @Query(value = "SELECT * FROM Transaction  WHERE date = :date AND account_id= :accountid",nativeQuery = true)
    List<Transaction> findTransactionsByDate(Long accountid, Date date);
    //TODO find between ranges
    //Optional<Transaction> findTopByAccountIdOrderByDateDesc(Long accountId);
}
