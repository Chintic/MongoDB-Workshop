package com.leonel.workshopmong.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leonel.workshopmong.DTO.UserDTO;
import com.leonel.workshopmong.domain.User;
import com.leonel.workshopmong.services.UserService;
import com.leonel.workshopmong.services.exception.ObjectNotFoundException;

@RestController
@RequestMapping(value ="/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	//Essa annotation reulta no mesmo que "@GetMapping". 
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll(){
		List<User> list = service.findAll();
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	/*@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> findById(@PathVariable String id){
		Optional<User> obj = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));
	}
	*/
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id){
	    User user = service.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	    UserDTO userDTO = new UserDTO(user);
	    return ResponseEntity.ok().body(userDTO);
	}

	
}
