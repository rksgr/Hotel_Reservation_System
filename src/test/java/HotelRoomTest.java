import model.Hotel;
import service.HotelBook;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.lang.reflect.Array;
import java.time.LocalDate;

public class HotelRoomTest {
    HotelBook hotelroom = new HotelBook();

    @Test
    public void givenRoom_WhenAdded_ShouldReturnTrue(){
        Hotel hotel_ad = new Hotel("Taj",200);
        boolean result_hotel_added = hotelroom.addHotel(hotel_ad);
        Assertions.assertEquals(true,result_hotel_added);
    }
    @Test
    public void givenDateRange_WhenSearched_ShouldReturnCheapestHotel(){
        // Date range
        LocalDate localdate1 = LocalDate.of(2020,9,10);
        LocalDate localdate2 = LocalDate.of(2020,9,11);
        String chp_hot = hotelroom.findCheapestHotel(localdate1,localdate2);
        System.out.println(chp_hot);
        Assertions.assertEquals("Lakewood",chp_hot);
    }
}
