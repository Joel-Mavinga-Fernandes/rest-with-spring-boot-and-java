package com.exercicse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.exercicse.data.vo.v1.BookVO;
import com.exercicse.data.vo.v1.PersonVO;
import com.exercicse.services.BookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;



@RequestMapping("/api/book/v1")
@RestController
@Tag(name ="Book", description = "Endpoints for Managing Books")
public class BookController {

	@Autowired
	private BookService service;
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@Operation(summary = "Finds All Books", description = "Finds All Books",
		tags = {"Book"},
		responses = {
				@ApiResponse(description = "success", responseCode = "200", 
						content= {
								@Content(
										mediaType = "application/json",
										array = @ArraySchema(schema = @Schema(implementation = BookVO.class))
										)
						}),
				@ApiResponse(description = "Bad Request", responseCode = "400", content= @Content),
				@ApiResponse(description = "Unauthorized", responseCode = "401", content= @Content),
				@ApiResponse(description = "Not Found", responseCode = "404", content= @Content),
				@ApiResponse(description = "Internal Error", responseCode = "500", content= @Content)
		})
	public ResponseEntity<List<BookVO>> findAllBooks(){
		
		var response = service.findAllBooks();
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value ="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@Operation(summary = "Finds a Book", description = "Finds a Book",
	tags = {"Book"},
	responses = {
			@ApiResponse(description = "success", responseCode = "200", 
					content= @Content (schema = @Schema(implementation = BookVO.class))		
			),
			@ApiResponse(description = "No Content", responseCode = "204", content= @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content= @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content= @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content= @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content= @Content)
	})
	public ResponseEntity<BookVO> findById(@PathVariable Long id){
		
		var response = service.findBookById(id);
		
		return ResponseEntity.ok(response);
	}
	
	@PostMapping(
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@Operation(summary = "Add a Book", description = "Add a Book",
	tags = {"Book"},
	responses = {
			@ApiResponse(description = "success", responseCode = "200", 
					content= @Content (schema = @Schema(implementation = BookVO.class))		
			),
			@ApiResponse(description = "Bad Request", responseCode = "400", content= @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content= @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content= @Content)
	})
	public ResponseEntity<BookVO> createBook (@RequestBody BookVO book) {
		
		var response = service.createBook(book);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@PutMapping(
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@Operation(summary = "Update a Book", description = "Update a Book",
	tags = {"Book"},
	responses = {
			@ApiResponse(description = "Updated", responseCode = "200", 
					content= @Content (schema = @Schema(implementation = BookVO.class))		
			),
			@ApiResponse(description = "Bad Request", responseCode = "400", content= @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content= @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content= @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content= @Content)
	})
	public ResponseEntity<BookVO> updateBook (@RequestBody BookVO book){
		
		var response = service.updateBook(book);
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@DeleteMapping(value= "/{id}")
	@Operation(summary = "Delete a Book", description = "Delete a Book",
	tags = {"Book"},
	responses = {
			@ApiResponse(description = "No Content", responseCode = "204", content= @Content ),		
			@ApiResponse(description = "Bad Request", responseCode = "400", content= @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content= @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content= @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content= @Content)
	})
	public void delete (@PathVariable Long id) {
		
		service.deleteBook(id);
	}
}
