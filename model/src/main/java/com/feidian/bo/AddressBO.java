package com.feidian.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressBO {

  private long id;
  private long userId;
  private String addressName;

  private long addressStatus;

  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;
  private long isDeleted;

  public AddressBO(long id, long userId, String addressName) {
    this.id = id;
    this.userId = userId;
    this.addressName = addressName;
  }

}
