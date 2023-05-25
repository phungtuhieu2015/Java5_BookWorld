package com.poly.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class user {
    @NotBlank(message = "Không được để trống tài khoản")
    String id;
    @NotBlank(message = "Không được để trống mật khẩu")
    String password;
    @NotBlank(message = "Không được để trống email")
    String email;
    @NotBlank(message = "Không được để trống họ và tên")
    String fullName;

}
