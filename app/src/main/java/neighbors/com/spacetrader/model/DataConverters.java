package neighbors.com.spacetrader.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
            mapToReturn.put((SkillType)skillsList.get(i), (Integer)skillsList.get(i + 1));
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
}
