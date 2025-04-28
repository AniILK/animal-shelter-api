package org.projecttest.animalshelterapi.controllers;

import lombok.RequiredArgsConstructor;
import org.projecttest.animalshelterapi.converter.MedicalHistoryRecordConverter;
import org.projecttest.animalshelterapi.dto.CreateMedicalHistoryRecordRequest;
import org.projecttest.animalshelterapi.dto.GetMedicalHistoryRecordResponse;
import org.projecttest.animalshelterapi.entity.MedicalHistoryRecord;
import org.projecttest.animalshelterapi.service.MedicalHistoryRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/medical-history-records")
@RestController
public class MedicalHistoryRecordController {

    private final MedicalHistoryRecordService medicalHistoryRecordService;
    private final MedicalHistoryRecordConverter converter;

    @GetMapping
    public List<GetMedicalHistoryRecordResponse> getAllMedicalHistoryRecords() {
        return converter.toDtoList(medicalHistoryRecordService.findAllRecords());
    }

    @PostMapping
    public GetMedicalHistoryRecordResponse createMedicalHistoryRecord(@RequestBody CreateMedicalHistoryRecordRequest request) {
        MedicalHistoryRecord record = converter.toEntity(request);
        return converter.toDto(medicalHistoryRecordService.createMedicalHistoryRecord(record, request.getAnimalId()));
    }

    @GetMapping("/{id}")
    public GetMedicalHistoryRecordResponse getMedicalHistoryRecordById(@PathVariable Long id) {
        return converter.toDto(medicalHistoryRecordService.findRecordById(id));
    }

    @PutMapping("/{id}")
    public GetMedicalHistoryRecordResponse putMedicalHistoryRecord(@PathVariable Long id, @RequestBody CreateMedicalHistoryRecordRequest request) {
        MedicalHistoryRecord record = converter.toEntity(request);
        return converter.toDto(medicalHistoryRecordService.updateMedicalHistoryRecord(id, record));
    }

    @PatchMapping("/{id}")
    public GetMedicalHistoryRecordResponse patchMedicalHistoryRecord(@PathVariable Long id, @RequestBody CreateMedicalHistoryRecordRequest request) {
        MedicalHistoryRecord record = converter.toEntity(request);
        return converter.toDto(medicalHistoryRecordService.partialUpdateMedicalHistoryRecord(id, record));
    }

    @DeleteMapping("/{id}")
    public void deleteMedicalHistoryRecord(@PathVariable Long id) {
        medicalHistoryRecordService.deleteMedicalHistoryRecord(id);
    }
}
