package com.yoolean.common.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"latitude", "longitude"})
public class Location {
  double latitude;
  double longitude;

  public Location() {}

  public Location(double latitude, double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }
  public Location add(double delta)
  {
    return new Location(this.latitude+delta,this.longitude+delta);
  }
  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Location{");
    sb.append("latitude=").append(latitude);
    sb.append(", longitude=").append(longitude);
    sb.append('}');
    return sb.toString();
  }
}
