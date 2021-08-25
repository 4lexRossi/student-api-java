package com.alos.fpt.apifaculpratodos.dto.mapper;

import com.alos.fpt.apifaculpratodos.dto.request.StudentDTO;
import com.alos.fpt.apifaculpratodos.entities.Student;

import org.mapstruct.Mapper;
// import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    // @Mapping(target = "id")
    // Student toModel(StudentDTO dto);

    StudentDTO toDTO(Student student);
}
