package main.java.dao;

//import com.sun.deploy.util.SessionState; ???????????? не грузится

import main.java.model.User;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public List<User> getAllUsers() {// throws SQLException - зачем throws SQLe????????
        List<User> allUsers = new ArrayList<>();
String sql = "SELECT * FROM users";

        try (PreparedStatement preStmt = connection.prepareStatement(sql)) {

            ResultSet result = preStmt.getResultSet();
            while (result.next()) {
                long idUser = result.getLong(1);
                String nameUser = result.getString(2);
                String loginUser = result.getString(3);
                String passwordUser = result.getString(4);

                allUsers.add(new User(idUser, nameUser, loginUser, passwordUser));
            }
       //     result.close();
       // stmt.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

        return allUsers;
    }
/*
    public boolean validateClient(String name, String password) throws SQLException {
        BankClient bankClient = getClientByName(name); //присвоить переменной клиента,найденного по ИМЕНИ
        if (bankClient.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }
*/
/*
    public void updateClientsMoney(String name, String password, Long transactValue) throws SQLException {
        //if (validateClient(name, password)) {
        BankClient bankClient = getClientByName(name);
        long updatedMoney = bankClient.getMoney() + transactValue;
        String requestSQL = "UPDATE bank_client SET money = ? WHERE name = ?";
        PreparedStatement preStmt = connection.prepareStatement(requestSQL);

        preStmt.setLong(1, updatedMoney);
        preStmt.setString(2, bankClient.getName());
        preStmt.executeUpdate();
        preStmt.close();

    }
*/


    public User getUserById(long id) /*throws SQLException*/ {
        User user = null;
        String sql = "SELECT * FROM users FROM users WHERE id = ?";
        try (PreparedStatement preStmt = connection.prepareStatement(sql)) {
            preStmt.setLong(1, id);

            ResultSet result = preStmt.getResultSet();
            result.next();
            long idUser = result.getLong(1);
            String nameUser = result.getString(2);
            String loginUser = result.getString(3);
            String passwordUser = result.getString(4);


            user = new User(idUser, nameUser, loginUser, passwordUser);


        } catch (SQLException e) {
            e.printStackTrace();
            // return null;
        }
        return user;
    }


    public long getUserIdByName(String name) /*throws SQLException*/ {
        String sql = "SELECT * FROM users WHERE name = ?";
        long id = 0L;
        try (PreparedStatement preStmt = connection.prepareStatement(sql)) {
            preStmt.setString(1, name);

            ResultSet result = preStmt.getResultSet();
            result.next();
            id = result.getLong(1);

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return id;
    }

    public User getUserByName(String name) /*throws SQLException*/ { // Добавил throws SQLException или НЕТ???

        User user = null;
        long id = 0L;
        String sql = "SELECT * FROM users WHERE name = ?";
        try (PreparedStatement preStmt = connection.prepareStatement(sql)) {
            preStmt.setLong(1, id);

            ResultSet result = preStmt.getResultSet();
            result.next();
            long idUser = result.getLong(1);
            String nameUser = result.getString(2);
            String loginUser = result.getString(3);
            String passwordUser = result.getString(4);



            user = new User(idUser, nameUser, loginUser, passwordUser);


        } catch (SQLException e) {
            e.printStackTrace();
            // return null;
        } return user;
    }


    //   public void addClient(BankClient client) /*throws DBException*/ {


/////////////////////////////////////////////////////////////////

    public void addUser(User user) {
        String sql ="INSERT INTO users (name, login, password) VALUES (?, ?, ?)";
        try (PreparedStatement preStmt = connection.prepareStatement(sql)) {
            preStmt.setString(1, user.getName());
            preStmt.setString(2, user.getLogin());
            preStmt.setString(3, user.getPassword());

            preStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // add deleteClient to BankClientDAO
    public void deleteUser(String name) /*throws SQLException*/ {// void or boolean
        User user = getUserByName(name); // а проверка имени клиента??? в Сервисе?
        String sql = "DELETE * FROM users WHERE name = ?";
        try (PreparedStatement preStmt = connection.prepareStatement(sql)) {
            preStmt.setString(1, user.getName());

            preStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


///////////////////////////////////////////////////////////


    public void createTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("CREATE TABLE if NOT EXISTS users (id bigint auto_increment, name varchar(256), login varchar(256), password varchar(256), primary key (id))");
        stmt.close();
    }

    public void dropTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DROP TABLE if EXISTS users");
        stmt.close();
    }




}
