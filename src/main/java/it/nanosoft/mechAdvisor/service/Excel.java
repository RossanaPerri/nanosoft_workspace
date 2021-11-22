package it.nanosoft.mechAdvisor.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {
	private static final Workbook book = new XSSFWorkbook();
	public void toExcel(ResultSet rs, String nq) throws FileNotFoundException, IOException, SQLException {		
		final ResultSetMetaData rsmd = rs.getMetaData();
		List<String> columns = new ArrayList<String>() {{
			for (int i=1; i <= rsmd.getColumnCount(); i++) {
				add(rsmd.getColumnLabel(i));
				}
			}};

			try {
				Sheet sheet = book.createSheet(nq);
				Row header = sheet.createRow(0);
				for (int i=0; i<columns. size(); i++) {
					header.createCell(i).setCellValue(columns.get(i));
				}
				int rowIndex = 0;
				while(rs.next()) {
					Row row = sheet. createRow(++rowIndex);
					for (int i=0; i<columns. size(); i++) {
						row.createCell(i).setCellValue(Objects.toString(rs.getObject(columns.get(i)),""));

					}
				}
			}catch (Exception e) {
				e.getMessage();
			}
			
	        File f = new File(System.getProperty("user.home").concat(System.getProperty("file.separator")).concat("query.xlsx"));

				
				try(FileOutputStream fos = new FileOutputStream (f))
				
{
					book.write (fos);
					}
			
			
			fromExcel();
	}
	
	public void fromExcel() {
	        try
	        {
	            FileInputStream file = new FileInputStream(new File("demo.xlsx"));
	 
	            //Create Workbook instance holding reference to .xlsx file
	            XSSFWorkbook workbook = new XSSFWorkbook(file);
	 
	            //Get first/desired sheet from the workbook
	            XSSFSheet sheet = workbook.getSheetAt(0);
	 
	            //Iterate through each rows one by one
	            Iterator<Row> rowIterator = sheet.iterator();
	            while (rowIterator.hasNext()) 
	            {
	                Row row = rowIterator.next();
	                //For each row, iterate through all the columns
	                Iterator<Cell> cellIterator = row.cellIterator();
	                 
	                while (cellIterator.hasNext()) 
	                {
	                    Cell cell = cellIterator.next();
	                    //Check the cell type and format accordingly
	                    switch (cell.getCellType()) 
	                    {
	                        case NUMERIC:
	                            System.out.print(cell.getNumericCellValue() + "---");
	                            break;
	                        case STRING:
	                            System.out.print(cell.getStringCellValue() + "---");
	                            break;
	                    }
	                }
	                System.out.println("");
	            }
	            file.close();
	        } 
	        catch (Exception e) 
	        {
	            e.printStackTrace();
	        }
	    }
	}


			
