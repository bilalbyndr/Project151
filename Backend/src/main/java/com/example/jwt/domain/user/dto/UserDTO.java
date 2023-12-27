package com.example.jwt.domain.user.dto;

import com.example.jwt.core.generic.ExtendedDTO;
import com.example.jwt.domain.role.dto.RoleDTO;
import com.example.jwt.domain.user.User;

import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.Email;

public class UserDTO extends ExtendedDTO {

  private String firstName;

  private String lastName;

  @Email
  private String email;

  @Valid
  private Set<RoleDTO> roles;

  private String address;
  private int seeds;
  private double totalSpend;

  private User.Rank rank  ;
  public UserDTO() {
  }

  public UserDTO(UUID id, String firstName, String lastName, String email, Set<RoleDTO> roles, User.Rank rank,String address,double totalSpend,int seeds ) {
    super(id);
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.roles = roles;
    this.rank=rank;
    this.address=address;
    this.seeds=seeds;
    this.totalSpend=totalSpend;
  }

  public String getAddress() {
    return address;
  }

  public UserDTO setAddress(String address) {
    this.address = address;
    return this;
  }

  public int getSeeds() {
    return seeds;

  }

  public UserDTO setSeeds(int seeds) {
    this.seeds = seeds;
    return this;
  }

  public double getTotalSpend() {
    return totalSpend;
  }

  public UserDTO setTotalSpend(double totalSpend) {
    this.totalSpend = totalSpend;
    return this;
  }

  public String getFirstName() {
    return firstName;
  }

  public UserDTO setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public UserDTO setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public UserDTO setEmail(String email) {
    this.email = email;
    return this;
  }

  public Set<RoleDTO> getRoles() {
    return roles;
  }

  public UserDTO setRoles(Set<RoleDTO> roles) {
    this.roles = roles;
    return this;
  }
  public User.Rank getRank() {
    return rank;
  }

  public void setRank(User.Rank rank) {
    this.rank = rank;
  }
}
