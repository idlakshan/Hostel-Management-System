package lk.ijse.D24.bo.custom.impl;

import lk.ijse.D24.bo.custom.UserBO;
import lk.ijse.D24.dao.custom.UserDAO;
import lk.ijse.D24.dao.custom.impl.UserDAOImpl;
import lk.ijse.D24.dto.UserDTO;
import lk.ijse.D24.entity.User;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {

    private final UserDAO userDAO=new UserDAOImpl();


    @Override
    public List<UserDTO> loadAllUsers() throws SQLException, ClassNotFoundException, IOException {

        List<User> users = userDAO.loadAll();
        ArrayList<UserDTO> arrayList = new ArrayList<>();

        for (User user : users) {
            arrayList.add(new UserDTO(
                    user.getUserId(),
                    user.getUserName(),
                    user.getPassword()
            ));

        }
        return arrayList;
    }
}
