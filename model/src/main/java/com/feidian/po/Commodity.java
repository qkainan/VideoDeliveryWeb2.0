package com.feidian.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Commodity {

  private long id;
  private long userId;

  private String commodityName;
  private String commodityType;
  private double price;
  private String commodityDescription;
  private String commodityAddress;
  private String coverUrl;

  private long commodityStatus;

  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;
  private long isDeleted;



}
