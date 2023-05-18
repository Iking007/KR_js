package com.example.serverBooksOnly.Model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    MODER_READ("management:read"),
    MODER_UPDATE("management:update"),
    MODER_CREATE("management:create"),
    MODER_DELETE("management:delete")

    ;

    @Getter
    private final String permission;
}
