package com.example.demo.dto;


public class taskFilterDto {
	
	private String startDate;
	
	private String endDate;
	
	private String summary;
	
	private Integer idOt;

	public taskFilterDto() {	
	}

	public taskFilterDto(String startDate, String endDate, String summary, Integer idOt) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.summary = summary;
		this.idOt = idOt;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Integer getIdOt() {
		return idOt;
	}

	public void setIdOt(Integer idOt) {
		this.idOt = idOt;
	}
	
	
}