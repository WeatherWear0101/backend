package com.example.weatherwear.common.devtool.domain.community.web.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.weatherwear.common.devtool.domain.community.web.dto.CommunityResponse;

@RestController
@RequestMapping("/api/v1/communities")
public class CommunityController {
  @PostMapping
  public ResponseEntity<CommunityResponse.FindById> save() {
    return ResponseEntity.status(HttpStatus.CREATED).body(null);
  }

  @GetMapping
  public ResponseEntity<Page<CommunityResponse.FindAll>> findAll() {
    return ResponseEntity.ok().body(null);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CommunityResponse.FindById> findById(@PathVariable("id") Integer id) {
    return ResponseEntity.ok().body(null);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CommunityResponse.FindById> put(@PathVariable("id") Integer id) {
    return ResponseEntity.ok().body(null);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
    return ResponseEntity.ok().body(null);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<CommunityResponse.FindById> review(
      @PathVariable("id") Integer id, @RequestParam("review") String review) {
    return ResponseEntity.ok().body(null);
  }
}
