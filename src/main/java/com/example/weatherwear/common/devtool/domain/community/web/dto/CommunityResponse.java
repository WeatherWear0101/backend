package com.example.weatherwear.common.devtool.domain.community.web.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;

public class CommunityResponse {
  @Builder
  public record FindById(
      LocalDateTime createTime,
      LocalDateTime updateTime,
      MemberInfo memberInfo,
      WeatherInfo weatherInfo,
      List<CodyInfo> codyInfoList) {

    @Builder
    private record MemberInfo(
        String name,
        String email,
        String provider,
        String image,
        String gender,
        Integer age,
        String nickname,
        String role,
        String phone) {}

    @Builder
    private record WeatherInfo(
        String doName,
        String siName,
        String guName,
        Double maxTemperature,
        Double minTemperature,
        String weatherCodeStatus,
        Double temperature,
        Double precipitation,
        Double precipitationProbability,
        Double fineDust,
        Double ultraFineDust) {}

    @Builder
    private record CodyInfo(String path, String type) {}
  }

  @Builder
  public record FindAll(
      LocalDateTime createTime,
      LocalDateTime updateTime,
      MemberInfo memberInfo,
      WeatherInfo weatherInfo,
      List<CodyInfo> codyInfoList) {

    @Builder
    private record MemberInfo(
        String name,
        String email,
        String provider,
        String image,
        String gender,
        Integer age,
        String nickname,
        String role,
        String phone) {}

    @Builder
    private record WeatherInfo(
        String doName,
        String siName,
        String guName,
        Double maxTemperature,
        Double minTemperature,
        String weatherCodeStatus,
        Double temperature,
        Double precipitation,
        Double precipitationProbability,
        Double fineDust,
        Double ultraFineDust) {}

    @Builder
    private record CodyInfo(String path, String type) {}
  }
}
