package pl.solbeg.ewallet.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.solbeg.ewallet.dto.response.CustomerDataResponse;
import pl.solbeg.ewallet.dto.response.CustomerResponse;
import pl.solbeg.ewallet.entity.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "login", source = "login")
    @Mapping(target = "customerName", expression = "java(setCustomerName(customer.getFirstName(), customer.getLastName()))")
    CustomerResponse customerToResponse(Customer customer);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "login", source = "login")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "customerName", expression = "java(setCustomerName(customer.getFirstName(), customer.getLastName()))")
    CustomerDataResponse customerToDataResponse(Customer customer);

    default String setCustomerName(String firstName, String lastName) {
        return firstName + " " + lastName;
    }
}