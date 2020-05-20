package com.mavenSwt.mavenSwtPF.listener;

import java.util.EventObject;

/**
 * 
 * @author Thief
 *
 */
public class CmdOutEvent extends EventObject {

    private static final long serialVersionUID = 1L;
    private int sourceState;
    private String _outMsg;
    public CmdOutEvent(Object source,String outMsg) {
        super(source);
        _outMsg=outMsg;
        //sourceState = ((SpringLoader)source).getFlag();
    }
    
    public int getSourceState() {
        return sourceState;
    }
    public String getOutMsg() {
        return _outMsg;
    }

}