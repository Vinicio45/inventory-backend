package com.pello.inventory.services;

import org.springframework.http.ResponseEntity;

import com.pello.inventory.model.Category;
import com.pello.inventory.response.CategoryResponseRest;

public interface ICategoryService {
	
	public ResponseEntity<CategoryResponseRest> search();
	
	public ResponseEntity<CategoryResponseRest> searchById(Long id);
	
	public ResponseEntity<CategoryResponseRest> save(Category category);

}
