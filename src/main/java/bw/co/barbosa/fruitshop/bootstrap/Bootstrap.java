package bw.co.barbosa.fruitshop.bootstrap;

import bw.co.barbosa.fruitshop.model.Category;
import bw.co.barbosa.fruitshop.model.Customer;
import bw.co.barbosa.fruitshop.model.Vendor;
import bw.co.barbosa.fruitshop.repository.CategoryRepository;
import bw.co.barbosa.fruitshop.repository.CustomerRepository;
import bw.co.barbosa.fruitshop.repository.VendorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        loadCustomers();
        loadCategories();
        loadVendors();
    }

    private void loadCustomers() {

        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstname("Foo");
        customer1.setLastname("Bar");

        Customer customer2 = new Customer();
        customer2.setId(1L);
        customer2.setFirstname("Fee");
        customer2.setLastname("Bar");

        Customer customer3 = new Customer();
        customer3.setId(3L);
        customer3.setFirstname("Barbosa");
        customer3.setLastname("TheKid");

        customerRepository.save(customer1);
        customerRepository.save(customer2);
        customerRepository.save(customer3);

        log.info("Customers Loaded = " + customerRepository.count());
    }

    private void loadCategories() {

        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");



        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);


        log.info("Categories Loaded = " + categoryRepository.count());

    }

    private void loadVendors() {

        Vendor vendor = new Vendor();
        vendor.setId(1L);
        vendor.setName("Foo Bar");

        Vendor vendor2 = new Vendor();
        vendor2.setId(2L);
        vendor2.setName("Fe Bar");

        Vendor vendor3 = new Vendor();
        vendor3.setId(3L);
        vendor3.setName("Bar Foo");

        vendorRepository.save(vendor);
        vendorRepository.save(vendor2);
        vendorRepository.save(vendor3);

        log.info("Vendors Loaded = " + vendorRepository.count());
    }
}
