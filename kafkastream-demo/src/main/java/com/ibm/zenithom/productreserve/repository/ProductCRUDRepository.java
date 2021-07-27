package com.ibm.zenithom.productreserve.repository;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ibm.zenithom.productreserve.model.Product;



//https://docs.oracle.com/javaee/6/tutorial/doc/bnbtg.html
//https://docs.oracle.com/javaee/6/tutorial/doc/gjitv.html

@Repository
public interface ProductCRUDRepository extends CrudRepository<Product, Long>{

	/**
	 * Finds a Product by using the name as a search criteria.
	 * @param name
	 * @return  A Product element matching with the given user.
	 */
	    @Query("SELECT t FROM Product t WHERE LOWER(t.name) = LOWER(:name)")
	    public Product find(@Param("name") String name);
	    
	    
//	    @Autowired
//	    private EntityManager entityManager;
//
//	    public User findByEmail(String email) {
//	        User user = null;
//	        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.email=:email");
//	        query.setParameter("email", email);
//	        try {
//	            user = (User) query.getSingleResult();
//	        } catch (Exception e) {
//	            // Handle exception
//	        }
//	        return user;
//         }
}
