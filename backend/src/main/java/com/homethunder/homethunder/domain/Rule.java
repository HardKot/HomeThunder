package com.homethunder.homethunder.domain;


public enum Rule {
    // Работа с пользователями
    userDelete,
    userBlocked,
    userChange,
    userUnblocked,
    userRoleAdded,
    userRoleRemoved,
    userRuleSet,

    userRoleShow,

    // Работа с ролями
    roleCreated,
    roleChange,
    roleDeleted,

    // Работа с новостями
    postCreated,
    postChange,
    postDeleted,


    // Работа с категориями новостей
    postCategoryCreated,
    postCategoryChange,
    postCategoryDeleted,


    // Работа с голосованием
    postContentPoolingCreated,
    postContentPoolingChange,
    postContentPooingDeleted,
}
