package org.example.ytlearning.repository;

import org.example.ytlearning.model.DeliveryPartner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryPartnerRepository extends JpaRepository<DeliveryPartner, Long> {

    List<DeliveryPartner> findAllByAvailable(boolean available);
}
