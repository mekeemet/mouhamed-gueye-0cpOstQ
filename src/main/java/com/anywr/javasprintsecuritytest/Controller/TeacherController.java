package com.anywr.javasprintsecuritytest.Controller;

import com.anywr.javasprintsecuritytest.Entity.Teacher;
import com.anywr.javasprintsecuritytest.EntityDto.TeacherDto;
import com.anywr.javasprintsecuritytest.Mapper.MapStructMapper;
import com.anywr.javasprintsecuritytest.Service.Interface.TeacherService;
import com.anywr.javasprintsecuritytest.Utils.Utils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teacher")
@Tag(description = " Teacher ressource REST Endpoint", name = "Teacher Controller")
@Slf4j
public class TeacherController {
    private final MapStructMapper mapstructMapper;
    private final TeacherService teacherService;

    public TeacherController(MapStructMapper mapstructMapper, TeacherService teacherService) {
        this.mapstructMapper = mapstructMapper;
        this.teacherService = teacherService;
    }


    @Operation(summary = "get all Teacher", description = "all Teacher")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found", content = {}),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @GetMapping()
    public ResponseEntity allTeachers(
            @Parameter(name = "page",
                    description = "Page number to display") @RequestParam(
                    value = "page", required = false,
                    defaultValue = "0") int page,
            @Parameter(name = "perPage",
                    description = "Number of element to show by page") @RequestParam(
                    value = "perPage", required = false,
                    defaultValue = "25") int perPage,
            @Parameter(name = "orderBy",
                    description = "Name of the field to order") @RequestParam(
                    value = "orderBy", required = false,
                    defaultValue = "") String orderBy,
            @Parameter(name = "direction",
                    description = "Order ASC or DESC") @RequestParam(
                    value = "direction", required = false, defaultValue = "") String direction) {
        try {
            log.info("Starting get Teacher");
            Pageable pageable = Utils.getPageable(page, perPage, orderBy, direction);
            Page<Teacher> result = teacherService.getTeacher(pageable);
            return ResponseEntity.status(HttpStatus.OK).body(result.
                    map(mapstructMapper::teacherToTeacherDto));
        } catch (RuntimeException e) {
            log.error("Error while retrieving Teacher", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error while retrieving Teacher");
        }
    }

    @Operation(summary = "save Teacher ", description = "save Teacher")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "409", description = "Conflit", content = {}),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @PostMapping()
    public ResponseEntity saveTeacher(@RequestBody TeacherDto teacherDto) {
        log.info("Starting creating Teacher");
        Teacher teacher = mapstructMapper.teacherDtoToTeacher(teacherDto);
        try {
            Teacher result = teacherService.createTeacher(teacher);
            return ResponseEntity.status(HttpStatus.OK).body(mapstructMapper.teacherToTeacherDto(result));
        }
        catch (RuntimeException e) {
            log.error("Error during creation Teacher", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error during creation Teacher");
        }
    }


    @Operation(summary = "update Teacher ", description = "update Teacher")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "409", description = "Conflit", content = {}),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @PutMapping(value = "/{id}")
    public ResponseEntity updateTeacher(@RequestBody TeacherDto teacherDto, @PathVariable Long id) {
        log.info("Starting update Teacher");
        Teacher teacher = mapstructMapper.teacherDtoToTeacher(teacherDto);
        try {
            Teacher result = teacherService.updateTeacher(teacher, id);
            return ResponseEntity.status(HttpStatus.OK).body(mapstructMapper.teacherToTeacherDto(result));
        }
        catch (RuntimeException e) {
            log.error("Error during update Teacher", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error during update Teacher");
        }
    }

    @Operation(summary = "Delete Teacher", description = "Delete Teacher")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found", content = {}),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @DeleteMapping(value = "{id}")
    public ResponseEntity deleteTeacher(@PathVariable Long id) {
        log.info("Starting delete Teacher");
        try {
            teacherService.deleteTeacher(id);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (RuntimeException e) {
            log.error("Error during deleted Teacher", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error during deleted Teacher");
        }
    }

}
