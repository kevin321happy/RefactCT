package com.wh.jxd.com.refactorqm.model;

/**
 * Created by kevin321vip on 2017/10/20.
 */
public class EnterpriseInfo extends BaseModel {
    @Override
    public String toString() {
        return "EnterpriseInfo{" +
                "enid='" + enid + '\'' +
                ", deptid='" + deptid + '\'' +
                ", ename='" + ename + '\'' +
                ", elogo='" + elogo + '\'' +
                ", enotice='" + enotice + '\'' +
                ", id='" + id + '\'' +
                ", live_type='" + live_type + '\'' +
                '}';
    }
    //企业id
    private String enid;
    //部门id
    private String deptid;
    //企业名称
    private String ename;
    //企业logo
    private String elogo;
    //企业公告
    private String enotice;
    private String id;
    private String live_type;//直播的类型

    public String getLive_type() {
        return live_type;
    }
    public void setLive_type(String live_type) {
        this.live_type = live_type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }

    public String getEnid()
    {
        return enid;
    }

    public void setEnid(String enid)
    {
        this.enid = enid;
    }

    public String getEname()
    {
        return ename;
    }

    public void setEname(String ename)
    {
        this.ename = ename;
    }

    public String getEnotice()
    {
        return enotice;
    }

    public void setEnotice(String enotice)
    {
        this.enotice = enotice;
    }

    public String getElogo()
    {
        return elogo;
    }

    public void setElogo(String elogo)
    {
        this.elogo = elogo;
    }
}
