package org.codenova.moneylog.request;


import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AvailableCheckRequest {
    @Email
    private String email;
}
