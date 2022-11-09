package com.exercicse.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exercicse.data.vo.v1.BookVO;
import com.exercicse.data.vo.v1.PersonVO;
import com.exercicse.model.Book;
import com.exercicse.model.Person;
import com.exercicse.repositories.BookRepository;
import com.exercicse.services.BookService;


@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

	@Mock
	private BookRepository repository;

	@InjectMocks
	private BookService service;

	
	@Test
	void testFindAllBooks() {
		Book book = new Book();
		book.setId(1l);
		book.setAuthor("Kevin");
		
		Book book1 = new Book();
		book1.setId(2l);
		book1.setAuthor("Paul");
		
		List<Book> books = new ArrayList<>();
		books.add(book);
		books.add(book1);
		
		Mockito.when(repository.findAll()).thenReturn(books);
		
		var result = service.findAllBooks();
		
		assertNotNull(result);
		assertEquals("Kevin", result.get(0).getAuthor());
		assertEquals(2l, result.get(1).getKey());
		assertTrue(result.size() == 2);
		assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
			
	}
	
	@Test
	void testFindBookById() {
		Book book = new Book();
		book.setId(1l);
		
		Mockito.when(repository.findById(1L)).thenReturn(Optional.of(book));
		
		var result = service.findBookById(1l);
		
		assertNotNull(result);
		assertNotNull(result.getLinks());
		assertNotNull(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
		assertEquals(1l, result.getKey());
	}
	
	@Test
	void testCreateBook() {
		
		Book book = new Book();
		book.setId(1l);
		book.setAuthor("Paul");
		
		BookVO bookVO = new BookVO();
		bookVO.setKey(1l);
		bookVO.setAuthor("Paul");
		
		Mockito.when(repository.save(book)).thenReturn(book);
		
		var result = service.createBook(bookVO);
		
		assertNotNull(result);
		assertNotNull(result.getLinks());
		assertEquals("Paul", result.getAuthor());
		assertEquals(1l, result.getKey());
		assertNotNull(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
	}
	
	@Test
	void testUpdateBook() {
		
		Book book = new Book();
		book.setId(1l);
		book.setAuthor("Paul");
		
		BookVO bookVO = new BookVO();
		bookVO.setKey(1l);
		bookVO.setAuthor("Paul");
		
		Mockito.when(repository.findById(bookVO.getKey())).thenReturn(Optional.of(book));
		Mockito.when(repository.save(book)).thenReturn(book);
		
		var response = service.updateBook(bookVO);
		
		assertNotNull(response);
		
	}
	
}
