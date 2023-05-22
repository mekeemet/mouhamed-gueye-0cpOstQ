package com.anywr.javasprintsecuritytest.Controller;

import com.anywr.javasprintsecuritytest.Entity.Student;
import com.anywr.javasprintsecuritytest.EntityDto.SearchStudentDto;
import com.anywr.javasprintsecuritytest.EntityDto.StudentDto;
import com.anywr.javasprintsecuritytest.Mapper.MapStructMapper;
import com.anywr.javasprintsecuritytest.Service.Interface.StudentService;
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
@RequestMapping("/api/student")
@Tag(description = " Classe ressource REST Endpoint", name = "Classe Controller")
@Slf4j
public class StudentController {
    private final MapStructMapper mapstructMapper;
    private final StudentService studentService;

    public StudentController(MapStructMapper mapstructMapper, StudentService studentService) {
        this.mapstructMapper = mapstructMapper;
        this.studentService = studentService;
    }


    @Operation(summary = "get all Student", description = "all Student")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found", content = {}),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @GetMapping()
    public ResponseEntity allStudents(
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
            log.info("Starting get Student");
            Pageable pageable = Utils.getPageable(page, perPage, orderBy, direction);
            Page<Student> result = studentService.getStudent(pageable);
            return ResponseEntity.status(HttpStatus.OK).body(result.
                    map(mapstructMapper::studentToStudentDto));
        } catch (RuntimeException e) {
            log.error("Error while retrieving Student ", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error while retrieving Student");
        }
    }

    @Operation(summary = "search Student ", description = "search Student")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found", content = {}),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @PostMapping(value = "search")
    public ResponseEntity searchStudent(@RequestBody SearchStudentDto searchStudentDto) {
        log.info("Starting searching Student");
        try {
            Pageable pageable = Utils.getPageable(searchStudentDto.getPage(), searchStudentDto.getPerPage(), searchStudentDto.getOrderBy(), searchStudentDto.getDirection());
            Page<Student> result = studentService.searchStudent(searchStudentDto,pageable);
            return ResponseEntity.status(HttpStatus.OK).body(result.
                    map(mapstructMapper::studentToStudentDto));
        } catch (RuntimeException e) {
            log.error("Error during search Student", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error during searching Student");
        }
    }

    @Operation(summary = "save Student ", description = "save Student")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found", content = {}),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @PostMapping()
    public ResponseEntity saveStudent(@RequestBody StudentDto studentDto) {
        log.info("Starting creating Student");
        Student student = mapstructMapper.studentDtoToStudent(studentDto);
        try {
            Student result = studentService.createStudent(student);
            return ResponseEntity.status(HttpStatus.CREATED).body(mapstructMapper.studentToStudentDto(result));
        } catch (RuntimeException e) {
            log.error("Error during creation Student", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error during creation Student");
        }
    }


    @Operation(summary = "update Student ", description = "update Student")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found", content = {}),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @PutMapping(value = "/{id}")
    public ResponseEntity updateParam(@RequestBody StudentDto studentDto, @PathVariable Long id) {
        log.info("Starting update Classe");
        Student student = mapstructMapper.studentDtoToStudent(studentDto);
        try {
            Student result = studentService.updateStudent(student, id);
            return ResponseEntity.status(HttpStatus.OK).body(mapstructMapper.studentToStudentDto(result));
        } catch (RuntimeException e) {
            log.error("Error during update Student", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error during update Student");
        }
    }

    @Operation(summary = "Delete Student ", description = "Delete Student")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found", content = {}),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @DeleteMapping(value = "{id}")
    public ResponseEntity deleteStudent(@PathVariable Long id) {
        log.info("Starting delete Student");
        try {
            studentService.deleteStudent(id);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (RuntimeException e) {
            log.error("Error during deleted Student", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error during deleted Student");
        }
    }

}
