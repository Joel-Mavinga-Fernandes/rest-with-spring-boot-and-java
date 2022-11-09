package com.exercicse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exercicse.data.vo.v1.PersonVO;
import com.exercicse.data.vo.v2.PersonVOV2;
import com.exercicse.model.Person;
import com.exercicse.services.PersonServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("api/person/v1")
@RestController
@Tag(name ="People", description = "Endpoints for Managing People")
public class PersonController {
	
	@Autowired
	private PersonServices service;
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@Operation(summary = "Finds All People", description = "Finds All People",
		tags = {"People"},
		responses = {
				@ApiResponse(description = "success", responseCode = "200", 
						content= {
								@Content(
										mediaType = "application/json",
										array = @ArraySchema(schema = @Schema(implementation = PersonVO.class))
										)
						}),
				@ApiResponse(description = "Bad Request", responseCode = "400", content= @Content),
				@ApiResponse(description = "Unauthorized", responseCode = "401", content= @Content),
				@ApiResponse(description = "Not Found", responseCode = "404", content= @Content),
				@ApiResponse(description = "Internal Error", responseCode = "500", content= @Content)
		})
	public ResponseEntity<List<PersonVO>> findAll(){
		
		var response = service.findAll();
		
		return ResponseEntity.ok(response);
		
	}
	
	@GetMapping(value ="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@Operation(summary = "Finds a Person", description = "Finds a Person",
	tags = {"People"},
	responses = {
			@ApiResponse(description = "success", responseCode = "200", 
					content= @Content (schema = @Schema(implementation = PersonVO.class))		
			),
			@ApiResponse(description = "No Content", responseCode = "204", content= @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content= @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content= @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content= @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content= @Content)
	})
	public ResponseEntity<PersonVO> findById(@PathVariable Long id){
		
		var response = service.findById(id);
		
		return ResponseEntity.ok(response);
		
	}
	
	@PostMapping(
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@Operation(summary = "Add a Person", description = "Add a Person",
	tags = {"People"},
	responses = {
			@ApiResponse(description = "success", responseCode = "200", 
					content= @Content (schema = @Schema(implementation = PersonVO.class))		
			),
			@ApiResponse(description = "Bad Request", responseCode = "400", content= @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content= @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content= @Content)
	})
	public PersonVO create(@RequestBody PersonVO person){
		
		var response = service.create(person);
		
		return response;	
	}
	
	@PostMapping(value= "/v2",
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public PersonVOV2 createV2(@RequestBody PersonVOV2 person){
		
		var response = service.createV2(person);
		
		return response;	
	}
	
	@PutMapping(
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@Operation(summary = "Update a Person", description = "Update a Person",
	tags = {"People"},
	responses = {
			@ApiResponse(description = "Updated", responseCode = "200", 
					content= @Content (schema = @Schema(implementation = PersonVO.class))		
			),
			@ApiResponse(description = "Bad Request", responseCode = "400", content= @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content= @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content= @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content= @Content)
	})
	public PersonVO update(@RequestBody PersonVO person){
		
		var response = service.update(person);
		
		return response;	
	}
	
	@DeleteMapping(value= "/{id}")
	@Operation(summary = "Delete a Person", description = "Delete a Person",
	tags = {"People"},
	responses = {
			@ApiResponse(description = "No Content", responseCode = "204", content= @Content ),		
			@ApiResponse(description = "Bad Request", responseCode = "400", content= @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content= @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content= @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content= @Content)
	})
	public void delete(@PathVariable Long id){
		
		service.delete(id);
		
	}
	
	
	
}
