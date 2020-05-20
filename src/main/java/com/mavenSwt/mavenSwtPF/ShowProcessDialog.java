package com.mavenSwt.mavenSwtPF;

/*import java.io.DataInputStream;
import java.io.IOException;*/
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
/**
 * ShowProcessDialog implements the Runnable to read the check echo result in
 * other thread.
 */
public class ShowProcessDialog extends Dialog implements Runnable {
    /**
     * reads the socket to get the echo result
     */
    private Thread tRead;
    protected Object result;
    protected Shell shell;
    protected Display display = getParent().getDisplay();
    //private DataInputStream dataIn;
    private String echoStr;
    private CustomDelegate _doAction;
    
    /**
     * Create the dialog
     * 
     * @param parent
     * @param style
     */
    public ShowProcessDialog(Shell parent, int style) {
        super(parent, style);
    }
    /**
     * Create the dialog
     * 
     * @param parent
     */
	/*
	 * public ShowProcessDialog(Shell parent, DataInputStream in) { this(parent,
	 * SWT.NONE); dataIn = in; }
	 */
    public ShowProcessDialog(Shell parent, CustomDelegate doAction) {
        this(parent, SWT.NONE);
        _doAction = doAction;
    }
    /**
     * Open the dialog
     * 
     * @return the result
     */
    public Object open() {
        createContents();
        //start the thread to read the Agent echo result
        tRead = new Thread(this);
        tRead.start();
        shell.open();
        shell.layout();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        return result;
    }
    /**
     * Create contents of the dialog
     */
    protected void createContents() {
        shell = new Shell(getParent(), SWT.TITLE | SWT.BORDER | SWT.APPLICATION_MODAL);
        shell.setSize(345, 181);
        shell.setText("运行提示");
        final Label label = new Label(shell, SWT.NONE);
        label.setAlignment(SWT.CENTER);
        label.setText("正在运行，请稍候...");
        label.setBounds(55, 70, 204, 44);
    }
    /**
     * closes the dialog, because read Thread do this, it's
     * other thread, so should close dialog through dialog
     * thread using syncExec.
     */
    public void close() {
        Display.getDefault().syncExec(new Runnable() {
            public void run() {
                shell.close();
                shell.dispose();
            }
        });
    }

    public void run() {

        _doAction.setValue("");
        close();
    }
	/*
	 * public void run() { try { System.out.println("wait for reading..."); echoStr
	 * = dataIn.readUTF(); } catch (IOException e) {
	 * System.out.println("read from Agent exception."); } close(); }
	 */
    
    public String getEcho() {
        return echoStr;
    }
}
