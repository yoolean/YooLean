package com.yoolean.platform.data.access.impl;

import com.yoolean.common.model.HotSpot;
import com.yoolean.common.model.Location;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by chenhang on 2015/3/30.
 */
@Ignore
public class HotSpotDataAccessMongodbImplTest {
  private HotSpotDataAccessMongodbImpl dao;

  @Before
  public void setUp() throws Exception {
    dao = new HotSpotDataAccessMongodbImpl();
    dao.setHost("127.0.0.1");
    dao.setPort(27017);
  }

  @Test
  public void testInsertHotSpot() throws Exception {
    HotSpot hotSpot = getHotSpot();
    dao.insertHotSpot(hotSpot);
  }

  @Test
  public void testFindHotSpotByOwner() throws Exception {
    String owner = "18508283602";
    List<HotSpot> hotSpotList = dao.findHotSpotsByOwner(owner);
    for (HotSpot hotSpot : hotSpotList) {
      assertEquals(owner, hotSpot.getOwner());
    }
  }

  @Test
  @Ignore
  public void testRemoveHotSpotByOwner() throws Exception {
    String owner = "18508283602";
    dao.removeHotSpotsByOwner(owner);
    List<HotSpot> hotSpotList=dao.findHotSpotsByOwner(owner);
    assertEquals(0,hotSpotList.size());

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
