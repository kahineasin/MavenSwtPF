package pf.java.pfHelper;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import pf.java.pfHelper.config.PFDataHelper;

public class PFSwtHelper {
	public static void Alert(Shell shell,String msg) {
		if(PFDataHelper.StringIsNullOrWhiteSpace(msg)) {return;}
		MessageBox dialog=new MessageBox(shell,SWT.NONE);
        dialog.setText("提示");
        dialog.setMessage(msg);
        dialog.open();		
	}
}
