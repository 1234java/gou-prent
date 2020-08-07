package com.anyu.tiangou.goods.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
@author shkstart
@create 2020-07-17 16:45
*/
@Data
@ToString
@ApiModel(description = "UndoLog",value = "UndoLog")
@Table(name="undo_log")
public class UndoLog implements Serializable {

    private Integer id;

    private Long branchId;

    private String xid;

    private byte[] rollbackInfo;

    private Integer logStatus;

    private Date logCreated;

    private Date logModified;

    private String ext;
}