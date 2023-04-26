package pl.solbeg.ewallet.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import pl.solbeg.ewallet.service.CurrencyService;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CurrencyControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CurrencyService currencyService;

    private static final String URI_PATH = "/api/v1/currency";
    private static final String GET_ALL = "/all";

    @Test
    @WithMockUser(username = "user", password = "pass")
    void getAllCurrencyUnauthorized() throws Exception {
        MockHttpServletRequestBuilder builder = get(URI_PATH + GET_ALL)
                .characterEncoding("UTF_8")
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON);

        mockMvc.perform(builder)
                .andExpect(status().isUnauthorized());
    }
}