package neighbors.com.spacetrader.ui.configuration;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import androidx.lifecycle.ViewModel;
import neighbors.com.spacetrader.model.Player;
import neighbors.com.spacetrader.model.Repository;
import neighbors.com.spacetrader.model.SkillType;

public class EditConfigurationViewModel extends ViewModel {
    private static final String TAG = EditConfigurationViewModel.class.getCanonicalName();
    private static final int MAX_POINTS = 16;

    private int availablePoints;
    private Map<SkillType, Integer> skillsPoints;
    private Repository repository;

    public EditConfigurationViewModel() {
        skillsPoints = new HashMap<>();
        for (SkillType value : SkillType.values()) {
            skillsPoints.put(value, 0);
        }
        availablePoints = MAX_POINTS;
        repository = Repository.getInstance();
    }

    public void savePlayer(Player player) {
        repository.setPlayer(player);
    }

    /**
     * Incrmenets a skilltype by one if possible
     *
     * @param type the type to increment
     * @return will return null if success and a String for error message
     */
    public String incrementSkill(SkillType type) {
        if (availablePoints <= 0) {
            return "Can't have more than 16 points!";
        }
        skillsPoints.put(type, skillsPoints.get(type) + 1);
        availablePoints--;
        return null;
    }

    /**
     * Decrements a skilltype by one if possible
     *
     * @param type the type to decrement
     * @return will return null if success and a String for error message
     */
    public String decrementSkill(SkillType type) {
        Integer points = skillsPoints.get(type);
        if (points <= 0) {
            return "Can't have negative points!";
        }
        skillsPoints.put(type, points - 1);
        availablePoints++;
        return null;
    }

    public int getSkillPoints(SkillType skillType) {
        return skillsPoints.get(skillType);
    }

    public int getAvailablePoints() {
        return availablePoints;
    }


    public Map<SkillType, Integer> getSkills() {
        return skillsPoints;
    }

    public int getMaxPoints() {
        return MAX_POINTS;
    }
}
