package com.leonel.workshopmong.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.leonel.workshopmong.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{
	
	// Spring já irá automaticamente implementar este método para fazer a query de busca
	List<Post> findByTitleContainingIgnoreCase(String text);

	// Esta é uma forma alternativa de procurar um dado pelo nome. Ela usa a query do MongoDB dentro do @Query.
	@Query("{ 'title': { $regex: ?0 , $options: 'i' } }")
	List<Post> searchTitle(String text);
	
}
