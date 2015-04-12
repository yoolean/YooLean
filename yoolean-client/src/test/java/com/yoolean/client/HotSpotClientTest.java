package com.yoolean.client;

import com.yoolean.common.model.HotArea;
import com.yoolean.common.model.HotSpot;
import com.yoolean.common.model.Location;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by chenhang on 2015/4/11.
 */
@Ignore
public class HotSpotClientTest {
    private HotSpotClient client;

    @Before
    public void setUp() throws Exception {
        client=new HotSpotClient("http://121.43.148.11:8080");
    }

    @Test
    public void testCreateHotSpot() throws Exception {
        client.createHotSpot(getHotSpot());
    }


    @Test
    public void testGetHotArea() throws Exception {
        Location center=new Location(123.001, 23.23);
        long radius=5000;
        HotArea hotArea=client.getHotArea(center,radius);
        assertTrue(hotArea.getHotSpots().size()>0);

    }

    private HotSpot getHotSpot() {
        HotSpot hotSpot = new HotSpot();
        hotSpot.setId("1234456");
        hotSpot.setDescription("This is a test description");
        hotSpot.setOwner("18508283602");
        hotSpot.setLocation(new Location(123.001, 23.23));
        hotSpot.setTitle("Test");
        hotSpot.setStatus("new");
        return hotSpot;
    }
}