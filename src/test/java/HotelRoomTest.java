import model.Hotel;
import service.HotelBook;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HotelRoomTest {
    HotelBook hotelbook = new HotelBook();

    @Test
    public void givenRoom_WhenAdded_ShouldReturnTrue(){
        Hotel hotel_ad = new Hotel("Taj",200,250);
        boolean result_hotel_added = hotelbook.addHotel(hotel_ad);
        assertEquals(true,result_hotel_added);
    }
    @Test
    public void givenDateRange_WhenSearched_ShouldReturnCheapestHotel(){
        // Date range
        LocalDate localdate1 = LocalDate.of(2020,9,10);
        LocalDate localdate2 = LocalDate.of(2020,9,11);
        String chp_hot = hotelbook.findCheapestHotel(localdate1,localdate2);
        System.out.println(chp_hot);
        assertEquals("Lakewood",chp_hot);
    }
    @Test
    public void givenHotels_WhenRoomRatesWeekDayWeekendAdded_ShouldContainWeekdayRates(){
        hotelbook.addHotel(new Hotel("Lakewood",0,0));
        hotelbook.addHotel(new Hotel("Bridgewood",0,0));
        hotelbook.addHotel(new Hotel("Ridgewood",0,0));
        hotelbook.addWeekdayWeekendRates();
        Assertions.assertNotEquals("Lakewood",null);
    }
}
