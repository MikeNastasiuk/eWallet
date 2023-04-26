package pl.solbeg.ewallet.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.solbeg.ewallet.dto.response.AccountResponse;
import pl.solbeg.ewallet.entity.Account;
import util.TestUtil;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountMapperTest {

    @Autowired
    private AccountMapper accountMapper;

    @Test
    void accountToResponse() {
        Account account = TestUtil.createAccount();

        AccountResponse accountResponse = accountMapper.accountToResponse(account);

        assertEquals(accountResponse.getAccountCurrency(), "USD");
        assertEquals(accountResponse.getAccountName(), "123456789");
        assertEquals(accountResponse.getDescription(), "description");
        assertEquals(accountResponse.getAmount(), Double.valueOf("250.00"));
    }
}