package com.Hospital.Management.System.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {

//    private Long id;

    @NotBlank(message = "Username is mandatory")
    public String username;

    @NotBlank(message = "Password is mandatory")
    private String password;



    public @NotBlank(message = "Username is mandatory")
    String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "Username is mandatory") String username) {
        this.username = username;
    }

    public @NotBlank(message = "Password is mandatory") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password is mandatory") String password) {
        this.password = password;
    }
//    private String role; // Optionally, you could keep this as an enum if needed

}
