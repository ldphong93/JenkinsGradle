package com.example.JenkinsSampleGradle.controller;

import com.example.JenkinsSampleGradle.entity.ThemeParkRide;
import com.example.JenkinsSampleGradle.repository.ThemeParkRideRepository;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@Slf4j(topic = "[ThemeParkRideController]" )
public class ThemeParkRideController {

  private final ThemeParkRideRepository themeParkRideRepository;

  public ThemeParkRideController(ThemeParkRideRepository themeParkRideRepository) {
    this.themeParkRideRepository = themeParkRideRepository;
  }

  @GetMapping(value = "/rides", produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<ThemeParkRide> getRides() {
    log.info("Getting all rides information.");
    return themeParkRideRepository.findAll();
  }

  @GetMapping(value = "/ride/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ThemeParkRide getRide(@PathVariable long id) {
    log.info("Getting ride [{}] information", id);
    return themeParkRideRepository
        .findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
            String.format("Invalid ride id %s", id)));
  }

  @PostMapping(value = "/ride", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ThemeParkRide createRide(@Valid @RequestBody ThemeParkRide themeParkRide) {
    log.info("Creating a ride.");
    return themeParkRideRepository.save(themeParkRide);
  }
}
