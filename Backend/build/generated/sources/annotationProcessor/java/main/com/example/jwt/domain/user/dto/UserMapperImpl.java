package com.example.jwt.domain.user.dto;

import com.example.jwt.domain.authority.Authority;
import com.example.jwt.domain.authority.dto.AuthorityDTO;
import com.example.jwt.domain.role.Role;
import com.example.jwt.domain.role.dto.RoleDTO;
import com.example.jwt.domain.user.User;
import com.example.jwt.domain.user.dtoAdmin.AdminDTO;
import com.example.jwt.domain.user.dtoAdmin.AdminRegisterDTO;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-31T01:51:05+0100",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.jar, environment: Java 17.0.9 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public List<User> fromDTOs(List<UserDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( dtos.size() );
        for ( UserDTO userDTO : dtos ) {
            list.add( fromDTO( userDTO ) );
        }

        return list;
    }

    @Override
    public Set<User> fromDTOs(Set<UserDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        Set<User> set = new LinkedHashSet<User>( Math.max( (int) ( dtos.size() / .75f ) + 1, 16 ) );
        for ( UserDTO userDTO : dtos ) {
            set.add( fromDTO( userDTO ) );
        }

        return set;
    }

    @Override
    public Set<UserDTO> toDTOs(Set<User> BOs) {
        if ( BOs == null ) {
            return null;
        }

        Set<UserDTO> set = new LinkedHashSet<UserDTO>( Math.max( (int) ( BOs.size() / .75f ) + 1, 16 ) );
        for ( User user : BOs ) {
            set.add( toDTO( user ) );
        }

        return set;
    }

    @Override
    public User fromUserRegisterDTO(UserRegisterDTO dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setId( dto.getId() );
        user.setDateOfBirth( dto.getDateOfBirth() );
        user.setAddress( dto.getAddress() );
        user.setFirstName( dto.getFirstName() );
        user.setLastName( dto.getLastName() );
        user.setEmail( dto.getEmail() );
        user.setPassword( dto.getPassword() );

        return user;
    }

    @Override
    public User fromDTO(UserDTO dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setId( dto.getId() );
        user.setAddress( dto.getAddress() );
        user.setSeeds( dto.getSeeds() );
        user.setTotalSpend( dto.getTotalSpend() );
        user.setRank( dto.getRank() );
        user.setFirstName( dto.getFirstName() );
        user.setLastName( dto.getLastName() );
        user.setEmail( dto.getEmail() );
        user.setRoles( roleDTOSetToRoleSet( dto.getRoles() ) );

        return user;
    }

    @Override
    public UserDTO toDTO(User BO) {
        if ( BO == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( BO.getId() );
        userDTO.setAddress( BO.getAddress() );
        userDTO.setSeeds( BO.getSeeds() );
        userDTO.setTotalSpend( BO.getTotalSpend() );
        userDTO.setFirstName( BO.getFirstName() );
        userDTO.setLastName( BO.getLastName() );
        userDTO.setEmail( BO.getEmail() );
        userDTO.setRoles( roleSetToRoleDTOSet( BO.getRoles() ) );
        userDTO.setRank( BO.getRank() );

        return userDTO;
    }

    @Override
    public AdminDTO toAdminDTO(User BO) {
        if ( BO == null ) {
            return null;
        }

        AdminDTO adminDTO = new AdminDTO();

        adminDTO.setId( BO.getId() );
        adminDTO.setFirstName( BO.getFirstName() );
        adminDTO.setLastName( BO.getLastName() );
        adminDTO.setEmail( BO.getEmail() );
        adminDTO.setRoles( roleSetToRoleDTOSet( BO.getRoles() ) );

        return adminDTO;
    }

    @Override
    public User fromAdminRegisterDTO(AdminRegisterDTO dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setId( dto.getId() );
        user.setFirstName( dto.getFirstName() );
        user.setLastName( dto.getLastName() );
        user.setEmail( dto.getEmail() );
        user.setPassword( dto.getPassword() );

        return user;
    }

    @Override
    public List<UserDTO> toDTOs(List<User> BOs) {
        if ( BOs == null ) {
            return null;
        }

        List<UserDTO> list = new ArrayList<UserDTO>( BOs.size() );
        for ( User user : BOs ) {
            list.add( toDTO( user ) );
        }

        return list;
    }

    protected Authority authorityDTOToAuthority(AuthorityDTO authorityDTO) {
        if ( authorityDTO == null ) {
            return null;
        }

        Authority authority = new Authority();

        authority.setId( authorityDTO.getId() );
        authority.setName( authorityDTO.getName() );

        return authority;
    }

    protected Set<Authority> authorityDTOSetToAuthoritySet(Set<AuthorityDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Authority> set1 = new LinkedHashSet<Authority>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( AuthorityDTO authorityDTO : set ) {
            set1.add( authorityDTOToAuthority( authorityDTO ) );
        }

        return set1;
    }

    protected Role roleDTOToRole(RoleDTO roleDTO) {
        if ( roleDTO == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( roleDTO.getId() );
        role.setName( roleDTO.getName() );
        role.setAuthorities( authorityDTOSetToAuthoritySet( roleDTO.getAuthorities() ) );

        return role;
    }

    protected Set<Role> roleDTOSetToRoleSet(Set<RoleDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Role> set1 = new LinkedHashSet<Role>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RoleDTO roleDTO : set ) {
            set1.add( roleDTOToRole( roleDTO ) );
        }

        return set1;
    }

    protected AuthorityDTO authorityToAuthorityDTO(Authority authority) {
        if ( authority == null ) {
            return null;
        }

        AuthorityDTO authorityDTO = new AuthorityDTO();

        authorityDTO.setId( authority.getId() );
        authorityDTO.setName( authority.getName() );

        return authorityDTO;
    }

    protected Set<AuthorityDTO> authoritySetToAuthorityDTOSet(Set<Authority> set) {
        if ( set == null ) {
            return null;
        }

        Set<AuthorityDTO> set1 = new LinkedHashSet<AuthorityDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Authority authority : set ) {
            set1.add( authorityToAuthorityDTO( authority ) );
        }

        return set1;
    }

    protected RoleDTO roleToRoleDTO(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDTO roleDTO = new RoleDTO();

        roleDTO.setId( role.getId() );
        roleDTO.setName( role.getName() );
        roleDTO.setAuthorities( authoritySetToAuthorityDTOSet( role.getAuthorities() ) );

        return roleDTO;
    }

    protected Set<RoleDTO> roleSetToRoleDTOSet(Set<Role> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleDTO> set1 = new LinkedHashSet<RoleDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Role role : set ) {
            set1.add( roleToRoleDTO( role ) );
        }

        return set1;
    }
}
