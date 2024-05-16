package com.pello.inventory.services;

import org.springframework.http.ResponseEntity;

import com.pello.inventory.response.CategoryResponseRest;

public interface ICategoryService {
	
	public ResponseEntity<CategoryResponseRest> search();

}
