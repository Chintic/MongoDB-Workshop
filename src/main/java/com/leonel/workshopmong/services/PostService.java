package com.leonel.workshopmong.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonel.workshopmong.domain.Post;
import com.leonel.workshopmong.domain.User;
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
	
}
