package com.kodlamaio.hrmsDemo6.business.abstracts;

import java.util.List;

import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.DataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.Result;
import com.kodlamaio.hrmsDemo6.entities.concretes.SystemEmployeeConfirmToEmployer;

public interface SystemEmployeeConfirmToEmployerService {
	DataResult<List<SystemEmployeeConfirmToEmployer>> getAll();
	DataResult<SystemEmployeeConfirmToEmployer> get(int id);
	DataResult<SystemEmployeeConfirmToEmployer> getByEmployerId(int id);
	//DataResult<SystemEmployeeConfirmToEmployer> getFirstByEmployerIdOrderByDateOfConfirmDesc(int id);
	// Date'e göre en güncel confirm üzerinden yapıcaktım ama date de sadece gün-ay-yıl olduğu için 3 tane aynı tarihte confirm olunca
	// ilk confirm i seçiyor ve en güncel confirm gelmiyor. Ayrıca employer bilgisi değişince bütün confirmlerde bilgilerde değişiyor.
	// Böyle olunca da her confirm'in kaydını ayrı ayrı tutma fikrim pek mantıklı olmadı.
	// LocalDate'den date türüne çevirmekte istemedim.
	// Bende date üzerinden değilde farklı bir yolla yapmaya karar verdim.
	
	Result add(SystemEmployeeConfirmToEmployer systemEmployeeConfirmToEmployer);
	Result delete(int id);
	Result deleteByEmployerId(int id);
	Result update(SystemEmployeeConfirmToEmployer systemEmployeeConfirmToEmployer);
	
	Result confirmEmployer(int systemEmployeeId, int employerId);
}
