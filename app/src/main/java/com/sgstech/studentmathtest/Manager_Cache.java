package com.sgstech.studentmathtest;

import android.content.Context;
import android.content.SharedPreferences;

public class Manager_Cache {

    /**
     *
     * Developed by : Sujith Jeewantha
     * email        : sujith@suji-tech.com
     * Init Date    : 11/07/2021
     * Updated at   : 12/07/2021
     *
     */

    private static Manager_Cache managerCache;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    private Manager_Cache(Context context) {
        sharedPreferences = context.getSharedPreferences(Config_cacheData.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.apply();
    }

    public static Manager_Cache getPreferences(Context context) {
        if (managerCache == null) managerCache = new Manager_Cache(context);
        return managerCache;
    }

    /**
     * getters and setters
     */
    public void setUserEmail(String userEmail){
        editor.putString(Config_cacheData.USER_EMAIL, userEmail);
        editor.apply();
    }

    public String getUserEmail(){
        return sharedPreferences.getString(Config_cacheData.USER_EMAIL, "");
    }

    public void setUserPassword(String userPassword){
        editor.putString(Config_cacheData.USER_PASSWORD, userPassword);
        editor.apply();
    }

    public String getUserPassword(){
        return sharedPreferences.getString(Config_cacheData.USER_PASSWORD, "");
    }

    /**
     * Service data
     */

    public void setServiceRegionName(String serviceRegionName){
        editor.putString(Config_cacheData.SERVICE_REGION_NAME, serviceRegionName);
        editor.apply();
    }

    public String getServiceRegionName(){
        return sharedPreferences.getString(Config_cacheData.SERVICE_REGION_NAME, "Not found");
    }

    public void setServiceTeamName(String serviceTeamName){
        editor.putString(Config_cacheData.SERVICE_TEAM_NAME, serviceTeamName);
        editor.apply();
    }

    public String getServiceTeamName(){
        return sharedPreferences.getString(Config_cacheData.SERVICE_TEAM_NAME, "Not found");
    }

    public void setServiceTeamLeaderUserName(String serviceTeamLeaderUserName){
        editor.putString(Config_cacheData.SERVICE_TEAM_LEADER_USER_NAME, serviceTeamLeaderUserName);
        editor.apply();
    }

    public String getServiceTeamLeaderUserName(){
        return sharedPreferences.getString(Config_cacheData.SERVICE_TEAM_LEADER_USER_NAME, "Not found");
    }

    public void setServiceTeamLeaderName(String serviceTeamLeaderName){
        editor.putString(Config_cacheData.SERVICE_TEAM_LEADER_NAME, serviceTeamLeaderName);
        editor.apply();
    }

    public String getServiceTeamLeaderName(){
        return sharedPreferences.getString(Config_cacheData.SERVICE_TEAM_LEADER_NAME, "Not found");
    }

    public void setServiceTeamLeaderNIC(String serviceTeamLeaderNIC){
        editor.putString(Config_cacheData.SERVICE_TEAM_LEADER_NIC_NO, serviceTeamLeaderNIC);
        editor.apply();
    }

    public String getServiceTeamLeaderNIC(){
        return sharedPreferences.getString(Config_cacheData.SERVICE_TEAM_LEADER_NIC_NO, "Not found");
    }

    public void setServiceTeamLeaderTelephone(String serviceTeamLeaderTelephone){
        editor.putString(Config_cacheData.SERVICE_TEAM_LEADER_TELEPHONE, serviceTeamLeaderTelephone);
        editor.apply();
    }

    public String getServiceTeamLeaderTelephone(){
        return sharedPreferences.getString(Config_cacheData.SERVICE_TEAM_LEADER_TELEPHONE, "Not found");
    }

    public void setServiceTeamHelperUserName(String serviceTeamHelperUsername){
        editor.putString(Config_cacheData.SERVICE_TEAM_HELPER_USER_NAME, serviceTeamHelperUsername);
        editor.apply();
    }

    public String getServiceTeamHelperUserName(){
        return sharedPreferences.getString(Config_cacheData.SERVICE_TEAM_HELPER_USER_NAME, "Not found");
    }

    public void setServiceTeamHelperName(String serviceTeamHelperName){
        editor.putString(Config_cacheData.SERVICE_TEAM_HELPER_NAME, serviceTeamHelperName);
        editor.apply();
    }

    public String getServiceTeamHelperName(){
        return sharedPreferences.getString(Config_cacheData.SERVICE_TEAM_HELPER_NAME, "Not found");
    }

    public void setServiceTeamHelperNIC(String serviceTeamHelperNIC){
        editor.putString(Config_cacheData.SERVICE_TEAM_HELPER_NIC_NO, serviceTeamHelperNIC);
        editor.apply();
    }

    public String getServiceTeamHelperNIC(){
        return sharedPreferences.getString(Config_cacheData.SERVICE_TEAM_HELPER_NIC_NO, "Not found");
    }

    public void setServiceTeamHelperTelephone(String serviceTeamHelperTelephone){
        editor.putString(Config_cacheData.SERVICE_TEAM_HELPER_TELEPHONE, serviceTeamHelperTelephone);
        editor.apply();
    }

    public String getServiceTeamHelperTelephone(){
        return sharedPreferences.getString(Config_cacheData.SERVICE_TEAM_HELPER_TELEPHONE, "Not found");
    }

    public void setServiceSiteID(String serviceSiteID){
        editor.putString(Config_cacheData.SERVICE_SITE_ID, serviceSiteID);
        editor.apply();
    }

    public String getServiceSiteID(){
        return sharedPreferences.getString(Config_cacheData.SERVICE_SITE_ID, "");
    }

    public void setServiceSiteName(String serviceSiteName){
        editor.putString(Config_cacheData.SERVICE_SITE_NAME, serviceSiteName);
        editor.apply();
    }

    public String getServiceSiteName(){
        return sharedPreferences.getString(Config_cacheData.SERVICE_SITE_NAME, "");
    }

    public void setServiceSiteDate(String serviceSiteDate){
        editor.putString(Config_cacheData.SERVICE_SITE_DATE, serviceSiteDate);
        editor.apply();
    }

    public String getServiceSiteDate(){
        return sharedPreferences.getString(Config_cacheData.SERVICE_SITE_DATE, "");
    }

    public void setServiceSiteIN(String serviceSiteIN){
        editor.putString(Config_cacheData.SERVICE_SITE_IN, serviceSiteIN);
        editor.apply();
    }

    public String getServiceSiteIN(){
        return sharedPreferences.getString(Config_cacheData.SERVICE_SITE_IN, "");
    }

    public void setServiceSiteOUT(String serviceSiteOUT){
        editor.putString(Config_cacheData.SERVICE_SITE_OUT, serviceSiteOUT);
        editor.apply();
    }

    public String getServiceSiteOUT(){
        return sharedPreferences.getString(Config_cacheData.SERVICE_SITE_OUT, "");
    }

    public void setServiceSiteRemarks(String serviceRemarks){
        editor.putString(Config_cacheData.SERVICE_REMARKS, serviceRemarks);
        editor.apply();
    }

    public String getServiceSiteRemarks(){
        return sharedPreferences.getString(Config_cacheData.SERVICE_REMARKS, "");
    }

    public void setServiceSiteMaterialUsage(String serviceMaterialUsage){
        editor.putString(Config_cacheData.SERVICE_MATERIAL_USAGE, serviceMaterialUsage);
        editor.apply();
    }

    public String getServiceSiteMaterialUsage(){
        return sharedPreferences.getString(Config_cacheData.SERVICE_MATERIAL_USAGE, "");
    }

    /**
     * Service Team Info Status
     */
    public void setServiceTeamInfoStatus(String serviceTeamInfoStatus){
        editor.putString(Config_cacheData.SERVICE_TEAM_INFO_STATUS, serviceTeamInfoStatus);
        editor.apply();
    }

    public String getServiceTeamInfoStatus(){
        return sharedPreferences.getString(Config_cacheData.SERVICE_TEAM_INFO_STATUS, "not_done");
    }

    /**
     * Service Site Info Status
     */
    public void setServiceSiteInfoStatus(String serviceSiteInfoStatus){
        editor.putString(Config_cacheData.SERVICE_SITE_INFO_STATUS, serviceSiteInfoStatus);
        editor.apply();
    }

    public String getServiceSiteInfoStatus(){
        return sharedPreferences.getString(Config_cacheData.SERVICE_SITE_INFO_STATUS, "not_done");
    }

    /**
     * Service Summary Status
     */
    public void setServiceSummaryStatus(String serviceSummaryStatus){
        editor.putString(Config_cacheData.SERVICE_SUMMARY_STATUS, serviceSummaryStatus);
        editor.apply();
    }

    public String getServiceSummaryStatus(){
        return sharedPreferences.getString(Config_cacheData.SERVICE_SUMMARY_STATUS, "not_done");
    }

    /**
     * Service Gallery Status
     */
    public void setServiceGalleryStatus(String serviceGalleryStatus){
        editor.putString(Config_cacheData.SERVICE_GALLERY_STATUS, serviceGalleryStatus);
        editor.apply();
    }

    public String getServiceGalleryStatus(){
        return sharedPreferences.getString(Config_cacheData.SERVICE_GALLERY_STATUS, "not_done");
    }


    /**
     * Breakdown data-------------------------------------------------------------------------------
     */

    public void setBreakdownRegionName(String breakdownRegionName){
        editor.putString(Config_cacheData.BREAKDOWN_REGION_NAME, breakdownRegionName);
        editor.apply();
    }

    public String getBreakdownRegionName(){
        return sharedPreferences.getString(Config_cacheData.BREAKDOWN_REGION_NAME, "Not found");
    }

    public void setBreakdownTeamName(String breakdownTeamName){
        editor.putString(Config_cacheData.BREAKDOWN_TEAM_NAME, breakdownTeamName);
        editor.apply();
    }

    public String getBreakdownTeamName(){
        return sharedPreferences.getString(Config_cacheData.BREAKDOWN_TEAM_NAME, "Not found");
    }

    public void setBreakdownTeamLeaderUserName(String breakdownTeamLeaderUserName){
        editor.putString(Config_cacheData.BREAKDOWN_TEAM_LEADER_USER_NAME, breakdownTeamLeaderUserName);
        editor.apply();
    }

    public String getBreakdownTeamLeaderUserName(){
        return sharedPreferences.getString(Config_cacheData.BREAKDOWN_TEAM_LEADER_USER_NAME, "Not found");
    }

    public void setBreakdownTeamLeaderName(String breakdownTeamLeaderName){
        editor.putString(Config_cacheData.BREAKDOWN_TEAM_LEADER_NAME, breakdownTeamLeaderName);
        editor.apply();
    }

    public String getBreakdownTeamLeaderName(){
        return sharedPreferences.getString(Config_cacheData.BREAKDOWN_TEAM_LEADER_NAME, "Not found");
    }

    public void setBreakdownTeamLeaderNIC(String breakdownTeamLeaderNIC){
        editor.putString(Config_cacheData.BREAKDOWN_TEAM_LEADER_NIC_NO, breakdownTeamLeaderNIC);
        editor.apply();
    }

    public String getBreakdownTeamLeaderNIC(){
        return sharedPreferences.getString(Config_cacheData.BREAKDOWN_TEAM_LEADER_NIC_NO, "Not found");
    }

    public void setBreakdownTeamLeaderTelephone(String breakdownTeamLeaderTelephone){
        editor.putString(Config_cacheData.BREAKDOWN_TEAM_LEADER_TELEPHONE, breakdownTeamLeaderTelephone);
        editor.apply();
    }

    public String getBreakdownTeamLeaderTelephone(){
        return sharedPreferences.getString(Config_cacheData.BREAKDOWN_TEAM_LEADER_TELEPHONE, "Not found");
    }

    public void setBreakdownTeamHelperUserName(String breakdownTeamHelperUsername){
        editor.putString(Config_cacheData.BREAKDOWN_TEAM_HELPER_USER_NAME, breakdownTeamHelperUsername);
        editor.apply();
    }

    public String getBreakdownTeamHelperUserName(){
        return sharedPreferences.getString(Config_cacheData.BREAKDOWN_TEAM_HELPER_USER_NAME, "Not found");
    }

    public void setBreakdownTeamHelperName(String breakdownTeamHelperName){
        editor.putString(Config_cacheData.BREAKDOWN_TEAM_HELPER_NAME, breakdownTeamHelperName);
        editor.apply();
    }

    public String getBreakdownTeamHelperName(){
        return sharedPreferences.getString(Config_cacheData.BREAKDOWN_TEAM_HELPER_NAME, "Not found");
    }

    public void setBreakdownTeamHelperNIC(String breakdownTeamHelperNIC){
        editor.putString(Config_cacheData.BREAKDOWN_TEAM_HELPER_NIC_NO, breakdownTeamHelperNIC);
        editor.apply();
    }

    public String getBreakdownTeamHelperNIC(){
        return sharedPreferences.getString(Config_cacheData.BREAKDOWN_TEAM_HELPER_NIC_NO, "Not found");
    }

    public void setBreakdownTeamHelperTelephone(String breakdownTeamHelperTelephone){
        editor.putString(Config_cacheData.BREAKDOWN_TEAM_HELPER_TELEPHONE, breakdownTeamHelperTelephone);
        editor.apply();
    }

    public String getBreakdownTeamHelperTelephone(){
        return sharedPreferences.getString(Config_cacheData.BREAKDOWN_TEAM_HELPER_TELEPHONE, "Not found");
    }

    public void setBreakdownSiteID(String breakdownSiteID){
        editor.putString(Config_cacheData.BREAKDOWN_SITE_ID, breakdownSiteID);
        editor.apply();
    }

    public String getBreakdownSiteID(){
        return sharedPreferences.getString(Config_cacheData.BREAKDOWN_SITE_ID, "");
    }

    public void setBreakdownSiteName(String breakdownSiteName){
        editor.putString(Config_cacheData.BREAKDOWN_SITE_NAME, breakdownSiteName);
        editor.apply();
    }

    public String getBreakdownSiteName(){
        return sharedPreferences.getString(Config_cacheData.BREAKDOWN_SITE_NAME, "");
    }

    public void setBreakdownSiteDate(String breakdownSiteDate){
        editor.putString(Config_cacheData.BREAKDOWN_SITE_DATE, breakdownSiteDate);
        editor.apply();
    }

    public String getBreakdownSiteDate(){
        return sharedPreferences.getString(Config_cacheData.BREAKDOWN_SITE_DATE, "");
    }

    public void setBreakdownSiteIN(String breakdownSiteIN){
        editor.putString(Config_cacheData.BREAKDOWN_SITE_IN, breakdownSiteIN);
        editor.apply();
    }

    public String getBreakdownSiteIN(){
        return sharedPreferences.getString(Config_cacheData.BREAKDOWN_SITE_IN, "");
    }

    public void setBreakdownSiteOUT(String breakdownSiteOUT){
        editor.putString(Config_cacheData.BREAKDOWN_SITE_OUT, breakdownSiteOUT);
        editor.apply();
    }

    public String getBreakdownSiteOUT(){
        return sharedPreferences.getString(Config_cacheData.BREAKDOWN_SITE_OUT, "");
    }

    public void setBreakdownSiteRemarks(String breakdownRemarks){
        editor.putString(Config_cacheData.BREAKDOWN_REMARKS, breakdownRemarks);
        editor.apply();
    }

    public String getBreakdownSiteRemarks(){
        return sharedPreferences.getString(Config_cacheData.BREAKDOWN_REMARKS, "");
    }

    public void setBreakdownSiteMaterialUsage(String breakdownMaterialUsage){
        editor.putString(Config_cacheData.BREAKDOWN_MATERIAL_USAGE, breakdownMaterialUsage);
        editor.apply();
    }

    public String getBreakdownSiteMaterialUsage(){
        return sharedPreferences.getString(Config_cacheData.BREAKDOWN_MATERIAL_USAGE, "");
    }

    /**
     * Breakdown Team Info Status
     */
    public void setBreakdownTeamInfoStatus(String breakdownTeamInfoStatus){
        editor.putString(Config_cacheData.BREAKDOWN_TEAM_INFO_STATUS, breakdownTeamInfoStatus);
        editor.apply();
    }

    public String getBreakdownTeamInfoStatus(){
        return sharedPreferences.getString(Config_cacheData.BREAKDOWN_TEAM_INFO_STATUS, "not_done");
    }

    /**
     * Breakdown Site Info Status
     */
    public void setBreakdownSiteInfoStatus(String breakdownSiteInfoStatus){
        editor.putString(Config_cacheData.BREAKDOWN_SITE_INFO_STATUS, breakdownSiteInfoStatus);
        editor.apply();
    }

    public String getBreakdownSiteInfoStatus(){
        return sharedPreferences.getString(Config_cacheData.BREAKDOWN_SITE_INFO_STATUS, "not_done");
    }

    /**
     * Breakdown Summary Status
     */
    public void setBreakdownSummaryStatus(String breakdownSummaryStatus){
        editor.putString(Config_cacheData.BREAKDOWN_SUMMARY_STATUS, breakdownSummaryStatus);
        editor.apply();
    }

    public String getBreakdownSummaryStatus(){
        return sharedPreferences.getString(Config_cacheData.BREAKDOWN_SUMMARY_STATUS, "not_done");
    }

    /**
     * Breakdown Gallery Status
     */
    public void setBreakdownGalleryStatus(String breakdownGalleryStatus){
        editor.putString(Config_cacheData.BREAKDOWN_GALLERY_STATUS, breakdownGalleryStatus);
        editor.apply();
    }

    public String getBreakdownGalleryStatus(){
        return sharedPreferences.getString(Config_cacheData.BREAKDOWN_GALLERY_STATUS, "not_done");
    }
//
//    public void setUserEmail(String userName){
//        editor.putString(Config_cacheData.USER_EMAIL, userName);
//        editor.apply();
//    }
//
//    public String getUserEmail(){
//        return sharedPreferences.getString(Config_cacheData.USER_EMAIL, "Not found");
//    }
//
//    public void setUserEmail(String userName){
//        editor.putString(Config_cacheData.USER_EMAIL, userName);
//        editor.apply();
//    }
//
//    public String getUserEmail(){
//        return sharedPreferences.getString(Config_cacheData.USER_EMAIL, "Not found");
//    }
}
