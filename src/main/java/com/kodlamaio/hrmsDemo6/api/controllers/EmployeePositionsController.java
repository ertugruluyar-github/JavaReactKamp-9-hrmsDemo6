package com.kodlamaio.hrmsDemo6.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.hrmsDemo6.business.abstracts.EmployeePositionService;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.DataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.Result;
import com.kodlamaio.hrmsDemo6.entities.concretes.EmployeePosition;

@RestController
@RequestMapping("/api/employeepositions")
public class EmployeePositionsController {
	
	private EmployeePositionService employeePositionService;
	
	@Autowired
	public EmployeePositionsController(EmployeePositionService employeePositionService) {
		this.employeePositionService = employeePositionService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<EmployeePosition>> getAll() {
		return this.employeePositionService.getAll();
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody EmployeePosition employeePosition) {
		return this.employeePositionService.add(employeePosition);
	}
	
}
