package mk.ukim.finki.roomie.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mk.ukim.finki.roomie.model.Comment;
import mk.ukim.finki.roomie.repository.CommentRepository;
import mk.ukim.finki.roomie.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentRepository commentRepository;

	public Comment getCommentByRentalUnitID(Integer id) {
		return commentRepository.getCommentById(id);
	}

	public List<Comment> getAllCommentsForRentalUnit(Integer rental_id) {
		return commentRepository.findAll(rental_id);
	}

	public Comment storeComment(Comment comment) {
		return commentRepository.saveOrUpdate(comment);
	}

	public Comment updateComment(Comment comment) {
		return commentRepository.saveOrUpdate(comment);
	}

	public void deleteComment(Integer id) {
		commentRepository.delete(id);
	}

	public List<Comment> getAllCommentsForRentalUnit(int rental_id,	int page, int maxResults) {
		return commentRepository.findAll(rental_id, page, maxResults);
	}

	public Long getTotal(int rental_id) {
		return commentRepository.getTotal(rental_id);
	}

}
