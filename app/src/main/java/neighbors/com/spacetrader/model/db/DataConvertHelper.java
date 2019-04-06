package neighbors.com.spacetrader.model.db;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

import neighbors.com.spacetrader.model.Good;
import neighbors.com.spacetrader.model.Planet;
import neighbors.com.spacetrader.model.SkillType;

public class DataConvertHelper {

    public static String skillsToJson(Map<SkillType, Integer> skills) {
        return new GsonBuilder().create().toJson(skills);
    }


    public static Map<SkillType, Integer> jsonToSkills(String skills) {
        return new Gson().fromJson(skills,
                new TypeToken<Map<SkillType, Integer>>() {
                }.getType());
    }

    public static String inventoryToJson(Map<Good, Integer> inventory) {
        return new GsonBuilder().create().toJson(inventory);
    }

    public static Map<Good, Integer> jsonToInventory(String inventory) {
        return new Gson().fromJson(inventory,
                new TypeToken<Map<Good, Integer>>() {
                }.getType());
    }

    public static String planetsToJson(List<Planet> planets) {
        return new GsonBuilder().create().toJson(planets);
    }

    public static List<Planet> jsonToPlanents(String planet) {
        return new Gson().fromJson(planet,
                new TypeToken<List<Planet>>() {
                }.getType());
    }
}
