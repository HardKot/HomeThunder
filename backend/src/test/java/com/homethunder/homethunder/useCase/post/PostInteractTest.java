package com.homethunder.homethunder.useCase.post;

import com.homethunder.homethunder.domain.post.IPostGateway;
import com.homethunder.homethunder.domain.post.Post;
import com.homethunder.homethunder.domain.user.User;
import com.homethunder.homethunder.useCase.post.dto.ICreatePostDTO;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.UUID;

class PostInteractTest {
    private final PostInteract postInteract = new PostInteract();
    private IPostGateway postGateway;
    private Post testPost;

    @BeforeEach
    void generateGateway() {
        postGateway = Mockito.mock(IPostGateway.class);

        Mockito.when(postGateway.create(Mockito.any(Post.class))).thenAnswer(i -> i.getArgument(0));
        Mockito.when(postGateway.update(Mockito.any(Post.class))).thenAnswer(i -> i.getArgument(0));
    }

    @BeforeEach
    void generateTestPost() {
        testPost = new Post();
        testPost.setTitle("Test Post");
        testPost.setContent("Test Content");
        testPost.setDescription("Test Description");
        testPost.setDateTimePublish(LocalDateTime.now());
        testPost.setImage(UUID.randomUUID());
        testPost.setAuthor(new User());
    }

    ICreatePostDTO createPostDTO() {
        ICreatePostDTO createPostDTO = Mockito.mock(ICreatePostDTO.class);
        Mockito.when(createPostDTO.title()).thenReturn("Test Post");
        Mockito.when(createPostDTO.content()).thenReturn("Content");
        Mockito.when(createPostDTO.description()).thenReturn("Test Description");
        Mockito.when(createPostDTO.image()).thenReturn(UUID.randomUUID());
        Mockito.when(createPostDTO.dateTimePublish()).thenReturn(null);
        Mockito.when(createPostDTO.postCategoryID()).thenReturn(UUID.randomUUID());

        return createPostDTO;
    }
}
