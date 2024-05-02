package com.homethunder.homethunder.useCase.post;

public sealed class PostInteractError {
    public static final class NotHaveAccess extends PostInteractError {}
    public static final class NotFoundPostCategory extends PostInteractError {}
}
