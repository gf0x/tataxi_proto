package app.utils;

/**
 * Created by Alex_Frankiv on 05.04.2017.
 */

import com.google.maps.model.AddressComponent;
import com.google.maps.model.AddressComponentType;
import com.google.maps.model.GeocodingResult;

/**
 * Helps to work with GeocodingResult entities
 */
public class GeocodingUtil {

    public static String getCity(GeocodingResult result){
        for(AddressComponent addressComponent : result.addressComponents){
            for(AddressComponentType addressComponentType : addressComponent.types)
                if(addressComponentType == AddressComponentType.LOCALITY)
                    return addressComponent.longName;
        }
        return "";
    }

    public static boolean areFromOneCity(GeocodingResult... results){
        if(results.length>0){
            String first = getCity(results[0]);
            for(int i=1; i<results.length; ++i)
                if (!first.equals(getCity(results[i])))
                    return false;
        }
        return true;
    }
}
