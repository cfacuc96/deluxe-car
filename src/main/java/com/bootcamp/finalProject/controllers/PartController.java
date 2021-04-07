package com.bootcamp.finalProject.controllers;

import com.bootcamp.finalProject.services.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/parts")
public class PartController {

    @Autowired
    PartService service;

    //TODO: ARMAR el o los ENDPOINT.
}
