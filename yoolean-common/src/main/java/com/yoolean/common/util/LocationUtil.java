package com.yoolean.common.util;

import com.yoolean.common.model.Location;

/**
 * Created by chenhang on 2015/3/31.
 */
public class LocationUtil {
  private final static double AVERAGE_RADIUS_OF_EARTH = 6371000;

  /**
   * Calculate the distance of two Location on the earth base on the latitude and longitude.
   * 
   * @param start
   * @param end
   * @return The distance in meters.
   */
  public static int distance(Location start, Location end) {
    double latDistance = Math.toRadians(start.getLatitude() - end.getLatitude());
    double lngDistance = Math.toRadians(start.getLongitude() - end.getLongitude());
    double a =
        Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
            + Math.cos(Math.toRadians(start.getLatitude()))
            * Math.cos(Math.toRadians(end.getLatitude())) * Math.sin(lngDistance / 2)
            * Math.sin(lngDistance / 2);
    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    return (int) (Math.round(AVERAGE_RADIUS_OF_EARTH * c));
  }

  public static double delta(long radius)
  {
    return ((radius/1000)*0.01*2);
  }
}
