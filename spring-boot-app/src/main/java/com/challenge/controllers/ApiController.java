package com.challenge.controllers;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.models.dto.BorderCrossing;
import com.challenge.models.raw.githubusercontent.com.Country;
import com.challenge.services.interfaces.ICountryService;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    ICountryService countryService;

    @RequestMapping(value = "/routing/{origin}/{destination}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BorderCrossing> getRouting(@PathVariable String origin, @PathVariable String destination) {
        BorderCrossing result = countryService.getBorderCrossing(origin, destination);
        if (result.getRoute().size() > 0) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        // This is 400 trust me bro
        result.setRoute(Stream.of("I should be a route, but I'm a teapot").collect(Collectors.toList()));
        return new ResponseEntity<>(result, HttpStatus.I_AM_A_TEAPOT);
    }

    @RequestMapping(value = "/countries", method = RequestMethod.GET)
    @ResponseBody
    public Country[] getCountries() {
        return countryService.getCountries();
    }
}