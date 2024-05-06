package com.homethunder.homethunder.domain.post;

import java.util.Optional;

public interface IPostTagGateway {
    PostTag create(PostTag postTag);
    PostTag update(PostTag postTag);
    void delete(PostTag postTag);

    Optional<PostTag> findByName(PostTag postTag);
}
