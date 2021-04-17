package bw.co.barbosa.fruitshop.service.impl;

import bw.co.barbosa.fruitshop.api.v1.dto.CustomerDTO;
import bw.co.barbosa.fruitshop.api.v1.dto.CustomerListDTO;
import bw.co.barbosa.fruitshop.api.v1.mapper.CustomerMapper;
import bw.co.barbosa.fruitshop.model.Customer;
import bw.co.barbosa.fruitshop.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    @Mock
    CustomerRepository customerRepository;

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    CustomerServiceImpl customerService;

    @BeforeEach
    void setUp() throws Exception {

        MockitoAnnotations.openMocks(this);
        customerService = new CustomerServiceImpl(customerMapper, customerRepository);
    }

    @Test
    void getAllCustomersTest() throws Exception {

        List<Customer> customers = Arrays.asList(getCustomer1(), getCustomer2());

        given(customerRepository.findAll()).willReturn(customers);
        CustomerListDTO customerDTOS = customerService.getAllCustomers();

        then(customerRepository).should(times(1)).findAll();
        assertThat(customerDTOS.getCustomers().size(), is(equalTo(2)));
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

    @Test
    void saveCustomerByDtoTest() throws Exception {

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("Jim");

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstname(customerDTO.getFirstname());
        savedCustomer.setLastname(customerDTO.getLastname());
        savedCustomer.setId(1L);

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);
        CustomerDTO savedDto = customerService.saveCustomerByDTO(1L, customerDTO);

        assertEquals(customerDTO.getFirstname(), savedDto.getFirstname());
        assertEquals("/api/v1/customer/1", savedDto.getCustomerUrl());
    }

    @Test
    void deleteCustomerByIdTest() throws Exception {
        Long id = 1L;
        customerRepository.deleteById(id);
        verify(customerRepository, times(1)).deleteById(anyLong());
    }

    private Customer getCustomer1() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setLastname("Bar");
        customer.setFirstname("Foo");
        return customer;
    }

    private Customer getCustomer2() {
        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setLastname("Bar1");
        customer2.setFirstname("Fee");
        return customer2;
    }
}