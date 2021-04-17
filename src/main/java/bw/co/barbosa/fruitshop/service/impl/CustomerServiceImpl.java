package bw.co.barbosa.fruitshop.service.impl;

import bw.co.barbosa.fruitshop.api.v1.dto.CustomerDTO;
import bw.co.barbosa.fruitshop.api.v1.dto.CustomerListDTO;
import bw.co.barbosa.fruitshop.api.v1.mapper.CustomerMapper;
import bw.co.barbosa.fruitshop.exception.ResourceNotFoundException;
import bw.co.barbosa.fruitshop.model.Customer;
import bw.co.barbosa.fruitshop.repository.CustomerRepository;
import bw.co.barbosa.fruitshop.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    public static final String CUSTOMER_URL = "/api/v1/customer/";
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerListDTO getAllCustomers() {

        log.info("Fetching all customers");
        List<CustomerDTO> customerDTOS = customerRepository
                .findAll()
                .stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerUrl("/api/v1/customers/" + customer.getId());
                    return customerDTO;
                })
                .collect(Collectors.toList());

        return new CustomerListDTO(customerDTOS);
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {

        log.info("Fetching customer id: " + id);
        return customerRepository.findById(id)
                .map(customerMapper::customerToCustomerDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {

        return saveAndReturnDTO(customerMapper.customerDtoToCustomer(customerDTO));
    }

    @Override
    public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {

        Customer customer = customerMapper.customerDtoToCustomer(customerDTO);
        customer.setId(id);
        log.info("Updated customer with ID: " + id);
        return saveAndReturnDTO(customer);
    }

    @Override
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {

        log.info("Updating customer with ID: " + id);
        return customerRepository.findById(id).map(customer -> {
            if (customerDTO.getFirstname() != null) {
                customer.setFirstname(customerDTO.getFirstname());
            }
            if (customerDTO.getLastname() != null) {
                customer.setLastname(customerDTO.getLastname());
            }

            CustomerDTO returnDTO = customerMapper.customerToCustomerDTO(customer);
            customerDTO.setCustomerUrl("/api/v1/customers/" + id);
            return returnDTO;
        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }

    private CustomerDTO saveAndReturnDTO(Customer customer) {

        Customer savedCustomer = customerRepository.save(customer);
        CustomerDTO returnDTO = customerMapper.customerToCustomerDTO(customer);
        returnDTO.setCustomerUrl(CUSTOMER_URL + savedCustomer.getId());
        return returnDTO;
    }
}
