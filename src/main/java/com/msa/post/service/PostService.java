package com.msa.post.service;

import com.msa.post.domain.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
	
	Post addPost(String title, String content);
	
	Optional<Post> getPost(int id);

	List<Post> getPostList();

	void delete(int id);
}
