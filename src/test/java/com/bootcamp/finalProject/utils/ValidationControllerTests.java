package com.bootcamp.finalProject.utils;

import com.bootcamp.finalProject.dtos.SubsidiaryResponseDTO;
import com.bootcamp.finalProject.exceptions.IncorrectParamsGivenException;
import com.bootcamp.finalProject.exceptions.InternalExceptionHandler;
import com.bootcamp.finalProject.mnemonics.DeliveryStatus;
import com.bootcamp.finalProject.mnemonics.ExceptionMessage;
import com.bootcamp.finalProject.model.Order;
import com.bootcamp.finalProject.model.OrderDetail;
import com.bootcamp.finalProject.model.Subsidiary;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ValidationControllerTests {

    @Test
    public void shouldNotThrowSinceItsCorrect(){
        //arrange
        String badProviderId = "34";
        //act
        //assert
        Assertions.assertDoesNotThrow(() -> ValidationController.validateProviderId(badProviderId));
    }
    @Test
    public void shouldThrowSinceProviderIdIsNull(){
        //arrange
        String badProviderId = null;
        //act
        //assert
        Assertions.assertThrows(IncorrectParamsGivenException.class, () -> ValidationController.validateProviderId(badProviderId));
    }

    @Test
    public void shouldThrowSinceProviderIdIsNotANumber(){
        //arrange
        String badProviderId = "elMalo";
        //act
        //assert
        Assertions.assertThrows(IncorrectParamsGivenException.class, () -> ValidationController.validateProviderId(badProviderId));
    }

    @Test
    public void shouldThrowSinceProviderIdIsLowerThanZero(){
        //arrange
        String badProviderId = "-5959";
        //act
        //assert
        Assertions.assertThrows(IncorrectParamsGivenException.class, () -> ValidationController.validateProviderId(badProviderId));
    }



    @Test
    public void ShouldThrowIncorrectParamGivenExceptionForBadStatusGiven(){
        //arrange
        String badOrderStatus = "NonExistentStatus"; //pending
        //act
        //assert
        Assertions.assertThrows(IncorrectParamsGivenException.class, () -> ValidationController.validateOrderStatus(badOrderStatus)) ;
    }

    @Test
    public void shouldThrowSinceParamsAreEmpty(){
        //Arrange
        Map<String, String> map = new HashMap<>();
        //Act

        //Assert
        Assertions.assertThrows(IncorrectParamsGivenException.class, () -> ValidationController.validateSubsidiaryStockParams(map));
    }
    @Test
    public void shouldThrowSinceParamDealerNumberIsIncorrect(){
        //Arrange
        Map<String, String> map = new HashMap<>();
        map.put("dealerNumber", "kdkdk");
        //Act

        //Assert
        Assertions.assertThrows(IncorrectParamsGivenException.class, () -> ValidationController.validateSubsidiaryStockParams(map));
    }
    @Test
    public void shouldThrowSinceParamDealerNumberIsNull(){
        //Arrange
        Map<String, String> map = new HashMap<>();
        map.put("dealerNumber", null);
        //Act

        //Assert
        Assertions.assertThrows(IncorrectParamsGivenException.class, () -> ValidationController.validateSubsidiaryStockParams(map));
    }

}
