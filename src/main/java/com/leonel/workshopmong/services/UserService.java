package com.leonel.workshopmong.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonel.workshopmong.DTO.UserDTO;
import com.leonel.workshopmong.domain.User;
import com.leonel.workshopmong.repository.UserRepository;
import com.leonel.workshopmong.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public Optional<User> findById(String id) {
		Optional<User> user = repository.findById(id);
		if (user == null) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		}
		return user;
	}
	
	public User insert(User obj) {
		return repository.insert(obj);
	}
	
	public void delete (String id) {
		findById(id);
		repository.deleteById(id);
	}
	
	// Poderia ter feito na própria classe UserDTO para manter o propósito único. Mas como pode haver necessidades de acesso ao
	// repository, foi preferível fazer aqui.
	public User fromDTO(UserDTO objDTO) {
		return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
	}
}
