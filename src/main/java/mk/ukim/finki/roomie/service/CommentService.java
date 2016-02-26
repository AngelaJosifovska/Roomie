package mk.ukim.finki.roomie.service;

import java.util.List;

import mk.ukim.finki.roomie.model.Comment;
import mk.ukim.finki.roomie.model.RentalUnit;

public interface CommentService {
	
	public Comment getCommentByRentalUnitID(Integer id);
		
	public List<Comment> getAllCommentsForRentalUnit(Integer rental_id);
	
    public List<Comment> getAllCommentsForRentalUnit(int rental_id,int page, int maxResults);
	
	public Long getTotal(int rental_id);
	
	public Comment storeComment(Comment comment);
	
	public Comment updateComment(Comment comment);
	
	public void deleteComment(Integer id);


}
