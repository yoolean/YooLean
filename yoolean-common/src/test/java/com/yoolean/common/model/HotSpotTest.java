package com.yoolean.common.model;

import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;

/**
 * Created by chenhang on 2015/3/28.
 */
public class HotSpotTest {
    @Test
    public void testJaxb_Given_Object_Expect_XML() throws Exception {
        HotSpot hotSpot = getHotSpot();

        JAXBContext context = JAXBContext.newInstance(HotSpot.class);
         Marshaller marshaller= context.createMarshaller();

        File file=new File("./src/test/resources/hotspot_data.xml");
        file.createNewFile();
        marshaller.marshal(hotSpot,file);

    }


    private HotSpot getHotSpot() {
        HotSpot hotSpot=new HotSpot();
        hotSpot.setId("1234456");
        hotSpot.setDescription("This is a test description");
        hotSpot.setOwner("18508283602");
        hotSpot.setLocation(new Location(123.001,23.23));
        hotSpot.setTitle("Test");
        hotSpot.setStatus("new");
        return hotSpot;
    }
}