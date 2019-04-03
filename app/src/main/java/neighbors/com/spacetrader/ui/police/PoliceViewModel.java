package neighbors.com.spacetrader.ui.police;

import java.util.Random;

import androidx.lifecycle.ViewModel;
import neighbors.com.spacetrader.model.Good;
import neighbors.com.spacetrader.model.Player;
import neighbors.com.spacetrader.model.Repository;
import neighbors.com.spacetrader.model.SkillType;

public class PoliceViewModel extends ViewModel {
    private Repository repo;

    public PoliceViewModel() {
        repo = Repository.getInstance();
    }

    /**
     * Decides the result of talking to the police
     *
     * @return true if positive result false if negative
     */

    public boolean talk() {
        int difficulty = repo.getPlayer().getDifficulty().getMultiplier();
        int narcNum = repo.getPlayer().getQuantity(Good.NARCOTICS);
        int gunNum = repo.getPlayer().getQuantity(Good.FIREARMS);
        int chance = new Random().nextInt(100);
        if ((narcNum + gunNum) * difficulty / 7 > chance) {
            repo.getPlayer().getInventory().remove(Good.NARCOTICS, narcNum);
            repo.getPlayer().getInventory().remove(Good.FIREARMS, gunNum);
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
        int difficulty = repo.getPlayer().getDifficulty().getMultiplier();
        int flight = repo.getPlayer().getSkills().get(SkillType.PILOT);
        int chance = new Random().nextInt(20);
        if ((flight * chance) / difficulty > 1) {
            return true;
        }
        Player player = repo.getPlayer();
        int credits = player.getCredits();
        int removeCredits = credits / 7;
        player.removeCredits(removeCredits);
        return false;
    }
}
