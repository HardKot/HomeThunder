package com.homethunder.homethunder.domain.post;

import com.homethunder.homethunder.domain.BaseEntity;
import jakarta.persistence.Entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;

@Entity
@Getter
@Setter
@Table(name = "post_categories")
public class PostCategory extends BaseEntity {
    @NotEmpty
    @Max(255)
    private String name;

    private String description;

    @Max(255)
    private String icon;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @Nullable
    private PostCategory parent;

    public PostCategory() {

    }

    public PostCategory(String name) {
        this.name = name;

    }

//    @Override
//    public boolean getIsDeleted() {
//        if (parent != null && parent.getIsDeleted()) return true;
//        return super.getIsDeleted();
//    }

    static PostCategory NoCategory = new PostCategory("No Category");
}
