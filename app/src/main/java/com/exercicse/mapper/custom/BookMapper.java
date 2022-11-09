package com.exercicse.mapper.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.exercicse.data.vo.v1.BookVO;
import com.exercicse.data.vo.v2.PersonVOV2;
import com.exercicse.model.Book;
import com.exercicse.model.Person;

@Service
public class BookMapper {
	
	public BookVO  convertEntityToVo(Book book) {
		BookVO vo = new BookVO();
		vo.setKey(book.getId());
		vo.setAuthor(book.getAuthor());
		vo.setLaunchDate(book.getLaunchDate());
		vo.setPrice(book.getPrice());
		vo.setTitle(book.getTitle());
		
		return vo;
	}
	
	public Book convertVoEntity(BookVO book) {
		Book bookEntity = new Book();
		
		bookEntity.setId(book.getKey());
		bookEntity.setAuthor(book.getAuthor());
		bookEntity.setLaunchDate(book.getLaunchDate());
		bookEntity.setPrice(book.getPrice());
		bookEntity.setTitle(book.getTitle());
		
		return bookEntity;
	}

}
