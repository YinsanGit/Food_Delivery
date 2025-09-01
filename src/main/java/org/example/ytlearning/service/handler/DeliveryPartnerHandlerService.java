package org.example.ytlearning.service.handler;

import org.example.ytlearning.Constant.Constant;
import org.example.ytlearning.dto.DeliveryPartnerRequest;
import org.example.ytlearning.dto.DeliveryPartnerResponse;
import org.example.ytlearning.emunration.VehicleType;
import org.example.ytlearning.model.DeliveryPartner;
import org.example.ytlearning.utils.DateTimeUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class DeliveryPartnerHandlerService {
    public DeliveryPartner convertDeliveryPartnerRequestToDeliveryPartner(DeliveryPartner deliveryPartner, DeliveryPartnerRequest deliveryPartnerRequest) {



        deliveryPartner.setFirstName(deliveryPartnerRequest.getFirstName());
        deliveryPartner.setLastName(deliveryPartnerRequest.getFirstName());
        deliveryPartner.setUsername(deliveryPartnerRequest.getUsername());
        deliveryPartner.setPassword(deliveryPartnerRequest.getPassword());
        deliveryPartner.setGender(deliveryPartnerRequest.getGender());
        deliveryPartner.setDateOfBirth(DateTimeUtils.convertStringToDate(deliveryPartnerRequest.getDob()));
        deliveryPartner.setPhoneNumber(deliveryPartnerRequest.getPhoneNumber());
        deliveryPartner.setEmail(deliveryPartnerRequest.getEmail());
        deliveryPartner.setAddress(deliveryPartnerRequest.getAddress());
        deliveryPartner.setVehicleType(VehicleType.valueOf(deliveryPartnerRequest.getVehicle()));
        deliveryPartner.setAvailable(deliveryPartnerRequest.isAvailable());
        if (deliveryPartner.getId() == null) {
            deliveryPartner.setCreateBy(Constant.SYSTEM);
            deliveryPartner.setCreateAt(new Date());
        }




        return deliveryPartner;
    }

    public DeliveryPartnerResponse convertDeliveryPartnerResponseToDeliveryPartner(DeliveryPartner deliveryPartner) {

        DeliveryPartnerResponse deliveryPartnerResponse = new DeliveryPartnerResponse();
        deliveryPartnerResponse.setId(deliveryPartner.getId());
        deliveryPartnerResponse.setFirstName(deliveryPartner.getFirstName());
        deliveryPartnerResponse.setLastName(deliveryPartner.getLastName());
        deliveryPartnerResponse.setUsername(deliveryPartner.getUsername());
        deliveryPartnerResponse.setGender(deliveryPartner.getGender());
        deliveryPartnerResponse.setDob(deliveryPartner.getDateOfBirth().toString());
        deliveryPartnerResponse.setPhoneNumber(deliveryPartner.getPhoneNumber());
        deliveryPartnerResponse.setEmail(deliveryPartner.getEmail());
        deliveryPartnerResponse.setAddress(deliveryPartner.getAddress());
        deliveryPartnerResponse.setVehicle(deliveryPartner.getVehicleType().toString());
        deliveryPartnerResponse.setAvailable(deliveryPartner.isAvailable());
        deliveryPartnerResponse.setCreatedBy(deliveryPartner.getCreateBy());
        deliveryPartnerResponse.setCreatedAt(deliveryPartner.getCreateAt().toString());
        if (deliveryPartner.getUpdateAt() != null) {
            deliveryPartnerResponse.setUpdateBy(deliveryPartner.getUpdateBy());
            deliveryPartnerResponse.setUpdatedAt(deliveryPartner.getUpdateAt().toString());
        }
        return deliveryPartnerResponse;
    }

}
