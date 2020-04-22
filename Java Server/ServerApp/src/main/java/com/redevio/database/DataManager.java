package com.redevio.database;

import com.redevio.models.Patient;
import com.redevio.models.PatientPhoto;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Muhammed Topgul.
 * Date: 17.04.2020
 * Time: 00:35
 */
public class DataManager {
    private static Connection connection;
    private static ResultSet resultSet;
    private static PreparedStatement preparedStatement;
    private static Statement statement;

    public static boolean login(String doctorID, String password) {
        connection = DatabaseConnection.getConnection();

        String query = "Select * From tbl_doctor Where id = ? and password = ?";

        try {
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, doctorID);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();

            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection(connection);

        return false;
    }

    public static boolean register(String doctorName, String password) {
        connection = DatabaseConnection.getConnection();

        String query = "INSERT INTO `unity_hair_3d`.`tbl_doctor` (`name`, `password`) VALUES (?, ?);";

        try {
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, doctorName);
            preparedStatement.setString(2, password);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        closeConnection(connection);
        return true;
    }

    public static String getPatientByDoctorIDOrPatientID(String ID, String type) {

        ArrayList<Patient> patients = new ArrayList<Patient>();
        connection = DatabaseConnection.getConnection();
        String query;
        if (type.equals("all")) {
            query = "SELECT * FROM unity_hair_3d.tbl_patient Where doctor_id = " + ID;
        } else {
            query = "SELECT * FROM unity_hair_3d.tbl_patient Where id = " + ID;
        }

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String tel = resultSet.getString("tel");
                String age = resultSet.getString("age");
                String address = resultSet.getString("address");
                String hairCaliber = resultSet.getString("hair_caliber");
                String hairColour = resultSet.getString("hair_colour");
                String gender = resultSet.getString("gender");
                String donor = resultSet.getString("donor");
                String norwoodScale = resultSet.getString("norwood_scale");
                String doctorID = resultSet.getString("doctor_id");

                patients.add(new Patient(id, name, email, tel, age, address, hairCaliber, hairColour, gender, donor, norwoodScale, doctorID));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        closeConnection(connection);

        return appendOperationForPatients(patients);
    }

