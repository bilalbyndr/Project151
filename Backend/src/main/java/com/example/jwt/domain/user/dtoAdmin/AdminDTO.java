package com.example.jwt.domain.user.dtoAdmin;

import com.example.jwt.core.generic.ExtendedDTO;
import com.example.jwt.domain.role.dto.RoleDTO;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.Set;
import java.util.UUID;

public class AdminDTO extends ExtendedDTO {
    private String firstName;

    private String lastName;

    @Email
    private String email;
    @Valid
    private Set<RoleDTO> roles;

    public AdminDTO(UUID id, String firstName, String lastName, String email, Set<RoleDTO> roles) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = roles;
    }

    public AdminDTO(){}

    public String getFirstName() {
        return firstName;
    }

    public AdminDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public AdminDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AdminDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }

    public AdminDTO setRoles(Set<RoleDTO> roles) {
        this.roles = roles;
        return this;
    }
}
