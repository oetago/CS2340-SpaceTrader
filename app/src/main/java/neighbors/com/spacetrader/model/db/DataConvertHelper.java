package neighbors.com.spacetrader.model.db;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

import neighbors.com.spacetrader.model.Good;
import neighbors.com.spacetrader.model.Planet;
import neighbors.com.spacetrader.model.SkillType;

/**
 * Class to help with data conversions for database
 */
public class DataConvertHelper {

    /**
     * Converts a map of skills into a JSON room can use
     * @param skills our map of skills
     * @return JSON for ROOM
     */
    public static String skillsToJson(Map<SkillType, Integer> skills) {
        return new GsonBuilder().create().toJson(skills);
    }

    /**
     * Converts a JSON representation of our skill map into a the map of skills
     * @param skills JSON to convert
     * @return our map of skills
     */
    public static Map<SkillType, Integer> jsonToSkills(String skills) {
        return new Gson().fromJson(skills,
                new TypeToken<Map<SkillType, Integer>>() {
                }.getType());
    }

    /**
     * Converts a map of inventory into a JSON room can use
     * @param inventory our map of inventory
     * @return JSON for room
     */
    public static String inventoryToJson(Map<Good, Integer> inventory) {
        return new GsonBuilder().create().toJson(inventory);
    }

    /**
     * Converts a JSON representation of our inventory into the map of inventory
     * @param inventory JSON to convert
     * @return our map of inventory
     */
    public static Map<Good, Integer> jsonToInventory(String inventory) {
        return new Gson().fromJson(inventory,
                new TypeToken<Map<Good, Integer>>() {
                }.getType());
    }

    /**
     * Converts a list of planets into a JSON our db can use
     * @param planets list of planets to convert
     * @return JSON to store in room
     */
    public static String planetsToJson(List<Planet> planets) {
        return new GsonBuilder().create().toJson(planets);
    }

    /**
     * Converts a JSON into a list of planets
     * @param planet JSON to convert
     * @return list of planets
     */
    public static List<Planet> jsonToPlanets(String planet) {
        return new Gson().fromJson(planet,
                new TypeToken<List<Planet>>() {
                }.getType());
    }
}
