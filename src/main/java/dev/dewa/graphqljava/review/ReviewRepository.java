package dev.dewa.graphqljava.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;

@GraphQlRepository
public interface ReviewRepository extends JpaRepository<Review, Long>{
    
}
