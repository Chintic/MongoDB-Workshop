package com.leonel.workshopmong.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leonel.workshopmong.domain.Post;
import com.leonel.workshopmong.resources.util.URL;
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
	
	@GetMapping("/titlesearch")
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue="") String text){
	    text = URL.decodeParam(text);
	    List<Post> list = service.findByTitle(text);
	    return ResponseEntity.ok().body(list);
	}
	
}
