package org.projecttest.animalshelterapi.converter;

import org.projecttest.animalshelterapi.dto.GetMedicalHistoryRecordResponse;
import org.projecttest.animalshelterapi.entity.MedicalHistoryRecord;
import org.projecttest.animalshelterapi.exceptions.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MedicalHistoryRecordConverter {

    // Convert from MedicalHistoryRecord entity to GetMedicalHistoryRecordResponse DTO
    public GetMedicalHistoryRecordResponse entityToDto(MedicalHistoryRecord record) {
        if (record == null) {
            throw new ResourceNotFoundException("Medical history record not found");
        }

        GetMedicalHistoryRecordResponse response = new GetMedicalHistoryRecordResponse();
        BeanUtils.copyProperties(record, response);

        if (record.getAnimal() != null) {
            response.setAnimalId(record.getAnimal().getId());
            response.setAnimalName(record.getAnimal().getName());
            response.setAnimalSpecies(record.getAnimal().getSpecies().toString());
            response.setAnimalBreed(record.getAnimal().getBreed());
            response.setAnimalGender(record.getAnimal().getGender());
            response.setAnimalAge(record.getAnimal().getAge());
        }

        return response;
    }

    // Convert from MedicalHistoryRecord DTO to entity
    public MedicalHistoryRecord toEntity(MedicalHistoryRecord request) {
        if (request == null) {
            throw new ResourceNotFoundException("Medical history record not found");
        }

        MedicalHistoryRecord record = new MedicalHistoryRecord();
        BeanUtils.copyProperties(request, record);
        return record;
    }

    // Convert from a list of MedicalHistoryRecord entities to a list of GetMedicalHistoryRecordResponse DTOs
    public List<GetMedicalHistoryRecordResponse> entityListToDto(List<MedicalHistoryRecord> records) {
        List<GetMedicalHistoryRecordResponse> responseList = new ArrayList<>();
        for (MedicalHistoryRecord record : records) {
            responseList.add(entityToDto(record));
        }
        return responseList;
    }
}
