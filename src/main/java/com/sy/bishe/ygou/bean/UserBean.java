package com.sy.bishe.ygou.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户对象
 */
public class UserBean {

    private int user_id;

    private String user_name;

    private String user_word;

    private  String user_password;

    private String user_email;

    private String user_phone;

    private String user_address;

    private String user_age;

    private String user_birth;

    private String user_gender;

    private String user_school;

    private String user_specialty;

    private String user_grade;

    private String user_stunumber;

    private String user_isidentificated;

    private Double user_balance;

    private String user_img;

    private String user_college;

    private String user_signature;

    private String user_paynum;


    public String getUser_img() {
        return user_img;
    }

    public void setUser_img(String user_img) {
        this.user_img = user_img;
    }

    public Double getUser_balance() {
        return user_balance;
    }

    public void setUser_balance(Double user_balance) {
        this.user_balance = user_balance;
    }

    public List<CartBean> getCartBeans() {
        return cartBeans;
    }

    public void setCartBeans(List<CartBean> cartBeans) {
        this.cartBeans = cartBeans;
    }

    private List<CartBean> cartBeans = new ArrayList<>();

    private List<Integer> friedIds = new ArrayList<>();

    public List<Integer> getFriedIds() {
        return friedIds;
    }

    public void setFriedIds(List<Integer> friedIds) {
        this.friedIds = friedIds;
    }

    public UserBean() {
    }

    public UserBean(int user_id, String user_name, String user_word, String user_password, String user_email, String user_phone, String user_address, String user_age, String user_birth, String user_gender, String user_school, String user_specialty, String user_grade, String user_stunumber, String user_isdentificated, Double user_balance, String user_img, String user_college, String user_signature, String user_paynum) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_word = user_word;
        this.user_password = user_password;
        this.user_email = user_email;
        this.user_phone = user_phone;
        this.user_address = user_address;
        this.user_age = user_age;
        this.user_birth = user_birth;
        this.user_gender = user_gender;
        this.user_school = user_school;
        this.user_specialty = user_specialty;
        this.user_grade = user_grade;
        this.user_stunumber = user_stunumber;
        this.user_isidentificated = user_isdentificated;
        this.user_balance = user_balance;
        this.user_img = user_img;
        this.user_college = user_college;
        this.user_signature = user_signature;
        this.user_paynum = user_paynum;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_word() {
        return user_word;
    }

    public void setUser_word(String user_word) {
        this.user_word = user_word;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public String getUser_age() {
        return user_age;
    }

    public void setUser_age(String user_age) {
        this.user_age = user_age;
    }

    public String getUser_birth() {
        return user_birth;
    }

    public void setUser_birth(String user_birth) {
        this.user_birth = user_birth;
    }

    public String getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(String user_gender) {
        this.user_gender = user_gender;
    }

    public String getUser_school() {
        return user_school;
    }

    public void setUser_school(String user_school) {
        this.user_school = user_school;
    }

    public String getUser_stuspeciality() {
        return user_specialty;
    }

    public void setUser_stuspeciality(String user_specialty) {
        this.user_specialty = user_specialty;
    }

    public String getUser_grade() {
        return user_grade;
    }

    public void setUser_grade(String user_grade) {
        this.user_grade = user_grade;
    }

    public String getUser_stunumber() {
        return user_stunumber;
    }

    public void setUser_stunumber(String user_stunumber) {
        this.user_stunumber = user_stunumber;
    }

    public String getUser_isidentificated() {
        return user_isidentificated;
    }

    public void setUser_isidentificated(String user_isidentificated) {
        this.user_isidentificated = user_isidentificated;
    }

    public String getUser_college() {
        return user_college;
    }

    public void setUser_college(String user_college) {
        this.user_college = user_college;
    }

    public String getUser_signature() {
        return user_signature;
    }

    public void setUser_signature(String user_signature) {
        this.user_signature = user_signature;
    }

    public String getUser_specialty() {
        return user_specialty;
    }

    public void setUser_specialty(String user_specialty) {
        this.user_specialty = user_specialty;
    }

    public String getUser_paynum() {
        return user_paynum;
    }

    public void setUser_paynum(String user_paynum) {
        this.user_paynum = user_paynum;
    }
}
