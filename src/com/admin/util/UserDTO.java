package com.admin.util;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "user")
public class UserDTO {

	private Integer id;
	private String realName;
	private String userName;
	private String age;
	private String userType;
	private String mobilePhone;
	private String fixedPhone;
	private String email;
	private String personalImageUrl;
	private Integer provinceId;
	private Integer cityId;
	private Integer countyId;
	private Integer addressInfoId;
	private String suffix;
	private String formatAddress;
	private String qq;
	private String wechat;
	private String bankAccount;
	private String bankDepositName;
	private String bankDepositeAddress;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getFixedPhone() {
		return fixedPhone;
	}

	public void setFixedPhone(String fixedPhone) {
		this.fixedPhone = fixedPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPersonalImageUrl() {
		return personalImageUrl;
	}

	public void setPersonalImageUrl(String personalImageUrl) {
		this.personalImageUrl = personalImageUrl;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getCountyId() {
		return countyId;
	}

	public void setCountyId(Integer countyId) {
		this.countyId = countyId;
	}

	public Integer getAddressInfoId() {
		return addressInfoId;
	}

	public void setAddressInfoId(Integer addressInfoId) {
		this.addressInfoId = addressInfoId;
	}

	public String getFormatAddress() {
		return formatAddress;
	}

	public void setFormatAddress(String formatAddress) {
		this.formatAddress = formatAddress;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getBankDepositName() {
		return bankDepositName;
	}

	public void setBankDepositName(String bankDepositName) {
		this.bankDepositName = bankDepositName;
	}

	public String getBankDepositeAddress() {
		return bankDepositeAddress;
	}

	public void setBankDepositeAddress(String bankDepositeAddress) {
		this.bankDepositeAddress = bankDepositeAddress;
	}

}
