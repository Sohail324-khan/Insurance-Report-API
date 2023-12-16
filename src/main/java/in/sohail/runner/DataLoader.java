package in.sohail.runner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ser.std.ArraySerializerBase;

import in.sohail.entity.CitizenPlan;
import in.sohail.repository.CitizenPlanRepository;

@Component
public class DataLoader implements ApplicationRunner {

	@Autowired
	private CitizenPlanRepository repo;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		// For Food with approved
		CitizenPlan c1 = new CitizenPlan();
		c1.setCitizenName("Sohail");
		c1.setGender("Male");
		c1.setPlanName("Food");
		c1.setPlanStatus("Approved");
		c1.setStartDate(LocalDate.now());
		c1.setEndDate(LocalDate.now().plusMonths(6));
		c1.setBenifitAmount(800.00);

		// For Food with denied
		CitizenPlan c2 = new CitizenPlan();
		c2.setCitizenName("Saood");
		c2.setGender("Male");
		c2.setPlanName("Food");
		c2.setPlanStatus("Denied");
		c2.setDeniedReason("Income more than 30000");

		// For Food with terminated
		CitizenPlan c3 = new CitizenPlan();
		c3.setCitizenName("Rabiya");
		c3.setGender("Female");
		c3.setPlanName("Food");
		c3.setPlanStatus("Terminated");
		c3.setStartDate(LocalDate.now().minusMonths(4));
		c3.setEndDate(LocalDate.now().plusMonths(6));
		c3.setTerminatedDate(LocalDate.now());
		c3.setTerminationReason("Employeed");

		//////////////////////////////////////////////////////////
		// For Cash with approved
		CitizenPlan c4 = new CitizenPlan();
		c4.setCitizenName("Saad");
		c4.setGender("Male");
		c4.setPlanName("Cash");
		c4.setPlanStatus("Approved");
		c4.setStartDate(LocalDate.now());
		c4.setEndDate(LocalDate.now().plusMonths(6));
		c4.setBenifitAmount(2000.00);

		// For Cash with denied
		CitizenPlan c5 = new CitizenPlan();
		c5.setCitizenName("Mayur");
		c5.setGender("Male");
		c5.setPlanName("Cash");
		c5.setPlanStatus("Denied");
		c5.setDeniedReason("Income more than 450000");

		// For Cash with terminated
		CitizenPlan c6 = new CitizenPlan();
		c6.setCitizenName("Zareen");
		c6.setGender("Female");
		c6.setPlanName("Cash");
		c6.setPlanStatus("Terminated");
		c6.setStartDate(LocalDate.now().minusMonths(4));
		c6.setEndDate(LocalDate.now().plusMonths(6));
		c6.setTerminatedDate(LocalDate.now());
		c6.setTerminationReason("Employeed");

		//////////////////////////////////////////////////////////
		// For Medical with approved
		CitizenPlan c7 = new CitizenPlan();
		c7.setCitizenName("Jaanu");
		c7.setGender("Male");
		c7.setPlanName("Medical");
		c7.setPlanStatus("Approved");
		c7.setStartDate(LocalDate.now());
		c7.setEndDate(LocalDate.now().plusMonths(6));
		c7.setBenifitAmount(1500.00);

		// For Medical with denied
		CitizenPlan c8 = new CitizenPlan();
		c8.setCitizenName("Shanu");
		c8.setGender("Male");
		c8.setPlanName("Medical");
		c8.setPlanStatus("Denied");
		c8.setDeniedReason("Income more than 20000");

		// For Medical with terminated
		CitizenPlan c9 = new CitizenPlan();
		c9.setCitizenName("Raani");
		c9.setGender("Female");
		c9.setPlanName("Medical");
		c9.setPlanStatus("Terminated");
		c9.setStartDate(LocalDate.now().minusMonths(4));
		c9.setEndDate(LocalDate.now().plusMonths(6));
		c9.setTerminatedDate(LocalDate.now());
		c9.setTerminationReason("Employeed");
		
		
		repo.deleteAll();
		List<CitizenPlan> list = Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9);
		repo.saveAll(list);

	}

}
