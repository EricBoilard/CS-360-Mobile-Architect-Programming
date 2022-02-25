package com.zybooks.myappproject;

public class UserModel {
    // declare variables and create constructor, getters, and setters
    private int id;
    private int id2;
    private int weight;
    private String date;
    private Integer goal;
    private String username;
    private String password;
    private String rep_pass;

    //constructors
    public UserModel(int id, int weight, String date) {
        this.id = id;
        this.weight = weight;
        this.date = date;
    }

    // empty constructor
    public UserModel(){

    }
    // constructors
    public UserModel(int id, int goal) {
        this.id = id;
        this.goal = goal;
    }

    public UserModel(int id, int weight, int goal) {
        this.id = id;
        this.weight = weight;
        this.goal = goal;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserModel(int id, Integer goal, String username, String password, String rep_pass) {
        this.id = id;
        this.goal = goal;
        this.username = username;
        this.password = password;
        this.rep_pass = rep_pass;
    }

    // toString needed for printing contents of a class object
    @Override
    public String toString() {
        return  weight + "lbs" + "      " + date ;
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }
}
