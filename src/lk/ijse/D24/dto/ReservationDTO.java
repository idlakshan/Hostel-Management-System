package lk.ijse.D24.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservationDTO {
    private String registerID;
    private LocalDate date;
    private String studentID;
    private String roomID;
    private String status;

}
