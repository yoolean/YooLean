package com.yoolean.common.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenhang on 2015/3/29.
 */
@XmlRootElement(name = "hotArea")
public class HotArea {
  private List<HotSpot> hotSpots = new ArrayList<HotSpot>();

  public List<HotSpot> getHotSpots() {
    return hotSpots;
  }

  public void setHotSpots(List<HotSpot> hotSpots) {
    this.hotSpots = hotSpots;
  }
}
