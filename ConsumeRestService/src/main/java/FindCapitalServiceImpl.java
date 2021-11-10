
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import service.FindCapitalService;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FindCapitalServiceImpl implements FindCapitalService {

    private static final String baseUrl = "https://restcountries.com/v3.1/";

    private List<String> findCapital(final URL url) {
        List<String> capitalList = new ArrayList<>();
        StringBuilder responseText = new StringBuilder();
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader inputStreamReader = new InputStreamReader(conn.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                responseText.append(line);
            }
            try {
                JSONArray jsonarray = new JSONArray(responseText.toString());
                JSONObject content = jsonarray.getJSONObject(0);
                JSONArray capitals = content.getJSONArray("capital");
                for (int i = 0; i < capitals.length(); i++) {
                    capitalList.add(capitals.getString(i));
                }
            } catch (JSONException jsonException) {
                System.out.println(jsonException.getMessage());
            }
            conn.disconnect();
        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }
        return capitalList;
    }

    @Override
    public List<String> findCapitalByName(String countryName) {
        URL requestUrl = null;
        try {
            requestUrl = new URL(baseUrl + "name/" + countryName + "?fullText=true");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return findCapital(requestUrl);
    }

    @Override
    public List<String> findCapitalByCode(String countryCode) {
        URL requestUrl = null;
        try {
            requestUrl = new URL(baseUrl + "alpha/" + countryCode);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return findCapital(requestUrl);
    }

    @Override
    public void findCapitalsByFile(String filePath) {
        URL requestUrl = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    requestUrl = new URL(baseUrl + "/name/" + line.trim() + "?fullText=true");
                    for (String capital : findCapital(requestUrl)) {
                        System.out.println("country:"+line.trim()+" "+"capital:"+capital);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public static void main(String[] args) {
        int option;
        List<String> capitals = new ArrayList<>();
        FindCapitalServiceImpl capitalService = new FindCapitalServiceImpl();
        do {
            System.out.println("Choose one of the below option");
            System.out.println("0. Read from file");
            System.out.println("1. Search capital by country name");
            System.out.println("2. Search capital by county code");
            System.out.println("3. Exit the search");
            Scanner input = new Scanner(System.in);
            option = input.nextInt();
            if (option != 3) {
                if (option == 0) {
                    System.out.print("Give the file path:");
                    String filePath = input.next();
                    capitalService.findCapitalsByFile(filePath);
                } else if (option == 1) {
                    System.out.print("Enter country name:");
                    String countryName = input.next();
                    capitals = capitalService.findCapitalByName(countryName);
                } else if (option == 2) {
                    System.out.print("Enter country code:");
                    String countryCode = input.next();
                    capitals = capitalService.findCapitalByCode(countryCode);
                }
                for (String capital : capitals) {
                    System.out.println(capital);
                }
            }
        } while (option != 3);
    }
}
