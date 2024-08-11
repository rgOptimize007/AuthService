package com.security.authservice.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonSerialize
public class UserDto {

    private String userName;
    private String userPassword;
    private List<UserRoleDto> userRoleDtoList;

}