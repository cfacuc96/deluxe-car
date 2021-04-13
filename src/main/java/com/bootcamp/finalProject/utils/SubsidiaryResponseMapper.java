package com.bootcamp.finalProject.utils;

import com.bootcamp.finalProject.dtos.SubsidiaryResponseDTO;
import com.bootcamp.finalProject.dtos.SubsidiaryStockDTO;
import com.bootcamp.finalProject.dtos.SubsidiaryStockRequestDTO;
import com.bootcamp.finalProject.dtos.SubsidiaryStockResponseDTO;
import com.bootcamp.finalProject.model.Subsidiary;
import com.bootcamp.finalProject.model.SubsidiaryStock;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.bootcamp.finalProject.utils.MapperUtils.completeNumberByLength;

public class SubsidiaryResponseMapper {
    OrderResponseMapper mapper = new OrderResponseMapper();

    public SubsidiaryResponseDTO toOrderDTO(Subsidiary subsidiary) {
        SubsidiaryResponseDTO ret = new SubsidiaryResponseDTO();
        String idSubsidiaryString = String.valueOf(subsidiary.getIdSubsidiary());
        ret.setDealerNumber(completeNumberByLength(idSubsidiaryString, 4));

        var orders = subsidiary.getOrders().stream().map(f -> mapper.toDTO(f, subsidiary.getIdSubsidiary())).collect(Collectors.toList());

        ret.setOrders(orders);

        return ret;
    }

    public SubsidiaryStockResponseDTO toStockDTO(Subsidiary subsidiary) {
        SubsidiaryStockResponseDTO result = new SubsidiaryStockResponseDTO();
        result.setDealerNumber(completeNumberByLength(String.valueOf(subsidiary.getIdSubsidiary()), 4));
        result.setName(subsidiary.getName());

        List<SubsidiaryStockDTO> subDtos = new ArrayList<>();

        for (SubsidiaryStock s : subsidiary.getSubsidiaryStocks()) {
            SubsidiaryStockDTO dto = new SubsidiaryStockDTO();
            dto.setPartCode(String.valueOf(s.getPart().getPartCode()));
            dto.setDescription(s.getPart().getDescription());
            dto.setQuantityInSubsidiary(String.valueOf(s.getQuantity()));

            subDtos.add(dto);
        }

        result.setSubsidiaryStocks(subDtos);

        return result;
    }
}
