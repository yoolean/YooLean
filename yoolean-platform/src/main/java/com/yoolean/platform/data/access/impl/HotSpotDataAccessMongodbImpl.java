package com.yoolean.platform.data.access.impl;

import com.mongodb.*;
import com.yoolean.common.model.HotArea;
import com.yoolean.common.model.HotSpot;
import com.yoolean.common.model.Location;
import com.yoolean.platform.data.access.HotSpotDataAccess;
import com.yoolean.platform.data.access.DataAccessSupport;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenhang on 2015/3/28.
 */
@Component
public class HotSpotDataAccessMongodbImpl extends DataAccessSupport implements HotSpotDataAccess {
    private static final Logger LOG = LoggerFactory.getLogger(HotSpotDataAccessMongodbImpl.class);

    @Override
    public String insertHotSpot(HotSpot hotSpot) {
        LOG.debug("Insert hot spot " + hotSpot);
        String id = "";
        try {
            BasicDBObject spot = toDBObject(hotSpot);
            getCollection().insert(spot);
            id = spot.getString("_id");
        } catch (Exception e) {
            LOG.error("Error while inserting hot sport", e);
        }
        return id;
    }

    @Override
    public HotSpot findHotSpotById(String id) {
        HotSpot hotSpot = null;
        try {
            BasicDBObject condition = new BasicDBObject("_id", new ObjectId(id));
            DBObject dbObject = getCollection().findOne(condition);
            if (dbObject != null) {
                hotSpot = toHotSpot(dbObject);
            }
        } catch (Exception e) {
            LOG.error("Error while finding hot spot by ID " + id, e);
        }
        return hotSpot;
    }

    @Override
    public List<HotSpot> findHotSpotsByOwner(String owner) {
        List<HotSpot> hotSpotList = new ArrayList<HotSpot>();
        try {
            BasicDBObject condition = new BasicDBObject("owner", owner);
            hotSpotList = queryForHotSpotList(condition);
        } catch (Exception e) {
            LOG.error("Error while finding hot spot list by owner " + owner, e);
        }
        return hotSpotList;
    }

    @Override
    public void removeHotSpotById(String id) {
        try {
            getCollection().remove(new BasicDBObject("_id", id));
        } catch (Exception e) {
            LOG.error("Error while remove hot spot " + id, e);
        }
    }

    @Override
    public void removeHotSpotsByOwner(String owner) {
        try {
            getCollection().remove(new BasicDBObject("owner", owner));
        } catch (Exception e) {
            LOG.error("Error while remove hot spot by owner" + owner, e);
        }
    }

    @Override
    public List<HotSpot> findHotSpotListByRange(Location bottom, Location top) {
        LOG.debug("Find hot spot list from " + bottom + " to " + top);
        List<HotSpot> hotSpotList = null;
        try {
            BasicDBObject condition = getRangeCondition(bottom, top);
            hotSpotList = queryForHotSpotList(condition);
        } catch (Exception e) {
            LOG.error("Error while finding hot spot list from " + bottom + " to " + top, e);
        }
        return hotSpotList;
    }

    private BasicDBObject getRangeCondition(Location bottom, Location top) {
        BasicDBObject condition = new BasicDBObject();
        condition.append("location.latitude", new BasicDBObject("$gt", bottom.getLatitude()));
        condition.append("location.longitude", new BasicDBObject("$gt", bottom.getLongitude()));
        condition.append("location.latitude", new BasicDBObject("$lt", top.getLatitude()));
        condition.append("location.longitude", new BasicDBObject("$lt", top.getLongitude()));
        return condition;
    }

}
