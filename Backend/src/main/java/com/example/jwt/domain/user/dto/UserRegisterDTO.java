package com.example.jwt.domain.user.dto;

import com.example.jwt.core.generic.ExtendedDTO;
import com.example.jwt.domain.authority.Authority;
import com.example.jwt.domain.authority.dto.AuthorityDTO;
import com.example.jwt.domain.role.Role;
import com.example.jwt.domain.role.dto.RoleDTO;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class UserRegisterDTO extends ExtendedDTO {
@NotNull(message = "{m.ll}")
  private String firstName;
@NotNull(message = "Last name must not be null")
  private String lastName;

  @NotNull(message = "email is missing")
  @Email(message = "Write the email with a right format")
  private String email;
@NotNull(message = "Password is missing")
  private String password;

  @NotNull(message = "Address is missing")
  private String address;

  @NotNull(message = "Date of birth is missing")
  private LocalDate dateOfBirth;
  public UserRegisterDTO() {
  }

  public UserRegisterDTO(UUID id, String firstName, String lastName, String email,
      String password,String address,LocalDate dateOfBirth) {
    super(id);
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.address=address;
    this.dateOfBirth=dateOfBirth;
  }

  public String getFirstName() {
    return firstName;
  }

  public UserRegisterDTO setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public UserRegisterDTO setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
    return this;
  }

  public UserRegisterDTO setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public String getAddress() {
    return address;
  }

  public UserRegisterDTO setAddress(String address) {
    this.address = address;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public UserRegisterDTO setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public UserRegisterDTO setPassword(String password) {
    this.password = password;
    return this;
  }

}
