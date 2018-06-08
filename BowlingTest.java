package com.bowling;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * Test for Bowling Class
 */
public class BowlingTest {

    @Test
    public void allStrikesTest(){

        String totalFrames = "XXXXXXXXXXXX";
       int totalPoints =  new com.bowling.Bowling(totalFrames).run();
        Assert.assertEquals(300, totalPoints);
    }

    @Test
    public void allSparesTest(){
        String totalFrames = "5/5/5/5/5/5/5/5/5/5/5";
        int totalPoints = new com.bowling.Bowling(totalFrames).run();
        Assert.assertEquals(150, totalPoints);

    }
    @Test
    public void allNinesTest(){
        String totalFrames =   "9-9-9-9-9-9-9-9-9-9-";
        int totalPoints = new com.bowling.Bowling(totalFrames).run();
        Assert.assertEquals(90, totalPoints);
    }
    @Test
    public void misFrameTest(){

        String totalFrames = "X7/9-X-88/-6XXX81";
        int totalPoints = new com.bowling.Bowling(totalFrames).run();
        Assert.assertEquals(167, totalPoints);
    }
}
