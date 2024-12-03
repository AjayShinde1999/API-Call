package com.thirdparty.api.call.clients;

import com.thirdparty.api.call.dto.DepartmentDto;

import java.util.List;

public interface DepartmentClient {

    List<DepartmentDto> getAllDepartment();

    DepartmentDto getDepartmentById(long id);

    DepartmentDto createDepartment(DepartmentDto dto);
}
