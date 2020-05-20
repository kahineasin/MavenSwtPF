package com.mavenSwt.mavenSwtPF;

import org.eclipse.swt.*;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;


public class ProgressBarExample extends Dialog {

	protected Shell shell;
	private int result;

	/*
	 * private ProgressBar progressBar; private int max; private int i; private int
	 * Value; private int value;
	 */

	public ProgressBarExample(Shell parent) {
		//super(parent, SWT.APPLICATION_MODAL);
		super(parent);

	}

	public int open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		
		return result;
	}
	public int close() {
		shell.dispose();
		return 1;
	}

	protected void createContents() {
		{
	         shell  =   new  Shell(getParent(), SWT.DIALOG_TRIM  |  SWT.APPLICATION_MODAL);
	         shell.setSize( 150 ,  70 );
	         shell.setText( " 第一个对话框 " );
	  
	          final  Button okButton  =   new  Button(shell, SWT.NONE);
	          okButton.addSelectionListener( new  SelectionAdapter()  {
	               public   void  widgetSelected(SelectionEvent e)  {
	                 result  =   1 ;
	                 shell.dispose();
	             } 
	         } );
	         okButton.setText( " OK " );
	         okButton.setBounds( 10 ,  10 ,  48 ,  22 );
			/*
			 * try { Thread.sleep(5000); } catch (Throwable e) { } shell.dispose();
			 */
	         
	  /*
	          final  Button cancelButton  =   new  Button(shell, SWT.NONE);
	          cancelButton.addSelectionListener( new  SelectionAdapter()  {
	               public   void  widgetSelected(SelectionEvent e)  {
	                 result  =   2 ;
	                 shell.dispose();
	             } 
	         } );
	         cancelButton.setText( " Cancel " );
	         cancelButton.setBounds( 89 ,  10 ,  48 ,  22 );
	         
	         
		final Display display = Display.getDefault();
		shell = new Shell(SWT.ON_TOP);
		shell.setSize(340, 300);
		// 设置窗体位置
		shell.setLocation(350, 180);
		shell.setLayout(new FormLayout());
		shell.setBackground(display.getSystemColor(SWT.COLOR_BLUE | SWT.COLOR_BLACK)); // 设置背景颜色
*/		/*
		 * // 定义进度条 progressBar = new ProgressBar(shell, SWT.SMOOTH); FormData data =
		 * new FormData(337, 20); data.bottom = new FormAttachment(50, 50, 0);
		 * progressBar.setLayoutData(data); max = progressBar.getMaximum(); // 创建一个线程
		 * new Thread() { public void run() { for (i = 0; i <= max; i++) { try {
		 * Thread.sleep(5); } catch (Throwable e) { } display.asyncExec(new Runnable() {
		 * public void run() { progressBar.setSelection(i); Value =
		 * progressBar.getMaximum(); value = progressBar.getSelection(); if (Value ==
		 * value) { //shell.dispose();// 释放shell 对象 final Display dis =
		 * Display.getDefault(); Shell sh = new Shell(dis);
		 * sh.setText("ProgressBar 实例"); sh.setSize(500, 400); sh.open(); while
		 * (!sh.isDisposed()) { if (!dis.readAndDispatch()) dis.sleep(); } } } }); } }
		 * }.start();// 启动线程
		 * 
		 * shell.open(); shell.layout(); while (!shell.isDisposed()) { if
		 * (!display.readAndDispatch()) display.sleep(); }
		 */
		result = 1;
	}

	/*
	 * public ProgressBarExample() { }
	 */
	/*
	 * public static void main(String[] args) { new ProgressBarExample(); }
	 */
}
}