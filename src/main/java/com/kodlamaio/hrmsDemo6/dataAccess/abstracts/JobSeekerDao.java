package com.kodlamaio.hrmsDemo6.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kodlamaio.hrmsDemo6.entities.concretes.JobSeeker;

/**
 * @author Ertuğrul Uyar
 * @LinkedIn www.linkedin.com/in/ertugruluyar
 * @GitHub https://github.com/euyar42
 */

@Repository
public interface JobSeekerDao extends JpaRepository<JobSeeker, Integer> {
	boolean existsJobSeekerByNationalityId(String nationalityId);
	boolean existsJobSeekerByEmail(String email);
	
	List<JobSeeker> findByFavouriteJobAdvertisements_Id(int id);
	// Id'si "id" olan iş ilanını beğenmiş olan iş arayanları getirir.
	// İsterlerde yok ama ilerde lazım olabilir diye sadece manager'a kadar yazacağım ve controller'a eklemeyeceğim.
}
