package com.bootcamp.finalProject.utils;

import com.bootcamp.finalProject.exceptions.OrderTypeException;
import com.bootcamp.finalProject.exceptions.TypeOfQueryException;
import com.bootcamp.finalProject.mnemonics.OrderType;
import org.springframework.data.domain.Sort;

public class ValidationPartUtils {

    /**
     * @param typeOfQuery Value: "C", "P", "V"
     * @return boolean corresponding if it valid query
     * @throws TypeOfQueryException If typeOfQuery is null
     */
    public static boolean typeOfQueryValidation(String typeOfQuery) throws TypeOfQueryException {
        if(typeOfQuery == null){
            throw new TypeOfQueryException();
        }
        return true;
    }

    /**
     * @param order Value: 0 DEFAULT ASC BY partCode, 1 ASC BY description, 2 DESC BY description, 3 DESC by lastModification
     * @return Sorting type corresponding to the code received as a parameter
     * @throws OrderTypeException If order is differete than 0, 1, 2, 3
     */
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
                sort = Sort.by(Sort.Direction.DESC, "lastModification");
                break;
            default:
                throw new OrderTypeException();
        }
        return sort;
    }
}
