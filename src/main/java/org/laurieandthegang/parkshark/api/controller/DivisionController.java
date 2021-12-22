package org.laurieandthegang.parkshark.api.controller;

import org.laurieandthegang.parkshark.api.dto.CreateDivisionDTO;
import org.laurieandthegang.parkshark.api.dto.DivisionDto;
import org.laurieandthegang.parkshark.service.DivisionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/divisions")
public class DivisionController {

    private final static Logger logger = LoggerFactory.getLogger(DivisionController.class);

    private DivisionService divisionService;

    public DivisionController(DivisionService divisionService) {
        this.divisionService = divisionService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public DivisionDto postDivision(@RequestBody CreateDivisionDTO createDivisionDTO){
        logger.info("method call new division");
        return divisionService.addDivision(createDivisionDTO);
    }


//    @GetMapping(produces = APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.OK)
//    public List<DivisionDto> getAllDivisions(){
//        logger.info("Method call: get all divisions");
//        return divisionService.getAllDivisions();
//    }
//}
