package com.thirdparty.api.call.clients.impl;

import com.thirdparty.api.call.advices.ApiResponse;
import com.thirdparty.api.call.clients.DepartmentClient;
import com.thirdparty.api.call.dto.DepartmentDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentClientImpl implements DepartmentClient {

    private final RestClient restClient;

    Logger log = LoggerFactory.getLogger(DepartmentClientImpl.class);

    @Override
    public List<DepartmentDto> getAllDepartment() {
        log.trace("Trying to retrieve all department in getAllDepartment");
        try {
            ApiResponse<List<DepartmentDto>> departmentDtoList = restClient.get()
                                        .uri("department")
                                        .retrieve()
                                        .body(new ParameterizedTypeReference<>() {
                                        });
            log.debug("Successfully retrieved the department in getAllDepartment");
            log.trace("Retrieved list of department in getAllDepartment : {}",departmentDtoList.getData());
            return departmentDtoList.getData();
        } catch (Exception e) {
            log.error("Exception occurred in getAllDepartment",e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public DepartmentDto getDepartmentById(long id) {

        try {
            ApiResponse<DepartmentDto> departmentResponse = restClient.get()
                    .uri("department/{id}", id)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });
            return departmentResponse.getData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentdto) {
        try {
            ApiResponse<DepartmentDto> departmentDtoApiResponse = restClient.post()
                    .uri("/department/save")
                    .body(departmentdto)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                        System.out.println(new String(res.getBody().readAllBytes()));
                        throw new RuntimeException("Could not create the department");
                    })
                    .body(new ParameterizedTypeReference<>() {
                    });

            return departmentDtoApiResponse.getData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
