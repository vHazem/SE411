package psu.se.se411.lab14.lab14.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import psu.se.se411.lab14.lab14.model.Product;
import psu.se.se411.lab14.lab14.model.ProductRepository;


@RestController
@RequestMapping("/products")
public class ProductController {

	private final ProductRepository productRepository;

	public ProductController(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@GetMapping()
	private ResponseEntity<Iterable<Product>> findAll() {
		   return ResponseEntity.ok(productRepository.findAll());
	}
	
	@PostMapping()
	private ResponseEntity<Product> create(@RequestBody Product product) {
		Product savedProduct = productRepository.save(product);
		return ResponseEntity.status(201).body(savedProduct);
	}

}
