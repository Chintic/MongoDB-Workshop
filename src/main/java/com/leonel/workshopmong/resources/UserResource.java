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
	
	//Aqui dá continuidade da URL da classe principal: /users/id
	//PathVariable diz que a variável que vem depois da annotation tem que casar com o que está contido na URL.
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id){
	    User user = service.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	    UserDTO userDTO = new UserDTO(user);
	    return ResponseEntity.ok().body(userDTO);
	}

	@PostMapping()
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDTO){
		User obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
	    return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id){
	    service.delete(id);
	    return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody UserDTO objDTO, @PathVariable String id){
		User obj = service.fromDTO(objDTO);
		obj.setId(id);
		obj = service.update(obj);
	    return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}/posts")
	public ResponseEntity<List<Post>> findPosts(@PathVariable String id){
	    User user = service.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	    return ResponseEntity.ok().body(user.getPosts());
	}
	
	
}
