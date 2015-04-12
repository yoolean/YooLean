package com.yoolean.platform.data.access;

import com.mongodb.*;
import com.yoolean.common.model.HotSpot;
import com.yoolean.common.model.Location;
import com.yoolean.platform.data.access.impl.HotSpotDataAccessMongodbImpl;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenhang on 2015/4/7.
 */
public class DataAccessSupport {
    private static final String DB_NAME = "yoolean";
    private static final String COLLECTION_NAME = "hotspot";
    @Value("${mongodb.host}")
    private String host;
    @Value("${mongodb.port}")
    private int port;
    private MongoClient mongoClient;

    protected HotSpot toHotSpot(DBObject hotSpotObject) {
        HotSpot hotSpot = new HotSpot();
        ObjectId id = (ObjectId) hotSpotObject.get("_id");
        hotSpot.setId(id.toString());
        hotSpot.setOwner((String) hotSpotObject.get("owner"));
        hotSpot.setStatus((String) hotSpotObject.get("status"));
        hotSpot.setTitle((String) hotSpotObject.get("title"));
        hotSpot.setType((String) hotSpotObject.get("type"));
        hotSpot.setDescription((String) hotSpotObject.get("description"));
        DBObject locationObject = (DBObject) hotSpotObject.get("location");
        Location location = new Location();
        location.setLatitude((Double) locationObject.get("latitude"));
        location.setLongitude((Double) locationObject.get("longitude"));
        hotSpot.setLocation(location);
        return hotSpot;
    }

    protected BasicDBObject toDBObject(HotSpot hotSpot) {
        BasicDBObject spot = new BasicDBObject();
        spot.append("owner", hotSpot.getOwner());
        spot.append("status", hotSpot.getStatus());
        spot.append("title", hotSpot.getTitle());
        spot.append("type", hotSpot.getType());
        spot.append("description", hotSpot.getDescription());
        BasicDBObject location = new BasicDBObject();
        location.append("latitude", hotSpot.getLocation().getLatitude());
        location.append("longitude", hotSpot.getLocation().getLongitude());
        spot.append("location", location);
        return spot;
    }

    protected synchronized DBCollection getCollection() throws UnknownHostException {
        if (mongoClient == null) {
            mongoClient = new MongoClient(host, port);
        }
        return mongoClient.getDB(DB_NAME).getCollection(COLLECTION_NAME);
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    protected List<HotSpot> queryForHotSpotList(BasicDBObject condition) throws UnknownHostException {
        List<HotSpot> hotSpotList = new ArrayList<HotSpot>();
        DBCursor cursor = getCollection().find(condition);
        while (cursor.hasNext()) {
            DBObject hotSpotObject = cursor.next();
            HotSpot hotSpot = toHotSpot(hotSpotObject);
            hotSpotList.add(hotSpot);
        }
        return hotSpotList;
    }
}
