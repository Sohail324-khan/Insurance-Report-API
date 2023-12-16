package in.sohail.controller;

import java.beans.Beans;
import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.sohail.request.SearchRequest;
import in.sohail.service.ReportService;

@Controller
public class ReportController {
	SearchRequest filter = new SearchRequest();

	@Autowired
	private ReportService service;

	public void init(Model model) {
		// model.addAttribute("request", new SearchRequest());
		model.addAttribute("plans", service.getPlanNames());
		model.addAttribute("status", service.getPlanStatus());
		model.addAttribute("gender", Arrays.asList("Male", "Female"));
	}

	@GetMapping("/")
	public String loadIndexPage(Model model) {
		init(model);
		model.addAttribute("request", new SearchRequest());
		filter.setPlanName(null);
		filter.setGender(null);
		filter.setPlanStatus(null);
		filter.setStartDate(null);
		filter.setEndDate(null);
		return "index";
	}

	@PostMapping("/search")
	public String searchReportData(@ModelAttribute("request") SearchRequest req, Model model) {
		System.out.println(req);
		model.addAttribute("reportData", service.search(req));
		init(model);
		BeanUtils.copyProperties(req, filter);
		return "index";
	}

	public Boolean filterIsNull() {
		return ((filter.getGender() != null) || (filter.getPlanName() != null) || (filter.getPlanStatus() != null)
				|| (filter.getStartDate() != null) || (filter.getEndDate() != null));
	}

	public String showSearchError(Model model) {
		model.addAttribute("msg", "Search Plan first!");
		init(model);
		model.addAttribute("request", new SearchRequest());
		return "index";
	}

	@GetMapping("/excel")
	public String excelReportExport(HttpServletResponse response, Model model) throws Exception {

		if (filterIsNull()) {
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition", "attachment;filename=Plans.xls");
			service.exportExcell(filter, response);
		} else {
			return showSearchError(model);
		}
		return null;
	}

	@GetMapping("/pdf")
	public String pdfReportExport(HttpServletResponse response, Model model) throws Exception {

		if (filterIsNull()) {
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment;filename=Plans.pdf");
			service.exportPdf(filter, response);
		} else {
			return showSearchError(model);
		}
		return null;
	}
}
