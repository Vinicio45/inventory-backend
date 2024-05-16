package com.pello.inventory.dao;

import org.springframework.data.repository.CrudRepository;

import com.pello.inventory.model.Category;

public interface ICategoryDao extends CrudRepository<Category, Long> {

}
