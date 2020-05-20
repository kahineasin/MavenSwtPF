package com.mavenSwt.mavenSwtPF.thread;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.eclipse.swt.widgets.Display;
import com.mavenSwt.mavenSwtPF.CustomDelegate;
import com.mavenSwt.mavenSwtPF.listener.CmdOutEvent;
import com.mavenSwt.mavenSwtPF.listener.CmdOutListener;
import com.mavenSwt.mavenSwtPF.listener.CmdStartEvent;
import com.mavenSwt.mavenSwtPF.listener.CmdStartListener;
import com.mavenSwt.mavenSwtPF.model.PFSpringModel;
import com.mavenSwt.mavenSwtPF.projHelper.ProjHelper;
/**
 * ShowProcessDialog implements the Runnable to read the check echo result in
 * other thread.
 */
public class SpringLoader  implements Runnable {
    /**
     * reads the socket to get the echo result
     */
    private Thread tRead;
    protected Object result;
    //private DataInputStream dataIn;
    private String echoStr;
    private CustomDelegate _outAction;
    private CustomDelegate _errAction;
    //private CustomDelegate _startAction;//为了远程重启时监听--benjamin20200109
    private CmdStartListener _cmdStartListener;//为了远程重启时监听--benjamin20200109
    private String _springPath;
    private PFSpringModel _springModel;
	private  Process _process;
	private InputStreamReader inputReader;
	private BufferedReader stdoutReader;
	//private BufferedReader stderrReader;
	private Runtime runtime;

	private  Boolean _isRunning;
	private CmdOutListener _cmdOutListener;

	/*
	 * public SpringLoader(String springPath,CustomDelegate outAction,CustomDelegate
	 * errAction) { _springPath=springPath; _outAction=outAction;
	 * _errAction=errAction; } public SpringLoader(String springPath,CmdOutListener
	 * cmdOutListener,CustomDelegate errAction) { _springPath=springPath;
	 * _cmdOutListener=cmdOutListener; _errAction=errAction; }
	 */
    public SpringLoader(PFSpringModel springModel,CmdOutListener cmdOutListener,CustomDelegate errAction,
    		//CustomDelegate startAction
    		CmdStartListener cmdStartListener
    		) {
    	_springModel=springModel;
        _springPath=springModel.getJavaCmd();
        _cmdOutListener=cmdOutListener;
        _errAction=errAction;
        //_startAction=startAction;
        _cmdStartListener=cmdStartListener;
    }
    /**
     * Open the dialog
     * 
     * @return the result
     */
    public Object open() {
        //start the thread to read the Agent echo result
        tRead = new Thread(this);
        _isRunning=true;
        tRead.start();
        
    	//_startAction.setValue(_springPath+"  已经启动");
        return result;
    }
    /**
     * closes the dialog, because read Thread do this, it's
     * other thread, so should close dialog through dialog
     * thread using syncExec.
     */
    public void close() {
        Display.getDefault().syncExec(new Runnable() {
            public void run() {
                //cutEureka();
            	_process.destroy();
            	Dispose();
                _isRunning=false;
                //System.out.println("close success");
            }
        });
    }
    public void asyncClose() {
        new Thread() {//线程操作
            public void run() {
                //cutEureka();
            	_process.destroy();
            	Dispose();
                _isRunning=false;
                //System.out.println("close success");
            }
     }.start();
    }
    public void Dispose() {

    	if(inputReader!=null) {
    		try {
    			inputReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	if(stdoutReader!=null) {
    		try {
				stdoutReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	if(runtime!=null) {
    		runtime.gc();
    		runtime.freeMemory();
    	}
    	
    }
    public void run() {
		/*
		 * String javaStr
		 * ="java -jar D:\\\\eclipse_workspace\\springBootDemo\\demo-webapp\\target\\demo.jar"
		 * ; String javaStr2 =
		 * " java -jar D://eclipse_workspace/springBootDemo/demo-webapp/target/demo.jar"
		 * ;//shell关闭后才生效 String javaStr3 =
		 * "cmd /c java -jar D://eclipse_workspace/springBootDemo/demo-webapp/target/demo.jar"
		 * ;
		 */

		runtime = Runtime.getRuntime();
		try {
			  _cmdStartListener.handleEvent(new CmdStartEvent(this,_springPath+"  已经启动"));
			_process =  runtime.exec(_springPath);
		 inputReader=new InputStreamReader(_process.getInputStream());
          stdoutReader = new BufferedReader(inputReader);
          String line;
          //System.out.println("OUTPUT");
          while ((line = stdoutReader.readLine()) != null) {
        	  if(_cmdOutListener!=null) {
        		  _cmdOutListener.handleEvent(new CmdOutEvent(this,line));
        	  }
        	  if(_outAction!=null) {_outAction.setValue(line);}
        	  
				/*
				 * if(line.isEmpty()) { System.out.println("1111"); }
				 */
        	  
              //System.out.println(line);
          }
          //cutEurekaAsync();
          //stdoutReader.close();
          Dispose();
      	  _errAction.setValue(_springPath+"  已经停止");
          //System.out.println("ERROR");
      	  
			/*
			 * BufferedReader stderrReader = new BufferedReader(new
			 * InputStreamReader(_process.getErrorStream())); while ((line =
			 * stderrReader.readLine()) != null) { _errAction.setValue(line);
			 * //System.out.println(line); } stderrReader.close();
			 */
      	  
			/*
			 * int exitVal = _process.waitFor(); runtime.freeMemory();
			 */
		} catch (Throwable e1) {
			  //cutEurekaAsync();
		  	  _errAction.setValue(_springPath+"  "+e1.getMessage());
	          //runtime.freeMemory();
	          Dispose();
	          _process.destroy();
		}
        _isRunning=false;
		//System.out.close();
        //_doAction.setValue("");
        //close();
    }
    public Boolean IsRunning() {
    	return _isRunning;
    }
    /**
     * 注册事件监听器
     * 
     * @param listener
     */
    public void addCmdOutListener(CmdOutListener listener) {
        _cmdOutListener=listener;
    }
	/*
	 * public void run() { try { System.out.println("wait for reading..."); echoStr
	 * = dataIn.readUTF(); } catch (IOException e) {
	 * System.out.println("read from Agent exception."); } close(); }
	 */
    
    public String getEcho() {
        return echoStr;
    }
    
    public void cutEureka() {
    	if(_springModel.isEurekaClient()) {
		 	try {
		 		//现在已经是安全升级了,但如果想再保险一些,可以考虑先post到EurekaClient端的/pause方法,这样可以在保持Client运行的情况下暂停心跳.
		 		ProjHelper.EurekaServiceOut(_springModel.getServiceName(),_springModel.getInstanceName());
		 		ProjHelper.EurekaServiceDelete(_springModel.getServiceName(),_springModel.getInstanceName());
			} catch (Exception e1) {

			}
    	}
    }
    public void cutEurekaAsync() {
    	new Thread(new Runnable() {
            public void run() {
            	cutEureka();
            }
        }).run();
    }
	public PFSpringModel getSpringModel() {
		return _springModel;
	}
}
