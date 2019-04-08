package neighbors.com.spacetrader.ui.configuration;

import android.app.Application;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import neighbors.com.spacetrader.model.Player;
import neighbors.com.spacetrader.model.SkillType;
import neighbors.com.spacetrader.model.Universe;
import neighbors.com.spacetrader.ui.base.BaseViewModel;

public class EditConfigurationViewModel extends BaseViewModel {
    private static final String TAG = EditConfigurationViewModel.class.getCanonicalName();
    private static final int MAX_POINTS = 16;

    private int availablePoints;
    private final Map<SkillType, Integer> skillsPoints;

    /**
     * Creates an instance of EditConfigurationViewModel
     * @param application the application
     */
    public EditConfigurationViewModel(Application application) {
        super(application);
        skillsPoints = new HashMap<>();
        for (SkillType value : SkillType.values()) {
            skillsPoints.put(value, 0);
        }
        availablePoints = MAX_POINTS;
    }

    /**
     * Saves player to repository
     *
     * @param player the player to save
     */
    public void savePlayer(Player player) {
        repository.savePlayer(player, new Universe());
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

    /**
     * Gets the number of points in a certain skill
     * @param skillType the skillType to grab the points for
     * @return the number of points
     */
    public int getSkillPoints(SkillType skillType) {
        return skillsPoints.get(skillType);
    }

    /**
     * Gets the number of available skill points to use
     * @return the number of available skill points
     */
    public int getAvailablePoints() {
        return availablePoints;
    }


    /**
     * Gets the map of skillTypes to skill
     * @return the map of skills
     */
    public Map<SkillType, Integer> getSkills() {
        return Collections.unmodifiableMap(skillsPoints);
    }

    /**
     * Gets the max number of points allowed to use
     * @return the max points
     */
    public int getMaxPoints() {
        return MAX_POINTS;
    }
}
