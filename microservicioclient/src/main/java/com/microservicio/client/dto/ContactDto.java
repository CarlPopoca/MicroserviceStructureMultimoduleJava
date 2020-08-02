package com.microservicio.client.dto;

import java.math.BigInteger;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(value="ContactDto", description="request data")
public class ContactDto {
	public String id;
	public String name;
	public String gender;
	public BigInteger mobile;
	
	public ContactDto()
	{
		super();
	}

	public ContactDto(String id, String name, String gender, BigInteger mobile) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.mobile = mobile;
	}
	
	@ApiModelProperty(required=true, notes="The id contact")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@ApiModelProperty(required=true, notes="The name contact")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@ApiModelProperty(required=true, notes="The gender contact")
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@ApiModelProperty(required=true, notes="The mobile contact")
	public BigInteger getMobile() {
		return mobile;
	}
	public void setMobile(BigInteger mobile) {
		this.mobile = mobile;
	}
	
	@Override
	public String toString() {
		return "ContactsDTO [id=" + id + ", name=" + name + ", gender=" + gender + ", mobile=" + mobile + "]";
	}
}
