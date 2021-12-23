package org.laurieandthegang.parkshark.api.controller;

import org.laurieandthegang.parkshark.api.dto.people.CreateMemberDto;
import org.laurieandthegang.parkshark.api.dto.people.MemberDto;
import org.laurieandthegang.parkshark.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final static Logger LOGGER = LoggerFactory.getLogger(MemberController.class);

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public MemberDto registerNewMember(@RequestBody CreateMemberDto createMemberDto) {
        LOGGER.info("Method call: register new member.");
        return memberService.addMember(createMemberDto);
    }
}
