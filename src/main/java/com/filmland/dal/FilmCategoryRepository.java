package com.filmland.dal;

import com.filmland.dal.entity.FilmCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface FilmCategoryRepository extends JpaRepository<FilmCategory, Long> {

    @Query(value = "SELECT f.id, f.name, f.price, f.available_content FROM film_category f WHERE f.id NOT IN " +
            "(SELECT s.film_category_id FROM subscription s  JOIN customer_subscription cs JOIN customer c " +
            "WHERE cs.subscription_id = s.id AND cs.customer_id = c.id AND c.email=:email)",
    nativeQuery = true)
    Set<FilmCategory> getAllAvailableCategories(@Param("email") String email);

    Optional<FilmCategory> getFirstByName(String name);
}
