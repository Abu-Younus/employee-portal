package com.learntoyounus.domain;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    @NotEmpty(message = "The name is required!")
    @Size(min = 2, max = 100, message = "The length of name must be between 2 and 100")
    private String name;
    @NotEmpty(message = "The phone number is required!")
    private String phoneNumber;
    @NotEmpty(message = "The designation is required!")
    private String designation;
}
