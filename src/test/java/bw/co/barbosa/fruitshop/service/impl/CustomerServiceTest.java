package bw.co.barbosa.fruitshop.service.impl;

import bw.co.barbosa.fruitshop.api.v1.dto.CustomerDTO;
import bw.co.barbosa.fruitshop.api.v1.mapper.CustomerMapper;
import bw.co.barbosa.fruitshop.model.Customer;
import bw.co.barbosa.fruitshop.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class CustomerServiceTest {

    @Mock
    CustomerRepository customerRepository;

    CustomerMapper customerMapper;

    CustomerServiceImpl customerService;

    @BeforeEach
    void setUp() throws Exception {

        MockitoAnnotations.openMocks(this);
        customerService = new CustomerServiceImpl(customerMapper, customerRepository);
    }

    @Test
    void getAllCustomersTest() throws Exception {

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setLastname("Bar");
        customer.setFirstname("Foo");

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setLastname("Bar1");
        customer2.setFirstname("Fee");

        List<Customer> customers = Arrays.asList(customer, customer2);

        when(customerRepository.findAll()).thenReturn(customers);
        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();

        assertEquals(2, customerDTOS.size());
    }

    @Test
    void getCustomerByIdTest() throws Exception {

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setLastname("Bar");
        customer.setFirstname("Foo");

        when(customerRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(customer));
        CustomerDTO customerDTO = customerService.getCustomerById(1L);

        assertEquals("Foo", customerDTO.getFirstname());
        assertEquals(1L, customerDTO.getId());
    }

    @Test
    void createCustomerTest() throws Exception {

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("Foo");
        customerDTO.setLastname("Bar");

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstname(customerDTO.getFirstname());
        customer.setLastname(customerDTO.getLastname());

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        CustomerDTO savedDTO = customerService.createNewCustomer(customerDTO);

        assertEquals(customerDTO.getFirstname(), savedDTO.getFirstname());
        assertEquals("/api/v1/customer/1", savedDTO.getCustomerUrl());
    }
}