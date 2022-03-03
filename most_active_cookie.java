/**
 * @author Revanth Cherukuri
 * 03/02/2022
 * Class to find the most active cookie(s) on a specified day.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class most_active_cookie {
    public static void main (String [] args) {
        String path = args[0];
        String line = "";

        if (args.length < 3) {
            System.out.println("Please provide the sufficient parameters: <filename> -d date (YYYY-MM-DD)");
            return;
        }
        String specifiedDay = args[2];

        ArrayList <String> mostActiveCookies = getMostActiveCookies(line, path, specifiedDay);

        if (mostActiveCookies.size() < 1) {
            System.out.println("No cookies were active on the date " + specifiedDay);
        }
        else {
            for (String mostActiveCookie : mostActiveCookies) {
                System.out.println(mostActiveCookie);
            }
        }

    }

    public static ArrayList<String> getMostActiveCookies (String line, String path, String specifiedDay) {
        HashMap <String, Integer> cookieFrequency = new HashMap <> ();
        int maxVal = 0;
        ArrayList <String> mostActiveCookies = new ArrayList <> ();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            br.readLine();

            while ((line = br.readLine()) != null) {
                String [] cookieInfo = line.split(",");
                String date = cookieInfo[1].substring(0, 10);   //can also use Date class
                if (date.equals(specifiedDay)) {
                    cookieFrequency.put(cookieInfo[0], cookieFrequency.getOrDefault(cookieInfo[0], 0) + 1);
                }
            }
            for (Map.Entry<String, Integer> entry : cookieFrequency.entrySet()) {
                maxVal = Math.max(entry.getValue(), maxVal);
            }
            for (Map.Entry<String, Integer> entry : cookieFrequency.entrySet()) {
                if (entry.getValue() == maxVal) {
                    mostActiveCookies.add(entry.getKey());
                }
            }
        }

        catch (IOException e) {
            e.printStackTrace();
        }

        return mostActiveCookies;
    }
}
