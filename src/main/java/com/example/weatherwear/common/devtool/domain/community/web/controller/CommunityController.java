package com.example.weatherwear.common.devtool.domain.community.web.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.weatherwear.common.devtool.domain.community.web.dto.CommunityRequest;
import com.example.weatherwear.common.devtool.domain.community.web.dto.CommunityResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "게시글 API")
@RequestMapping("/api/v1/communities")
public class CommunityController {

  @PostMapping
  @Operation(summary = "게시글 작성")
  @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "게시글 작성 성공")})
  public ResponseEntity<CommunityResponse.FindById> save(@RequestBody CommunityRequest.Save dto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(null);
  }

  @GetMapping
  @Operation(summary = "모든 게시글 조회")
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "게시글 조회 성공")})
  public ResponseEntity<Page<CommunityResponse.FindAll>> findAll(
      @RequestParam("sort") String sort, @RequestParam("page") int page) {
    return ResponseEntity.ok().body(null);
  }

  @GetMapping("/{id}")
  @Operation(summary = "개별 게시글 조회")
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "개별 조회 성공")})
  public ResponseEntity<CommunityResponse.FindById> findById(@PathVariable("id") Integer id) {
    return ResponseEntity.ok().body(null);
  }

  @PutMapping("/{id}")
  @Operation(summary = "게시글 수정")
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "개별 수정 성공")})
  public ResponseEntity<CommunityResponse.FindById> put(
      @PathVariable("id") Integer id, @RequestBody CommunityRequest.Save dto) {
    return ResponseEntity.ok().body(null);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "게시글 삭제")
  @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "삭제 성공")})
  public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("/{id}")
  @Operation(summary = "게시글 리뷰 작성")
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "게시글 리뷰 작성")})
  public ResponseEntity<CommunityResponse.FindById> review(
      @PathVariable("id") Integer id, @RequestParam("review") String review) {
    return ResponseEntity.ok().body(null);
  }
}
