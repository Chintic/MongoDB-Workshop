package com.leonel.workshopmong.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonel.workshopmong.domain.Post;
import com.leonel.workshopmong.repository.PostRepository;
import com.leonel.workshopmong.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	PostRepository repository;
	
	public List<Post> findAll(){
		return repository.findAll();
	}
	
	public Optional<Post> findById(String id) {
		Optional<Post> post = repository.findById(id);
		if (post == null) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		}
		return post;
	}
	
	/* Método 1 (sem a query personalizada):
	public List<Post> findByTitle(String text) {
		return repository.findByTitleContainingIgnoreCase(text);
	}
	*/
	
	//Método 2 (com a query personalizada):
	public List<Post> findByTitle(String text) {
		return repository.searchTitle(text);
	}
}
