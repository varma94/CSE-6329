/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.interfaces;

/**
 *
 * @author Frank R.
 */
public interface Constants {
    final static String INITIALIZE_APPOINTMENT_FAIL = "Error when assign studentID, ID must be 10 digit number starting with 1000 or 6000";
    final static String SUCCESS_MESSAGE = "";
    final static String AVAILABLE_FLYWEIGHT_KEY = "AvailableFlyweight";
    final static String APPOINTMENT_FLYWEIGHT_KEY = "AppointmentFlyweight";
    final static String AVAILABLE_FLYWEIGHT_WITH_SAVE_KEY = "SaveAvailableFlyweight";
    final static String CREATE_ADVISOR_FAIL = "Advisor can't be added";
    
    final static String ILLEGAL_ARGUMENT_FAULT = "The times that you selected are not valid (i.e. the end hour was before the start hour)";
    final static String ILLEGAL_KEY_FAULT = "The type of flyweight you were attempting to create was not valid.";
    final static String ILLEGAL_FLYWEIGHT_FAULT = "The flyweights could not be created.";
    final static String TIME_IS_NOT_FREE_FAULT = "The time for the appointment is not free.";
    
    final static String FDB_FILE_NAME = "FDB_file";
    final static String FLYWEIGHTS_EMPTY_FAULT = "There were no flyweights created to send to the database.";
    final static String FLYWEIGHTS_DIFFERENT_DATE_FAULT = "The flyweights submitted have different dates";
    
    final static int INCREMENT_GAP = 15;
    final static int MIN_HOUR = 0;
    final static int MAX_HOUR = 23;
    final static int MIN_MINUTE = 0;
    final static int MAX_MINUTE= 59;
    final static int ILLEGAL_APPT_ID = 0;
    final static int VALID_ID = 1;
}
