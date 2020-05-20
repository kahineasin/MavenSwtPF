package pf.java.pfHelper;

import pf.java.pfHelper.config.PFDataHelper;

public enum FuncAuthority  implements PFEnum {
	Default("Default",0),Fgs("Fgs",1)
	;
	private int _value;
	private String _text;
	
	@Override
	public int getValue() {
		return _value;
	}

	@Override
	public String getText() {
		return _text;
	}

	@Override
	public Boolean hasFlag(PFEnum other) {
		// TODO Auto-generated method stub
		return PFDataHelper.EnumHasFlag(this._value, other.getValue());
	}	

	private FuncAuthority(String text,int value) {
		_value=value;
		_text=text;		
	}		
}
