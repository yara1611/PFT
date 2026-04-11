package com.example.PFT.Repositories;

import com.example.PFT.Models.Account;
import com.example.PFT.Models.Transaction;
import com.example.PFT.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    @Query(value = "SELECT * FROM Account  WHERE user_id = :userid",nativeQuery = true)
    List<Account> findAccountsByUser(Long userid);

    Optional<Account> findByAccountIdAndUser(Long accountID, User user);
}
