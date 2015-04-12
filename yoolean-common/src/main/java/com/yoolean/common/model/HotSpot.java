package com.yoolean.common.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by chenhang on 2015/3/28.
 */
@XmlRootElement(name = "hotSpot")
@XmlType(propOrder = {"id", "owner", "status", "title", "type", "description", "location"})
public class HotSpot {
  private Location location;
  private String id;
  private String owner;
  private String status;
  private String type;
  private String title;
  private String description;

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("HotSpot{");
    sb.append("location=").append(location);
    sb.append(", id='").append(id).append('\'');
    sb.append(", owner='").append(owner).append('\'');
    sb.append(", status='").append(status).append('\'');
    sb.append(", title='").append(title).append('\'');
    sb.append(", type='").append(type).append('\'');
    sb.append(", description='").append(description).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
