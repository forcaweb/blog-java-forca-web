package net.forcaweb.entities;

import net.forcaweb.entities.enums.RoleUser;

public record RegisterDTO(String name, String email, String password, RoleUser roleUser) {

}
