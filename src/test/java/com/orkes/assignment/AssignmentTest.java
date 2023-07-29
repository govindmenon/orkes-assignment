package com.orkes.assignment;

import com.orkes.assignment.core.SpreadSheet;
import com.orkes.assignment.exceptions.CellNotFoundException;
import com.orkes.assignment.exceptions.UnsupportedFormulaException;
import com.orkes.assignment.exceptions.UnsupportedValueException;
import com.orkes.assignment.exceptions.ValueNotFoundException;
import org.junit.Test;
import static org.junit.Assert.*;

public class AssignmentTest {

    @Test
    public void setIntegerValuesInCells(){
        SpreadSheet sheet1 = new SpreadSheet();
        try {
            sheet1.setCellValue("A1", 13);
            sheet1.setCellValue("A2", 14);
            assertEquals(sheet1.getCellValue("A1"), 13);
            assertEquals(sheet1.getCellValue("A2"), 14);
        } catch (UnsupportedValueException | UnsupportedFormulaException | ValueNotFoundException | CellNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void setFormulaInCells(){
        SpreadSheet sheet1 = new SpreadSheet();
        try {
            sheet1.setCellValue("A1", 13);
            sheet1.setCellValue("A2", 14);
            sheet1.setCellValue("A3", "=A1+A2");
            sheet1.setCellValue("A4", "=A1+A2+A3");
            assertEquals(sheet1.getCellValue("A3"), 27);
            assertEquals(sheet1.getCellValue("A4"), 54);
        } catch (UnsupportedValueException | UnsupportedFormulaException | ValueNotFoundException | CellNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void setAnotherCellValueInCell(){
        SpreadSheet sheet1 = new SpreadSheet();
        try {
            sheet1.setCellValue("A1", 13);
            sheet1.setCellValue("A2", 14);
            sheet1.setCellValue("A3", "=A1+A2");
            sheet1.setCellValue("A4", "=A3");
            assertEquals(sheet1.getCellValue("A4"), 27);
        } catch (UnsupportedValueException | UnsupportedFormulaException | ValueNotFoundException | CellNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = UnsupportedFormulaException.class)
    public void setInvalidFormulaInCell() throws UnsupportedValueException, UnsupportedFormulaException, CellNotFoundException {
        SpreadSheet sheet1 = new SpreadSheet();
        sheet1.setCellValue("A1", "bhdbcjbcd");
    }

    @Test(expected = UnsupportedValueException.class)
    public void setInvalidValueInCell() throws UnsupportedValueException, UnsupportedFormulaException, CellNotFoundException {
        SpreadSheet sheet1 = new SpreadSheet();
        sheet1.setCellValue("A1", true);
    }

    @Test(expected = ValueNotFoundException.class)
    public void getInvalidCellId() throws ValueNotFoundException {
        SpreadSheet sheet1 = new SpreadSheet();
        sheet1.getCellValue("A1");
    }

    @Test(expected = CellNotFoundException.class)
    public void getFormulaValueWithInvalidCellId() throws UnsupportedValueException, CellNotFoundException, UnsupportedFormulaException {
        SpreadSheet sheet1 = new SpreadSheet();
        sheet1.setCellValue("A1","=A2");
    }

    @Test(expected = CellNotFoundException.class)
    public void getFormulaValueWithInvalidCellIds() throws UnsupportedValueException, CellNotFoundException, UnsupportedFormulaException {
        SpreadSheet sheet1 = new SpreadSheet();
        sheet1.setCellValue("A1",1);
        sheet1.setCellValue("A2","=A1+A2+A3");
    }
}
