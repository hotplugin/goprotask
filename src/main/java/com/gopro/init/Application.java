package com.gopro.init;

import com.gopro.pojos.LocationPojo;
import com.gopro.utils.ApiCaller;
import com.gopro.utils.CsvCreator;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        System.out.println("Hello! Enter Germany's any city Name: ");
        String path = ClassLoader.getSystemClassLoader().getClass().getResource("csv/a.txt").getPath();
        System.out.println(path);
//        String cityName;
//        Scanner scanIn = new Scanner(System.in);
//        cityName = scanIn.nextLine();
//        scanIn.close();
//        if (cityName.isEmpty()) {
//            System.out.println("Oops! you forgot to actually enter a city name !");
//        } else {
//            ApiCaller apicaller = new ApiCaller();
//            List<LocationPojo> locations = apicaller.generate(cityName.trim());
//            if (locations != null && !locations.isEmpty()) {
//                CsvCreator csv = new CsvCreator();
//                String absolutePath = "csv";
//                System.out.println("absolutepath "+absolutePath);
//                csv.writeCsvFile(absolutePath, locations);
//                System.out.println("Congratulations! Locations.csv saved in the RESOURCES folder!");
//            }
//        }

    }

}
