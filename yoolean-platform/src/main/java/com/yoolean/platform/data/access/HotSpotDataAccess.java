package com.yoolean.platform.data.access;

import com.yoolean.common.model.HotSpot;
import com.yoolean.common.model.Location;

import java.util.List;

/**
 * Created by chenhang on 2015/3/28.
 */
public interface HotSpotDataAccess {
    String insertHotSpot(HotSpot hotSpot);

    HotSpot findHotSpotById(String id);

    List<HotSpot> findHotSpotsByOwner(String owner);

    void removeHotSpotById(String id);

    void removeHotSpotsByOwner(String ower);

    List<HotSpot> findHotSpotListByRange(Location bottom,Location top);
}
