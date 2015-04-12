package com.yoolean.platform.service.impl;

import com.yoolean.common.model.HotArea;
import com.yoolean.common.model.HotSpot;
import com.yoolean.common.model.Location;
import com.yoolean.common.util.LocationUtil;
import com.yoolean.platform.data.access.HotSpotDataAccess;
import com.yoolean.platform.service.HotSpotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by chenhang on 2015/3/28.
 */
@Component
public class HotSpotServiceImpl implements HotSpotService {
    private static final Logger LOG = LoggerFactory.getLogger(HotSpotServiceImpl.class);
    @Autowired
    private HotSpotDataAccess dataAccess;

    public String createHotSpot(HotSpot hotSpot) {
        LOG.info("Create hot spot ...");
        LOG.debug(hotSpot.toString());
        String id = dataAccess.insertHotSpot(hotSpot);
        LOG.debug("Hot spot created:" + id);
        return id;
    }

    @Override
    public HotSpot findHotSpotById(String id) {
        LOG.info("Find hot spot ...");
        LOG.debug("id:" + id);
        return dataAccess.findHotSpotById(id);
    }

    @Override
    public HotArea findHotArea(Location center, long radius) {
        LOG.debug("Finding hot area ...");
        HotArea hotArea = new HotArea();
        try {
            double delta = LocationUtil.delta(radius);
            LOG.debug("delta:" + delta);
            Location bottom = center.add(-delta);
            Location top = center.add(delta);
            hotArea.setHotSpots(dataAccess.findHotSpotListByRange(bottom, top));
        } catch (Exception e) {
            LOG.error("Error while finding hot area for center " + center + " with radius " + radius, e);
        }
        return hotArea;
    }


    public List<HotSpot> findHotSpotsByOwner(String owner) {
        LOG.info("Find hot spot by owner ...");
        LOG.debug("owner:" + owner);
        List<HotSpot> hotSpotList = dataAccess.findHotSpotsByOwner(owner);
        LOG.debug("found hot spot:" + hotSpotList.size());
        return hotSpotList;
    }

    public void setDataAccess(HotSpotDataAccess dataAccess) {
        this.dataAccess = dataAccess;
    }
}