package com.mavenSwt.mavenSwtPF.pfTest;

import pf.java.pfHelper.PFRequestResult;
import pf.java.pfHelper.PFYmd;
import pf.java.pfHelper.config.PFDataHelper;

public class testEnum {

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) throws Exception{
        try {
        	int a=PFYmd.Hour|PFYmd.Minute;
        	Boolean b=PFDataHelper.EnumHasFlag(a, PFYmd.Minute);
        	Boolean c=b;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
