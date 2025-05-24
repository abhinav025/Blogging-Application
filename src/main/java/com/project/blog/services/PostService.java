package com.project.blog.services;

import java.util.List;

import com.project.blog.entities.Post;
import com.project.blog.payloads.PostDto;

public interface PostService {
	
	//-- create
	Post createPost(PostDto postDto);
	
	//-- update
	Post updatePost(PostDto postDto, Integer postId);
	
	//-- delete
	void deletePost(Integer postId);
	
	//-- get all
	List<Post> getAllPost();
	
	//-- get single post
	Post getPostById(Integer postId);
	
	//-- get all posts by Category
	List<Post> getPostsByCategory(Integer categoryId);
	
	//-- get all posts by User
	List<Post> getPostsByUser(Integer userId);
	
	

}
