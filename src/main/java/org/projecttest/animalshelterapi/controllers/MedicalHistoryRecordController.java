package org.projecttest.animalshelterapi.controllers;

import lombok.RequiredArgsConstructor;
import org.projecttest.animalshelterapi.converter.MedicalHistoryRecordConverter;
import org.projecttest.animalshelterapi.dto.CreateMedicalHistoryRecordRequest;
import org.projecttest.animalshelterapi.dto.GetMedicalHistoryRecordResponse;
import org.projecttest.animalshelterapi.entity.MedicalHistoryRecord;
import org.projecttest.animalshelterapi.service.MedicalHistoryRecordService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/medical-history-records")
@RestController
public class MedicalHistoryRecordController {

    private final MedicalHistoryRecordService medicalHistoryRecordService;
    private final MedicalHistoryRecordConverter converter;

    @GetMapping
    public Page<GetMedicalHistoryRecordResponse> getMedicalHistoryRecords(Pageable pageable) {
        return medicalHistoryRecordService.findAllRecords(pageable)
                .map(converter::entityToDto);
    }

    @PostMapping
    public GetMedicalHistoryRecordResponse createMedicalHistoryRecord(@RequestBody CreateMedicalHistoryRecordRequest request) {
        MedicalHistoryRecord record = converter.dtoToEntity(request);
        return converter.entityToDto(medicalHistoryRecordService.createMedicalHistoryRecord(record, request.getAnimalId()));
    }

    @GetMapping("/{id}")
    public GetMedicalHistoryRecordResponse getMedicalHistoryRecordById(@PathVariable Long id) {
        return converter.entityToDto(medicalHistoryRecordService.findRecordById(id));
    }

    @PutMapping("/{id}")
    public GetMedicalHistoryRecordResponse putMedicalHistoryRecord(@PathVariable Long id, @RequestBody CreateMedicalHistoryRecordRequest request) {
        MedicalHistoryRecord record = converter.dtoToEntity(request);
        return converter.entityToDto(medicalHistoryRecordService.updateMedicalHistoryRecord(id, record));
    }

    @PatchMapping("/{id}")
    public GetMedicalHistoryRecordResponse patchMedicalHistoryRecord(@PathVariable Long id, @RequestBody CreateMedicalHistoryRecordRequest request) {
        MedicalHistoryRecord record = converter.dtoToEntity(request);
        return converter.entityToDto(medicalHistoryRecordService.partialUpdateMedicalHistoryRecord(id, record));
    }

    @DeleteMapping("/{id}")
    public void deleteMedicalHistoryRecord(@PathVariable Long id) {
        medicalHistoryRecordService.deleteMedicalHistoryRecord(id);
    }
}
