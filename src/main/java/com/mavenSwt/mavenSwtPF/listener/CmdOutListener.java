package com.mavenSwt.mavenSwtPF.listener;

import java.util.EventListener;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

public class CmdOutListener implements EventListener {

	private Text _text;
	//private Button _button;
	public CmdOutListener(Text text//,Button button
			) {
		_text=text;
		//_button=button;
	}
    public void handleEvent(CmdOutEvent event) {
	    Display.getDefault().syncExec(new Runnable() {//为了解决其它线程不能处理本线程的swt控件https://blog.csdn.net/ecjtuxuan/article/details/2125023
	        public void run() {
	        	String t=_text.getText();
	        	//超出100行截半
	        	if(_text.getLineCount()>100) {
	        		int l=t.length();
	        		_text.setText(t.substring(l/2, l-1));
	        	}
	        	//if(t!=null&&t.length()>5000) {_text.setText("");}
	        	_text.append(event.getOutMsg()+"\r\n");
	        	////_button.setEnabled(true);
	        }
	    }); 
		/*
		 * System.out.println("触发状态改变事件。。。"); System.out.println("当前事件源状态为：" +
		 * event.getSourceState()); System.out.println("。。。。。。。。。。。。。。。。。。。。。。。");
		 */
    }
}