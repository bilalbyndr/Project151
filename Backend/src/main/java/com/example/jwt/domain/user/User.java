package com.example.jwt.domain.user;

import com.example.jwt.core.generic.ExtendedAuditEntity;
import com.example.jwt.domain.Purchases.Purchase;
import com.example.jwt.domain.role.Role;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.persistence.*;

@Entity
@Table(name = "users")
public class User extends ExtendedAuditEntity {

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "email", unique = true, nullable = false)
  private String email;
  @Enumerated(EnumType.STRING)
  private Rank rank;
  @Column(name = "password")
  private String password;

  // Date of Birth with type: LocalDate : example :"1990-03-15"
  @Column(name = "dateOfBirth")
  private LocalDate dateOfBirth;
  @OneToMany(mappedBy = "user")
  private List<Purchase> purchases;
  @Column(name = "address")
  private String address;

  // By crating (enum) of Rank
  @Column(name="seeds")
  private int seeds;
  @Column(name="totalSpent")
  private double totalSpend;
  @Column(name = "isActive", columnDefinition = "boolean default true")
  private boolean isActive;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
          name = "users_role",
          joinColumns = @JoinColumn(name = "users_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
  )
  private Set<Role> roles = new HashSet<>();

  public User() {
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }


  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public User(UUID id, String firstName, String lastName, String email, String password,
              Set<Role> roles, LocalDate dateOfBirth, String address,boolean isActive) {
    super(id);
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.roles = roles;
    this.dateOfBirth=dateOfBirth;
    this.address=address;
    this.isActive=isActive;
  }

  public boolean isActive() {
    return isActive;
  }

  public void setActive(boolean active) {
    isActive = active;
  }

  public int getSeeds() {
    return seeds;
  }

  public void setSeeds(int seeds) {
    this.seeds = seeds;
  }

  public double getTotalSpend() {
    return totalSpend;
  }

  public void setTotalSpend(double totalSpend) {
    this.totalSpend = totalSpend;
  }


  // defining the Ranks that can be easily referenced in code.
  public enum Rank {
    BRONZE, SILVER, GOLD, PLATINUM, DIAMOND
  }


  public Rank getRank() {
    return rank;
  }

  public void setRank(Rank rank) {
    this.rank = rank;
  }

  public String getFirstName() {
    return firstName;
  }

  public User setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public User setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public User setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public User setPassword(String password) {
    this.password = password;
    return this;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public User setRoles(Set<Role> roles) {
    this.roles = roles;
    return this;
  }
}
