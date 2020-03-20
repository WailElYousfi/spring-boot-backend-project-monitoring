package com.example.demo.dto;

import java.util.Map;


public class IncidenceFilterDto {
	
//	@JsonFormat(pattern = "yyyy-MM-dd")
	private String startDate;
	
	private String endDate;
	
	private String idOt;
	
	private String idPeticion;
	
	private String otCorrector;
	
	public Map<String, String> tareasCausante;
	
	private String acc;

	public IncidenceFilterDto() {
		
	}
	
	public IncidenceFilterDto(String startDate, String endDate, String idOt, String idPeticion, String otCorrector,
			Map<String, String> tareasCausante, String acc) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.idOt = idOt;
		this.idPeticion = idPeticion;
		this.otCorrector = otCorrector;
		this.tareasCausante = tareasCausante;
		this.acc = acc;
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

	public String getIdOt() {
		return idOt;
	}

	public void setIdOt(String idOt) {
		this.idOt = idOt;
	}

	public String getIdPeticion() {
		return idPeticion;
	}

	public void setIdPeticion(String idPeticion) {
		this.idPeticion = idPeticion;
	}

	public String getOtCorrector() {
		return otCorrector;
	}

	public void setOtCorrector(String otCorrector) {
		this.otCorrector = otCorrector;
	}
	
	public Map<String, String> getTareasCausante() {
		return tareasCausante;
	}

	public void setTareasCausante(Map<String, String> tareasCausante) {
		this.tareasCausante = tareasCausante;
	}

	public String getAcc() {
		return acc;
	}

	public void setAcc(String acc) {
		this.acc = acc;
	}
	
}