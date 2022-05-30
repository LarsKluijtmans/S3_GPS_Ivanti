package s3_gps_ivanti.business.dtoConvertor;

import s3_gps_ivanti.dto.version.*;
import s3_gps_ivanti.repository.entity.Version;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VersionDTOConverter {

    private VersionDTOConverter(){}

    public static Version convertToEntityForCreate(CreateMajorVersionRequestDTO majorVersionRequestDTO){
        return Version.builder()
                .number(majorVersionRequestDTO.getNumber())
                .totalDownloads(0)
                .appLocation(majorVersionRequestDTO.getAppLocation())
                .downloads(Collections.emptyList())
                .build();
    }
    public static Version convertToEntityForCreate(CreateMinorVersionRequestDTO minorVersionRequestDTO){
        return Version.builder()
                .number(minorVersionRequestDTO.getNumber())
                .totalDownloads(0)
                .appLocation(minorVersionRequestDTO.getAppLocation())
                .downloads(Collections.emptyList())
                .build();
    }

    public static CreateMajorVersionResponseDTO convertToDTOForMajorResponse(Version version){
        return CreateMajorVersionResponseDTO.builder()
                .number(version.getNumber())
                .build();
    }
    public static CreateMinorVersionResponseDTO convertToDTOForMinorResponse(Version version){
        return CreateMinorVersionResponseDTO.builder()
                .number(version.getNumber())
                .build();
    }

    public static Version convertToEntityForUpdate(UpdateVersionRequestDTO updateVersionRequestDTO, Version oldVersion){
        return Version.builder()
                .number(updateVersionRequestDTO.getNumber())
                .totalDownloads(oldVersion.getTotalDownloads())
                .appLocation(updateVersionRequestDTO.getAppLocation())
                .downloads(oldVersion.getDownloads())
                .build();
    }

    public static Version convertToEntity(VersionDTO versionDTO){
        return Version.builder()
                .number(versionDTO.getNumber())
                .totalDownloads(versionDTO.getTotalDownloads())
                .appLocation(versionDTO.getAppLocation())
                .downloads(versionDTO.getDownloads())
                .build();
    }
    public static VersionDTO convertToDTO(Version version){
        return VersionDTO.builder()
                .number(version.getNumber())
                .totalDownloads(version.getTotalDownloads())
                .appLocation(version.getAppLocation())
                .downloads(version.getDownloads())
                .build();
    }

    public static List<VersionDTO> convertToListOfDTO(List<Version> versions) {

        List<VersionDTO> result = new ArrayList<>();
        for (Version v :versions) {
            result.add(VersionDTOConverter.convertToDTO(v));
        }
        return result;
    }
    public static List<Version> convertToListOfEntity(List<VersionDTO> versions) {

        List<Version> result = new ArrayList<>();
        for (VersionDTO v :versions) {
            result.add(VersionDTOConverter.convertToEntity(v));
        }
        return result;
    }
}
