package com.orkes.assignment.core;

import com.orkes.assignment.exceptions.CellNotFoundException;
import com.orkes.assignment.exceptions.UnsupportedFormulaException;
import com.orkes.assignment.exceptions.UnsupportedValueException;
import com.orkes.assignment.exceptions.ValueNotFoundException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SpreadSheet {

    private Map<String, Object> cells;

    public SpreadSheet(){
        cells = new HashMap<>();
    }

    public void setCellValue(String cellId, Object value) throws UnsupportedValueException, UnsupportedFormulaException, CellNotFoundException {
        if(value instanceof Integer || value instanceof String){
            if(value instanceof Integer){
                cells.put(cellId, value);
            } else {
                String cellValue = (String)value;
                if(cellValue.startsWith("=")){
                    String[] parts = cellValue.replace("=","").split("\\+");
                    for(String part: parts){
                        if(!cells.containsKey(part)){
                            throw new CellNotFoundException(part + " cell not found in spreadsheet");
                        }
                    }
                    cells.put(cellId, cellValue.replace("=",""));
                } else {
                    throw new UnsupportedFormulaException("Formula not supported by spreadsheet");
                }
            }
        } else {
            throw new UnsupportedValueException("Value unsupported by spreadsheet");
        }
    }

    public int getCellValue(String cellId) throws ValueNotFoundException {
        if(!cells.containsKey(cellId)){
            throw new ValueNotFoundException("Value not found in spreadsheet");
        }
        Object value = cells.get(cellId);
        if(value instanceof Integer){
            return (int)value;
        } else {
            String[] parts = ((String)value).split("\\+");
            return Arrays.stream(parts).filter(e -> cells.containsKey(e)).map(e -> {
                int cellValue = 0;
                try {
                    cellValue = getCellValue(e);
                } catch (ValueNotFoundException ex) {
                    ex.printStackTrace();
                }
                return cellValue;
            }).reduce((a,b) -> a + b).get();
        }
    }

}
