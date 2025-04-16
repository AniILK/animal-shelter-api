package org.projecttest.animalshelterapi.service;

import lombok.RequiredArgsConstructor;
import org.projecttest.animalshelterapi.entity.MedicalHistoryRecord;
import org.projecttest.animalshelterapi.exceptions.ResourceNotFoundException;
import org.projecttest.animalshelterapi.repository.MedicalHistoryRecordRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicalHistoryRecordService {

    private final MedicalHistoryRecordRepository medicalHistoryRecordRepository;

//  Create or update MedHistRecord.
    public MedicalHistoryRecord save(MedicalHistoryRecord record) {
        return medicalHistoryRecordRepository.save(record);
    }

//  Get all MedHistRecords, throws an exception in no records are found.
    public List<MedicalHistoryRecord> getAllRecords() {
        List<MedicalHistoryRecord> records = medicalHistoryRecordRepository.findAll();
        if (records.isEmpty()) {
            throw new ResourceNotFoundException("No medical history records found");
        }
        return records;
    }

//  Retrieve a record by its id, throws exception if not found.
    public MedicalHistoryRecord getRecord(Long id) {
        return medicalHistoryRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(STR."Medical history record with id \{id} not found"));
    }

//  Update an existing record, throws exception if the record doesn't exist.
    public MedicalHistoryRecord updateRecord(Long id,MedicalHistoryRecord updatedRecord) {
        MedicalHistoryRecord record = medicalHistoryRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(STR."Medical history record not found with id: \{id}"));

        BeanUtils.copyProperties(updatedRecord, record, "id", "createdAt", "updatedAt");

        return medicalHistoryRecordRepository.save(record);
    }

//  Delete record  by its id, throws exception if the record doesn't exist.
    public void deleteRecord(Long id) {
        if(!medicalHistoryRecordRepository.existsById(id)) {
            throw new ResourceNotFoundException(STR."Medical history record with id \{id} not found");
        }
        medicalHistoryRecordRepository.deleteById(id);
    }

//  Gets MedHistRecords by animal id, throws exception, if animal id doesn't exist.
    public List<MedicalHistoryRecord> getRecordsByAnimalId(Long animalId) {
        List<MedicalHistoryRecord> records = medicalHistoryRecordRepository.findByAnimalId(animalId);
        if (records.isEmpty()) {
            throw new ResourceNotFoundException(STR."No medical history records found for animal id: \{animalId}");
        }
        return records;
    }
}
