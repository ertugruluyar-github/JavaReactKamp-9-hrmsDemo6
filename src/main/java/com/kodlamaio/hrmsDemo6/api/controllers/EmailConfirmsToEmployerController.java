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

import com.kodlamaio.hrmsDemo6.business.abstracts.EmailConfirmToEmployerService;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.ErrorDataResult;
import com.kodlamaio.hrmsDemo6.entities.concretes.EmailConfirmToEmployer;
import com.kodlamaio.hrmsDemo6.entities.concretes.Employer;

@RestController
@RequestMapping("/api/emailconfirmstoemployer")
@CrossOrigin
public class EmailConfirmsToEmployerController {
	
	private EmailConfirmToEmployerService emailConfirmToEmployerService;
	
	@Autowired
	public EmailConfirmsToEmployerController(EmailConfirmToEmployerService emailConfirmToEmployerService) {
		this.emailConfirmToEmployerService = emailConfirmToEmployerService;
	}
	
	@GetMapping("/getall")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(this.emailConfirmToEmployerService.getAll());
	}
	
	@GetMapping("/get")
	public ResponseEntity<?> get(@RequestParam(name = "id") int id) {
		return ResponseEntity.ok(this.emailConfirmToEmployerService.get(id));
	}
	
	@GetMapping("/getallbyemployerid")
	public ResponseEntity<?> getAllByEmployerId(@RequestParam(name = "id") int id) {
		return ResponseEntity.ok(this.emailConfirmToEmployerService.getAllByEmployerId(id));
	}
	
	@GetMapping("/getfirstbyemployeridorderbydateofconfirmdesc")
	public ResponseEntity<?> getFirstByEmployerIdOrderByDateOfConfirmDesc(@RequestParam(name = "id") int id) {
		return ResponseEntity.ok(this.emailConfirmToEmployerService.getFirstByEmployerIdOrderByDateOfConfirmDesc(id));
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody EmailConfirmToEmployer emailConfirmToToEmployer) {
		return ResponseEntity.ok(this.emailConfirmToEmployerService.add(emailConfirmToToEmployer));
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestParam(name = "id") int id) {
		return ResponseEntity.ok(this.emailConfirmToEmployerService.delete(id));
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody EmailConfirmToEmployer emailConfirmToToEmployer) {
		return ResponseEntity.ok(this.emailConfirmToEmployerService.update(emailConfirmToToEmployer));
	}
	
	@PostMapping("/confirmemployer)")
	public ResponseEntity<?> confirmEmployer(@Valid @RequestBody Employer employer) {
		return ResponseEntity.ok(this.emailConfirmToEmployerService.confirmEmployer(employer));
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
