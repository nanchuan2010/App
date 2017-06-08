package com.example.lzw.myapp.Provide.services;

import java.util.Date;

/**
 * Created by Administrator on 2017/6/8.
 */

public class BaseEntity {
    private int id;
    private String ownedAccount=null;
    private String createdBy;
    private Date createOn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwnedAccount() {
        return ownedAccount;
    }

    public void setOwnedAccount(String ownedAccount) {
        this.ownedAccount = ownedAccount;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public void setLastUpdatedOn(Date lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

    private String lastUpdatedBy;
    private Date lastUpdatedOn;

    public BaseEntity()
    {

    }

    public BaseEntity(String ownedAccount,String createdBy,Date createOn,String lastUpdatedBy,Date lastUpdatedOn,int id)
    {
        super();
        this.ownedAccount=ownedAccount;
        this.createdBy=createdBy;
        this.createOn=createOn;
        this.lastUpdatedBy=lastUpdatedBy;
        this.lastUpdatedOn=lastUpdatedOn;
        this.id=id;
    }

}
