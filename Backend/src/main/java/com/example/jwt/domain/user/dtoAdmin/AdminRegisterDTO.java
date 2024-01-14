package com.example.jwt.domain.user.dtoAdmin;

import com.example.jwt.core.generic.ExtendedDTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class AdminRegisterDTO extends ExtendedDTO {
    @NotNull(message = "{m.firstname}")
    private String firstName;
    @NotNull(message = "{m.lastname}")
    private String lastName;

    @NotNull(message = "{m.email}")
    @Email(message = "{m.format.email}")
    private String email;
    @NotNull(message = "{m.password}")
    private String password;


    public AdminRegisterDTO(UUID id, String firstName, String lastName, String email, String password) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
    public AdminRegisterDTO(){}

    public String getFirstName() {
        return firstName;
    }

    public AdminRegisterDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return  this;
    }

    public String getLastName() {
        return lastName;
    }

    public AdminRegisterDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AdminRegisterDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public AdminRegisterDTO setPassword(String password) {
        this.password = password;
        return this;
    }
}
