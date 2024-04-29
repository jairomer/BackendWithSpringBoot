package com.backend.prueba.services.repositories;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.backend.prueba.model.db.Price;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class PriceRepositoryImpl implements PriceRepository {
    
    private final EntityManager em;

    public PriceRepositoryImpl(EntityManager em) {
        this.em = em;
    }
    
    /**
     * Given a product id, a brand id and a timestamp, recover the highest priority price at that timestamp. 
    */
    public Optional<Price> getPriceForProductAndBrandInTimestamp(long productId, long brandId, Timestamp timestamp) {
        /* 
        * In this query we are assuming that for a given timeframe, the priorities are unique,
        * so that 
        */
        String hql =
            "SELECT p FROM Price p " + 
            "WHERE "+
            ":priceDate <= p.endDate AND :priceDate >= p.startDate " +
            "AND p.product.id = :prodId " +
            "AND p.brand.id = :brandId " +
            "ORDER BY p.priority DESC " +
            "LIMIT 1";

        TypedQuery<Price> q = em.createQuery(hql, Price.class);
        q.setParameter("priceDate", timestamp);
        q.setParameter("prodId", productId);
        q.setParameter("brandId", brandId);

        ArrayList<Price> results = (ArrayList<Price>) q.getResultList();
        return results.isEmpty() ? Optional.empty() : Optional.ofNullable(results.get(0));
    }

    
}
