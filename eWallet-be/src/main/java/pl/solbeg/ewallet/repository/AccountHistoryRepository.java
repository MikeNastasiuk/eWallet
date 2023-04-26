package pl.solbeg.ewallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.solbeg.ewallet.entity.AccountHistory;

import java.util.List;

@Repository
public interface AccountHistoryRepository extends JpaRepository<AccountHistory, Long> {

    @Query("""
        SELECT ah
        FROM AccountHistory ah
        WHERE ah.account.account = ?1
        ORDER BY ah.operationDate
     """)
    List<AccountHistory> getAccountHistory(String account);
}
