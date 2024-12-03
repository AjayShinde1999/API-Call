package com.thirdparty.api.call.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class DepartmentDto {

    private Long id;
    private String title;
    private boolean active;
    private LocalDate createdAt;

}
