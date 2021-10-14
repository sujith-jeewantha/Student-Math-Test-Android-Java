package com.sgstech.studentmathtest.Cache;

import android.content.Context;
import android.content.SharedPreferences;

public class Manager_Cache {


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
//    public void setUserEmail(String userEmail){
//        editor.putString(Config_cacheData.USER_EMAIL, userEmail);
//        editor.apply();
//    }
//
//    public String getUserEmail(){
//        return sharedPreferences.getString(Config_cacheData.USER_EMAIL, "");
//    }
//
//    public void setUserPassword(String userPassword){
//        editor.putString(Config_cacheData.USER_PASSWORD, userPassword);
//        editor.apply();
//    }
//
//    public String getUserPassword(){
//        return sharedPreferences.getString(Config_cacheData.USER_PASSWORD, "");
//    }
//
//    /**
//     * Service data
//     */
//
//    public void setServiceRegionName(String serviceRegionName){
//        editor.putString(Config_cacheData.SERVICE_REGION_NAME, serviceRegionName);
//        editor.apply();
//    }
//
//    public String getServiceRegionName(){
//        return sharedPreferences.getString(Config_cacheData.SERVICE_REGION_NAME, "Not found");
//    }
//
//    public void setServiceTeamName(String serviceTeamName){
//        editor.putString(Config_cacheData.SERVICE_TEAM_NAME, serviceTeamName);
//        editor.apply();
//    }
//
//    public String getServiceTeamName(){
//        return sharedPreferences.getString(Config_cacheData.SERVICE_TEAM_NAME, "Not found");
//    }
//
//    public void setServiceTeamLeaderUserName(String serviceTeamLeaderUserName){
//        editor.putString(Config_cacheData.SERVICE_TEAM_LEADER_USER_NAME, serviceTeamLeaderUserName);
//        editor.apply();
//    }
//
//    public String getServiceTeamLeaderUserName(){
//        return sharedPreferences.getString(Config_cacheData.SERVICE_TEAM_LEADER_USER_NAME, "Not found");
//    }
//
//    public void setServiceTeamLeaderName(String serviceTeamLeaderName){
//        editor.putString(Config_cacheData.SERVICE_TEAM_LEADER_NAME, serviceTeamLeaderName);
//        editor.apply();
//    }
//
//    public String getServiceTeamLeaderName(){
//        return sharedPreferences.getString(Config_cacheData.SERVICE_TEAM_LEADER_NAME, "Not found");
//    }
//
//    public void setServiceTeamLeaderNIC(String serviceTeamLeaderNIC){
//        editor.putString(Config_cacheData.SERVICE_TEAM_LEADER_NIC_NO, serviceTeamLeaderNIC);
//        editor.apply();
//    }
//
//    public String getServiceTeamLeaderNIC(){
//        return sharedPreferences.getString(Config_cacheData.SERVICE_TEAM_LEADER_NIC_NO, "Not found");
//    }
//
//    public void setServiceTeamLeaderTelephone(String serviceTeamLeaderTelephone){
//        editor.putString(Config_cacheData.SERVICE_TEAM_LEADER_TELEPHONE, serviceTeamLeaderTelephone);
//        editor.apply();
//    }
//
//    public String getServiceTeamLeaderTelephone(){
//        return sharedPreferences.getString(Config_cacheData.SERVICE_TEAM_LEADER_TELEPHONE, "Not found");
//    }
//
//    public void setServiceTeamHelperUserName(String serviceTeamHelperUsername){
//        editor.putString(Config_cacheData.SERVICE_TEAM_HELPER_USER_NAME, serviceTeamHelperUsername);
//        editor.apply();
//    }
//
//    public String getServiceTeamHelperUserName(){
//        return sharedPreferences.getString(Config_cacheData.SERVICE_TEAM_HELPER_USER_NAME, "Not found");
//    }
//
//    public void setServiceTeamHelperName(String serviceTeamHelperName){
//        editor.putString(Config_cacheData.SERVICE_TEAM_HELPER_NAME, serviceTeamHelperName);
//        editor.apply();
//    }
//
//    public String getServiceTeamHelperName(){
//        return sharedPreferences.getString(Config_cacheData.SERVICE_TEAM_HELPER_NAME, "Not found");
//    }
//
//    public void setServiceTeamHelperNIC(String serviceTeamHelperNIC){
//        editor.putString(Config_cacheData.SERVICE_TEAM_HELPER_NIC_NO, serviceTeamHelperNIC);
//        editor.apply();
//    }
//
//    public String getServiceTeamHelperNIC(){
//        return sharedPreferences.getString(Config_cacheData.SERVICE_TEAM_HELPER_NIC_NO, "Not found");
//    }
//
//    public void setServiceTeamHelperTelephone(String serviceTeamHelperTelephone){
//        editor.putString(Config_cacheData.SERVICE_TEAM_HELPER_TELEPHONE, serviceTeamHelperTelephone);
//        editor.apply();
//    }
//
//    public String getServiceTeamHelperTelephone(){
//        return sharedPreferences.getString(Config_cacheData.SERVICE_TEAM_HELPER_TELEPHONE, "Not found");
//    }
//
//    public void setServiceSiteID(String serviceSiteID){
//        editor.putString(Config_cacheData.SERVICE_SITE_ID, serviceSiteID);
//        editor.apply();
//    }
//
//    public String getServiceSiteID(){
//        return sharedPreferences.getString(Config_cacheData.SERVICE_SITE_ID, "");
//    }
//
//    public void setServiceSiteName(String serviceSiteName){
//        editor.putString(Config_cacheData.SERVICE_SITE_NAME, serviceSiteName);
//        editor.apply();
//    }
//
//    public String getServiceSiteName(){
//        return sharedPreferences.getString(Config_cacheData.SERVICE_SITE_NAME, "");
//    }
//
//    public void setServiceSiteDate(String serviceSiteDate){
//        editor.putString(Config_cacheData.SERVICE_SITE_DATE, serviceSiteDate);
//        editor.apply();
//    }
//
//    public String getServiceSiteDate(){
//        return sharedPreferences.getString(Config_cacheData.SERVICE_SITE_DATE, "");
//    }
//
//    public void setServiceSiteIN(String serviceSiteIN){
//        editor.putString(Config_cacheData.SERVICE_SITE_IN, serviceSiteIN);
//        editor.apply();
//    }
//
//    public String getServiceSiteIN(){
//        return sharedPreferences.getString(Config_cacheData.SERVICE_SITE_IN, "");
//    }
//
//    public void setServiceSiteOUT(String serviceSiteOUT){
//        editor.putString(Config_cacheData.SERVICE_SITE_OUT, serviceSiteOUT);
//        editor.apply();
//    }
//
//    public String getServiceSiteOUT(){
//        return sharedPreferences.getString(Config_cacheData.SERVICE_SITE_OUT, "");
//    }
//
//    public void setServiceSiteRemarks(String serviceRemarks){
//        editor.putString(Config_cacheData.SERVICE_REMARKS, serviceRemarks);
//        editor.apply();
//    }
//
//    public String getServiceSiteRemarks(){
//        return sharedPreferences.getString(Config_cacheData.SERVICE_REMARKS, "");
//    }
//
//    public void setServiceSiteMaterialUsage(String serviceMaterialUsage){
//        editor.putString(Config_cacheData.SERVICE_MATERIAL_USAGE, serviceMaterialUsage);
//        editor.apply();
//    }
//
//    public String getServiceSiteMaterialUsage(){
//        return sharedPreferences.getString(Config_cacheData.SERVICE_MATERIAL_USAGE, "");
//    }
//
//    /**
//     * Service Team Info Status
//     */
//    public void setServiceTeamInfoStatus(String serviceTeamInfoStatus){
//        editor.putString(Config_cacheData.SERVICE_TEAM_INFO_STATUS, serviceTeamInfoStatus);
//        editor.apply();
//    }
//
//    public String getServiceTeamInfoStatus(){
//        return sharedPreferences.getString(Config_cacheData.SERVICE_TEAM_INFO_STATUS, "not_done");
//    }
//
//    /**
//     * Service Site Info Status
//     */
//    public void setServiceSiteInfoStatus(String serviceSiteInfoStatus){
//        editor.putString(Config_cacheData.SERVICE_SITE_INFO_STATUS, serviceSiteInfoStatus);
//        editor.apply();
//    }
//
//    public String getServiceSiteInfoStatus(){
//        return sharedPreferences.getString(Config_cacheData.SERVICE_SITE_INFO_STATUS, "not_done");
//    }
//
//    /**
//     * Service Summary Status
//     */
//    public void setServiceSummaryStatus(String serviceSummaryStatus){
//        editor.putString(Config_cacheData.SERVICE_SUMMARY_STATUS, serviceSummaryStatus);
//        editor.apply();
//    }
//
//    public String getServiceSummaryStatus(){
//        return sharedPreferences.getString(Config_cacheData.SERVICE_SUMMARY_STATUS, "not_done");
//    }
//
//    /**
//     * Service Gallery Status
//     */
//    public void setServiceGalleryStatus(String serviceGalleryStatus){
//        editor.putString(Config_cacheData.SERVICE_GALLERY_STATUS, serviceGalleryStatus);
//        editor.apply();
//    }
//
//    public String getServiceGalleryStatus(){
//        return sharedPreferences.getString(Config_cacheData.SERVICE_GALLERY_STATUS, "not_done");
//    }






















}
