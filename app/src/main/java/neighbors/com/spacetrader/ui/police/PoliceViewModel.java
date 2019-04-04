package neighbors.com.spacetrader.ui.police;

import android.app.Application;

import java.util.Random;

import neighbors.com.spacetrader.model.Good;
import neighbors.com.spacetrader.model.Player;
import neighbors.com.spacetrader.model.SkillType;
import neighbors.com.spacetrader.ui.base.BaseViewModel;

public class PoliceViewModel extends BaseViewModel {

    public PoliceViewModel(Application app) {
        super(app);
    }

    /**
     * Decides the result of talking to the police
     *
     * @return true if positive result false if negative
     */

    public boolean talk() {
        int difficulty = repository.getPlayer().getDifficulty().getMultiplier();
        int narcNum = repository.getPlayer().getQuantity(Good.NARCOTICS);
        int gunNum = repository.getPlayer().getQuantity(Good.FIREARMS);
        int chance = new Random().nextInt(100);
        if ((narcNum + gunNum) * difficulty / 7 > chance) {
            repository.getPlayer().getInventory().remove(Good.NARCOTICS, narcNum);
            repository.getPlayer().getInventory().remove(Good.FIREARMS, gunNum);
            return false;
        } else {
            return true;
        }
    }

    /**
     * Decides the result of running from the police
     *
     * @return true if escape false if caught
     */
    public boolean run() {
        int difficulty = repository.getPlayer().getDifficulty().getMultiplier();
        int flight = repository.getPlayer().getSkills().get(SkillType.PILOT);
        int chance = new Random().nextInt(20);
        if ((flight * chance) / difficulty > 1) {
            return true;
        }
        Player player = repository.getPlayer();
        int credits = player.getCredits();
        int removeCredits = credits / 7;
        player.removeCredits(removeCredits);
        return false;
    }
}
