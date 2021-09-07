package model.model.vo;

import java.sql.Date; // utill 아니고 sql

public class Student {
	
	
	//멤버변수
	private String studentId;
	private String studentPw;
	private String studentName;
	private String studentGender; //char인데 string으로써도 상관은 없다
	private int studentAge;
	private String studentEmail;
	private String studentPhone;
	private String studentAddress;
	private String studentHobby;
	private Date enrollDate;
	
	//생성자
	public Student() {}
	
	//매개변수가 있는 생성자
	
	
	//게터 세터
	public String getStudentId() {
		return studentId;
	}
	
	public Student(String studentId, String studentPw, String studentName, String studentGender, int studentAge,
			String studentEmail, String studentPhone, String studentAddress, String studentHobby) {
		super();
		this.studentId = studentId;
		this.studentPw = studentPw;
		this.studentName = studentName;
		this.studentGender = studentGender;
		this.studentAge = studentAge;
		this.studentEmail = studentEmail;
		this.studentPhone = studentPhone;
		this.studentAddress = studentAddress;
		this.studentHobby = studentHobby;
	}
	
	
	// 수정용
	public Student(String studentId, String studentPw, String studentEmail, String studentPhone, String studentAddress,
			String studentHobby) {
		super();
		this.studentId = studentId;
		this.studentPw = studentPw;
		this.studentEmail = studentEmail;
		this.studentPhone = studentPhone;
		this.studentAddress = studentAddress;
		this.studentHobby = studentHobby;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getStudentPw() {
		return studentPw;
	}
	public void setStudentPw(String studentPw) {
		this.studentPw = studentPw;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentGender() {
		return studentGender;
	}
	public void setStudentGender(String studentGender) {
		this.studentGender = studentGender;
	}
	public int getStudentAge() {
		return studentAge;
	}
	public void setStudentAge(int studentAge) {
		this.studentAge = studentAge;
	}
	public String getStudentEmail() {
		return studentEmail;
	}
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	public String getStudentPhone() {
		return studentPhone;
	}
	public void setStudentPhone(String studentPhone) {
		this.studentPhone = studentPhone;
	}
	public String getStudentAddress() {
		return studentAddress;
	}
	public void setStudentAddress(String studentAddress) {
		this.studentAddress = studentAddress;
	}
	public String getStudentHobby() {
		return studentHobby;
	}
	public void setStudentHobby(String studentHobby) {
		this.studentHobby = studentHobby;
	}
	public Date getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}
	
	//to String
	@Override
	public String toString() {
		return "STUDENT [학생 아이디 =" + studentId + ", 학생 비밀번호 =" + studentPw + ", 학생 이름 =" + studentName
				+ ", 학생 성별 =" + studentGender + ", 학생 나이 =" + studentAge + ", 학생 이메일 =" + studentEmail
				+ ", 학생 전화번호 =" + studentPhone + ", 학생 주소 =" + studentAddress + ", 취미 = "+studentHobby 
				+", 등록일=" + enrollDate+ "]";
	}
	
	

}
