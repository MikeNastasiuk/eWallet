package pl.solbeg.ewallet.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.solbeg.ewallet.dto.TokenData;
import pl.solbeg.ewallet.dto.request.CustomerCreateRequest;
import pl.solbeg.ewallet.dto.response.CustomerDataResponse;
import pl.solbeg.ewallet.dto.response.CustomerResponse;
import pl.solbeg.ewallet.entity.Customer;
import pl.solbeg.ewallet.exeption.DecodedException;
import pl.solbeg.ewallet.exeption.ErrorCodeEnum;
import pl.solbeg.ewallet.mapper.CustomerMapper;
import pl.solbeg.ewallet.repository.CustomerRepository;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final PasswordEncryptionService passwordService;

    public Customer findCustomerById(Long id) {
        log.info("Get user by id: {}", id);

        return customerRepository.findById(id)
                .orElseThrow(() -> new DecodedException(ErrorCodeEnum.CUSTOMER_NOT_FOUND));
    }

    @Transactional
    public CustomerResponse createCustomer(CustomerCreateRequest request) {
        if (checkCustomerByLogin(request.getLogin()) != null) {
            throw new DecodedException(ErrorCodeEnum.CUSTOMER_ALREADY_EXIST);
        }
        Customer customer = newCustomer(request);

        return customerMapper.customerToResponse(saveCustomer(customer));
    }

    public Customer findCustomerByLogin(String login) {
        log.info("Get user by login: {}", login);

        return customerRepository.findByLogin(login)
                .orElseThrow(() -> new DecodedException(ErrorCodeEnum.CUSTOMER_NOT_FOUND));
    }

    public Customer checkCustomerByLogin(String login) {
        return customerRepository.findByLogin(login).orElse(null);
    }

    public CustomerDataResponse getCustomerData(String login) {
        Customer customer = findCustomerByLogin(login);
        return customerMapper.customerToDataResponse(customer);
    }

    private Customer newCustomer(CustomerCreateRequest request) {
        byte[] salt;
        byte[] password;
        Customer customer = new Customer();
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setLogin(request.getLogin());
        try {
            salt = passwordService.generateSalt();
            password = passwordService.getEncryptedPassword(request.getPassword(), salt);
        } catch (Exception ex) {
            log.error("Password generation error");
            throw new DecodedException(ErrorCodeEnum.CUSTOMER_NOT_LOGGED);
        }
        customer.setPassword(password);
        customer.setSalt(salt);

        return customer;
    }

    private Customer saveCustomer(Customer entity) {
        return customerRepository.save(entity);
    }

    public boolean isAuthorized(HttpServletRequest request) {
        if (request.getHeader("Authorization") == null) {
            return false;
        }
        TokenData tokenData = passwordService.getTokenData(request.getHeader("Authorization")
                .replaceAll("Basic ", ""));
        Customer customer = findCustomerByLogin(tokenData.getLogin());

        return passwordService.authenticate(tokenData.getPassword(),
                customer.getPassword(),
                customer.getSalt());
    }

    @PostConstruct
    private void createOneUser() {
        byte[] salt;
        byte[] password;
        Customer customer = customerRepository.findByLogin("JohnJohnson").orElse(null);
        if (customer == null) {
            customer = new Customer();
            customer.setId(51L);
            customer.setFirstName("John");
            customer.setLastName("Johnson");
            customer.setLogin("JohnJohnson");
            try {
                salt = passwordService.generateSalt();
                password = passwordService.getEncryptedPassword("password", salt);
            } catch (Exception ex) {
                log.error("Password generation error");
                throw new DecodedException(ErrorCodeEnum.CUSTOMER_NOT_LOGGED);
            }
            customer.setPassword(password);
            customer.setSalt(salt);

            customerRepository.save(customer);
        }
    }
}