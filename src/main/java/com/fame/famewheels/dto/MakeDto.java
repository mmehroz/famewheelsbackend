package com.fame.famewheels.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MakeDto {

    private int makeId;
    @NotBlank
    private String makeName;
    
}
