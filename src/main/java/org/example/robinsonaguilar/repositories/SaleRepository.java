package org.example.robinsonaguilar.repositories;

import org.example.robinsonaguilar.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface SaleRepository extends JpaRepository<Sale,Integer> {
    @Query(value = "select stock from product where id_product = :idProduct",nativeQuery = true)
    public int stokProduct(Integer idProduct);
}
