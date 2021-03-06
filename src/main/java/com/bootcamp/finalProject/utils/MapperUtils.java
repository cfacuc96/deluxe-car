package com.bootcamp.finalProject.utils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MapperUtils
{
    //el numero ingresado debe ser String y el resultado es String
    //hay que tener en cuenta para validar que
    // si la long del numero es mayor al length ingresado devuelve "".
    public static String completeNumberByLength(String Number, int length)
    {
        String result = "";
        if(Number.length() < length)
        {
            int limit = length - Number.length();
            result = replicate('0', limit);
            result += Number;
        }
        return result;
    }

    private static String replicate(char character, int limit)
    {
        String result = "";
        for (int i = 0; i < limit ; i++)
        {
            result += character;
        }
        return result;
    }

    public static Integer getDifferencesInDays(Date deliveryDate, Date deliveredDate) {

        if (deliveredDate != null) {
            if (deliveredDate.before(deliveryDate)) {
                return 0;
            }
            long diffInMillies = Math.abs(deliveryDate.getTime() - deliveredDate.getTime());
            return Math.toIntExact((TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS)));
        } else {
            Date current = new Date();
            if (current.before(deliveryDate)) {
                return 0;
            } else {
                long diffInMillies = Math.abs(current.getTime() - deliveryDate.getTime());
                return Math.toIntExact((TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS)));
            }
        }
    }
}
