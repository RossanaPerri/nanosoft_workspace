package it.nanosoft.mechAdvisor.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;

import it.nanosoft.mechAdvisor.model.Utente;

/**
 * Questa è la classe che gestisce il salvataggio delle query su file xlsx e la
 * stampa delle eury su console tramite lettura del file xlsx precedentemente
 * saalvato.
 * 
 * @author emicovi
 */

public class ReportMaker implements Loggable {

	public void reportUtente(List<Utente> userList) throws IOException {

		try {
			InputStream fis = ReportMaker.class.getResourceAsStream("/reportUtenteTemplate.xlsx");
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			fis.close();
			XSSFSheet sh = wb.getSheetAt(q);

			int rownum = 1;
			for (Utente user : userList) {
				Row row = sh.createRow(rownum++);
				createList(user, row);
			}
			FileOutputStream out = new FileOutputStream(System.getProperty("user.home").concat(System.getProperty("file.separator")).concat("reportUtente.xlsx")); // file name with path
			wb.write(out);
			out.close();
			wb.close();
		} catch (Exception e) {
			newloggerApp.error(" ---- : ", e);
		}
	}

// creating cells for each row
	private static void createList(Utente user, Row row) {
		Cell cell = row.createCell(0);
		cell.setCellValue(user.getNome());
		cell = row.createCell(1);
		cell.setCellValue(user.getCognome());
	}

	@Override
	public Logger logging() {
		// TODO Auto-generated method stub
		return null;
	}

}
