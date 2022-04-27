package net.sparkminds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.sparkminds.entity.Category;
import net.sparkminds.repository.CategoryRepo;

@RestController
public class CategoryController {
	@Autowired
	CategoryRepo categoryRepo;

	@GetMapping(value = "/category")
	public List<Category> getUsers() {
		return categoryRepo.findAll();
	}

	@GetMapping(value = "/category/{id}")
	public Category getCategoryById(@PathVariable Long id) {
		Category categoryInfo = categoryRepo.findById(id).get();
		return categoryInfo;
	}

	@PostMapping(value = "/save")
	public String createCategory(@RequestBody Category category) {
		categoryRepo.save(category);
		return "Saved ...";
	}
	
	@PutMapping(value = "/update/{id}")
	public String updateCategory(@PathVariable Long id, @RequestBody Category category) {
		Category updateCategory = categoryRepo.findById(id).get();
		updateCategory.setName(category.getName());
		updateCategory.setDescription(category.getDescription());
		updateCategory.setStatus(category.getStatus());
		categoryRepo.save(updateCategory);
		return "Saved...";
	}
	@DeleteMapping(value = "/delete/{id}")
	public String deleleCategory(@PathVariable long id) {
		Category categoryDLT = categoryRepo.findById(id).get();
		categoryRepo.delete(categoryDLT);
		return "Delete success...";
	}
}
