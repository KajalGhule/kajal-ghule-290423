package com.avisys.cim.repository;

import com.avisys.cim.entity.MobileNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MobileNumberRepository extends JpaRepository<MobileNumber, Long> {

    @Query("SELECT mn FROM MobileNumber mn WHERE mn.mobileNumber = :mobileNumber")
    MobileNumber getMobileNumberDetails(String mobileNumber);

}
