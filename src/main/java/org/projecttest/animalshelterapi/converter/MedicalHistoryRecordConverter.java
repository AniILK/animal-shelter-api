package org.projecttest.animalshelterapi.converter;

import org.projecttest.animalshelterapi.dto.CreateMedicalHistoryRecordRequest;
import org.projecttest.animalshelterapi.dto.GetMedicalHistoryRecordResponse;
import org.projecttest.animalshelterapi.entity.MedicalHistoryRecord;
import org.projecttest.animalshelterapi.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MedicalHistoryRecordConverter {

    public GetMedicalHistoryRecordResponse entityToDto(MedicalHistoryRecord record) {
        if (record == null) {
            throw new ResourceNotFoundException("MedicalHistoryRecord not found");
        }

        GetMedicalHistoryRecordResponse dto = new GetMedicalHistoryRecordResponse();
        dto.setId(record.getId());
        dto.setAnimalId(record.getAnimal().getId());
        dto.setAnimalName(record.getAnimal().getName());
        dto.setAnimalSpecies(String.valueOf(record.getAnimal().getSpecies()));
        dto.setAnimalBreed(record.getAnimal().getBreed());
        dto.setAnimalAge(record.getAnimal().getAge());
        dto.setDescription(record.getDescription());
        dto.setVeterinarian(record.getVeterinarian());

        if (record.getCreatedAt() != null) {
            dto.setCreatedAt(Timestamp.from(record.getCreatedAt().toInstant()));
        }

        if (record.getUpdatedAt() != null) {
            dto.setUpdatedAt(Timestamp.from(record.getUpdatedAt().toInstant()));
        }

        return dto;
    }

    public MedicalHistoryRecord dtoToEntity(CreateMedicalHistoryRecordRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("CreateMedicalHistoryRecordRequest cannot be null");
        }

        MedicalHistoryRecord record = new MedicalHistoryRecord();
        record.setDescription(request.getDescription());
        record.setVeterinarian(request.getVeterinarian());

        return record;
    }
}
