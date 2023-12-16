package in.sohail.utills;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import in.sohail.entity.CitizenPlan;

@Component
public class ExcellGenerator {

	public void generateExcel(HttpServletResponse response, List<CitizenPlan> list, File file) throws IOException {
		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet("Citizen-Plans");
		Row headerRow = sheet.createRow(0);

		headerRow.createCell(0).setCellValue("ID");
		headerRow.createCell(1).setCellValue("Holder Name");
		headerRow.createCell(2).setCellValue("Gender");
		headerRow.createCell(3).setCellValue("Plan name	");
		headerRow.createCell(4).setCellValue("Plan Status");
		headerRow.createCell(5).setCellValue("Plan Start Date");
		headerRow.createCell(6).setCellValue("Plan End Date");
		headerRow.createCell(7).setCellValue("Benifit Amount(Rs)");

		int rownNum = 1;

		for (CitizenPlan plan : list) {
			Row dataRow = sheet.createRow(rownNum);
			dataRow.createCell(0).setCellValue(plan.getCitizenID());
			dataRow.createCell(1).setCellValue(plan.getCitizenName());
			dataRow.createCell(2).setCellValue(plan.getGender());
			dataRow.createCell(3).setCellValue(plan.getPlanName());
			dataRow.createCell(4).setCellValue(plan.getPlanStatus());

			if (null != plan.getStartDate()) {
				dataRow.createCell(5).setCellValue(plan.getStartDate() + "");
			} else {
				dataRow.createCell(5).setCellValue("NA");
			}

			if (null != plan.getEndDate()) {
				dataRow.createCell(6).setCellValue(plan.getEndDate() + "");
			} else {
				dataRow.createCell(6).setCellValue("NA");
			}

			if (null != plan.getBenifitAmount()) {
				dataRow.createCell(7).setCellValue(plan.getBenifitAmount());
			} else {
				dataRow.createCell(7).setCellValue("NA");
			}
			rownNum++;
		}

		ServletOutputStream stream = response.getOutputStream();

		// get local excell file
		FileOutputStream fos = new FileOutputStream(file);
		
		workbook.write(fos);
		workbook.write(stream);
		workbook.close();
	}
}
