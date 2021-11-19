package com.example.petstore;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonProperty;

@Schema(name = "PetType")
public class PetType {
	
	@Schema(required = true, description = "Pet Type Id")
	@JsonProperty("pet_type_id")
	private Integer petTypeId;

	@Schema(required = true, description = "Pet Type")
	@JsonProperty("pet_type")
	private String petType;
	
	public Integer getPetTypeId() {
		return petTypeId;
	}

	public void setPetTypeId(Integer petTypeId) {
		this.petTypeId = petTypeId;
	}
	
	public String getPetType() {
		return petType;
	}

	public void setPetType(String petType) {
		this.petType = petType;
	}
}
