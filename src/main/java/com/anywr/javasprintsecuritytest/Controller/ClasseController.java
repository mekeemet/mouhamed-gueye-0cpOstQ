package com.anywr.javasprintsecuritytest.Controller;

import com.anywr.javasprintsecuritytest.Entity.Classe;
import com.anywr.javasprintsecuritytest.EntityDto.ClasseDto;
import com.anywr.javasprintsecuritytest.Mapper.MapStructMapper;
import com.anywr.javasprintsecuritytest.Service.Interface.ClasseService;
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

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/classe")
@Tag(description = " Classe ressource REST Endpoint", name = "Classe Controller")
@Slf4j
public class ClasseController {
    private final MapStructMapper mapstructMapper;
    private final ClasseService classeService;

    public ClasseController(MapStructMapper mapstructMapper, ClasseService classeService) {
        this.mapstructMapper = mapstructMapper;
        this.classeService = classeService;
    }


    @Operation(summary = "get all Classe", description = "all classe")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found", content = {}),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @GetMapping()
    public ResponseEntity allClasses(
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
            log.info("Starting get Classe");
            Pageable pageable = Utils.getPageable(page, perPage, orderBy, direction);
            Page<Classe> result = classeService.getClasse(pageable);
            return ResponseEntity.status(HttpStatus.OK).body(result.
                    map(mapstructMapper::classeToClasseDto));
        } catch (RuntimeException e) {
            log.error("Error while retrieving class ", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error while retrieving class");
        }
    }

    @Operation(summary = "save classe ", description = "save Classe")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found", content = {}),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @PostMapping()
    public ResponseEntity saveClasse(@RequestBody ClasseDto classeDto) {
        log.info("Starting creating Classe");
        Classe classe = mapstructMapper.classeDtoToClasse(classeDto);

        try {
            Classe result = classeService.createClasse(classe);
            return ResponseEntity.status(HttpStatus.CREATED).body(mapstructMapper.classeToClasseDto(result));
        } catch (RuntimeException e) {
            log.error("Error during creation classe", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error during creation classe");
        }
    }


    @Operation(summary = "update classe ", description = "update classe")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found", content = {}),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @PutMapping(value = "/{id}")
    public ResponseEntity updateClass(@RequestBody ClasseDto classeDto, @PathVariable Long id) {
        log.info("Starting update Classe");
        Classe classe = mapstructMapper.classeDtoToClasse(classeDto);
        try {
            Classe result = classeService.updateClasse(classe, id);
            return ResponseEntity.status(HttpStatus.OK).body(mapstructMapper.classeToClasseDto(result));
        } catch (RuntimeException e) {
            log.error("Error during update Classe", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error during update Classe");
        }
    }

    @Operation(summary = "Delete Classe ", description = "Delete classe")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found", content = {}),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @DeleteMapping(value = "{id}")
    public ResponseEntity deleteClass(@PathVariable Long id) {
        log.info("Starting delete classe");
        try {
            classeService.deleteClasse(id);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (RuntimeException e) {
            log.error("Error during deleted classe", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error during deleted classe");
        }
    }

}
