package mk.ukim.finki.roomie.service;

import java.util.List;

import mk.ukim.finki.roomie.model.Comment;

public interface CommentService {
	
	public Comment getCommentById(Long id);
		
	public List<Comment> getAllComments(Long rental_id);
	
	public Comment storeComment(Comment comment);
	
	public Comment updateComment(Comment comment);
	
	public void deleteComment(Long id);

}
