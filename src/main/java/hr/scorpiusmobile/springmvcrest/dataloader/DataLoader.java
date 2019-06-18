package hr.scorpiusmobile.springmvcrest.dataloader;

import hr.scorpiusmobile.springmvcrest.domain.Category;
import hr.scorpiusmobile.springmvcrest.domain.Customer;
import hr.scorpiusmobile.springmvcrest.repositories.CategoryRepository;
import hr.scorpiusmobile.springmvcrest.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;

    public DataLoader(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        loadCategories();

        loadCustomers();
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
}
