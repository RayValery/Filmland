package com.filmland.dal;

import com.filmland.dal.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    @Query("SELECT s FROM Subscription s JOIN s.customers c JOIN s.filmCategory f WHERE c.email = :email")
    Set<Subscription> getAllCustomerSubscriptions(@Param("email") String email);

    @Query("SELECT s FROM Subscription s JOIN s.customers c JOIN s.filmCategory f WHERE c.email = :email AND f.name = :categoryName")
    Optional<Subscription> getSubscriptionByFilmCategory(@Param("email") String email, @Param("categoryName") String categoryName);

    @Query(value = "SELECT * FROM subscription s WHERE s.payment_date <= CURRENT_TIMESTAMP ORDER BY s.payment_date LIMIT 1",
    nativeQuery = true)
    Optional<Subscription> getSubscriptionWithPaymentDateReached();
}
