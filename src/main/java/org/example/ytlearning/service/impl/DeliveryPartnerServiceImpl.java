package org.example.ytlearning.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.ytlearning.Constant.Constant;
import org.example.ytlearning.dto.DeliveryPartnerRequest;
import org.example.ytlearning.dto.DeliveryPartnerResponse;
import org.example.ytlearning.model.DeliveryPartner;
import org.example.ytlearning.repository.DeliveryPartnerRepository;
import org.example.ytlearning.service.DeliveryPartnerService;
import org.example.ytlearning.service.handler.DeliveryPartnerHandlerService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class DeliveryPartnerServiceImpl implements DeliveryPartnerService{
    private final DeliveryPartnerRepository deliveryPartnerRepository;
    private final DeliveryPartnerHandlerService deliveryPartnerHandlerService;


    @Override
    public DeliveryPartnerResponse create(DeliveryPartnerRequest deliveryPartnerRequest) {
        DeliveryPartner deliveryPartner = new DeliveryPartner();
        deliveryPartner = deliveryPartnerHandlerService
                .convertDeliveryPartnerRequestToDeliveryPartner(deliveryPartner, deliveryPartnerRequest);
        log.info("Save delivery partner record: {}", deliveryPartner);
        deliveryPartnerRepository.saveAndFlush(deliveryPartner);
        if (deliveryPartner.getId() != null) {
            return deliveryPartnerHandlerService.convertDeliveryPartnerResponseToDeliveryPartner(deliveryPartner);
        }
        return null;
    }

    @Override
    public DeliveryPartnerResponse update(Long id, DeliveryPartnerRequest deliveryPartnerRequest) {
        Optional<DeliveryPartner> deliveryPartner = deliveryPartnerRepository.findById(id);
        if (deliveryPartner.isEmpty()) {
            return new DeliveryPartnerResponse();
        }
        DeliveryPartner updateDeliveryPartner = deliveryPartnerHandlerService
                .convertDeliveryPartnerRequestToDeliveryPartner(deliveryPartner.get(), deliveryPartnerRequest);


            updateDeliveryPartner.setUpdateBy(Constant.SYSTEM);
            updateDeliveryPartner.setUpdateAt(new Date());

            deliveryPartnerRepository.saveAndFlush(updateDeliveryPartner);
            return deliveryPartnerHandlerService.convertDeliveryPartnerResponseToDeliveryPartner(updateDeliveryPartner);

    }

    @Override
    public void delete(Long id) {
        Optional<DeliveryPartner> deliveryPartner = deliveryPartnerRepository.findById(id);
        if (deliveryPartner.isEmpty()) {
            log.info("Delivery partner with ID {} not found in database", id);
            return;
        }
        deliveryPartnerRepository.deleteById(id);
    }

    @Override
    public DeliveryPartnerResponse findById(Long id) {
        Optional<DeliveryPartner> deliveryPartner = deliveryPartnerRepository.findById(id);
        if(deliveryPartner.isEmpty()) {
            log.info("Find Delivery Partner by id {} not found.", id);
            return new DeliveryPartnerResponse();
        }
        return deliveryPartnerHandlerService
                .convertDeliveryPartnerResponseToDeliveryPartner(deliveryPartner.get());
    }

    @Override
    public List<DeliveryPartnerResponse> getAll() {
        List<DeliveryPartner> deliveryPartners = deliveryPartnerRepository.findAll();
        if(deliveryPartners.isEmpty()) {
            log.info("Fetch all Delivery Partner not found in DB.");
            return List.of();
        }

        List<DeliveryPartnerResponse> deliveryPartnerResponses = new ArrayList<>();
        for(DeliveryPartner deliveryPartner : deliveryPartners) {

            DeliveryPartnerResponse deliveryPartnerResponse = deliveryPartnerHandlerService
                    .convertDeliveryPartnerResponseToDeliveryPartner(deliveryPartner);

            deliveryPartnerResponses.add(deliveryPartnerResponse);
        }

        return deliveryPartnerResponses;
    }

}
