package com.csomet.springboot.app.model;

import java.io.Serializable;


public class DemoModelClass implements Serializable{

	private String attribute1;
	private int attribute2;
	private Long id;
	private String attribute3;
	
	
	
	
	public DemoModelClass() {
	
	}

	



	public String getAttribute1() {
		return attribute1;
	}





	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}





	public int getAttribute2() {
		return attribute2;
	}





	public void setAttribute2(int attribute2) {
		this.attribute2 = attribute2;
	}





	public Long getId() {
		return id;
	}





	public void setId(Long id) {
		this.id = id;
	}





	public String getAttribute3() {
		return attribute3;
	}





	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}





	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
