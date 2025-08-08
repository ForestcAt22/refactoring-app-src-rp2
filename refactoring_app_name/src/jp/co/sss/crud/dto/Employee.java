package jp.co.sss.crud.dto;

import jp.co.sss.crud.util.Constants;

public class Employee {

	private int empId;
	private String empName;
	private int gender;
	private String birthday;
	private String deptName;

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Override
	public String toString() {
		String genderStr = "";
		if (this.gender == Constants.GENDER_MALE) {
			genderStr = Constants.GENDER_STR_MALE;
		} else if (this.gender == Constants.GENDER_FEMALE) {
			genderStr = Constants.GENDER_STR_FEMALE;
		} else if (this.gender == Constants.GENDER_NOT_SPECIFIED) {
			genderStr = Constants.GENDER_STR_NOT_SPECIFIED;
		} else if (this.gender == Constants.GENDER_OTHER) {
			genderStr = Constants.GENDER_STR_OTHER;
		}
		return empId + "\t" + empName + "\t" + genderStr + "\t" + birthday + "\t" + deptName;
	}
}