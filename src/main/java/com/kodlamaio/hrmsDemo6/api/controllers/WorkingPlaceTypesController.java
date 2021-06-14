package com.kodlamaio.hrmsDemo6.api.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.hrmsDemo6.business.abstracts.WorkingPlaceTypeService;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.ErrorDataResult;
import com.kodlamaio.hrmsDemo6.entities.concretes.WorkingPlaceType;

@RestController
@RequestMapping("/api/workingplacetypes")
@CrossOrigin
public class WorkingPlaceTypesController {
	
	private WorkingPlaceTypeService workingPlaceTypeService;
	
	@Autowired
	public WorkingPlaceTypesController(WorkingPlaceTypeService workingPlaceTypeService) {
		this.workingPlaceTypeService = workingPlaceTypeService;
	}
	
	@GetMapping("/getall")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(this.workingPlaceTypeService.getAll());
	}
	
	@GetMapping("/get")
	public ResponseEntity<?> get(@RequestParam(name = "id") int id) {
		return ResponseEntity.ok(this.workingPlaceTypeService.get(id));
	}
	
	@GetMapping("/getallbytype")
	public ResponseEntity<?> getAllByType(@RequestParam(name = "type") String type) {
		return ResponseEntity.ok(this.workingPlaceTypeService.getAllByType(type));
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody WorkingPlaceType workingPlaceType) {
		return ResponseEntity.ok(this.workingPlaceTypeService.add(workingPlaceType));
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestParam(name = "id") int id) {
		return ResponseEntity.ok(this.workingPlaceTypeService.delete(id));
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody WorkingPlaceType workingPlaceType) {
		return ResponseEntity.ok(this.workingPlaceTypeService.update(workingPlaceType));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions) {
		Map<String, String> validationErrors = new HashMap<String, String>();
		for (FieldError fieldError: exceptions.getBindingResult().getFieldErrors()) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return new ErrorDataResult<Object>("Validation Errors", validationErrors);
	}
}
