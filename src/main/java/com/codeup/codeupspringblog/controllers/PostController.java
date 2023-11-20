package com.codeup.codeupspringblog.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    @GetMapping("/post")
    @ResponseBody
    public String post () {
        return "posts index page";
    }

    @GetMapping("/post/{id}")
    @ResponseBody
    public String postId (@PathVariable String id) {
        return "view an individual post from " + id;
    }

    @GetMapping("/post/create")
    @ResponseBody
    public String postView () {
        return "view the form for creating a post";
    }

    @PostMapping("/post/create")
    @ResponseBody
    public String postCreate () {
        return "Create post";
    }


}
