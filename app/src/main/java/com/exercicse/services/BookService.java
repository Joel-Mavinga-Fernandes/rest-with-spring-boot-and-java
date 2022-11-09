package com.exercicse.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exercicse.controller.BookController;
import com.exercicse.controller.PersonController;
import com.exercicse.data.vo.v1.BookVO;
import com.exercicse.exception.ResourceNotFoundException;
import com.exercicse.mapper.DozerMapper;
import com.exercicse.mapper.custom.PersonMapper;
import com.exercicse.model.Book;
import com.exercicse.repositories.BookRepository;
import com.github.dozermapper.core.inject.DozerBeanContainer;

@Service
public class BookService {
	
	private Logger logger = Logger.getLogger(BookService.class.getName());
	
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private PersonMapper personMapper;
	
	public List<BookVO> findAllBooks (){
		
		logger.info("Finding all Books");
		
		var books = DozerMapper.parseListObjects(repository.findAll(), BookVO.class);
		books.stream()
		.forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));
		return books;
		
	}
	
	public BookVO findBookById(Long id) {
		
		logger.info("Finding Book by Id");
		
		var book = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		var vo = DozerMapper.parseObjects(book, BookVO.class);
		
		vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		return vo;
		
	}
	
	public BookVO createBook (BookVO book) {

		logger.info("Create Book");
		
		var entity = DozerMapper.parseObjects(book, Book.class);
		var saved = repository.save(entity);
		var savedVO = DozerMapper.parseObjects(saved, BookVO.class);
		
		savedVO.add(linkTo(methodOn(BookController.class).findById(book.getKey())).withSelfRel());
		return savedVO;
			
	}
	
	public BookVO updateBook (BookVO book) {
		
		logger.info("Update Book");

		var entity = DozerMapper.parseObjects(book, Book.class);
		var findBook = repository.findById(entity.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		var saved = repository.save(findBook);
		var savedVO = DozerMapper.parseObjects(saved,BookVO.class);
		
		savedVO.add(linkTo(methodOn(BookController.class).findById(book.getKey())).withSelfRel());
		return savedVO;
			
	}
	
	public void deleteBook(Long id) {
		
		logger.info("delete Book");
		repository.deleteById(id);
		
	}

}
