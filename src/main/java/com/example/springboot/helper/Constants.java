package com.example.springboot.helper;

import java.io.File;

public final class Constants {
    public static final String FS = File.separator;
    public static final String ROOT_PATH = System.getProperty("user.dir");
    public static final String INPUT_PATH = ROOT_PATH+FS+"src"+FS+"main"+FS+"resources"+FS+"static"+FS;
    public static final String OUTPUT_PATH = ROOT_PATH+FS+"src"+FS+"main"+FS+"resources"+FS+"output_files"+FS;
    public static final String POLICE_REPORT_CSV = INPUT_PATH+"Electronic_Police_Report_2019.csv" ;
    public static final String CALL_SERVICE_CSV = INPUT_PATH+"Calls_for_Service_2019.csv" ;
    public static final String CALL_SERVICE_OUTPUT_CSV_PATH = OUTPUT_PATH+"Calls_for_Service_2019.rdf" ;
    public static final String POLICE_REPORT_OUTPUT_CSV_PATH = OUTPUT_PATH+"Electronic_Police_Report_2019.rdf" ;
    
    
    public static final String RESOURCE_URI = "https://data.nola.gov/resource" ;
    public static final String CALL_SERVICE_URI = RESOURCE_URI+"/callService" ;
    public static final String CALL_SERVICE_LOCATION_URI = RESOURCE_URI+"/location" ;
    public static final String CALL_SERVICE_TIME_URI = RESOURCE_URI+"/time" ;
    public static final String CALL_SERVICE_STATE_URI = RESOURCE_URI+"/state" ;
    
    public static final String POLICE_REPORT_URI = RESOURCE_URI+"/policeReport" ;
    public static final String POLICE_REPORT_LOCATION_URI = RESOURCE_URI+"/location" ;
    public static final String POLICE_REPORT_PERSON_URI = RESOURCE_URI+"/person" ;
    
    
    
    
    
}