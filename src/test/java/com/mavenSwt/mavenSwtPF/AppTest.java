package com.mavenSwt.mavenSwtPF;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import pf.java.pfHelper.PFYmd;
import pf.java.pfHelper.config.PFDataHelper;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    /**
     * Rigourous Test :-)
     */
    public void testPFEnum()
    {
    	int a=PFYmd.Hour|PFYmd.Minute;
    	Boolean b=PFDataHelper.EnumHasFlag(a, PFYmd.Minute);
        assertTrue( b );
    	Boolean c=PFDataHelper.EnumHasFlag(a, PFYmd.Second);
        assertFalse( c );
    }
}
