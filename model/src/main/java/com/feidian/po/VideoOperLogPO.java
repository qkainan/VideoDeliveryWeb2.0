package com.feidian.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "video_oper_log")
public class VideoOperLogPO {

  @TableId
  private Long id;
  private String businessType;
  private String method;
  private String requestMethod;
  private String operUsername;
  private String operUrl;
  private String operIp;
  private String operParam;
  private String jsonResult;
  private Long status;
  private String errorMsg;

  private java.sql.Timestamp operTime;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;
  private Long isDeleted;

}
