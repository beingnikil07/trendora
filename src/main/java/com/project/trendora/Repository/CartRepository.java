package com.project.trendora.Repository;

import com.project.trendora.models.Cart;
import com.project.trendora.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long>{

    Cart findByUser(User user);

}
