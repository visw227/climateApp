package com.londonappbrewery.clima_completed;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherDataModel {

    // Member variables that hold our relevant weather inforomation.
    private String mTemperature;
    private String mCity;
    private String mIcon;
    private int mCondition;
    private String mWind;
    private  String mCloud;
    private  String mPressure;
    private  String mHumidity;
    private  String mSunrise;
    private String mDescription;




    // Create a WeatherDataModel from a JSON.

    public static WeatherDataModel fromJson(JSONObject jsonObject) {

        // JSON parsing is risky business. Need to surround the parsing code with a try-catch block.
        try {
            WeatherDataModel weatherData = new WeatherDataModel();

            weatherData.mCity = jsonObject.getString("name");
            weatherData.mWind = jsonObject.getJSONObject("wind").getString("speed");
            weatherData.mCloud = jsonObject.getJSONObject("clouds").getString("all");
            weatherData.mPressure = jsonObject.getJSONObject("main").getString("pressure");
            weatherData.mHumidity = jsonObject.getJSONObject("main").getString("humidity");
            weatherData.mSunrise = jsonObject.getJSONObject("sys").getString("sunrise");
            weatherData.mDescription = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");
            weatherData.mIcon = jsonObject.getJSONArray("weather").getJSONObject(0).getString("icon");
            //weatherData.mDescription = jsonObject.getJSONObject("weather").getString("description");
            String weatherInfo = jsonObject.getString("weather");

            //Log.i("Weather content", weatherInfo);



            double tempResult = jsonObject.getJSONObject("main").getDouble("temp") - 273.15;
            //double faren = (9/5)*tempResult);
            double farenheit = (tempResult*(9/5)) + 32;
            int roundedValue = (int) Math.rint(farenheit);

            weatherData.mTemperature = Integer.toString(roundedValue);

            return weatherData;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get the weather image name from OpenWeatherMap's condition (marked by a number code)
    private static String updateWeatherIcon(int condition) {

        if (condition >= 0 && condition < 300) {
            return "tstorm1";
        } else if (condition >= 300 && condition < 500) {
            return "light_rain";
        } else if (condition >= 500 && condition < 600) {
            return "shower3";
        } else if (condition >= 600 && condition <= 700) {
            return "snow4";
        } else if (condition >= 701 && condition <= 771) {
            return "fog";
        } else if (condition >= 772 && condition < 800) {
            return "tstorm3";
        } else if (condition == 800) {
            return "sunny";
        } else if (condition >= 801 && condition <= 804) {
            return "cloudy2";
        } else if (condition >= 900 && condition <= 902) {
            return "tstorm3";
        } else if (condition == 903) {
            return "snow5";
        } else if (condition == 904) {
            return "sunny";
        } else if (condition >= 905 && condition <= 1000) {
            return "tstorm3";
        }

        return "dunno";
    }

    // Getter methods for temperature, city, and icon name:

    public String getTemperature() {
        return mTemperature + "°F";
    }

    public String getCity() {
        return mCity;
    }


    public String getmWind() {
        return mWind + "   ";
    }

    public String getmCloud() {
        return mCloud+ "   ";
    }

    public String getmPressure() {
        return mPressure+ "   ";
    }

    public String getmHumidity() {
        return mHumidity+ "   ";
    }

    public String getmSunrise() {
        return mSunrise+ "   ";
    }
   public String getmDescription(){
        return mDescription;
  }

    public String getmIcon() {
        return mIcon;
    }
}
