package com.a2a.api.microservices.application.repository;

import com.a2a.api.microservices.application.domaine.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Integer> {

}
