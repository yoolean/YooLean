package com.yoolean.platform.service;

import com.yoolean.common.model.HotArea;
import com.yoolean.common.model.HotSpot;
import com.yoolean.common.model.Location;

import java.util.List;

/**
 * Created by chenhang on 2015/3/28.
 */
public interface HotSpotService {
    String createHotSpot(HotSpot hotSpot);

    HotSpot findHotSpotById(String id);

    HotArea findHotArea(Location center, long radius);
}
