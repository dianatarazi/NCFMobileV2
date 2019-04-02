package com.example.dianatarazi.ncfmobilev2;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import androidx.room.TypeConverter;

public class MapConverter {

    @TypeConverter
    public static HashMap<String, String> fromString(String value) {
        Type mapType = new TypeToken<HashMap<String,String>>()
        {}.getType();

        return new Gson().fromJson(value, mapType);
    }

    @TypeConverter
    public static String fromMap(HashMap<String, String> map) {
        return new Gson().toJson(map);
    }
}
