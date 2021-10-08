package com.sgstech.studentmathtest.Database.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Student implements Serializable {



    @PrimaryKey(autoGenerate = true)
    public int id;



    @ColumnInfo(name = "service_region")
    public String service_region;

    @ColumnInfo(name = "service_site_id")
    public String service_site_id;

    @ColumnInfo(name = "service_site_name")
    public String service_site_name;

    @ColumnInfo(name = "service_team_name")
    public String service_team_name;

    @ColumnInfo(name = "service_unit_no")
    public String service_unit_no;

    @ColumnInfo(name = "service_capacity")
    public String service_capacity;

    @ColumnInfo(name = "service_brand")
    public String service_brand;

    @ColumnInfo(name = "service_model")
    public String service_model;

    @ColumnInfo(name = "service_od_sn")
    public String service_od_sn;

    @ColumnInfo(name = "service_id_sn")
    public String service_id_sn;

    @ColumnInfo(name = "service_summary_remark")
    public String service_summary_remark;

    @ColumnInfo(name = "service_summary_material_usage")
    public String service_summary_material_usage;

    /**
     * Service Gallery details----------------------------------------------------------------------
     */

    /**
     * Checklist image
     */
    @ColumnInfo(name = "service_checkList_img")
    public String service_checkList_img;




    @ColumnInfo(name = "finished")
    public boolean finished;


    /*
    * Getters and Setters
    * */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }




    /**
     * Unit No
     */
    public String getServiceRegion() {
        return service_region;
    }

    public void setServiceRegion(String service_region) {
        this.service_region = service_region;
    }

    public String getServiceSiteId() {
        return service_site_id;
    }

    public void setServiceSiteId(String service_site_id) {
        this.service_site_id = service_site_id;
    }

    public String getServiceSiteName() {
        return service_site_name;
    }

    public void setServiceSiteName(String service_site_name) {
        this.service_site_name = service_site_name;
    }

    public String getServiceTeamName(){ return service_team_name;}

    public void setServiceTeamName(String service_team_name) {
        this.service_team_name = service_team_name;
    }

    public String getUnitNo() {
        return service_unit_no;
    }

    public void setUnitNo(String service_unit_no) {
        this.service_unit_no = service_unit_no;
    }

    public String getODSN() {
        return service_od_sn;
    }

    public void setODSN(String service_od_sn) {
        this.service_od_sn = service_od_sn;
    }

    public String getIDSN() {
        return service_id_sn;
    }

    public void setIDSN(String service_id_sn) {
        this.service_id_sn = service_id_sn;
    }

    public String getSummaryRemark() {
        return service_summary_remark;
    }

    public void setSummaryRemark(String service_summary_remark) {
        this.service_summary_remark = service_summary_remark;
    }

    public String getSummaryMaterialUsage() {
        return service_summary_material_usage;
    }

    public void setSummaryMaterialUsage(String service_summary_material_usage) {
        this.service_summary_material_usage = service_summary_material_usage;
    }





    /**
     * profile image
     */
        public String getServiceChecklistImg() {
        return service_checkList_img;
    }

        public void setServiceChecklistImg(String service_checkList_img) {
            this.service_checkList_img = service_checkList_img;
    }



    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
