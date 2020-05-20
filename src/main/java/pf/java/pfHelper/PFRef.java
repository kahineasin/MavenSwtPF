package pf.java.pfHelper;

/*
 * 因为java没有ref关键字,这样用吧
 */
public class PFRef<T> {
	private T _value;
	public PFRef(T value) {
		
		_value=value;
	}
	public T GetValue() {
		return _value;		
	}
	public void SetValue(T value) {
		_value=value;
	}
}
