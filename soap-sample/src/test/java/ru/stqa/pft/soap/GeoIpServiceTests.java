package ru.stqa.pft.soap;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Пользователь on 10.08.2017.
 */
public class GeoIpServiceTests {

    @Test
    public void testMyIp(){
        GeoIp geoIp = new GeoIpService().getGeoIpServiceSoap12().getGeoIp("109.188.66.148");
        Assert.assertEquals(geoIp.getCountryCode(), "RUS");
    }
}
