package pl.solbeg.ewallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.solbeg.ewallet.entity.Account;
import pl.solbeg.ewallet.entity.Customer;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAllByCustomer(Customer customer);

    Optional<Account> findByAccountEquals(String account);
}
