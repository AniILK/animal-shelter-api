package org.projecttest.animalshelterapi.service;

import lombok.RequiredArgsConstructor;
import org.projecttest.animalshelterapi.entity.Animal;
import org.projecttest.animalshelterapi.entity.MedicalHistoryRecord;

import org.projecttest.animalshelterapi.exceptions.ResourceNotFoundException;
import org.projecttest.animalshelterapi.repository.AnimalRepository;
import org.projecttest.animalshelterapi.repository.MedicalHistoryRecordRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicalHistoryRecordService {

    private final MedicalHistoryRecordRepository medicalHistoryRecordRepository;
    private final AnimalRepository animalRepository;

    public Page<MedicalHistoryRecord> findAllRecords(Pageable pageable) {
        return medicalHistoryRecordRepository.findAll(pageable);
    }

    public MedicalHistoryRecord createMedicalHistoryRecord(MedicalHistoryRecord record, Long animalId) {
        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new ResourceNotFoundException(STR."Animal with id \{animalId} not found"));
        record.setAnimal(animal);
        return medicalHistoryRecordRepository.save(record);
    }

    public MedicalHistoryRecord findRecordById(Long id) {
        return medicalHistoryRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(STR."Medical history record with id \{id} not found"));
    }

    public MedicalHistoryRecord updateMedicalHistoryRecord(Long id, MedicalHistoryRecord updatedRecord) {
        MedicalHistoryRecord record = findRecordById(id);

        record.setDescription(updatedRecord.getDescription());
        record.setVeterinarian(updatedRecord.getVeterinarian());

        return medicalHistoryRecordRepository.save(record);
    }

    public MedicalHistoryRecord partialUpdateMedicalHistoryRecord(Long id, MedicalHistoryRecord updatedRecord) {
        MedicalHistoryRecord record = findRecordById(id);

        if (updatedRecord.getDescription() != null) record.setDescription(updatedRecord.getDescription());
        if (updatedRecord.getVeterinarian() != null) record.setVeterinarian(updatedRecord.getVeterinarian());

        return medicalHistoryRecordRepository.save(record);
    }

    public void deleteMedicalHistoryRecord(Long id) {
        MedicalHistoryRecord record = findRecordById(id);
        medicalHistoryRecordRepository.delete(record);
    }
}
