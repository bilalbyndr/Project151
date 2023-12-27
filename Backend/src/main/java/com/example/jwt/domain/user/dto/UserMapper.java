package com.example.jwt.domain.user.dto;

import com.example.jwt.core.generic.ExtendedMapper;
import com.example.jwt.domain.role.dto.RoleDTO;
import com.example.jwt.domain.user.User;
import com.example.jwt.domain.user.dtoAdmin.AdminDTO;
import com.example.jwt.domain.user.dtoAdmin.AdminRegisterDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends ExtendedMapper<User, UserDTO> {

  User fromUserRegisterDTO(UserRegisterDTO dto);
  User fromDTO(UserDTO dto);
  UserDTO toDTO(User BO);
  AdminDTO toAdminDTO(User BO);
  User fromAdminRegisterDTO(AdminRegisterDTO dto);
  List<UserDTO> toDTOs(List<User> BOs);
}
