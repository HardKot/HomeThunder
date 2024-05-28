package com.homethunder.useCase.post;

public sealed class PostInteractError {
    public static final class NotHaveAccess extends PostInteractError {}
    public static final class NotFoundPostCategory extends PostInteractError {}
    public static final class CategoryAlreadyExists extends PostInteractError {}
    public static final class ManyParentInCategory extends PostInteractError {}
    public static final class ParentNotFound extends PostInteractError {}
    public static final class RecursivePostCategory extends PostInteractError {}
}
