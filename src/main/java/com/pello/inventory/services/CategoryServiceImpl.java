package com.pello.inventory.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pello.inventory.dao.ICategoryDao;
import com.pello.inventory.model.Category;
import com.pello.inventory.response.CategoryResponseRest;

@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private ICategoryDao categoryDao;
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoryResponseRest> search() {
		
		CategoryResponseRest response = new CategoryResponseRest();
		try {
			List<Category> category	= (List<Category>) categoryDao.findAll();
			
			response.getCategoryResponse().setCategory(category);
			response.setMetadata("Respuesta ok", "00", "respuesta exitosa");
		}
		catch(Exception e){
			response.setMetadata("Respuesta nok", "-1", "Error al consultar");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}

	
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoryResponseRest> searchById(Long id) {
		
		CategoryResponseRest response = new CategoryResponseRest();
		List<Category> list = new ArrayList<>();
		
		try {
			Optional<Category> category = categoryDao.findById(id);
			if(category.isPresent()) {
				list.add(category.get());
				response.getCategoryResponse().setCategory(list);
				response.setMetadata("Respuesta ok", "0", "Categoria encontrada");
			}else {
				response.setMetadata("Respuesta nok", "-1", "Categoria no encontrada");
				return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		}
		catch(Exception e){
			response.setMetadata("Respuesta nok", "-1", "Error al consultar por id");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}



	@Override
	@Transactional
	public ResponseEntity<CategoryResponseRest> save(Category category) {
		
		CategoryResponseRest response = new CategoryResponseRest();
		List<Category> list = new ArrayList<>();
		
		try {
			Category categorySave = categoryDao.save(category);
			if(categorySave != null){
				list.add(categorySave);
				response.getCategoryResponse().setCategory(list);
				response.setMetadata("Respuesta ok", "0", "categoria guardada");
			}else{
				response.setMetadata("Respuesta nok", "-1", "categoria no guardada");
				return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
			
		}
		catch(Exception e){
			response.setMetadata("Respuesta nok", "-1", "Error al guardar datos");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}



	@Override
	public ResponseEntity<CategoryResponseRest> update(Long id, Category category) {
		
		CategoryResponseRest response = new CategoryResponseRest();
		List<Category> list = new ArrayList<>();
		
		try {
			
			Optional<Category> categorySearch = categoryDao.findById(id);
			if(categorySearch.isPresent()) {
				//se procede a actualizar el registro
				categorySearch.get().setName(category.getName());
				categorySearch.get().setDescription(category.getDescription());
				Category categoryUpdate = categoryDao.save(categorySearch.get());
				
				if (categoryUpdate != null) {
					list.add(categoryUpdate);
					response.getCategoryResponse().setCategory(list);
					response.setMetadata("Respuesta ok", "0", "categoria actualizada");
				}else {
					response.setMetadata("Respuesta nok", "-1", "categoria no actualizada");
					return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
				
				
				
			}else {
				response.setMetadata("Respuesta nok", "-1", "categoria no encontrada");
				return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
			
		}
		catch(Exception e){
			response.setMetadata("Respuesta nok", "-1", "Error al actualizar datos");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}

}
