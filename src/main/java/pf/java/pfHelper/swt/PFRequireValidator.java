package pf.java.pfHelper.swt;


import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

public class PFRequireValidator {
	private Text[] _texts;
	private Color _errorColor;
	private Color _defaultColor;
	public PFRequireValidator(Text[] texts) {
		_texts=texts;		
		_errorColor=new Color(Display.getCurrent(),255,0,0);
		//_defaultColor=texts[0].getBackground();
		_defaultColor=new Color(Display.getCurrent(),255,255,255);
	}
	public Boolean IsValidate() {
		for(int i=0;i<_texts.length;i++) {
			String t=_texts[i].getText();
			if(t==null||t.isEmpty()) {
				_texts[i].setBackground(_errorColor);
				return false;
			}else if(_texts[i].getBackground().equals(_errorColor)){
				_texts[i].setBackground(_defaultColor);
			}
		}
		return true;
	}
}
