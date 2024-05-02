package com.homethunder.homethunder.domain.post;

import com.homethunder.homethunder.domain.user.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IPostsGateway {
    Post create(Post post);

    Post update(Post post);

    Post delete(UUID id);

    User getAuthor(UUID id);

    List<Post> getPosts(User user);

    Optional<PostCategory> getPostCategoryById(UUID id);
}
