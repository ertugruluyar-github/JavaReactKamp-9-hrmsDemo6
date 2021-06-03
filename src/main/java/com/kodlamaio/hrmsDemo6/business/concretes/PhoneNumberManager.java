package com.kodlamaio.hrmsDemo6.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.hrmsDemo6.business.abstracts.PhoneNumberService;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.DataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.ErrorDataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.Result;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.SuccessDataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.SuccessResult;
import com.kodlamaio.hrmsDemo6.dataAccess.abstracts.PhoneNumberDao;
import com.kodlamaio.hrmsDemo6.entities.concretes.PhoneNumber;

@Service
public class PhoneNumberManager implements PhoneNumberService {
	
	private PhoneNumberDao phoneNumberDao;
	
	@Autowired
	public PhoneNumberManager(PhoneNumberDao phoneNumberDao) {
		this.phoneNumberDao = phoneNumberDao;
	}


	@Override
	public DataResult<List<PhoneNumber>> getAll() {
		return new SuccessDataResult<List<PhoneNumber>>("Phone numbers listed successfully.",
				this.phoneNumberDao.findAll());
	}

	@Override
	public DataResult<PhoneNumber> get(int id) {
		if (this.phoneNumberDao.findById(id).orElse(null) != null ) {
			return new SuccessDataResult<PhoneNumber>(
					"The specified phone number was found successfully.", this.phoneNumberDao.findById(id).get());
		} else {
			return new ErrorDataResult<PhoneNumber>("The specified phone number is not available.");
		}
	}

	@Override
	public Result add(PhoneNumber employeePosition) {
		this.phoneNumberDao.save(employeePosition);
		return new SuccessResult("Phone number added successfully.");
	}

	@Override
	public Result delete(int id) {
		this.phoneNumberDao.deleteById(id);
		return new SuccessResult("Phone number deleted successfully.");
	}

	@Override
	public Result update(PhoneNumber employeePosition) {
		this.phoneNumberDao.save(employeePosition);
		return new SuccessResult("Phone number updated successfully.");
	}
	
}
