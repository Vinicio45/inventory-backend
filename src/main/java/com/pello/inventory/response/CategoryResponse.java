package com.pello.inventory.response;


import java.util.List;

import com.pello.inventory.model.Category;

import lombok.Data;


@Data
public class CategoryResponse {
	
	private List<Category> category;

}
