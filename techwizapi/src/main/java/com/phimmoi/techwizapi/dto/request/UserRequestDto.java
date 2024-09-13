package com.phimmoi.techwizapi.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto {
    @Size(min = 3, message = "Tên ngắn quá")
    @Size(max = 16, message = "Tên dài quá")
    private String username;
    @Email(message = "Phải có định dạng email chứ")
    private String email;
    @Size(min = 3, message = "Pass ngắn quá")
    @Size(max = 16, message = "Pass dài quá")
    private String password;
}
