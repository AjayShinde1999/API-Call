package com.thirdparty.api.call;

import com.thirdparty.api.call.clients.DepartmentClient;
import com.thirdparty.api.call.dto.DepartmentDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class ThirdpartyApiCallApplicationTests {

    @Autowired
    private DepartmentClient departmentClient;

    @Test
    void getAllDepartment() {
        List<DepartmentDto> allDepartment = departmentClient.getAllDepartment();
        System.out.println(allDepartment);
    }

    @Test
    void getDepartmentById() {
        DepartmentDto departmentById = departmentClient.getDepartmentById(1L);
        System.out.println(departmentById);
    }

    @Test
    void saveDepartment() {
        DepartmentDto dto = new DepartmentDto(null, "title 120", true, LocalDate.of(2023, 8, 12));
        DepartmentDto department = departmentClient.createDepartment(dto);
        System.out.println(department);
    }
}
