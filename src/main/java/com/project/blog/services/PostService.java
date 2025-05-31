package com.project.blog.services;

import java.util.List;

import com.project.blog.entities.Post;
import com.project.blog.payloads.PostDto;
import com.project.blog.payloads.PostResponse;

public interface PostService {
	
	//-- create
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
	//-- update
	PostDto updatePost(PostDto postDto, Integer postId);
	
	//-- delete
	void deletePost(Integer postId);
	
	//-- get all
	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy);
	
	//-- get single post
	PostDto getPostById(Integer postId);
	
	//-- get all posts by Category
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	//-- get all posts by User
	List<PostDto> getPostsByUser(Integer userId);
	
	//-- search Posts
	List<Post> searchPosts(String keyword);

}
