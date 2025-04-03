package org.codenova.moneylog.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FindPasswordRequest {

    @Email
    private String email;
}
