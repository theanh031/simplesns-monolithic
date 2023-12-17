package com.msa;

import com.msa.post.domain.Post;
import com.msa.post.repository.PostRepositoryCustom;
import com.msa.post.service.PostServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PostServiceTests {

    @Autowired
    private PostServiceImpl postService;

    @SpyBean
    private PostRepositoryCustom postRepository;

    @Captor
    private ArgumentCaptor<Post> captor;

    @Autowired
    private ApplicationContext context;

    @Test
    public void test_bean_post_repo() {
        assertNotNull(context.getBean("postRepositoryCustom"));
    }

    @Test
    public void test_post_add() {
        String testContent = "test content";
        String testTitle = "test title";
        postService.addPost(testTitle, testContent);
        verify(postRepository, atLeastOnce()).addPost(testTitle, testContent);
    }

    @Test
    public void test_get_post_by_id() {
        Post stubPost = new Post();
        stubPost.setId(1);
        stubPost.setTitle("getPostById");
        stubPost.setContent("getPostById");
        when(postRepository.getPost(1)).thenReturn(Optional.of(stubPost));

        Optional<Post> optPost = postService.getPost(1);

        assertTrue(optPost.isPresent());
        Post captoredPost = optPost.get();
        assertEquals("getPostById", captoredPost.getTitle());
        assertEquals("getPostById", captoredPost.getContent());
    }

    @Test
    public void test_get_all_post() {

        postRepository.addPost("title3", "content3");
        postRepository.addPost("title2", "content2");
        postRepository.addPost("title1", "content1");

        List<Post> posts = postService.getPostList();
        assertFalse(posts.isEmpty());
        assertEquals(3L, posts.get(0).getId());

        boolean isSorted = IntStream.range(0, posts.size() - 1)
                .noneMatch(i -> posts.get(i).getId() == posts.get(i + 1).getId());

        assertTrue(isSorted);
    }

    @Test
    public void test_delete_post() {

        postRepository.addPost("title3", "content3");
        postRepository.addPost("title2", "content2");
        postRepository.addPost("title1", "content1");

        postService.delete(1);

        assertFalse(postRepository.getPostList()
                .stream().anyMatch(post -> post.getId() == 1));
    }

}
