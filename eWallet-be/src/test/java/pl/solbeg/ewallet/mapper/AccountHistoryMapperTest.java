package pl.solbeg.ewallet.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.solbeg.ewallet.dto.response.AccountHistoryResponse;
import pl.solbeg.ewallet.entity.AccountHistory;
import pl.solbeg.ewallet.entity.OperationEnum;
import util.TestUtil;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountHistoryMapperTest {

    @Autowired
    private AccountHistoryMapper accountHistoryMapper;

    @Test
    void accountHistoryToResponse() {
        AccountHistory accountHistory =
                TestUtil.createAccountHistory(1l, 23.09, OperationEnum.REPLENISH);

        AccountHistoryResponse response = accountHistoryMapper.accountHistoryToResponse(accountHistory);

        assertEquals(response.getAccount(), "123456789");
        assertEquals(response.getOperation(), "REPLENISH");
        assertEquals(response.getQuantity(), Double.valueOf("23.09"));
    }
}