package mk.ukim.finki.roomie.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mk.ukim.finki.roomie.model.Comment;
import mk.ukim.finki.roomie.repository.CommentRepository;
import mk.ukim.finki.roomie.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	CommentRepository commentRepository;

	public Comment getCommentById(Long id) {
		// TODO Auto-generated method stub
		return commentRepository.getCommentById(id);
	}

	public List<Comment> getAllComments(Long rental_id) {
		// TODO Auto-generated method stub
		return commentRepository.findAll(rental_id);
	}

	public Comment storeComment(Comment comment) {
		// TODO Auto-generated method stub
		return commentRepository.saveOrUpdate(comment);
	}

	public Comment updateComment(Comment comment) {
		// TODO Auto-generated method stub
		return commentRepository.saveOrUpdate(comment);
	}

	public void deleteComment(Long id) {
		commentRepository.delete(id);
		
	}

}
