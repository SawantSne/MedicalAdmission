package org.example.restcontroller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.example.model.Admission;
import org.example.model.Category;
import org.example.model.Sex;
import org.example.repository.AdmissionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class AdmissionControllerTest {

	public AdmissionControllerTest() {
	}

	@Autowired
	private TestEntityManager testEntityManager;

	@Autowired
	private AdmissionController admissionController;

	@Autowired
	private AdmissionRepository admissionRepository;

	@Test
	public void addAdmission_whenAllTheDetailsAreValid() throws Exception {
		Admission admission = new Admission(1, LocalDateTime.now(ZoneId.systemDefault()), "Alex",
				new SimpleDateFormat("yyyy-MM-dd").parse("1985-08-29"), Sex.Male, Category.Inpatient, "local");
		admissionController.addAdmission(admission);
		Admission found = admissionRepository.findById(admission.getId());
		assertThat(found.getId()).isEqualTo(admission.getId());
	}

	@Test
	public void addAdmission_whenNameIsNull() throws Exception {
		Admission admission = new Admission(1, LocalDateTime.now(ZoneId.systemDefault()), null,
				new SimpleDateFormat("yyyy-MM-dd").parse("1985-08-29"), Sex.Male, Category.Inpatient, "local");
		DataIntegrityViolationException thrown = Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
			admissionController.addAdmission(admission);
		});
	}

	@Test
	public void addAdmission_whenDOBIsNull() throws Exception {
		Admission admission = new Admission(1, LocalDateTime.now(ZoneId.systemDefault()), "Alex", null, Sex.Male,
				Category.Inpatient, "local");
		NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> {
			admissionController.addAdmission(admission);
		});
	}

	@Test
	public void addAdmission_whenSexIsNull() throws Exception {
		Admission admission = new Admission(1, LocalDateTime.now(ZoneId.systemDefault()), "Alex",
				new SimpleDateFormat("yyyy-MM-dd").parse("1985-08-29"), null, Category.Inpatient, "local");
		DataIntegrityViolationException thrown = Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
			admissionController.addAdmission(admission);
		});
	}

	@Test
	public void addAdmission_whenCategoryIsNull() throws Exception {
		Admission admission = new Admission(1, LocalDateTime.now(ZoneId.systemDefault()), "Alex",
				new SimpleDateFormat("yyyy-MM-dd").parse("1985-08-29"), Sex.Male, null, "local");
		DataIntegrityViolationException thrown = Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
			admissionController.addAdmission(admission);
		});
	}

	@Test
    public void updateAdmission_whenSourceIsExternal() throws Exception {
    	Admission admission = new Admission(1,
                LocalDateTime.now(ZoneId.systemDefault()),
                "Alex",
                new SimpleDateFormat("yyyy-MM-dd").parse("1985-08-29"),
                Sex.Male,
                Category.Inpatient,
                "external");
    	admissionController.addAdmission(admission); 
    	Admission editAdmissionRec = new Admission(1,
                LocalDateTime.now(ZoneId.systemDefault()),
                "Alia",
                new SimpleDateFormat("yyyy-MM-dd").parse("1985-08-29"),
                Sex.Female,
                Category.Outpatient,
                "external");
    	admissionController.editAdmission(editAdmissionRec);
    	Admission found = admissionRepository.findById(admission.getId()); 
    	assertThat(found.getCategory()).isEqualTo(admission.getCategory());
    }

	@Test
	public void updateAdmission_whenAllTheDetailsAreValid() throws Exception {
		Admission admission = new Admission(1, LocalDateTime.now(ZoneId.systemDefault()), "Alia",
				new SimpleDateFormat("yyyy-MM-dd").parse("1990-08-03"), Sex.Male, Category.Inpatient, "local");
		admissionController.addAdmission(admission);
		Admission editAdmissionRec = new Admission(1, LocalDateTime.now(ZoneId.systemDefault()), "Aliana",
				new SimpleDateFormat("yyyy-MM-dd").parse("1990-08-06"), Sex.Female, Category.Inpatient, "local");
		admissionController.editAdmission(editAdmissionRec);
		Admission found = admissionRepository.findById(admission.getId());
		assertThat(found.getId()).isEqualTo(admission.getId());
		assertThat(found.getName()).isEqualTo(editAdmissionRec.getName());
		assertThat(found.getSex()).isEqualTo(editAdmissionRec.getSex());
		assertThat(found.getCategory()).isEqualTo(editAdmissionRec.getCategory());
	}

	@Test
	public void updateAdmission_whenNameIsNull() throws Exception {
		Admission admission = new Admission(1, LocalDateTime.now(ZoneId.systemDefault()), "Alia",
				new SimpleDateFormat("yyyy-MM-dd").parse("1990-08-03"), Sex.Male, Category.Inpatient, "local");
		admissionController.addAdmission(admission);
		Admission editAdmissionRec = new Admission(1, LocalDateTime.now(ZoneId.systemDefault()), null,
				new SimpleDateFormat("yyyy-MM-dd").parse("1990-08-06"), Sex.Female, Category.Inpatient, "local");
		admissionController.editAdmission(editAdmissionRec);
		Admission found = admissionRepository.findById(admission.getId());
		assertThat(found.getId()).isEqualTo(admission.getId());
		assertThat(found.getSex()).isEqualTo(editAdmissionRec.getSex());
		assertThat(found.getCategory()).isEqualTo(editAdmissionRec.getCategory());
	}

	@Test
	public void updateAdmission_whenSexIsNull() throws Exception {
		Admission admission = new Admission(1, LocalDateTime.now(ZoneId.systemDefault()), "Alia",
				new SimpleDateFormat("yyyy-MM-dd").parse("1990-08-03"), Sex.Male, Category.Inpatient, "local");
		admissionController.addAdmission(admission);
		Admission editAdmissionRec = new Admission(1, LocalDateTime.now(ZoneId.systemDefault()), "Arul",
				new SimpleDateFormat("yyyy-MM-dd").parse("1990-08-03"), Sex.Male, Category.Inpatient, "external");
		admissionController.editAdmission(editAdmissionRec);
		Admission found = admissionRepository.findById(admission.getId());
		assertThat(found.getId()).isEqualTo(admission.getId());
		assertThat(found.getSex()).isEqualTo(editAdmissionRec.getSex());
		assertThat(found.getCategory()).isEqualTo(editAdmissionRec.getCategory());
	}

	@Test
	public void updateAdmission_whenCategoryIsNull() throws Exception {
		Admission admission = new Admission(1, LocalDateTime.now(ZoneId.systemDefault()), "Alex",
				new SimpleDateFormat("yyyy-MM-dd").parse("1985-08-29"), Sex.Male, Category.Inpatient, "local");
		admissionController.addAdmission(admission);
		Admission editAdmissionRec = new Admission(1, LocalDateTime.now(ZoneId.systemDefault()), "Arul",
				new SimpleDateFormat("yyyy-MM-dd").parse("1990-08-03"), Sex.Male, Category.Inpatient, "external");
		admissionController.editAdmission(editAdmissionRec);
		Admission found = admissionRepository.findById(admission.getId());
		assertThat(found.getId()).isEqualTo(admission.getId());
		assertThat(found.getSex()).isEqualTo(editAdmissionRec.getSex());
		assertThat(found.getCategory()).isEqualTo(editAdmissionRec.getCategory());
	}
	
	@Test
	public void updateAdmission_whenAdmissionNotFound() throws Exception {
		Admission admission = new Admission(1, LocalDateTime.now(ZoneId.systemDefault()), "Alex",
				new SimpleDateFormat("yyyy-MM-dd").parse("1985-08-29"), Sex.Male, Category.Inpatient, "local");
		admissionController.addAdmission(admission);
		Admission editAdmissionRec = new Admission(2, LocalDateTime.now(ZoneId.systemDefault()), "Arul",
				new SimpleDateFormat("yyyy-MM-dd").parse("1990-08-03"), Sex.Male, Category.Inpatient, "external");
		admissionController.editAdmission(editAdmissionRec);
		Admission found = admissionRepository.findById(editAdmissionRec.getId());
		assertThat(found).isEqualTo(null);
	}

	@Test
	public void fetchAdmission() throws Exception {
		Admission admission = new Admission(1, LocalDateTime.now(ZoneId.systemDefault()), "Alex",
				new SimpleDateFormat("yyyy-MM-dd").parse("1985-08-29"), Sex.Male, Category.Inpatient, "local");
		admissionController.addAdmission(admission);
		Admission admission1 = new Admission(2, LocalDateTime.now(ZoneId.systemDefault()), "Alina",
				new SimpleDateFormat("yyyy-MM-dd").parse("1985-08-29"), Sex.Female, Category.Outpatient, "local");
		admissionController.addAdmission(admission1);
		Admission admission2 = new Admission(3, LocalDateTime.now(ZoneId.systemDefault()), "Alpina",
				new SimpleDateFormat("yyyy-MM-dd").parse("1985-08-29"), Sex.Female, Category.Emergency, "local");
		admissionController.addAdmission(admission2);
		 
		 List<Admission> admissionList= admissionController.fetchAdmissions();
		 assertThat(admissionList.size()).isEqualTo(3);
	}
	
	@Test
	public void validateDOB() throws Exception {
		
		Admission admission = new Admission(1, LocalDateTime.now(ZoneId.systemDefault()), "Alex", 
				new SimpleDateFormat("yyyy-MM-dd").parse("2025-08-29"), Sex.Male, Category.Inpatient, "local");
		
		java.lang.Exception thrown = Assertions.assertThrows(java.lang.Exception.class, () -> {
			admissionController.addAdmission(admission);
		});		
	}

}