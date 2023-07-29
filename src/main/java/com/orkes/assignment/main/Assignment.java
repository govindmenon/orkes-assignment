package com.orkes.assignment.main;

import com.orkes.assignment.core.SpreadSheet;
import com.orkes.assignment.exceptions.CellNotFoundException;
import com.orkes.assignment.exceptions.UnsupportedFormulaException;
import com.orkes.assignment.exceptions.UnsupportedValueException;
import com.orkes.assignment.exceptions.ValueNotFoundException;

public class Assignment {
    public static void main(String[] args) {

        SpreadSheet sheet1 = new SpreadSheet();
        try {
            sheet1.setCellValue("A1", 13);
            sheet1.setCellValue("A2", 14);
            System.out.println(sheet1.getCellValue("A1"));
            System.out.println(sheet1.getCellValue("A2"));

            sheet1.setCellValue("A3", "=A1+A2");
            System.out.println(sheet1.getCellValue("A3"));

            sheet1.setCellValue("A4", "=A3");
            System.out.println(sheet1.getCellValue("A4"));

            sheet1.setCellValue("A1", 15);
            System.out.println(sheet1.getCellValue("A3"));
            System.out.println(sheet1.getCellValue("A4"));
        } catch (UnsupportedValueException | UnsupportedFormulaException | ValueNotFoundException | CellNotFoundException e) {
            e.printStackTrace();
        }
    }
}
