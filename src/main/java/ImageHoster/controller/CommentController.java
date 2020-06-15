package ImageHoster.controller;

import ImageHoster.model.Image;
import ImageHoster.model.User;
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

@Controller
public class CommentController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private TagService tagService;



    @RequestMapping(value="/image/{imageId}/{imageTitle}/comments",method= RequestMethod.POST)
    public String SubmitComment(@PathVariable("imageId")Integer imageId, @PathVariable("imageTitle")String imageTitle , @RequestParam(name="comment")String comment, HttpSession session, Model model)
    {






        Image image = imageService.getImage(imageId);
        User user = (User) session.getAttribute("loggeduser");





        return "images/imageID/imagetitle";





    }

    private String convertUploadedFileToBase64(MultipartFile file) throws IOException {
        return Base64.getEncoder().encodeToString(file.getBytes());
    }
}
