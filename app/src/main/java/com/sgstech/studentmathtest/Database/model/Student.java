package com.sgstech.studentmathtest.Database.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Student implements Serializable {



    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "student_no")
    public String student_no;

    /**
     * Profile image
     */
    @ColumnInfo(name = "student_profile_img")
    public String student_profile_img;

    @ColumnInfo(name = "student_first_name")
    public String student_first_name;

    @ColumnInfo(name = "student_last_name")
    public String student_last_name;



    /**
     * Phone Numbers
     */
    @ColumnInfo(name = "student_phone_one")
    public String student_phone_one;

    @ColumnInfo(name = "student_phone_two")
    public String student_phone_two;

    @ColumnInfo(name = "student_phone_three")
    public String student_phone_three;

    @ColumnInfo(name = "student_phone_four")
    public String student_phone_four;

    @ColumnInfo(name = "student_phone_five")
    public String student_phone_five;

    @ColumnInfo(name = "student_phone_six")
    public String student_phone_six;

    @ColumnInfo(name = "student_phone_seven")
    public String student_phone_seven;

    @ColumnInfo(name = "student_phone_eight")
    public String student_phone_eight;

    @ColumnInfo(name = "student_phone_nine")
    public String student_phone_nine;

    @ColumnInfo(name = "student_phone_ten")
    public String student_phone_ten;

    /**
     * Emails
     */

    @ColumnInfo(name = "student_email_one")
    public String student_email_one;

    @ColumnInfo(name = "student_email_two")
    public String student_email_two;

    @ColumnInfo(name = "student_email_three")
    public String student_email_three;

    @ColumnInfo(name = "student_email_four")
    public String student_email_four;

    @ColumnInfo(name = "student_email_five")
    public String student_email_five;

    @ColumnInfo(name = "student_email_six")
    public String student_email_six;

    @ColumnInfo(name = "student_email_seven")
    public String student_email_seven;

    @ColumnInfo(name = "student_email_eight")
    public String student_email_eight;

    @ColumnInfo(name = "student_email_nine")
    public String student_email_nine;

    @ColumnInfo(name = "student_email_ten")
    public String student_email_ten;



    /**
     * Getters and Setters----------------------------------------------------------------------
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudent_no() {
        return student_no;
    }

    public void setStudent_no(String student_no) {
        this.student_no = student_no;
    }

    /**
     * Profile image
     */
    public String getStudent_profile_img() {
        return student_profile_img;
    }

    public void setStudent_profile_img(String student_profile_img) {
        this.student_profile_img = student_profile_img;
    }

    public String getStudent_first_name() {
        return student_first_name;
    }

    public void setStudent_first_name(String student_first_name) {
        this.student_first_name = student_first_name;
    }

    public String getStudent_last_name() {
        return student_last_name;
    }

    public void setStudent_last_name(String student_last_name) {
        this.student_last_name = student_last_name;
    }


    /**
     * Phone Numbers
     */
    public String getStudent_phone_one() {
        return student_phone_one;
    }

    public void setStudent_phone_one(String student_phone_one) {
        this.student_phone_one = student_phone_one;
    }

    public String getStudent_phone_two() {
        return student_phone_two;
    }

    public void setStudent_phone_two(String student_phone_two) {
        this.student_phone_two = student_phone_two;
    }

    public String getStudent_phone_three() {
        return student_phone_three;
    }

    public void setStudent_phone_three(String student_phone_three) {
        this.student_phone_three = student_phone_three;
    }

    public String getStudent_phone_four() {
        return student_phone_four;
    }

    public void setStudent_phone_four(String student_phone_four) {
        this.student_phone_four = student_phone_four;
    }

    public String getStudent_phone_five() {
        return student_phone_five;
    }

    public void setStudent_phone_five(String student_phone_five) {
        this.student_phone_five = student_phone_five;
    }

    public String getStudent_phone_six() {
        return student_phone_six;
    }

    public void setStudent_phone_six(String student_phone_six) {
        this.student_phone_six = student_phone_six;
    }

    public String getStudent_phone_seven() {
        return student_phone_seven;
    }

    public void setStudent_phone_seven(String student_phone_seven) {
        this.student_phone_seven = student_phone_seven;
    }

    public String getStudent_phone_eight() {
        return student_phone_eight;
    }

    public void setStudent_phone_eight(String student_phone_eight) {
        this.student_phone_eight = student_phone_eight;
    }

    public String getStudent_phone_nine() {
        return student_phone_nine;
    }

    public void setStudent_phone_nine(String student_phone_nine) {
        this.student_phone_nine = student_phone_nine;
    }

    public String getStudent_phone_ten() {
        return student_phone_ten;
    }

    public void setStudent_phone_ten(String student_phone_ten) {
        this.student_phone_ten = student_phone_ten;
    }


    /**
     * Emails
     */
    public String getStudent_email_one() {
        return student_email_one;
    }

    public void setStudent_email_one(String student_email_one) {
        this.student_email_one = student_email_one;
    }

    public String getStudent_email_two() {
        return student_email_two;
    }

    public void setStudent_email_two(String student_email_two) {
        this.student_email_two = student_email_two;
    }

    public String getStudent_email_three() {
        return student_email_three;
    }

    public void setStudent_email_three(String student_email_three) {
        this.student_email_three = student_email_three;
    }

    public String getStudent_email_four() {
        return student_email_four;
    }

    public void setStudent_email_four(String student_email_four) {
        this.student_email_four = student_email_four;
    }

    public String getStudent_email_five() {
        return student_email_five;
    }

    public void setStudent_email_five(String student_email_five) {
        this.student_email_five = student_email_five;
    }

    public String getStudent_email_six() {
        return student_email_six;
    }

    public void setStudent_email_six(String student_email_six) {
        this.student_email_six = student_email_six;
    }

    public String getStudent_email_seven() {
        return student_email_seven;
    }

    public void setStudent_email_seven(String student_email_seven) {
        this.student_email_seven = student_email_seven;
    }

    public String getStudent_email_eight() {
        return student_email_eight;
    }

    public void setStudent_email_eight(String student_email_eight) {
        this.student_email_eight = student_email_eight;
    }

    public String getStudent_email_nine() {
        return student_email_nine;
    }

    public void setStudent_email_nine(String student_email_nine) {
        this.student_email_nine = student_email_nine;
    }

    public String getStudent_email_ten() {
        return student_email_ten;
    }

    public void setStudent_email_ten(String student_email_ten) {
        this.student_email_ten = student_email_ten;
    }


    @ColumnInfo(name = "finished")
    public boolean finished;


    /*
    * Getters and Setters
    * */





    /**
     * Unit No
     */




//    public String getUnitNo() {
//        return student_unit_no;
//    }

//    public void setUnitNo(String student_unit_no) {
//        this.student_unit_no = student_unit_no;
//    }




    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
