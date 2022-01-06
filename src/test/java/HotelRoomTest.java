import Service.HotelRoom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;

public class HotelRoomTest {
    HotelRoom hotelroom = new HotelRoom();

    @Test
    public void givenRoom_WhenAdded_ShouldReturnTrue(){
        ArrayList<Object> hotel_ad = new ArrayList<>();
        hotel_ad.add("Taj");
        hotel_ad.add(5);
        hotel_ad.add(200);
        hotel_ad.add(300);
        boolean result_hotel_added = hotelroom.addHotelRoom(hotel_ad);
        Assertions.assertEquals(true,result_hotel_added);
    }
}
