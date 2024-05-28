package com.homethunder.useCase.post;

import com.homethunder.domain.Rule;
import com.homethunder.domain.post.IPostGateway;
import com.homethunder.domain.post.Post;
import com.homethunder.domain.post.PostCategory;
import com.homethunder.domain.user.User;
import com.homethunder.useCase.post.dto.ICreatePostCategoryDTO;
import com.homethunder.useCase.post.dto.ICreatePostDTO;
import com.homethunder.useCase.post.dto.IUpdatePostDTO;
import com.leakyabstractions.result.api.Result;
import com.leakyabstractions.result.core.Results;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Setter
public class PostInteract {
    private IPostGateway postsGateway;

    public Result<Post, PostInteractError> create(User author, ICreatePostDTO dto) {
        if (author.hasAccess(Rule.postCreated)) return Results.failure(new PostInteractError.NotHaveAccess());
        Post post = new Post();
        post.setTitle(dto.title());
        post.setContent(dto.content());
        post.setAuthor(author);

        PostCategory category = postsGateway.getPostCategoryById(dto.postCategoryID()).orElse(null);
        if (category == null) return Results.failure(new PostInteractError.NotFoundPostCategory());
        post.setCategory(category);

        post.setDescription(dto.description());
        post.setDateTimePublish(dto.dateTimePublish());

        return Results.success(postsGateway.create(post));
    }

    public Result<Post, PostInteractError> update(Post post, IUpdatePostDTO dto) {
        post.setTitle(dto.title());
        post.setContent(dto.content());
        post.setDescription(dto.description());
        post.setImage(dto.image());
        post.setDateTimePublish(dto.dateTimePublish());
        post.setDeletedAt(null);

        PostCategory category = postsGateway.getPostCategoryById(dto.PostCategoryID()).orElse(null);
        if (category == null) return Results.failure(new PostInteractError.NotFoundPostCategory());
        post.setCategory(category);

        return Results.success(postsGateway.update(post));
    }

    public Result<Post, PostInteractError> delete(Post post) {
        post.setDeletedAt(LocalDateTime.now());
        return Results.success(postsGateway.update(post));
    }

    public Result<PostCategory, PostInteractError> createPostCategory(ICreatePostCategoryDTO dto) {
        if (postsGateway.getPostCategoryByName(dto.name()).isPresent()) return Results.failure(new PostInteractError.CategoryAlreadyExists());
        PostCategory parentPostCategory = postsGateway.getPostCategoryById(dto.parentCategoryID()).orElse(null);

        if (Objects.isNull(parentPostCategory)) return Results.failure(new PostInteractError.NotFoundPostCategory());
        if (Objects.nonNull(parentPostCategory.getParent())) return Results.failure(new PostInteractError.ManyParentInCategory());

        PostCategory postCategory = new PostCategory();

        postCategory.setName(dto.name());
        postCategory.setDescription(dto.description());
        postCategory.setImageID(dto.imageID());
        postCategory.setParent(parentPostCategory);

        return Results.success(postsGateway.savePostCategory(postCategory));
    }

    public Result<PostCategory, PostInteractError> updatePostCategory(PostCategory postCategory, ICreatePostCategoryDTO dto) {
        if (postsGateway.getPostCategoryByName(dto.name()).isPresent()) return Results.failure(new PostInteractError.CategoryAlreadyExists());
        PostCategory parentPostCategory = postsGateway.getPostCategoryById(dto.parentCategoryID()).orElse(null);

        if (Objects.isNull(parentPostCategory)) return Results.failure(new PostInteractError.NotFoundPostCategory());
        if (Objects.nonNull(parentPostCategory.getParent())) return Results.failure(new PostInteractError.ManyParentInCategory());
        if (postCategory.equals(parentPostCategory)) return Results.failure(new PostInteractError.RecursivePostCategory());

        postCategory.setName(dto.name());
        postCategory.setDescription(dto.description());
        postCategory.setImageID(dto.imageID());
        postCategory.setParent(parentPostCategory);

        return Results.success(postsGateway.updatePostCategory(postCategory));
    }

    public void deletePostCategory(PostCategory postCategory) {
        postsGateway.savePosts(postsGateway
                .getPostsByCategory(postCategory)
                .stream()
                .peek(post -> post.setDeletedAt(LocalDateTime.now()))
                .toList()
        );
        postsGateway.deletePostCategory(postCategory);
    }
}
