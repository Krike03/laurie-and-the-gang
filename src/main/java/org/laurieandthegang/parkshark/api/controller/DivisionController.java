package org.laurieandthegang.parkshark.api.controller;

import org.laurieandthegang.parkshark.api.dto.parkinglot.CreateDivisionDto;
import org.laurieandthegang.parkshark.api.dto.parkinglot.DivisionDto;
import org.laurieandthegang.parkshark.service.DivisionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/divisions")
public class DivisionController {

    private final static Logger logger = LoggerFactory.getLogger(DivisionController.class);

    private final DivisionService divisionService;

    public DivisionController(DivisionService divisionService) {
        this.divisionService = divisionService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADD_DIVISION')")
    public DivisionDto postDivision(@RequestBody CreateDivisionDto createDivisionDTO) {
        logger.info("method call new division");
        return divisionService.addDivision(createDivisionDTO);
    }


    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('GET_DIVISIONS')")
    public List<DivisionDto> getAllDivisions() {
        logger.info("Method call: get all divisions");
        return divisionService.getAllDivisions();
    }
}
