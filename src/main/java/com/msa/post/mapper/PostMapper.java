package com.msa.post.mapper;

import com.msa.post.domain.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PostMapper {
    Post save(@Param("post") Post post);
    Optional<Post> findById(@Param("id") int id);
    
    List<Post> findAll();
    void delete(@Param("id") int id);
}
