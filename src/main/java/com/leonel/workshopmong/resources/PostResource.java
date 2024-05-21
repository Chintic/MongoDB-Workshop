package com.leonel.workshopmong.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.leonel.workshopmong.DTO.UserDTO;
import com.leonel.workshopmong.domain.Post;
import com.leonel.workshopmong.domain.User;
import com.leonel.workshopmong.services.PostService;
import com.leonel.workshopmong.services.exception.ObjectNotFoundException;

@RestController
@RequestMapping(value ="/posts")
public class PostResource {
	
	@Autowired
	private PostService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id){
	    Post post = service.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	    return ResponseEntity.ok().body(post);
	}
	
}
