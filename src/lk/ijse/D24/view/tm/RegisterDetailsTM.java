package lk.ijse.D24.view.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterDetailsTM {
    private String registerID;
    private LocalDate date;
    private String studentId;
    private String name;
    private String contactNo;

}
