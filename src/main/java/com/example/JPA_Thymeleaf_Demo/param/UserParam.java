package com.example.JPA_Thymeleaf_Demo.param;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class UserParam {
	private long id;
	@NotEmpty(message = "姓名不能为空")
	private String userName;
	@NotEmpty
	@Length(min = 6, message = "密码长度不能小于6位")
	private String passWord;
	@Max(value = 100, message = "年龄不能大于100")
	@Min(value = 18, message = "年龄不能小于18")
	private int age;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
