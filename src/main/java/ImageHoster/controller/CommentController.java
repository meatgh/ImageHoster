package ImageHoster.controller;

import ImageHoster.model.Image;
import ImageHoster.model.User;
import  ImageHoster.model.Comment;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import ImageHoster.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CommentService commentService;



    @RequestMapping(value="/image/{imageId}/{imageTitle}/comments",method= RequestMethod.POST)
    public String SubmitComment(@PathVariable("imageId")Integer imageId, @PathVariable("imageTitle")String imageTitle , @RequestParam(name="comment")String comment, HttpSession session, Model model)
    {
        Comment commentToUpload = new Comment();
        Image image = image = imageService.getImageByTitle(imageTitle);
        User user = (User) session.getAttribute("loggeduser");


        commentToUpload.setText(comment);
        commentToUpload.setImage(image);
        commentToUpload.setCreatedDate(new Date());
        commentToUpload.setUser(user);

        commentService.createComment(commentToUpload);
        List<Comment> comments = image.getComments();
//        System.out.println(comments);
        comments.add(commentToUpload);
        image.setComments(comments);
       imageService.updateImage(image);

        model.addAttribute("image", image);
        model.addAttribute("tags", image.getTags());
        model.addAttribute("comments", image.getComments());




        return "images/image";

    }


}
