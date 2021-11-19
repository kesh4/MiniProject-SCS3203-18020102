package com.example.petstore;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/petStore")
@Produces("application/json")
public class PetResource {
	List<Pet> pets = new ArrayList<Pet>();
	List<PetType> petTypes = new ArrayList<PetType>();
	

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Load Pets", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@GET
	@Path("/")
	public Response getAllPets() {
		return Response.ok(pets).build();
	}

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Load Pet by id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found by the  given id") })
	@GET
	@Path("/{petId}")
	public Response getPetById(@PathParam("petId") int petId) {
		boolean b = false;
		for(int i = 0; i < pets.size() ; i++) {
			if(pets.get(i).getPetId()==petId) {
				b = true;
			}
		}
		Pet pet = null;
		if (!b) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}else {
			for(int i = 0; i < pets.size() ; i++) {
				if(pets.get(i).getPetId()==petId) {
					pet = pets.get(i);
				}
			}
			return Response.ok(pet).build();
						
		}
		
	}
	
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Add Pet", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@POST
	@Path("/addPet")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Response addPet(Pet pet) {
		int petID = this.pets.size() + 1;

		pet.setPetId(petID);
		this.pets.add(pet);
		return Response.ok(pet).build();
	}
	
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Delete Pet By Id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@DELETE
	@Path("/removePet/{petId}")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Response deletePet(@PathParam("petId") int petId) {
		if (petId < 0 || pets.get(petId).getPetName() == "undefined") {
			return Response.status(Status.NOT_FOUND).build();
		}
		Pet petDeleted = null;
		for(int i = 0; i < pets.size() ; i++) {
			if(pets.get(i).getPetId()==petId) {
				petDeleted = pets.get(i);
				pets.remove(i);
			}
		}

		return Response.ok(petDeleted).build();
	}
	
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Update Pet", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@PUT
	@Path("/updatePet")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Response updatePet(Pet pet) {
		if (pet.getPetId() < 0 ) {
			return Response.status(Status.NOT_FOUND).build();
		}
		Pet petSelected = null;
		for(int i = 0; i < pets.size() ; i++) {
			if(pets.get(i).getPetId()==pet.getPetId()) {
				petSelected = pets.get(i);
			}
		}
		petSelected.setPetAge(pet.getPetAge());
		petSelected.setPetName(pet.getPetName());
		petSelected.setPetType(pet.getPetType());
		
		return Response.ok(petSelected).build();
	}
	
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Get All Pet Types", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "PetType"))) })
	@GET
	@Path("/getAllPetTypes")
	public Response getAllPetTypes() {
		return Response.ok(petTypes).build();
	}

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Get Pet Type by id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "PetType"))),
			@APIResponse(responseCode = "404", description = "No Pet Type found by the id.") })
	@GET
	@Path("/getPetTypeById/{petTypeId}")
	public Response getPetTypeByID(@PathParam("petTypeId") int petTypeId) {
		boolean b = false;
		for(int i = 0; i < petTypes.size() ; i++) {
			if(petTypes.get(i).getPetTypeId()==petTypeId) {
				b = true;
			}
		}
		PetType petTypeSelected = null;
		if (!b) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}else {
			for(int i = 0; i < petTypes.size() ; i++) {
				if(petTypes.get(i).getPetTypeId()==petTypeId) {
					petTypeSelected = petTypes.get(i);
				}
			}
			return Response.ok(petTypeSelected).build();
						
		}
		
	}
	
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Add Pet Type", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "PetType"))) })
	@POST
	@Path("/addPetType/")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Response createPetType(PetType petType) {
		int petId = this.petTypes.size() + 1;

		petType.setPetTypeId(petId);
		this.petTypes.add(petType);
		return Response.ok(petType).build();
	}
	
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Delete Pet Type By ID", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "PetType"))) })
	@DELETE
	@Path("/removePetType/{petTypeId}")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Response deletePetTypeById(@PathParam("petTypeId") int petTypeId) {
		if (petTypeId < 0 || petTypes.get(petTypeId).getPetType() == "undefined") {
			return Response.status(Status.NOT_FOUND).build();
		}
		PetType petSelected = null;
		for(int i = 0; i < petTypes.size() ; i++) {
			if(petTypes.get(i).getPetTypeId()==petTypeId) {
				petSelected = petTypes.get(i);
				petTypes.remove(i);
			}
		}

		return Response.ok(petSelected).build();
	}
	
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Update Pet Type", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "PetType"))) })
	@PUT
	@Path("/updatePetType/")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Response updatePet(PetType petType) {
		if (petType.getPetTypeId() < 0 ) {
			return Response.status(Status.NOT_FOUND).build();
		}
		PetType petSelected = null;
		for(int i = 0; i < petTypes.size() ; i++) {
			if(petTypes.get(i).getPetTypeId()==petType.getPetTypeId()) {
				petSelected = petTypes.get(i);
			}
		}
		petSelected.setPetType(petType.getPetType());
		
		return Response.ok(petSelected).build();
	}
	
}
