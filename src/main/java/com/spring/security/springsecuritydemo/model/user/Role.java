package com.spring.security.springsecuritydemo.model.user;

/*
Enum for indicating roles and rights of people in DB
 */


import com.spring.security.springsecuritydemo.model.user.Permission;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    USER(Set.of(Permission.DEVELOPERS_READ)),
    ADMIN(Set.of(Permission.DEVELOPERS_CHANGE, Permission.DEVELOPERS_READ));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }


    /*
    В классе UserDetails в Spring Security есть GrantedAuthority, что и является в Спринге правами.
    SimpleGrantedAuthority позволяет определить Security кто и к чему имеет доступ
    Нам требуется конвертировать наши permissions в эти SimpleGrantedAuthority
     */
    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}