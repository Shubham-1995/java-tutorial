import java.io.File;
import java.io.IOException;
import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
public class ReadWorkSheet {
	public static void main(String[] args) {
		File file = new File("src/2017_ITR1_PR2.xls");  // get file path
		//System.out.println("file ------ >>>> "+file);
		try {
			Workbook workbook = Workbook.getWorkbook(file); // get file Object and Provides a handler to individual sheets
			System.out.println("workbook ------ >>>> "+workbook);
			 Sheet sheet = workbook.getSheet(0); //getSheet
			 System.out.println("sheet ------ >>>> "+sheet);// Provides a handle to the individual cells, or lines of cells (grouped by Row or Column)
			// System.out.println("get Rows --- > "+sheet.getRows());
			// System.out.println("get Coloumn ------- > "+sheet.getColumns());
			 int nuberColm = sheet.getColumns();// get coloumn in excel sheet 
			 for(int i=0;i<sheet.getRows();i++){
				 for(int j=0;j<sheet.getColumns();j++){
					 Cell cell = sheet.getCell(j, i); // Returns the cell specified at this row and at this column
					 CellType type = cell.getType(); // type of cell
					// System.out.println("see Type --------- > "+type);
 					 if(type == CellType.LABEL){ 
 						 if(j%nuberColm == 0){
 							System.out.print("\n");
 						 }
 						 System.out.print(cell.getContents()+"\t");
 					 }	
 					 if(type == CellType.NUMBER){
 						 if(j%4 == 0){
   							System.out.print("\n");
   						 }
  						System.out.print(cell.getContents()+"\t");  
 					 }
 				
 					 if(type == CellType.FORMULA_ERROR){
 						 if(j%4 == 0){
   							System.out.print("\n");
   						 }
  						System.out.print(cell.getContents()+"\t");  
  					 }
 					 if(type == CellType.NUMBER_FORMULA){
 						 if(j%4 == 0){
   							System.out.print("\n");
   						 }
  						System.out.print(cell.getContents()+"\t");   
  					 }
				 }
			 }
	/*		 Cell Form = sheet.getCell(0, 0);
			 Cell FirstName = sheet.getCell(1,3);
			 Cell LastName = sheet.getCell(2,3);
			 System.out.println("get Content ------- > "+Form.getContents());
			 System.out.println("get Content2 ------- > "+FirstName.getContents());
			 System.out.println("get Content3 ------- > "+LastName.getContents());*/
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}