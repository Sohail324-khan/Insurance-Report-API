package in.sohail.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import in.sohail.entity.CitizenPlan;
import in.sohail.request.SearchRequest;

public interface ReportService {

	public List<String> getPlanNames();

	public List<String> getPlanStatus();

	public List<CitizenPlan> search(SearchRequest request);

	public void exportExcell(SearchRequest request,HttpServletResponse response) throws Exception;

	public void exportPdf(SearchRequest request,HttpServletResponse response)throws Exception;

}
