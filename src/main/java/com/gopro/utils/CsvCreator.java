/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gopro.utils;

import com.gopro.pojos.LocationPojo;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Dell
 */
public class CsvCreator {

    private static final String COMMA = ",";
    private static final String NEW_LINE = "\n";
    private static final String HEADER = "id,name,type,latitude,longitutde";

    public boolean writeCsvFile(String fileName, List<LocationPojo> locationPojo) {
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(fileName);
            fileWriter.append(HEADER);
            fileWriter.append(NEW_LINE);

            for (LocationPojo location : locationPojo) {
                fileWriter.append(String.valueOf(location.getId()));
                fileWriter.append(COMMA);
                fileWriter.append(location.getName());
                fileWriter.append(COMMA);
                fileWriter.append(location.getType());
                fileWriter.append(COMMA);
                fileWriter.append(String.valueOf(location.getLatitude()));
                fileWriter.append(COMMA);
                fileWriter.append(String.valueOf(location.getLongitude()));
                fileWriter.append(NEW_LINE);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return false;
    }
}
