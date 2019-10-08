package main.java.service;


import main.java.dao.UserDAO;
import main.java.exception.DBException;
import main.java.model.User;


import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    public UserService() {
    }

    public User getUserByName(String name) { // Почему нет исключения?????
        return getUserDAO().getUserByName(name);
    }


    public List<User> getAllUsers() /*throws DBException*/ {// Почему нет исключения????? - оно в методе в ДАО!!!

        return getUserDAO().getAllUsers();
    }

    public boolean addUser(User user)  /* throws DBException*/ {// Idea говорит, что такого исключения нет???
        if (getUserByName(user.getName()) == null) {// Проверка, существует ли клиент в сервисе. Добавить если нет
            getUserDAO().addUser(user);
            return true;
        }
        return false;
    }

    public boolean deleteUser(String name) { // add deleteClient to BankClientDAO
        if (getUserByName(name) != null) {// Проверка, существует ли клиент в сервисе. Удалить если есть
            getUserDAO().deleteUser(name);
            return true;
        }
        return false;
    }


/*
    // add validateClient to BankClientDAO
    public boolean validateClient(String name, String password) throws SQLException {
        if (getBankClientDAO().validateClient(name, password)) {
            return true;
            //} else {
            //    return false;
            // }
        }
        return false;
    }

 */
/*
    // add sendMoneyToClient to BankClientDAO
    public boolean sendMoneyToClient(BankClient sender, String name, Long value) throws DBException {
        try {
            getBankClientDAO().sendMoneyToClient(sender, name, value); // добавить if - true?????
         //   return true;
        } catch (SQLException e) {
              throw new DBException(e);
            //return false;
        }

        return false;
    }
*/




    public void cleanUp() throws DBException {
        UserDAO dao = getUserDAO();
        try {
            dao.dropTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
    public void createTable() throws DBException{
        UserDAO dao = getUserDAO();
        try {
            dao.createTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    private static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("db_example888?").          //db name
                    append("user=root&").          //login Alex
                    append("password=19181938");       //password 19181938

            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    private static UserDAO getUserDAO() {
        return new UserDAO(getMysqlConnection());
    }
}
