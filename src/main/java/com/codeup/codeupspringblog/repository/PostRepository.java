package com.codeup.codeupspringblog.repository;

import com.codeup.codeupspringblog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository  extends JpaRepository <Post, Long> {

    Post getPostById(long id);

}
