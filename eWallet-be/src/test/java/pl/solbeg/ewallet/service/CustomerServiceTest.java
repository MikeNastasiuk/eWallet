package pl.solbeg.ewallet.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.solbeg.ewallet.dto.request.CustomerCreateRequest;
import pl.solbeg.ewallet.dto.response.CustomerResponse;
import pl.solbeg.ewallet.entity.Customer;
import pl.solbeg.ewallet.exeption.DecodedException;
import pl.solbeg.ewallet.mapper.CustomerMapper;
import pl.solbeg.ewallet.repository.CustomerRepository;
import util.TestUtil;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CustomerMapper customerMapper;
    @Mock
    private PasswordEncryptionService passwordService;
    @InjectMocks
    private CustomerService customerService;

    @Test
    void findCustomerById() {
        Optional<Customer> customer = Optional.of(TestUtil.createCustomer());

        when(customerRepository.findById(any())).thenReturn(customer);

        Customer c = customerService.findCustomerById(1L);

        assertEquals(c.getId(), 1L);
        assertEquals(c.getLogin(), "Ivanov");
        assertEquals(c.getLastName(), "Ivanov");
    }

    @Test
    void findCustomerByIdError() {
        Optional<Customer> customer = Optional.empty();

        when(customerRepository.findById(any())).thenReturn(customer);

        assertThatThrownBy(() -> customerService.findCustomerById(2L)).isInstanceOf(DecodedException.class);
    }

    @Test
    void createCustomer() {
        CustomerCreateRequest request = TestUtil.createCustomerRequest();
        CustomerResponse customerResponse = TestUtil.createCustomerResponse();

        when(customerMapper.customerToResponse(any())).thenReturn(customerResponse);

        CustomerResponse response = customerService.createCustomer(request);

        assertEquals(response.getCustomerName(), "firstName lastName");
        assertEquals(response.getLogin(), "login");
    }
}