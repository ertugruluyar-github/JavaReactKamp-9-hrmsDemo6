package com.kodlamaio.hrmsDemo6.api.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

import com.kodlamaio.hrmsDemo6.business.abstracts.JobAdvertisementService;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.ErrorDataResult;
import com.kodlamaio.hrmsDemo6.entities.concretes.JobAdvertisement;

@RestController
@RequestMapping("/api/jobadvertisements")
@CrossOrigin
public class JobAdvertisementsController {
	
	private JobAdvertisementService jobAdvertisementService;
	
	@Autowired
	public JobAdvertisementsController(JobAdvertisementService jobAdvertisementService) {
		this.jobAdvertisementService = jobAdvertisementService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody JobAdvertisement jobAdvertisement) {
		return ResponseEntity.ok(this.jobAdvertisementService.add(jobAdvertisement));
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestParam(name = "id") int id) {
		return ResponseEntity.ok(this.jobAdvertisementService.delete(id));
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody JobAdvertisement jobAdvertisement) {
		return ResponseEntity.ok(this.jobAdvertisementService.update(jobAdvertisement));
	}
	
	@GetMapping("/getall")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(this.jobAdvertisementService.getAll());
	}
	
	@GetMapping("/get")
	public ResponseEntity<?> get(@RequestParam(name = "id") int id) {
		return ResponseEntity.ok(this.jobAdvertisementService.get(id));
	}
	
	@GetMapping("/getallactivated")
	public ResponseEntity<?> getAllActivated() {
		return ResponseEntity.ok(this.jobAdvertisementService.getAllByActivated());
	}
	
	@GetMapping("/getallactivatedorderbyreleasedateasc")
	public ResponseEntity<?> getAllByActivatedOrderByReleaseDateAsc() {
		return ResponseEntity.ok(this.jobAdvertisementService.getAllByActivatedOrderByReleaseDateAsc());
	}	

	@GetMapping("/getallactivatedorderbyapplicationdeadlineasc")
	public ResponseEntity<?> getAllByActivatedOrderByApplicationDeadlineAsc() {
		return ResponseEntity.ok(this.jobAdvertisementService.getAllByActivatedOrderByApplicationDeadlineAsc());
	}		
	
	@GetMapping("/getallactivatedandemployerid")
	public ResponseEntity<?> getAllByActivatedAndEmployerId(@RequestParam(name = "id") int id) {
		return ResponseEntity.ok(this.jobAdvertisementService.getAllByActivatedAndEmployerId(id));
	}
	
	@GetMapping("/getallbyactivatedandworkingtimetype")
	public ResponseEntity<?> getAllByActivatedAndWorkingTimeType(@RequestParam(name = "type") String type) {
		return ResponseEntity.ok(this.jobAdvertisementService.getAllByActivatedAndWorkingTimeType(type));
	}
	
	@GetMapping("/getallbyactivatedandworkingplacetype")
	public ResponseEntity<?> getAllByActivatedAndWorkingPlaceType(@RequestParam(name = "type") String type) {
		return ResponseEntity.ok(this.jobAdvertisementService.getAllByActivatedAndWorkingPlaceType(type));
	}
	
	@GetMapping("/getallbyactivatedwithpageable")
	public ResponseEntity<?> getAllByActivatedWithPageable(@RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "pageSize") int pageSize) {
		Pageable allEntitiesWithPageable = PageRequest.of(pageNumber - 1, pageSize);
		return ResponseEntity.ok(this.jobAdvertisementService.getAllByActivatedWithPageable(allEntitiesWithPageable));
	}
	
	@GetMapping("/activatejobadvertisement")
	public ResponseEntity<?> activateJobAdvertisement(@RequestParam(name = "id") int id) {
		return ResponseEntity.ok(this.jobAdvertisementService.activateJobAdvertisement(id));
	}
	
	@GetMapping("/deactivatejobadvertisement")
	public ResponseEntity<?> deactivateJobAdvertisement(@RequestParam(name = "id") int id) {
		return ResponseEntity.ok(this.jobAdvertisementService.deactivateJobAdvertisement(id));
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
