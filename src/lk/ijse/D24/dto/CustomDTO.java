package lk.ijse.D24.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomDTO {
    private String studentId;
    private String name;
    private String address;
    private String contactNo;
    private LocalDate dob;
    private String gender;

    private String roomId;
    private String type;
    private double keyMoney;
    private int qty;

    private String registerID;
    private LocalDate date;
    private String status;

}
