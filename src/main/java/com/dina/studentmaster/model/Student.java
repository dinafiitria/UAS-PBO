package com.dina.studentmaster.model;

public class Student {
    private String studentId;
    private String studentName;
    private String major;
    private String email;
    private String phone;
    private String address;

    public Student() {}

    public Student(String studentId, String studentName, String major, String email, String phone, String address) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.major = major;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}