    private static String appendOperationForPatients(ArrayList<Patient> list) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            builder.append("{");
            builder.append(list.get(i).getId());
            builder.append(",");
            builder.append(list.get(i).getName());
            builder.append(",");
            builder.append(list.get(i).getEmail());
            builder.append(",");
            builder.append(list.get(i).getTel());
            builder.append(",");
            builder.append(list.get(i).getAge());
            builder.append(",");
            builder.append(list.get(i).getAddress());
            builder.append(",");
            builder.append(list.get(i).getHairCaliber());
            builder.append(",");
            builder.append(list.get(i).getHairColour());
            builder.append(",");
            builder.append(list.get(i).getGender());
            builder.append(",");
            builder.append(list.get(i).getDonor());
            builder.append(",");
            builder.append(list.get(i).getNorwoodScale());
            builder.append(",");
            builder.append("} ");
            builder.deleteCharAt(builder.lastIndexOf(","));
        }

        builder.deleteCharAt(builder.lastIndexOf(" "));
        System.out.println("[" + builder.toString() + "]");

        return "[" + builder + "]";
    }

    public static String getPatientPhotoByPatientID(String patientID) {
        ArrayList<PatientPhoto> patientPhotos = new ArrayList<PatientPhoto>();
        connection = DatabaseConnection.getConnection();

        try {
            String query = "SELECT * FROM unity_hair_3d.tbl_patient_photo Where patient_id = " + patientID;
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String photoName = resultSet.getString("photo_name");

                patientPhotos.add(new PatientPhoto(id, photoName));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return appendOperationForPatientPhotos(patientPhotos);
    }

    private static String appendOperationForPatientPhotos(ArrayList<PatientPhoto> list) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            builder.append("{");
            builder.append(list.get(i).getId());
            builder.append(",");
            builder.append(list.get(i).getPhotoName());
            builder.append(",");
            builder.append("} ");
            builder.deleteCharAt(builder.lastIndexOf(","));
        }
        builder.deleteCharAt(builder.lastIndexOf(" "));
        System.out.println("[" + builder.toString() + "]");

        return "[" + builder + "]";
    }

    public static boolean addPatientByDoctorID(Patient patient) {

        connection = DatabaseConnection.getConnection();

        try {
            String query = "INSERT INTO `unity_hair_3d`.`tbl_patient` (`name`, `email`, `tel`, `age`, `address`, `hair_caliber`, `hair_colour`, `gender`, `donor`, `norwood_scale`, `doctor_id`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, patient.getName());
            preparedStatement.setString(2, patient.getEmail());
            preparedStatement.setString(3, patient.getTel());
            preparedStatement.setString(4, patient.getAge());
            preparedStatement.setString(5, patient.getAddress());
            preparedStatement.setString(6, patient.getHairCaliber());
            preparedStatement.setString(7, patient.getHairColour());
            preparedStatement.setString(8, patient.getGender());
            preparedStatement.setString(9, patient.getDonor());
            preparedStatement.setString(10, patient.getNorwoodScale());
            preparedStatement.setInt(11, Integer.valueOf(patient.getDoctorID()));

            preparedStatement.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

        closeConnection(connection);

        return true;
    }

    public static boolean updatePatientByPatientID(Patient patient) {
        connection = DatabaseConnection.getConnection();

        try {
            String query = "UPDATE `unity_hair_3d`.`tbl_patient` SET `name` = ?, `email` =  ?, `tel` = ?, `age` = ?, `address` = ?, `hair_caliber` = ?, `hair_colour` = ?, `gender` = ?, `donor` = ?, `norwood_scale` = ?, `doctor_id` = ? WHERE (`id` = ?)";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, patient.getName());
            preparedStatement.setString(2, patient.getEmail());
            preparedStatement.setString(3, patient.getTel());
            preparedStatement.setString(4, patient.getAge());
            preparedStatement.setString(5, patient.getAddress());
            preparedStatement.setString(6, patient.getHairCaliber());
            preparedStatement.setString(7, patient.getHairColour());
            preparedStatement.setString(8, patient.getGender());
            preparedStatement.setString(9, patient.getDonor());
            preparedStatement.setString(10, patient.getNorwoodScale());
            preparedStatement.setInt(11, Integer.valueOf(patient.getDoctorID()));
            preparedStatement.setInt(12, Integer.valueOf(patient.getId()));

            preparedStatement.executeUpdate();

        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }

        return true;
    }

    public static boolean deletePatientByID(String patientID) {
        connection = DatabaseConnection.getConnection();

        try {
            preparedStatement = connection.prepareStatement("DELETE FROM `unity_hair_3d`.`tbl_patient` WHERE `id` = " + patientID);
            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

        try {
            preparedStatement = connection.prepareStatement("DELETE FROM `unity_hair_3d`.`tbl_patient_photo` WHERE `patient_id` = " + patientID);
            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

        closeConnection(connection);
        return true;
    }

    public static boolean addPatientPhotoByPatientID(PatientPhoto patientPhoto) {
        connection = DatabaseConnection.getConnection();

        try {
            String query = "INSERT INTO `unity_hair_3d`.`tbl_patient_photo` (`photo_name`, `patient_id`) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, patientPhoto.getPhotoName());
            preparedStatement.setString(2, patientPhoto.getId());


            preparedStatement.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

        closeConnection(connection);

        return true;
    }

    public static boolean deletePatientPhotoByID(String patientID) {
        connection = DatabaseConnection.getConnection();

        try {
            preparedStatement = connection.prepareStatement("DELETE FROM `unity_hair_3d`.`tbl_patient_photo` WHERE `id` = " + patientID);
            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

        closeConnection(connection);
        return true;
    }

    private static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        // System.out.println(DataManager.login("1001", "2222"));
//        // DataManager.getAllPatientsByDoctorID("1000");
//         DataManager.getPatientPhotoByPatientID("3");
//    }
}
