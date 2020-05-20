package com.mavenSwt.mavenSwtPF.listener;

import java.util.EventListener;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

import com.mavenSwt.mavenSwtPF.form.MavenSwtPFAppForm;
import com.mavenSwt.mavenSwtPF.form.MavenSwtPFAppJarTab;

public class CmdStartListener implements EventListener {

	private MavenSwtPFAppJarTab _jarTab;
	public CmdStartListener(MavenSwtPFAppJarTab jarTab//,Button button
			) {
		_jarTab=jarTab;
	}
    public void handleEvent(CmdStartEvent event) {
//	    Display.getDefault().syncExec(new Runnable() {//为了解决其它线程不能处理本线程的swt控件https://blog.csdn.net/ecjtuxuan/article/details/2125023
//	        public void run() {
//				_jarTab.getRebootBtn().setEnabled(false);
//				_jarTab.getTabItemTmp().setShowClose(false);
//				_jarTab.getTabItemTmp().setText(_jarTab.getSpringModel().getSpringName());
//				_jarTab.getOutText().setText("");
//				_jarTab.getErrText().setText("");
//	        }
//	    }); 
    	MavenSwtPFAppForm._display.syncExec(new Runnable() {//为了解决其它线程不能处理本线程的swt控件https://blog.csdn.net/ecjtuxuan/article/details/2125023
	        public void run() {
				_jarTab.getRebootBtn().setEnabled(false);
				_jarTab.getTabItemTmp().setShowClose(false);
				_jarTab.getTabItemTmp().setText(_jarTab.getSpringModel().getSpringName());
				_jarTab.getOutText().setText("");
				_jarTab.getErrText().setText("");
	        }
	    }); 
    }
}