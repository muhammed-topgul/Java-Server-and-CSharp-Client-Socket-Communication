package com.redevio.server;

import com.redevio.database.DataManager;
import com.redevio.models.Patient;
import com.redevio.models.PatientPhoto;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by Muhammed Topgul.
 * Date: 17.04.2020
 * Time: 00:32
 */

public class Echoer extends Thread {

    private Socket socket;

    public Echoer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (true) {
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();

                // Receiving
                byte[] bytesLenght = new byte[4];
                inputStream.read(bytesLenght, 0, 4);
                int len = (((bytesLenght[3] & 0xff) << 24) | ((bytesLenght[2] & 0xff) << 16)
                        | ((bytesLenght[1] & 0xff) << 8) | (bytesLenght[0] & 0xff));
                byte[] receivedBytes = new byte[len];
                inputStream.read(receivedBytes, 0, len);
                String request = new String(receivedBytes, 0, len);
                String response;

                System.out.println(request);

                String version1 = request.substring(1, request.length() - 1);
                String[] version2 = version1.split("-");

                if (version2[0].equals("[login]")) {
                    String version3 = version2[1].substring(1, version2[1].length() - 1);
                    String[] version4 = version3.split(",");

                    response = String.valueOf(DataManager.login(version4[0], version4[1]));
                } else if (version2[0].equals("[register]")) {
                    String version3 = version2[1].substring(1, version2[1].length() - 1);
                    String[] version4 = version3.split(",");

                    response = String.valueOf(DataManager.register(version4[0], version4[1]));
                } else if (version2[0].equals("[get_patients_by_doctor_id]")) {
                    String version3 = version2[1].substring(1, version2[1].length() - 1);
                    String[] version4 = version3.split(",");
                    response = DataManager.getPatientByDoctorIDOrPatientID(version4[0], "all");
                } else if (version2[0].equals("[get_patient_by_patient_id]")) {
                    String version3 = version2[1].substring(1, version2[1].length() - 1);
                    String[] version4 = version3.split(",");
                    response = DataManager.getPatientByDoctorIDOrPatientID(version4[0], "single");
                } else if (version2[0].equals("[get_patient_photos_by_id]")) {
                    String version3 = version2[1].substring(1, version2[1].length() - 1);
                    String[] version4 = version3.split(",");
                    System.out.println("kod: " + version4[0]);
                    response = DataManager.getPatientPhotoByPatientID(version4[0]);
                } else if (version2[0].equals("[add_new_patient_by_doctor_id]")) {
                    String version3 = version2[1].substring(1, version2[1].length() - 1);
                    String[] version4 = version3.split(",");

                    response = String.valueOf(DataManager.addPatientByDoctorID(new Patient(version4[0], version4[1], version4[2], version4[3], version4[4], version4[5], version4[6], version4[7], version4[8], version4[9], version4[10])));
                } else if (version2[0].equals("[update_patient_by_patient_id]")) {
                    String version3 = version2[1].substring(1, version2[1].length() - 1);
                    String[] version4 = version3.split(",");

                    response = String.valueOf(DataManager.updatePatientByPatientID(new Patient(version4[0], version4[1], version4[2], version4[3], version4[4], version4[5], version4[6], version4[7], version4[8], version4[9], version4[10], version4[11])));
                } else if (version2[0].equals("[delete_patient_by_patient_id]")) {
                    String version3 = version2[1].substring(1, version2[1].length() - 1);
                    String[] version4 = version3.split(",");

                    response = String.valueOf(DataManager.deletePatientByID(version4[0]));
                } else if (version2[0].equals("[add_new_photo_by_patient_id]")) {
                    String version3 = version2[1].substring(1, version2[1].length() - 1);
                    String[] version4 = version3.split(",");

                    response = String.valueOf(DataManager.addPatientPhotoByPatientID(new PatientPhoto(version4[0], version4[1])));
                } else if (version2[0].equals("[delete_photo_by_patient_id]")) {
                    String version3 = version2[1].substring(1, version2[1].length() - 1);
                    String[] version4 = version3.split(",");

                    response = String.valueOf(DataManager.deletePatientPhotoByID(version4[0]));
                } else {
                    response = "Undefined Request";
                }


                // response
                byte[] sendingBytes = response.getBytes();
                int sendLength = sendingBytes.length;
                byte[] sendingByteLength = new byte[4];
                sendingByteLength[0] = (byte) (sendLength & 0xff);
                sendingByteLength[1] = (byte) ((sendLength >> 8) & 0xff);
                sendingByteLength[2] = (byte) ((sendLength >> 16) & 0xff);
                sendingByteLength[3] = (byte) ((sendLength >> 24) & 0xff);
                outputStream.write(sendingByteLength);
                outputStream.write(sendingBytes);
            }

        } catch (Exception ex) {
            System.out.println("Error : " + ex.getMessage());
        } finally {
            try {
                socket.close();
            } catch (Exception ex) {
                System.out.println("Error : " + ex.getMessage());
            }
        }
    }
}