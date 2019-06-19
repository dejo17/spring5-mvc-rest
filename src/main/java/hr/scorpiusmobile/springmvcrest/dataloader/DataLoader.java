package hr.scorpiusmobile.springmvcrest.dataloader;

import hr.scorpiusmobile.springmvcrest.domain.Category;
import hr.scorpiusmobile.springmvcrest.domain.Customer;
import hr.scorpiusmobile.springmvcrest.domain.Vendor;
import hr.scorpiusmobile.springmvcrest.repositories.CategoryRepository;
import hr.scorpiusmobile.springmvcrest.repositories.CustomerRepository;
import hr.scorpiusmobile.springmvcrest.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;

    public DataLoader(CategoryRepository categoryRepository, CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        loadCategories();

        loadCustomers();

        loadVendors();
    }

    private void loadCustomers() {
        Customer pero = new Customer();
        pero.setLastName("Peric");
        pero.setFirstName("Pero");

        Customer dino = new Customer();
        dino.setLastName("Dinic");
        dino.setFirstName("Dino");

        Customer alenko = new Customer();
        alenko.setLastName("Haramija");
        alenko.setFirstName("Alenko");

        Customer dani = new Customer();
        dani.setLastName("Glavic");
        dani.setFirstName("Dani");

        Customer djuro = new Customer();
        djuro.setLastName("Djuric");
        djuro.setFirstName("Djuro");

        customerRepository.save(pero);
        customerRepository.save(dino);
        customerRepository.save(alenko);
        customerRepository.save(dani);
        customerRepository.save(djuro);

        System.out.println("Customers Loaded = " + customerRepository.count());
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


        System.out.println("Categories Loaded = " + categoryRepository.count());
    }

    private void loadVendors(){

        Vendor vendor1 = new Vendor();
        vendor1.setName("Primorka");

        Vendor vendor2 = new Vendor();
        vendor2.setName("Vera");

        Vendor vendor3 = new Vendor();
        vendor3.setName("Karmela");

        Vendor vendor4 = new Vendor();
        vendor4.setName("Roly");

        Vendor vendor5 = new Vendor();
        vendor5.setName("Barby");

        vendorRepository.save(vendor1);
        vendorRepository.save(vendor2);
        vendorRepository.save(vendor3);
        vendorRepository.save(vendor4);
        vendorRepository.save(vendor5);

        System.out.println("Vendors Loaded = " + vendorRepository.count());

    }
}
