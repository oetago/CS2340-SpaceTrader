package neighbors.com.spacetrader.ui.pirate;

import java.util.Random;

import androidx.lifecycle.ViewModel;
import neighbors.com.spacetrader.model.Player;
import neighbors.com.spacetrader.model.Repository;
import neighbors.com.spacetrader.model.SkillType;

public class PirateViewModel extends ViewModel {
    private Repository repo;

    public PirateViewModel() {
        repo = Repository.getInstance();
    }


    /**
     * Decides the result of running from the pirate
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
        int difficulty = repo.getPlayer().getDifficulty().getMultiplier();
        int fight = repo.getPlayer().getSkills().get(SkillType.FIGHTER);
        int chance = new Random().nextInt(20);
        Player player = repo.getPlayer();
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
