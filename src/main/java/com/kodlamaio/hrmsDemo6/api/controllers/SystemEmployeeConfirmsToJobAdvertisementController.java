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

import com.kodlamaio.hrmsDemo6.business.abstracts.SystemEmployeeConfirmToJobAdvertisementService;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.ErrorDataResult;
import com.kodlamaio.hrmsDemo6.entities.concretes.JobAdvertisement;
import com.kodlamaio.hrmsDemo6.entities.concretes.SystemEmployeeConfirmToJobAdvertisement;

@RestController
@RequestMapping("/api/systememployeeconfirmstojobadvertisement")
@CrossOrigin
public class SystemEmployeeConfirmsToJobAdvertisementController {
	
	private SystemEmployeeConfirmToJobAdvertisementService systemEmployeeConfirmToJobAdvertisementService;
	
	@Autowired
	public SystemEmployeeConfirmsToJobAdvertisementController(SystemEmployeeConfirmToJobAdvertisementService systemEmployeeConfirmToJobAdvertisementService) {
		this.systemEmployeeConfirmToJobAdvertisementService = systemEmployeeConfirmToJobAdvertisementService;
	}
	
	@GetMapping("/getall")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(this.systemEmployeeConfirmToJobAdvertisementService.getAll());
	}
	
	@GetMapping("/get")
	public ResponseEntity<?> get(@RequestParam(name = "id") int id) {
		return ResponseEntity.ok(this.systemEmployeeConfirmToJobAdvertisementService.get(id));
	}
	
	@GetMapping("/getallbyjobadvertisementid")
	public ResponseEntity<?> getAllByJobAdvertisementId(@RequestParam(name = "id") int id) {
		return ResponseEntity.ok(this.systemEmployeeConfirmToJobAdvertisementService.getAllByJobAdvertisementId(id));
	}
	
	@GetMapping("/getfirstbyjobadvertisementidorderbydateofconfirmdesc")
	public ResponseEntity<?> getFirstByJobAdvertisementIdOrderByDateOfConfirmDesc(@RequestParam(name = "id") int id) {
		return ResponseEntity.ok(this.systemEmployeeConfirmToJobAdvertisementService.getFirstByJobAdvertisementIdOrderByDateOfConfirmDesc(id));
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody SystemEmployeeConfirmToJobAdvertisement systemEmployeeConfirmToJobAdvertisement) {
		return ResponseEntity.ok(this.systemEmployeeConfirmToJobAdvertisementService.add(systemEmployeeConfirmToJobAdvertisement));
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestParam(name = "id") int id) {
		return ResponseEntity.ok(this.systemEmployeeConfirmToJobAdvertisementService.delete(id));
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody SystemEmployeeConfirmToJobAdvertisement systemEmployeeConfirmToJobAdvertisement) {
		return ResponseEntity.ok(this.systemEmployeeConfirmToJobAdvertisementService.update(systemEmployeeConfirmToJobAdvertisement));
	}
	
	@PostMapping("/confirmjobadvertisement)")
	public ResponseEntity<?> confirmJobAdvertisement(@Valid @RequestBody JobAdvertisement jobAdvertisement) {
		return ResponseEntity.ok(this.systemEmployeeConfirmToJobAdvertisementService.confirmJobAdvertisement(jobAdvertisement));
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
