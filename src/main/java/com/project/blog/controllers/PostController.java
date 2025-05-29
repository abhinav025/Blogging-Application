package com.project.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.blog.entities.Post;
import com.project.blog.payloads.ApiResponse;
import com.project.blog.payloads.PostDto;
import com.project.blog.services.PostService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	//-- create
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId){
		
		PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
	}
	
	//-- get by userr
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
		List<PostDto> posts = this.postService.getPostsByUser(userId);
		
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
	//-- get by catgory
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getCategoryByUser(@PathVariable Integer categoryId){
		List<PostDto> posts = this.postService.getPostsByCategory(categoryId);
		
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}
	
	//-- get all post
	@GetMapping("/posts")
	public ResponseEntity<List<PostDto>> getAllPost(){
		List<PostDto> allPost = this.postService.getAllPost();
		return new ResponseEntity<List<PostDto>>(allPost,HttpStatus.OK);
	}
	
	//-- get post details by ID
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> singlePostById(@PathVariable Integer postId){
		 PostDto postById = this.postService.getPostById(postId);
		 return new ResponseEntity<PostDto>(postById,HttpStatus.OK);
	}
	
	//-- delete post
	@DeleteMapping("/posts/{postId}")
	public ApiResponse deletePost(@PathVariable Integer postId) {
		this.postService.deletePost(postId);
		
		return new ApiResponse("Post deleted succesfully", true);
	}
	
	//-- update post
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId){
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}

}
