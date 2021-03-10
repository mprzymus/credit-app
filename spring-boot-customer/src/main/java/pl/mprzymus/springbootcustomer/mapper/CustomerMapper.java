package pl.mprzymus.springbootcustomer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.mprzymus.springbootcustomer.model.Customer;
import pl.mprzymus.springbootcustomer.model.CustomerDto;
import pl.mprzymus.springbootcustomer.model.CustomerInfoDto;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creditId", source = "creditNumber")
    @Mapping(target = "pesel", source = "customer.pesel")
    @Mapping(target = "surname", source = "customer.surname")
    @Mapping(target = "firstName", source = "customer.firstName")
    Customer customerInfoDtoToCustomer(CustomerInfoDto customerInfoDto);

    CustomerDto customerToCustomerDto(Customer customer);
}
