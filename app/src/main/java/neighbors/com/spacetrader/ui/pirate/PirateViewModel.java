package neighbors.com.spacetrader.ui.pirate;

import android.app.Application;

import java.util.Random;

import neighbors.com.spacetrader.model.Player;
import neighbors.com.spacetrader.model.SkillType;
import neighbors.com.spacetrader.ui.base.BaseViewModel;

public class PirateViewModel extends BaseViewModel {

    public PirateViewModel(Application application) {
        super(application);
    }


    /**
     * Decides the result of running from the pirate
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
        int removeCredits = credits / 10;
        player.removeCredits(removeCredits);
        return false;
    }

    /**
     * Decides the result of fighting the pirate
     *
     * @return true if successful false if fail
     */
    public boolean fight() {
        int difficulty = repository.getPlayer().getDifficulty().getMultiplier();
        int fight = repository.getPlayer().getSkills().get(SkillType.FIGHTER);
        int chance = new Random().nextInt(20);
        Player player = repository.getPlayer();
        int credits = player.getCredits();
        if ((fight * chance) / difficulty > 1) {
            int addCredits = credits / 10;
            player.addCredits(addCredits);
            return true;
        }
        int removeCredits = credits / 10;
        player.removeCredits(removeCredits);
        return false;
    }

}
