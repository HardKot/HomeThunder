package com.homethunder.homethunder.domain.post;

import com.homethunder.homethunder.domain.user.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IPostGateway {
    Post create(Post post);

    Post update(Post post);

    void deletePostCategory(PostCategory postCategory);

    User getAuthor(UUID id);

    List<Post> getPosts(User user);

    List<Post> getPostsByCategory(PostCategory category);

    Optional<PostCategory> getPostCategoryById(UUID id);

    Optional<PostCategory> getPostCategoryByName(String name);

    PostCategory savePostCategory(PostCategory postCategory);

    PostCategory updatePostCategory(PostCategory postCategory);

    List<Post> savePosts(List<Post> posts);
}
