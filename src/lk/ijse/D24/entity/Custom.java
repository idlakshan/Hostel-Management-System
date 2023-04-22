package lk.ijse.D24.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Custom {
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

    public Custom(String registerID, LocalDate date, String studentId, String name, String contactNo) {
        this.registerID = registerID;
        this.date = date;
        this.studentId = studentId;
        this.name = name;
        this.contactNo = contactNo;
    }

}
