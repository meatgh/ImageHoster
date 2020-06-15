package ImageHoster.service;

import ImageHoster.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.stream.events.Comment;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    //Call the getAllComments() method in the Repository and obtain a List of all the images in the database
    public List<Comment> getAllComments() {
        return commentRepository.getAllComments();
    }


    //The method calls the createComment() method in the Repository and passes the image to be persisted in the database
    public void createComment(Comment comment) {
        commentRepository.createComment(comment);
        System.out.println("New Comment: "+comment);
    }

}
