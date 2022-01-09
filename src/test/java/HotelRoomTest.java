import model.Hotel;
import service.HotelBook;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
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
        hotelbook.addWeekdayWeekendRates();
        Integer room_rate = hotelbook.getHotelReservationSystem().get(0).getRoomRateWeekday();
        Assertions.assertEquals(110,room_rate);
    }
    @Test
    public void givenDateRange_WhenSearchCheapestHotels_ShouldReturnCheapstHotel(){
        // Add three hotels
        hotelbook.addHotel(new Hotel("Lakewood",110,90));
        hotelbook.addHotel(new Hotel("Bridgewood",150,50));
        hotelbook.addHotel(new Hotel("Ridgewood",220,150));

        // Dates for which cheap hotel has to be searched
        LocalDate localDate1 = LocalDate.of(2020,9,11);
        LocalDate localDate2 = LocalDate.of(2020,9,12);
        ArrayList<String[]> chp_hotels = hotelbook.findCheapHotelWeekDayOrEnd(localDate1, localDate2);

        // Assert total rates and hotel names
        assertEquals("200",chp_hotels.get(0)[1]);
        assertEquals("200",chp_hotels.get(1)[1]);
        assertEquals("Lakewood",chp_hotels.get(0)[0]);
        assertEquals("Bridgewood",chp_hotels.get(1)[0]);
    }
}
