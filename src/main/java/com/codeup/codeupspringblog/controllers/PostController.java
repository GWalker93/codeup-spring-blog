package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.repository.PostRepository;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repository.UserRepository;
import com.codeup.codeupspringblog.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    private final PostRepository postDao;

    private final UserRepository userDao;

    private EmailService emailService;

    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailService) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailService = emailService;
    }


    //Show all post in the index page
    @RequestMapping(path = "/posts", method = RequestMethod.GET)
    public String post(Model model) {
        model.addAttribute("postList", postDao.findAll());
        return "posts/index";
    }

    //Show a post based on the id
    @RequestMapping(path = "/post/{id}", method = RequestMethod.GET)
    public String getPostId(@PathVariable long id, Model model) {
        Post post = postDao.getPostById(id);
        model.addAttribute("post", post);
        return "posts/show";
    }

    //Show the page to create a post
    @GetMapping(path = "/post/create")
    public String getCreatePost(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    //Create a post
    @PostMapping(path = "/post/create")
    public String postCreate(@ModelAttribute Post post) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.getReferenceById(loggedInUser.getId());
        post.setUser(user);
        postDao.save(post);
        emailService.prepareAndSend(post,"Post Created","Hello,your post has been created!");
        return "redirect:/posts";
    }

    //Show the page to edit a post
    @GetMapping(path = "/post/{id}/edit")
    public String getEditPost(@PathVariable long id, Model model) {
        Post post = postDao.getPostById(id);
        model.addAttribute("post", post);
        return "posts/edit";
    }

    @PostMapping(path = "/post/{id}/edit")
    public String editPost(@ModelAttribute Post post) {
        postDao.save(post);
        return "redirect:/posts";
    }


}
