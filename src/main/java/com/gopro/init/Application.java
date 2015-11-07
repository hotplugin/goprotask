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
        outMsg("Hello! Welcome.");
        processCity();

    }

    public static void processCity() {
        outMsg("Enter Germany's any city Name: ");
        String cityName;
        Scanner scanIn = new Scanner(System.in);
        cityName = scanIn.nextLine();
        if (cityName.isEmpty()) {
            outMsg("Oops! you forgot to actually enter a city name !");
        } else {
            ApiCaller apicaller = new ApiCaller();
            List<LocationPojo> locations = apicaller.generate(cityName.trim());
            String path = ClassLoader.getSystemClassLoader().getClass().getResource("/csv/").getPath();
            String savepath = path.concat(cityName).concat(".csv");

            if (locations != null && !locations.isEmpty()) {
                CsvCreator csv = new CsvCreator();
                csv.writeCsvFile(savepath, locations);
                outMsg("Congratulations! File Saved to :  " + savepath);
            } else {
                outMsg("Could not locate such city! :( ");
                outMsg("Try again? Y or N");
                String choseOption;
                choseOption = scanIn.nextLine();

                if (choseOption.equalsIgnoreCase("Y")) {
                    processCity();
                } else {
                    outMsg("BYE!");
                    scanIn.close();
                }
            }
        }

    }

    public static void outMsg(String msg) {
        System.out.println(msg);
    }

}
