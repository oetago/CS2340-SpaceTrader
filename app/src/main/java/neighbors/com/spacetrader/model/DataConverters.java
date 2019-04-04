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

public class DataConverters {

    //Converts our skill map to an object ROOM can use
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
        String json = gson.toJson(listFromMap, type);
        return json;
    }

    //Converts a JSON string into our skills map
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
            mapToReturn.put(SkillType.valueOf((String) skillsList.get(i)), ((Double) skillsList.get(i + 1)).intValue());
        }
        return mapToReturn;
    }

    //Converts our inventory map into a string ROOM can use
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
        String json = gson.toJson(listFromMap, type);
        return json;
    }

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

    @TypeConverter
    public int fromDifficulty(Difficulty difficulty) {
        return difficulty.ordinal();
    }

    @TypeConverter
    public Difficulty toDifficulty(int pos) {
        return Difficulty.values()[pos];
    }

    @TypeConverter
    public int fromResource(Resources resource) {
        if (resource == null) {
            return 0;
        }
        return resource.ordinal();
    }

    @TypeConverter
    public Resources toResource(int pos) {
        return Resources.values()[pos];
    }

    @TypeConverter
    public String fromSpaceship(Spaceship spaceship) {
        return spaceship.ordinal() + ":" + spaceship.getFuel();
    }

    @TypeConverter
    public Spaceship toSpaceship(String spaceship) {
        String[] parts = spaceship.split(":");
        Spaceship ship = Spaceship.values()[Integer.parseInt(parts[0])];
        ship.setFuel(Integer.parseInt(parts[1]));
        return ship;
    }

    @TypeConverter
    public int fromTechLevel(TechLevel techLevel) {
        if (techLevel == null) {
            return 0;
        }
        return techLevel.ordinal();
    }

    @TypeConverter
    public TechLevel toTechLevel(int pos) {
        return TechLevel.values()[pos];
    }

    @TypeConverter
    public String fromCoord(Coord coord) {
        if (coord == null) {
            return "0:0";
        }
        return coord.getX() + ":" + coord.getY();
    }

    @TypeConverter
    public Coord toCoord(String coord) {
        String[] parts = coord.split(":");
        return new Coord(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    }

    @TypeConverter
    public String fromPlanent(List<Planet> planent) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Planet>>() {
        }.getType();
        String json = gson.toJson(planent, type);
        return json;
    }

    @TypeConverter
    public List<Planet> toPlanent(String plant) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Planet>>() {
        }.getType();
        return gson.fromJson(plant, type);
    }

}
