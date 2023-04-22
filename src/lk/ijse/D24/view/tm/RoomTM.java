package lk.ijse.D24.view.tm;

import lk.ijse.D24.dto.RoomDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomTM extends RoomDTO {
    private String roomId;
    private String type;
    private double keyMoney;
    private int qty;

}
