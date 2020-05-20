package pf.java.pfHelper;

/*
 * 无用
 */
public class PFNullable <T> {
	private T _value;
	private Boolean _isNull;
//	public PFNullable(T value) {
//		_value=value;
//	}
	public T GetValue() {
		return _value;
	}
	public Boolean IsNull() {
		return _isNull;
	}
}
