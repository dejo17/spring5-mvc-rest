package hr.scorpiusmobile.springmvcrest.repositories;

import hr.scorpiusmobile.springmvcrest.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String s);
}
