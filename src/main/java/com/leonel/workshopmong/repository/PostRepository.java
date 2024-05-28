package com.leonel.workshopmong.repository;

import java.util.Date;
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
	
	// Vamos ressaltar que os parâmetros da função não são os dados aos quais quero obter na query, e sim parâmetros que definem características das quais quero buscar
	// Na busca de comentários, que está dentro da busca do texto, usamos comments.text pois o "comments" da classe Post se trata de uma lista de comentários. Sendo assim, se fez necessário acessar o seu texto com o .text.
	@Query("{ $and: [ { date: {$gte: ?1} }, { date: {$lte: ?2} }, { $or: [ { 'title': { $regex: ?0 , $options: 'i' } }, { 'body': { $regex: ?0 , $options: 'i' } }, { 'comments.text': { $regex: ?0 , $options: 'i' } } ] } ] }")
	List<Post> fullSearch(String text, Date minDate, Date maxDate);
}
