package com.feidian.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "commodity")
public class CommodityPO {

  @TableId
  private Long id;
  private Long userId;

  private String commodityName;
  private String commodityType;
  private BigDecimal price;
  private String commodityDescription;
  private String commodityAddress;
  private String coverUrl;

  private Long commodityStatus;

  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;
  private Long isDeleted;



}
