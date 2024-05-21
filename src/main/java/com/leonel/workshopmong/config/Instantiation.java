package com.leonel.workshopmong.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.leonel.workshopmong.DTO.AuthorDTO;
import com.leonel.workshopmong.domain.Post;
import com.leonel.workshopmong.domain.User;
import com.leonel.workshopmong.repository.PostRepository;
import com.leonel.workshopmong.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		repository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		//Se não salvarmos os usuários antes de salvar os posts, os usuários aninhados aos posts não terão seus ids corretamente associados
		repository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo. Abraços", new AuthorDTO(maria));
		Post post2 = new Post (null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje.", new AuthorDTO(maria));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		repository.save(maria);
	}

}
