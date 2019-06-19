package hr.scorpiusmobile.springmvcrest.repositories;

import hr.scorpiusmobile.springmvcrest.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
    Vendor findByName(String name);
}
