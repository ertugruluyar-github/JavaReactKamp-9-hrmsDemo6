package com.kodlamaio.hrmsDemo6.entities.concretes;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="job_advertisements")
public class JobAdvertisement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="description", nullable = false)// zorunlu
	@NotNull
	@NotBlank
	private String description;
	
	@Column(name="min_salary")// opsiyonel
	@Positive
	private int minSalary;
	
	@Column(name="max_salary")// opsiyonel
	@Positive
	private int maxSalary;
	
	@Column(name="number_of_position", nullable = false)// zorunlu
	@NotNull
	@Positive
	private short numberOfPosition;
	
	@Column(name="application_deadline")
	@Future
	private LocalDate applicationDeadline;
	
	@Column(name="release_date", nullable = false)
	@NotNull
	@PastOrPresent
	private LocalDate releaseDate = LocalDate.now();
	// Json'da releaseDate gönderilmezse. Null bırakamaz ama yinede olsun.
	
	@Column(name="active", nullable = false)
	@NotNull
	private boolean active;
	
	
	@ManyToOne
	private EmployeePosition employeePosition;
	
	// Default olarak sütun ismini, field adı(city) ile City'nin primary key adını(id)
	// "_" ile birleştirerek city_id şeklinde oluşturur. Ekstra bu adı düzenlemek için JoinColumn'a ihtiyaç yoktur.
	@ManyToOne//(cascade = CascadeType.PERSIST)// JobAdvertisement kaydedince City'yi de otomatik kaydeder.
	private City city;
	// Henüz sistemde hazır kayıtlı şehirler olmadığı için iş ilanı kaydederken otoatik city'yi de kaydetmesi için cascade koyulabilir.
	// şehirleri önceden veri tabanına kaydedersek buna gerek kalmaz.
	
	// Default olarak sütun ismini, field adı(employer) ile Employer'ın primary key adını(id)
	// "_" ile birleştirerek employer_id şeklinde oluşturur. Ekstra bu adı düzenlemek için JoinColumn'a ihitaç yoktur.
	@ManyToOne//(cascade = CascadeType.PERSIST)// JobAdvertisement kaydedince Employer'ı de otomatik kaydeder.
	private Employer employer;
	// Henüz sistemde hazır kayıtlı işverenler olmadığı için iş ilanı kaydederken otoatik employer'ı da kaydetmesi için cascade koyulabilir.
	// iş veren, sisteme yeni iş ilanı eklerken zaten kayıtlı olacağı için bu cascade kaldırılır sonra.
	
	@ManyToOne
	@JoinColumn(name = "working_place_type_id")
	private WorkingPlaceType workingPlaceType;
	
	@ManyToOne
	@JoinColumn(name = "working_time_type_id")
	private WorkingTimeType workingTimeType;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "favouriteJobAdvertisements", cascade = CascadeType.REMOVE)
	private List<JobSeeker> jobSeekersWhoLikeIt;
	
	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = LocalDate.now();
		// Json'da releaseDate gönderilmeye çalışılırsa işe yaramayacak.
	}
	
}
