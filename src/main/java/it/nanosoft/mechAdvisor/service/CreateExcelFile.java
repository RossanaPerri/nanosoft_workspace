package it.nanosoft.mechAdvisor.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Set;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;

public class CreateExcelFile implements Loggable {

	private static String fileName = "queryResult.xlsx";
	private static XSSFWorkbook workbook = new XSSFWorkbook();
	private static File file = new File(fileName);

	public static void user(Map<Integer, Object[]> userT, int queryNum) {

		String sheetName = "QueryUserResutl_" + Integer.toString(queryNum);

		// creo un foglio bianco
		XSSFSheet sheet = workbook.createSheet(sheetName);

		// Creo una Map che può contentere un array generico di oggetti
		Map<Integer, Object[]> userTemplate = userT;

		// Itero sulla map e scrivo sul foglio
		Set<Integer> keyset = userTemplate.keySet();
		int rownum = 0;
		for (Integer key : keyset) {
			Row row = sheet.createRow(rownum++);
			Object[] objArr = userTemplate.get(key);
			int cellnum = 0;
			for (Object obj : objArr) {
				Cell cell = row.createCell(cellnum++);
				if (obj instanceof String)
					cell.setCellValue((String) obj);
				else if (obj instanceof Integer)
					cell.setCellValue((Integer) obj);
			}
		}
		writeFiles();
	}

	public static void workshop(Map<Integer, Object[]> workshopT, int queryNum) {

		String sheetName = "QueryWorkshopTResutl_" + Integer.toString(queryNum);

		// creo un foglio bianco
		XSSFSheet sheet = workbook.createSheet(sheetName);

		// Creo una Map che può contentere un array generico di oggetti
		Map<Integer, Object[]> workshopTTemplate = workshopT;

		// Itero sulla map e scrivo sul foglio
		Set<Integer> keyset = workshopTTemplate.keySet();
		int rownum = 0;
		for (Integer key : keyset) {
			Row row = sheet.createRow(rownum++);
			Object[] objArr = workshopTTemplate.get(key);
			int cellnum = 0;
			for (Object obj : objArr) {
				Cell cell = row.createCell(cellnum++);
				if (obj instanceof String)
					cell.setCellValue((String) obj);
				else if (obj instanceof Integer)
					cell.setCellValue((Integer) obj);
			}
		}
		writeFiles();
	}

	public static void writeFiles() {
		try {
			// Scrivo workbook in file system
			FileOutputStream out = new FileOutputStream(file);
			workbook.write(out);
			out.close();
			newloggerApp.error(" ---- queryResult.xlsx written successfully on disk ----");
		} catch (Exception e) {
			newloggerApp.error(e.getMessage());
		}
	}

	@Override
	public Logger logging() {
		// TODO Auto-generated method stub
		return null;
	}
}
