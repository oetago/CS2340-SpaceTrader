package neighbors.com.spacetrader.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import androidx.room.TypeConverter;

/**
 * Class to convert our data objects into items Room can use for storage
 */
@SuppressWarnings("EmptyClass")
class DataConverters {

    /**
     * Converts our skill map to an object ROOM can use
     * @param skills skill map to convert
     * @return object Room can use
     */
    @TypeConverter
    public String fromSkillsMap(Map<SkillType,Integer> skills) {
        if (skills == null) {
            return null;
        }
        Iterator it = skills.entrySet().iterator();
        List<Object> listFromMap = new ArrayList<>();
        while(it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            listFromMap.add(pair.getKey());
            listFromMap.add(pair.getValue());
            it.remove();
        }

        Gson gson = new Gson();
        Type type = new TypeToken<List<Object>>() {}.getType();
        return gson.toJson(listFromMap, type);
    }

    /**
     * Converts a JSON string into our skills map
     * @param skillsMapString JSON string to convert
     * @return our skills map
     */
    @TypeConverter
    public Map<SkillType, Integer> toSkillMap(String skillsMapString) {
        if (skillsMapString == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Object>>() {}.getType();
        List<Object> skillsList = gson.fromJson(skillsMapString, type);

        Map<SkillType, Integer> mapToReturn = new HashMap<>();
        for(int i = 0; i < skillsList.size(); i += 2){
            mapToReturn.put(SkillType.valueOf((String) skillsList.get(i)),
                    ((Double) skillsList.get(i + 1)).intValue());
        }
        return mapToReturn;
    }

    /**
     * Converts our inventory map into a string ROOM can use
     * @param inventory inventory to convert
     * @return JSON string for ROOM to use
     */
    @TypeConverter
    public String fromInventory(Map<Good, Integer> inventory) {
        if (inventory == null) {
            return null;
        }
        Iterator it = inventory.entrySet().iterator();
        List<Object> listFromMap = new ArrayList<>();
        while(it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            listFromMap.add(pair.getKey());
            listFromMap.add(pair.getValue());
            it.remove();
        }

        Gson gson = new Gson();
        Type type = new TypeToken<List<Object>>() {}.getType();
        return gson.toJson(listFromMap, type);
    }

    /**
     * Converts a JSON string into our inventory map
     * @param inventoryMapString JSON string to convert
     * @return our inventory map
     */
    @TypeConverter
    public Map<Good, Integer> toInventoryMap(String inventoryMapString) {
        if (inventoryMapString == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Object>>() {}.getType();
        List<Object> inventoryList = gson.fromJson(inventoryMapString, type);

        Map<Good, Integer> mapToReturn = new HashMap<>();
        for(int i = 0; i < inventoryList.size(); i += 2){
            mapToReturn.put((Good)inventoryList.get(i), (Integer)inventoryList.get(i + 1));
        }
        return mapToReturn;
    }

    /**
     * Gets the oridnal of the difficulty enum
     * @param difficulty the difficulty to inquire
     * @return the ordinal of the enum
     */
    @TypeConverter
    public int fromDifficulty(Difficulty difficulty) {
        return difficulty.ordinal();
    }

    /**
     * Gets the corresponding enum based on the ordinal
     * @param pos the ordinal of the enum value
     * @return the corresponding enum value
     */
    @TypeConverter
    public Difficulty toDifficulty(int pos) {
        return Difficulty.values()[pos];
    }

    /**
     * Gets the ordinal associated with the enum value
     * @param resource the resource to get the ordinal of
     * @return the ordinal of the resource value
     */
    @TypeConverter
    public int fromResource(Resources resource) {
        if (resource == null) {
            return 0;
        }
        return resource.ordinal();
    }

    /**
     * Gets the resource enum associated with the ordinal
     * @param pos the ordinal of the enum value
     * @return the corresponding enum value
     */
    @TypeConverter
    public Resources toResource(int pos) {
        return Resources.values()[pos];
    }

    /**
     * Gets the ordinal and fuel amount of the spaceship
     * @param spaceship the spaceship to inquire about
     * @return String representation of the spaceship to save
     */
    @TypeConverter
    public String fromSpaceship(Spaceship spaceship) {
        return spaceship.ordinal() + ":" + spaceship.getFuel();
    }

    /**
     * Gets the spaceship based on the values from the string from the database
     * @param spaceship the string representation of our spaceship from the database
     * @return our spaceship
     */
    @TypeConverter
    public Spaceship toSpaceship(String spaceship) {
        String[] parts = spaceship.split(":");
        Spaceship ship = Spaceship.values()[Integer.parseInt(parts[0])];
        ship.setFuel(Integer.parseInt(parts[1]));
        return ship;
    }

    /**
     * Gets the ordinal of the tech level
     * @param techLevel the tech level to get the ordinal of
     * @return the corrdesponding ordinal value
     */
    @TypeConverter
    public int fromTechLevel(TechLevel techLevel) {
        if (techLevel == null) {
            return 0;
        }
        return techLevel.ordinal();
    }

    /**
     * Gets the TechLevel associated with the ordinal value passed in
     * @param pos the ordinal value of our enum
     * @return the corresponding enum value
     */
    @TypeConverter
    public TechLevel toTechLevel(int pos) {
        return TechLevel.values()[pos];
    }

    /**
     * Converts a Coord object into a String to store in database
     * @param coord our Coord object
     * @return String representation of Coord object to store
     */
    @TypeConverter
    public String fromCoord(Coord coord) {
        if (coord == null) {
            return "0:0";
        }
        return coord.getX() + ":" + coord.getY();
    }

    /**
     * Converts a string representation of the coordinates into a coord object
     * @param coord the string to parse
     * @return our Coord object
     */
    @TypeConverter
    public Coord toCoord(String coord) {
        String[] parts = coord.split(":");
        return new Coord(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    }

    /**
     * Converts a planet into a string representation to store in DB
     * @param planet planet to store
     * @return String representation of planet to store
     */
    @TypeConverter
    public String fromPlanet(List<Planet> planet) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Planet>>() {
        }.getType();
        return gson.toJson(planet, type);
    }

    /**
     * Converts a String representation of our planet in to a planet
     * @param planet the String to convert
     * @return our Planet object
     */
    @TypeConverter
    public List<Planet> toPlanet(String planet) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Planet>>() {
        }.getType();
        return gson.fromJson(planet, type);
    }

}
