package com.bootcamp.finalProject.utils;

import com.bootcamp.finalProject.dtos.BackOrderDTO;
import com.bootcamp.finalProject.dtos.BackOrderDetailDTO;
import com.bootcamp.finalProject.model.BackOrder;
import com.bootcamp.finalProject.model.BackOrderDetail;

import java.text.SimpleDateFormat;

import static com.bootcamp.finalProject.utils.MapperUtils.completeNumberByLength;

public class BackOrderMapper {
    static SimpleDateFormat datePattern = new SimpleDateFormat("yyyy-MM-dd");

    public static BackOrderDTO toDTO(BackOrder backOrder, String orderNumberCM){
        BackOrderDTO backOrderDTO = new BackOrderDTO();
        backOrderDTO.setBackOrderDate(datePattern.format(backOrder.getBackOrderDate()));
        if(backOrder.getFinishBackOrderDate() != null){
            backOrderDTO.setFinishBackOrderDate(datePattern.format(backOrder.getFinishBackOrderDate()));
        }
        backOrderDTO.setBackOrderPriority(backOrder.getBackOrderPriority());
        backOrderDTO.setBackOrderStatus(backOrder.getBackOrderStatus());
        Long idSubsidiary = backOrder.getSubsidiary().getIdSubsidiary();
        backOrderDTO.setBackOrderNumberCM(completeNumberByLength(String.valueOf(idSubsidiary),4) +
                "-" + completeNumberByLength(String.valueOf(backOrder.getIdBackOrder()),8));
        backOrderDTO.setOrderNumberCM(orderNumberCM);
        backOrderDTO.setBackOrderDetail(toDTO(backOrder.getBackOrderDetail()));
        return backOrderDTO;
    }

    public static BackOrderDetailDTO toDTO(BackOrderDetail backOrderDetail){
        BackOrderDetailDTO backOrderDetailDTO = new BackOrderDetailDTO();
        backOrderDetailDTO.setPartCode(backOrderDetail.getPartBackOrder().getPartCode());
        backOrderDetailDTO.setDescription(backOrderDetail.getPartBackOrder().getDescription());
        backOrderDetailDTO.setQuantity(backOrderDetail.getQuantity());
        backOrderDetailDTO.setAccountType(backOrderDetail.getAccountType());
        return backOrderDetailDTO;
    }
}
