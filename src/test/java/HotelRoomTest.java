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

    @Test
    public void whenRating_SetForEachHotel_FetchRatingOfHotelByName(){
        hotelbook.addRatingsToHotel("Lakewood",3);
        hotelbook.addRatingsToHotel("Bridgewood",4);
        hotelbook.addRatingsToHotel("Ridgewood",5);

        // Get rating of hotel Lakewood
        Integer ratng = hotelbook.getHotelReservationSystem().stream()
                .filter(hotel->hotel.getHotelName()=="Lakewood").map(hotel->hotel.getRating()).findFirst().get();

        assertEquals(3,ratng);
    }
    @Test
    public void findCheapestBestRatedHotelForGivenDateRange(){
        // Dates for which cheapest hotel with maximum rating has to be searched
        LocalDate locdat1 = LocalDate.of(2020,9,11);
        LocalDate locdat2 = LocalDate.of(2020,9,12);

        // Get cheapest hotel name, rating and total rate
        String[] cheapest_max_rating_hotel = hotelbook.findCheapestBestRatedHotel(locdat1,locdat2);
        String hotel_name = cheapest_max_rating_hotel[0];
        String hotel_rating = cheapest_max_rating_hotel[1];
        String hotel_total_rate = cheapest_max_rating_hotel[2];

        assertEquals("Bridgewood",hotel_name);
        assertEquals("4",hotel_rating);
        assertEquals("200",hotel_total_rate);
    }

    @Test
    public void givenDateRange_WhenSearchBestRatedHotel_ShouldReturnBestRatedHotelAndTotalRates(){
        // Dates for which hotel with maximum rating has to be searched
        LocalDate locdat1 = LocalDate.of(2020,9,11);
        LocalDate locdat2 = LocalDate.of(2020,9,12);

        String[] max_rating_hotel = hotelbook.findMaxRatedHotel(locdat1,locdat2);
        String hotel_name = max_rating_hotel[0];
        String hotel_total_rate = max_rating_hotel[1];

        assertEquals("Ridgewood",hotel_name);
        assertEquals("370",hotel_total_rate);
    }

    @Test
    public void afterSpecialRatesForRewardCustomersAdded_fetchWeekdayAndWeekendRatesForRewardCustomers(){
        LocalDate locdat = LocalDate.of(2020,10,11);

        // Add special weekday and weekend rates for reward customers
        hotelbook.addSpecialRatesRewardCustomers("Bridgewood",110,50);

        // Fetch weekday and weekend rates for reward customers
        Integer reward_cust_weekday_rate = hotelbook.getHotelReservationSystem().stream()
                                                    .filter(hotel->hotel.getHotelName()=="Bridgewood")
                                                    .findFirst().get().getRewardCustWeekdayRate();
        Integer reward_cust_weekend_rate = hotelbook.getHotelReservationSystem().stream()
                                                    .filter(hotel->hotel.getHotelName()=="Bridgewood")
                                                    .findFirst().get().getRewardCustWeekendRate();

        assertEquals("110",String.valueOf(reward_cust_weekday_rate));
        assertEquals("50",String.valueOf(reward_cust_weekend_rate));
    }
}
