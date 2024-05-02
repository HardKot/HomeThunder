package com.homethunder.homethunder.useCase.post;

import com.homethunder.homethunder.domain.Rule;
import com.homethunder.homethunder.domain.post.IPostsGateway;
import com.homethunder.homethunder.domain.post.Post;
import com.homethunder.homethunder.domain.post.PostCategory;
import com.leakyabstractions.result.api.Result;
import com.leakyabstractions.result.core.Results;
import lombok.Setter;

public class PostInteract {
    @Setter
    private IPostsGateway postsGateway;

    public Result<Post, PostInteractError> create(CreatePostDTO dto) {
        if (dto.author().hasAccess(Rule.postCreated)) return Results.failure(new PostInteractError.NotHaveAccess());
        Post post = new Post();
        post.setTitle(dto.title());
        post.setContent(dto.content());
        post.setAuthor(dto.author());

        PostCategory category = postsGateway.getPostCategoryById(dto.PostCategoryID()).orElse(null);
        if (category != null) return Results.failure(new PostInteractError.NotFoundPostCategory());
        post.setDescription(dto.description());
        post.setDateTimePublish(dto.dateTimePublish());

        post = postsGateway.create(post);

        return Results.success(post);
    }
}
