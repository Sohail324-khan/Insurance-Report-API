package in.sohail.service;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Example;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.sohail.entity.CitizenPlan;
import in.sohail.repository.CitizenPlanRepository;
import in.sohail.request.SearchRequest;
import in.sohail.utills.EmailSender;
import in.sohail.utills.ExcellGenerator;
import in.sohail.utills.PdfGenerator;

@Service
public class ReportServiceImpl implements ReportService {
	@Autowired
	private CitizenPlanRepository repo;
	@Autowired
	private PdfGenerator pdfGenrate;
	@Autowired
	private ExcellGenerator excelGenarate;

	@Autowired
	private EmailSender sendMail;
	
	@Override
	public List<String> getPlanNames() {
		List<String> list = repo.getplans();
		return list;
	}

	@Override
	public List<String> getPlanStatus() {
		List<String> list = repo.getStatus();
		return list;
	}

	@Override
	public List<CitizenPlan> search(SearchRequest request) {

		CitizenPlan citizenPlan = new CitizenPlan();

		if (null != request.getPlanName() && !(request.getPlanName().isEmpty())) {
			citizenPlan.setPlanName(request.getPlanName());
		}

		if (null != request.getPlanStatus() && !(request.getPlanStatus().isEmpty())) {
			citizenPlan.setPlanStatus(request.getPlanStatus());
		}

		if (null != request.getGender() && !(request.getGender().isEmpty())) {
			citizenPlan.setGender(request.getGender());
		}

		if (null != request.getStartDate() && !(request.getStartDate().isEmpty())) {
			String startdate = request.getStartDate();
			DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate planStartdate = LocalDate.parse(startdate, FORMATTER);
			System.out.println("coverted date--------" + planStartdate);
			citizenPlan.setStartDate(planStartdate);
		}

		if (null != request.getEndDate() && !(request.getEndDate().isEmpty())) {
			String endDate = request.getEndDate();
			DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate planEnddate = LocalDate.parse(endDate, FORMATTER);
			System.out.println("coverted date--------" + planEnddate);
			citizenPlan.setEndDate(planEnddate);
		}

		return repo.findAll(Example.of(citizenPlan));

	}

	@Override
	public void exportExcell(SearchRequest request, HttpServletResponse response) throws Exception {

		List<CitizenPlan> list = search(request);
		File file = new File("Report.xls");
		excelGenarate.generateExcel(response, list, file);
		String to = "khansohailengg@gmail.com";
		String subject = "Test Mail";
		String body = "Hello is test mail for email Functionality";

		sendMail.sendMail(to, subject, body, file,"Plans.xls");
	}

	@Override
	public void exportPdf(SearchRequest request, HttpServletResponse response) throws Exception {

		List<CitizenPlan> list = search(request);

		// defining file name
		File file = new File("Report.pdf");
		pdfGenrate.generatePdf(response, list, file);
		String to = "khansohailengg@gmail.com";
		String subject = "Test Mail";
		String body = "Hello is test mail for email Functionality";

		sendMail.sendMail(to, subject, body, file,"Plans.pdf");
	}
}
