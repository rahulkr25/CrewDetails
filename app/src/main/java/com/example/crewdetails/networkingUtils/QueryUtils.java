package com.example.crewdetails.networkingUtils;

import android.util.Log;

import com.example.crewdetails.database.CrewEntry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public final class QueryUtils {

    private QueryUtils() {
    }

    public static ArrayList<CrewEntry> extractCrew(String url) {

        URL urlCrew = createUrl(url);
        ArrayList<CrewEntry> crewDetails;
        String crew_string="";
        try {
            crew_string= makeHttpRequest(urlCrew);
        } catch (IOException e) {


            Log.e("CrewUtils","couldn't get response back from  the given url",e);
            return null;
        }

        // Extract relevant fields from the JSON response and create an {@link Event} object
        crewDetails = extractCrewFromJson(crew_string);

        // Return the {@link Event} object as the result fo the {@link TsunamiAsyncTask}
        return crewDetails;
        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.


        // Return the list of earthquakes

    }
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException exception) {
            Log.e("QueryUtils", "Error with creating URL", exception);
            return null;
        }
        return url;
    }
    public static ArrayList<CrewEntry> extractCrewFromJson(String CrewJSON) {
        ArrayList< CrewEntry>crewEntries=new ArrayList<>();

        try {
           /* JSONObject root=new JSONObject(CrewJSON);
            JSONArray features=root.optJSONArray("features");
            for(int i=0;i<features.length();i++)
            {
                JSONObject features_object=features.getJSONObject(i);
                JSONObject properties=features_object.getJSONObject("properties");
                String mag= properties.optString("mag");
                String place=properties.optString("place");
                // String time=properties.optString("time");
                long time_milisecond=properties.getLong("time");
                /*
                String url=properties.getString("url");
                earthquakes.add(new CrewEntry(mag,place,time_milisecond,url));
            }

            // build up a list of Earthquake objects with the corresponding data.
              */
            ////////////////////////////////////////////////////
            JSONArray jsonArray=new JSONArray(CrewJSON);
            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                String name=jsonObject.optString("name");
                String agency=jsonObject.optString("agency");
                String imageUrl=jsonObject.optString("image");
                String wikiUrl=jsonObject.optString("wikipedia");
                String status=jsonObject.optString("status");
                crewEntries.add(new CrewEntry(name,status,agency,imageUrl,wikiUrl));
            }




            /////////////////////////////////////////////////////
        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return crewEntries;
    }
    private static String makeHttpRequest(URL url) throws IOException {
        if(url==null)
        {
            return "";
        }
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();
            if(urlConnection.getResponseCode()==200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
            else
            {
                //Log.e(LOG_TAG,"Other code");
            }

        }
        catch (ProtocolException e) {
            //  Log.e(LOG_TAG,"Request Method isnt valid for HTTP",e);
        }catch (IOException e) {

            // Log.e(LOG_TAG,"Problem from reading input Stream",e);
        }

        finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // function must handle java.io.IOException here
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
}
