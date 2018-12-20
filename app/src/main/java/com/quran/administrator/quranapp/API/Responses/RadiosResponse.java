package com.quran.administrator.quranapp.API.Responses;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class RadiosResponse{

	@SerializedName("Radios")
	private List<RadiosItem> radios;

	public void setRadios(List<RadiosItem> radios){
		this.radios = radios;
	}

	public List<RadiosItem> getRadios(){
		return radios;
	}

	@Override
 	public String toString(){
		return 
			"RadiosResponse{" + 
			"radios = '" + radios + '\'' + 
			"}";
		}
}