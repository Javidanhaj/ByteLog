package com.javidanhaj.bytelog.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class UpdateTagRequest {
    @NotBlank(message = "Tag name cannot be blank")
    @Size(max = 50, message = "Tag name cannot exceed 50 characters")
    private String name;
}
