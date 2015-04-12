package com.yoolean.platform.web.rest;

import com.yoolean.common.model.HotArea;
import com.yoolean.common.model.HotSpot;
import com.yoolean.common.model.Location;
import com.yoolean.platform.service.HotSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.util.List;

/**
 * Created by chenhang on 2015/3/28.
 */
@Path("hotspot")
@Component
public class HotSpotResources {
    @Autowired
    private HotSpotService hotSpotService;


    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.TEXT_PLAIN)
    public String createHotSpot(HotSpot hotSpot) {
        return hotSpotService.createHotSpot(hotSpot);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public HotSpot getHotSpotById(@QueryParam("id") String id) {
        return hotSpotService.findHotSpotById(id);
    }

    @Path("area")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public HotArea findHotArea(@QueryParam("latitude") double latitude, @QueryParam("longitude") double longitude, @QueryParam("radius") long radius) {
        Location center = new Location(latitude, longitude);
        return hotSpotService.findHotArea(center, radius);
    }

    public void setHotSpotService(HotSpotService hotSpotService) {
        this.hotSpotService = hotSpotService;
    }
}
