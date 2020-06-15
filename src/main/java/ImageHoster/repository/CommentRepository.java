package ImageHoster.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.xml.stream.events.Comment;
import java.util.List;

@Repository
public class CommentRepository {



    //Get an instance of EntityManagerFactory from persistence unit with name as 'imageHoster'
    @PersistenceUnit(unitName = "imageHoster")
    private EntityManagerFactory emf;

    public javax.xml.stream.events.Comment createComment(ImageHoster.model.Comment newComment){

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(newComment);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }

        return (Comment) newComment;
    }

    public List<Comment> getAllComments(){

        EntityManager em = emf.createEntityManager();

        TypedQuery<Comment> query = em.createQuery("SELECT c from Comment c", Comment.class);
        List<Comment> resultList = (List) query.getResultList();
        return resultList;


    }

}
