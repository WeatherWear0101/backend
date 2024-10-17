package com.example.weatherwear.common.devtool.domain.community.web.dto;

import java.util.List;

public class CommunityRequest {
  public record Save(
      String doName,
      String siName,
      String guName,
      Double latitude,
      Double longitude,
      List<CodyInfo> codyInfoList) {
    private record CodyInfo(String type, String path) {}
  }

  public record Put(
      String doName,
      String siName,
      String guName,
      Double latitude,
      Double longitude,
      List<CodyInfo> codyInfoList) {
    private record CodyInfo(String type, String path) {}
  }
}
