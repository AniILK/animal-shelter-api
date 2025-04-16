package org.projecttest.animalshelterapi.repository;

import org.projecttest.animalshelterapi.entity.MedicalHistoryRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalHistoryRecordRepository extends JpaRepository<MedicalHistoryRecord, Long> {
//  Get MedHistRecords using animal id.
    List<MedicalHistoryRecord> findByAnimalId(Long animalId);
}
