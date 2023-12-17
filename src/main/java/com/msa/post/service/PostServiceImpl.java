package com.msa.post.service;

import com.msa.post.domain.Post;
import com.msa.post.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private PostMapper postMapper;
	@Override
	public Post addPost(String title, String content) {
		Post newPost = new Post(title, content);
		return postMapper.save(newPost);
	}

	@Override
	public Optional<Post> getPost(int id) {
		Optional<Post> post = postMapper.findById(id);
		if(post.isEmpty()){
			return null;
		}
		return post;
	}

	@Override
	public List<Post> getPostList() {
		return postMapper.findAll();
	}

	@Override
	public void delete(int id) {
		postMapper.delete(id);
	}

}
