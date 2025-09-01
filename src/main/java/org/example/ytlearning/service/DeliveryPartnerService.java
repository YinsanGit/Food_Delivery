package org.example.ytlearning.service;

import org.example.ytlearning.dto.DeliveryPartnerRequest;
import org.example.ytlearning.dto.DeliveryPartnerResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeliveryPartnerService {

    DeliveryPartnerResponse create(DeliveryPartnerRequest deliveryPartnerRequest);

    DeliveryPartnerResponse update(Long id, DeliveryPartnerRequest deliveryPartnerRequest);

    void delete(Long id);

    DeliveryPartnerResponse findById(Long id);

    List<DeliveryPartnerResponse> getAll();

}
