package com.yoolean.common.util;

import com.yoolean.common.model.Location;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by chenhang on 2015/4/2.
 */
public class LocationUtilTest {

    @Test
    public void testDistance() throws Exception {
        Location a=new Location(23.00,38.00);
        Location b=new Location(23.01,38.00);
        double dis=LocationUtil.distance(a,b);
        System.out.println(dis);
    }

    @Test
    public void testDelta() throws Exception {
        double delta=LocationUtil.delta(5000);
        System.out.println(delta);
        Location a=new Location(23.00,38.00);
        Location b=a.add(delta);
        double dis=LocationUtil.distance(a,b);
        System.out.println(dis);
    }
}