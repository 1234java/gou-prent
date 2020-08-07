package com.anyu.tiangou.user.mdoel;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/****
 * @Author:admin
 * @Description:UndoLog构建
 * @Date 2019/6/14 19:13
 *****/
@Table(name="undo_log")
@Data
@ToString
public class UndoLog implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;//

    @Column(name = "branch_id")
	private Long branchId;//

    @Column(name = "xid")
	private String xid;//

    @Column(name = "rollback_info")
	private String rollbackInfo;//

    @Column(name = "log_status")
	private Integer logStatus;//

    @Column(name = "log_created")
	private Date logCreated;//

    @Column(name = "log_modified")
	private Date logModified;//

    @Column(name = "ext")
	private String ext;//


    public UndoLog(Long branchId, String xid, String rollbackInfo, Integer logStatus, Date logCreated, Date logModified, String ext) {
        this.branchId = branchId;
        this.xid = xid;
        this.rollbackInfo = rollbackInfo;
        this.logStatus = logStatus;
        this.logCreated = logCreated;
        this.logModified = logModified;
        this.ext = ext;
    }

    public UndoLog() {
    }
}
