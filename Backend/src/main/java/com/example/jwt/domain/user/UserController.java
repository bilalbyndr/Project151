package com.example.jwt.domain.user;

import com.example.jwt.core.security.CustomAuthenticationFilter;
import com.example.jwt.domain.authority.Authority;
import com.example.jwt.domain.authority.AuthorityRepository;
import com.example.jwt.domain.role.Role;
import com.example.jwt.domain.role.RoleRepository;
import com.example.jwt.domain.user.dto.UserDTO;
import com.example.jwt.domain.user.dto.UserMapper;
import com.example.jwt.domain.user.dto.UserRegisterDTO;

import java.util.*;
import java.util.stream.Collectors;
import javax.validation.Valid;

import com.example.jwt.domain.user.dtoAdmin.AdminDTO;
import com.example.jwt.domain.user.dtoAdmin.AdminRegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasAuthority;

@Validated
@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  private final UserMapper userMapper;
  private RoleRepository roleRepository;
  private AuthorityRepository authorityRepository;

  @Autowired
  public UserController(UserService userService, UserMapper userMapper, RoleRepository roleRepository
  , AuthorityRepository authorityRepository) {
    this.userService = userService;
    this.userMapper = userMapper;
    this.roleRepository =roleRepository;
    this.authorityRepository=authorityRepository;

  }

  @GetMapping("/{id}")
  @PreAuthorize("hasRole('CAN_RETRIEVE_USERS')")
  public ResponseEntity<UserDTO> retrieveById(@PathVariable UUID id) {
    User user = userService.findById(id);
    return new ResponseEntity<>(userMapper.toDTO(user), HttpStatus.OK);
  }


  @GetMapping({"", "/"})
  @PreAuthorize("hasAuthority('CAN_RETRIEVE_USERS')")
  public ResponseEntity<List<UserDTO>> retrieveAll() {
    List<User> users = userService.findAll();
    return new ResponseEntity<>(userMapper.toDTOs(users), HttpStatus.OK);
  }

//Get the customer details by just injecting jwt in the header
  @GetMapping("/profile")

  public ResponseEntity<UserDTO> getMyUserId(@AuthenticationPrincipal UserDetailsImpl userDetails) {
    User user=userService.findById(userDetails.getId());

      return new ResponseEntity<>(userMapper.toDTO(user), HttpStatus.OK);
  }


  //A1: Register
  @PostMapping("/register")
  public ResponseEntity<UserDTO> register(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {
      //Take the varaibel for  the Prameter (UserRegisterDTO) and creating uer object as type User using fromUserRegisterDTO
   User user= userMapper.fromUserRegisterDTO(userRegisterDTO);
   //Searching for Role of name CLIENT if existing, if not creat a ("ROLE_CLIENT") with the proveiding authorities
    Role customerRole = roleRepository.findByName("ROLE_CLIENT")
            .orElseGet(() -> {
              Role newRole = new Role();
              newRole.setName("ROLE_CLIENT");
              return roleRepository.save(newRole);
            });
    Set<Authority> authorities = new HashSet<>();
    String[] authorityNames = {"CAN_PLACE_ORDER", "CAN_RETRIEVE_PURCHASE_HISTORY", "CAN_RETRIEVE_PRODUCTS"};
    for (String name : authorityNames) {
      Authority authority = authorityRepository.findByName(name)
              .orElseGet(() -> {
                Authority newAuthority = new Authority();
                newAuthority.setName(name);
                return authorityRepository.save(newAuthority);
              });
      authorities.add(authority);
    }

    // when a user is created the user_rank is Bronze first, Rank:Bronze was assigned as default for a new Client
    user.setRank(User.Rank.BRONZE);
    // Associate authorities with the role
    customerRole.setAuthorities(authorities);
    // Assign the role to the user
    user.setRoles(Collections.singleton(customerRole));
    User f = userService.register(user);
    return new ResponseEntity<>(userMapper.toDTO(f), HttpStatus.CREATED);
  }


    @PostMapping("/registerAdmin")
    public ResponseEntity<AdminDTO> registerAdmin(@Valid @RequestBody AdminRegisterDTO userRegisterDTO) {

        User user= userMapper.fromAdminRegisterDTO(userRegisterDTO);
        Role customerRole = roleRepository.findByName("ROLE_ADMIN")
                .orElseGet(() -> {
                    Role newRole = new Role();
                    newRole.setName("ROLE_ADMIN");
                    return roleRepository.save(newRole);
                });
        Set<Authority> authorities = new HashSet<>();
        String[] authorityNames = {"USER_DELETE", "USER_MODIFY", "CAN_RETRIEVE_PRODUCTS","CAN_RETRIEVE_USERS"};
        for (String name : authorityNames) {
            Authority authority = authorityRepository.findByName(name)
                    .orElseGet(() -> {
                        Authority newAuthority = new Authority();
                        newAuthority.setName(name);
                        return authorityRepository.save(newAuthority);
                    });
            authorities.add(authority);
        }

        // Associate authorities with the role
        customerRole.setAuthorities(authorities);



        // Assign the role to the user
        user.setRoles(Collections.singleton(customerRole));

        User f = userService.register(user);


        return new ResponseEntity<>(userMapper.toAdminDTO(f), HttpStatus.CREATED);
    }
  @PutMapping("/{id}")
  @PreAuthorize("hasAuthority('USER_MODIFY') && @userPermissionEvaluator.isUserAboveAge(authentication.principal.user,18)")
  public ResponseEntity<UserDTO> updateById(@PathVariable UUID id,
      @Valid @RequestBody UserDTO userDTO) {
    User user = userService.updateById(id, userMapper.fromDTO(userDTO));
    return new ResponseEntity<>(userMapper.toDTO(user), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('USER_DELETE')")
  public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
    userService.deleteById(id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }


}
