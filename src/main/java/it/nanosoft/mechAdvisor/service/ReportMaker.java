package it.nanosoft.mechAdvisor.service;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;

import it.nanosoft.mechAdvisor.model.Officina;
import it.nanosoft.mechAdvisor.model.Recensione;
import it.nanosoft.mechAdvisor.model.Utente;


/**
 * Classe per la creazione ed il salvataggio dei report su file xlsx.
 * @author emicovi
 */

public class ReportMaker implements Loggable {
	
	

	public ReportMaker() {
	}

	public void createUtenteReports(List<Utente> userList, String queryName) throws SQLException {

		try {
			InputStream fis = ReportMaker.class.getResourceAsStream("/reportUtenteTemplate.xlsx");
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sh = wb.getSheetAt(0);
			fis.close();

			int rownum = 1;
			for (Utente user : userList) {
				Row row = sh.createRow(rownum++);
				createListUser(user, row);
			}

			FileOutputStream out = new FileOutputStream(
			System.getProperty("user.home").concat(System.getProperty("file.separator")).concat(queryName)); 
			wb.write(out);
			out.close();
			wb.close();
		} catch (Exception e) {
			newloggerApp.error(" ---- : ", e);
		}
	}

	// creating cells for each row
	private static void createListUser(Utente user, Row row) {
		Cell cell = row.createCell(0);
		cell.setCellValue(user.getNome());

		cell = row.createCell(1);
		cell.setCellValue(user.getCognome());
	}

	public void createOfficinaReports(List<Officina> officinaList, String queryName) throws SQLException {

		try {
			InputStream fis = ReportMaker.class.getResourceAsStream("/reportOfficinaTemplate.xlsx");
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sh = wb.getSheetAt(0);
			fis.close();

			int rownum = 1;
			for (Officina off : officinaList) {
				Row row = sh.createRow(rownum++);
				createListOfficna(off, row);
			}

			FileOutputStream out = new FileOutputStream(
			System.getProperty("user.home").concat(System.getProperty("file.separator")).concat(queryName)); 
			wb.write(out);
			out.close();
			wb.close();
		} catch (Exception e) {
			newloggerApp.error(" ---- : ", e);
		}
	}

	// creating cells for each row
	private static void createListOfficna(Officina off, Row row) {
		Cell cell = row.createCell(0);
		cell.setCellValue(off.getNome());
		cell = row.createCell(1);
		cell.setCellValue(off.getVotoMedio());

	}

	@Override
	public Logger logging() {
		return null;
	}
}