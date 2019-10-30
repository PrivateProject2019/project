package com.kh.ezcol.common.model.vo;

import java.io.Serializable;

import org.springframework.stereotype.Component;



@Component
public class FileName implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7159598137694148527L;

	
	private String ofilename;
	private String rfilename;
	
	public FileName() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "FileName [ofilename=" + ofilename + ", rfilename=" + rfilename + "]";
	}

	public FileName(String ofilename, String rfilename) {
		super();
		this.ofilename = ofilename;
		this.rfilename = rfilename;
	}

	public String getOfilename() {
		return ofilename;
	}

	public void setOfilename(String ofilename) {
		this.ofilename = ofilename;
	}

	public String getRfilename() {
		return rfilename;
	}

	public void setRfilename(String rfilename) {
		this.rfilename = rfilename;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
