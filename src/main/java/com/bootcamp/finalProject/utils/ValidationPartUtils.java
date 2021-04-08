package com.bootcamp.finalProject.utils;

import com.bootcamp.finalProject.exceptions.OrderTypeException;
import com.bootcamp.finalProject.exceptions.TypeOfQueryException;
import com.bootcamp.finalProject.mnemonics.OrderType;
import org.springframework.data.domain.Sort;

public class ValidationPartUtils {

    public static boolean typeOfQueryValidation(String typeOfQuery) throws TypeOfQueryException {
        if(typeOfQuery == null){
            throw new TypeOfQueryException();
        }
        return true;
    }

    public static Sort orderTypeValidation(Integer order) throws OrderTypeException {
        Sort sort = null;
        switch (order){
            case OrderType.DEFAULT:
                sort = Sort.by(Sort.Direction.ASC, "partCode");
                break;
            case OrderType.ASC:
                sort = Sort.by(Sort.Direction.ASC, "description");
                break;
            case OrderType.DESC:
                sort = Sort.by(Sort.Direction.DESC, "description");
                break;
            case OrderType.LAST_CHANGE:
                sort = Sort.by(Sort.Direction.ASC, "lastModification");
                break;
            default:
                throw new OrderTypeException();
        }
        return sort;
    }
}
