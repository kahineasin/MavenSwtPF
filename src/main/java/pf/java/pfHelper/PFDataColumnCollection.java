package pf.java.pfHelper;

import java.util.ArrayList;

public class PFDataColumnCollection extends ArrayList<PFDataColumn>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PFDataColumn add(String columnName, Class<?> type) {
		PFDataColumn column=new PFDataColumn(columnName,type);		
		super.add(column );
		return column;
	}
}
