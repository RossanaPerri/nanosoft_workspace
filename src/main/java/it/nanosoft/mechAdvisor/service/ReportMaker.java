package it.nanosoft.mechAdvisor.service;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import it.nanosoft.mechAdvisor.model.Officina;
import it.nanosoft.mechAdvisor.model.Utente;

/**
 * Questa classe gestisce la creazione di un report di risultati delle query
 * eseguite
 * 
 * @author RossanaPerri
 *
 */
public class ReportMaker implements Loggable {

	/**
	 * Costruttore di default
	 */
	public ReportMaker() {
	}

	/**
	 * crea un report in formato xlsx che contiente i risultati di una query Utente
	 * 
	 * @param userList   lista di utenti che rappresentano il risultato di una query
	 * @param reportName Stringa che rappresenta il nome da assegnare al report
	 *                   creato
	 */
	public void createUtenteReports(List<Utente> userList, String reportName) {
		try {
			InputStream fis = ReportMaker.class.getResourceAsStream("/reportUtenteTemplate.xlsx");
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sh = wb.getSheetAt(0);
			fis.close();

			int rownum = 1;
			for (Utente user : userList) {
				Row row = sh.createRow(rownum++);
				createRowUser(user, row);
			}

			FileOutputStream out = new FileOutputStream(
					System.getProperty("user.home").concat(System.getProperty("file.separator")).concat(reportName));
			wb.write(out);
			out.close();
			wb.close();
		} catch (Exception e) {
			newloggerApp.error(" ---- : ", e);
		}
	}

	/**
	 * crea una riga del file
	 * 
	 * @param user rappresenta l'utenza che verrà inserita sulla riga
	 * @param row  riga del file che verrà valorizzata con le informazione di Utente
	 */
	private void createRowUser(Utente user, Row row) {
		Cell cell = row.createCell(0);
		cell.setCellValue(user.getNome());

		cell = row.createCell(1);
		cell.setCellValue(user.getCognome());
	}

	/**
	 * crea un report in formato xlsx che contiente i risultati di una query
	 * Officina
	 * 
	 * @param officinaList lista di officine che rappresentano il risultato di una
	 *                     query
	 * @param reportName   Stringa che rappresenta il nome da assegnare al report
	 *                     creato
	 */
	public void createOfficinaReports(List<Officina> officinaList, String reportName) {
		try {
			InputStream fis = ReportMaker.class.getResourceAsStream("/reportOfficinaTemplate.xlsx");
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sh = wb.getSheetAt(0);
			fis.close();

			int rownum = 1;
			for (Officina off : officinaList) {
				Row row = sh.createRow(rownum++);
				createRowOfficna(off, row);
			}

			FileOutputStream out = new FileOutputStream(
					System.getProperty("user.home").concat(System.getProperty("file.separator")).concat(reportName));
			wb.write(out);
			out.close();
			wb.close();
		} catch (Exception e) {
			newloggerApp.error(" ---- : ", e);
		}
	}

	/**
	 * crea una riga del file
	 * 
	 * @param off rappresenta l'officina che verrà inserita sulla riga
	 * @param row riga del file che verrà valorizzata con le informazione di
	 *            Officina
	 */
	private void createRowOfficna(Officina off, Row row) {
		Cell cell = row.createCell(0);
		cell.setCellValue(off.getNome());
	}

	@Override
	public Logger logging() {
		return null;
	}
}
