package com.homethunder.homethunder.domain.post;


import com.homethunder.homethunder.domain.BaseEntity;
import com.homethunder.homethunder.domain.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.annotation.Nullable;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Post extends BaseEntity {
    @NotEmpty
    @Size(min = 10, max = 255)
    private String title;


    private String content;

    @Max(255)
    @Nullable
    private String previewImg;

    @Nullable
    @Max(255)
    private String previewText;



    @ManyToOne
    @JoinColumn(name = "author_id")
    @NotNull
    private User author;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @Nullable
    private PostCategory category;

    public PostCategory getCategory() {
        if (category == null) {
            return PostCategory.NoCategory;
        }
        return category;
    }

//    boolean getIsDeleted() {
//        if (category.getIsDeleted()) return true;
//        return super.getIsDeleted();
//    }

    public String getPreviewText() {
        if (previewText == null) {
            return content.substring(0, 100) + "...";
        }
        return previewText;
    }
}
