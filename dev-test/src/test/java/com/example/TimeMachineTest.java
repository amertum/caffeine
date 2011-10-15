package com.example;

import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test of {@link TimeMachine}.
 */
public class TimeMachineTest {
    
    /**
     * Test method for {@link com.example.TimeMachine#isPast(java.util.Date)}.
     */
    @Test
    public void testIsPast()
        throws Exception
    {
        final TimeMachine timeMachine = new TimeMachine();
        
        final Date past = DateUtils.parseDate("2009-05-15", DATE_PATTERNS);
        final Date present = DateUtils.parseDate("2010-12-25", DATE_PATTERNS);
        final Date futur = DateUtils.parseDate("2011-02-13", DATE_PATTERNS);
        
        Assert.assertTrue(timeMachine.isPast(past));
        Assert.assertFalse(timeMachine.isPast(present));
        Assert.assertFalse(timeMachine.isPast(futur));
    }
    

    private static final String[] DATE_PATTERNS = {
        DateFormatUtils.ISO_DATE_FORMAT.getPattern()
    };
    
}
