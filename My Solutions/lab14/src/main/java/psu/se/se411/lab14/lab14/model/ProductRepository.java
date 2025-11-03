package psu.se.se411.lab14.lab14.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends CrudRepository<Product, Integer>,
											PagingAndSortingRepository<Product, Integer>{

}
