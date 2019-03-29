package neighbors.com.spacetrader.model;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class Repository {

    private PlayerDao playerDao;
    private LiveData<List<Player>> allSavedGames;

    public Repository(Application application) {
        PlayerDatabase database = PlayerDatabase.getInstance(application);
        playerDao = database.playerDao();
        allSavedGames = playerDao.getAllSavedGames();
    }

    public void insert(Player player) { new InsertPlayerAsyncTask(playerDao).execute(player); }
    public void update(Player player) { new UpdatePlayerAsyncTask(playerDao).execute(player); }
    public void delete(Player player) { new DeletePlayerAsyncTask(playerDao).execute(player); }
    public LiveData<List<Player>> getAllSavedGames() { return this.allSavedGames; }

    private static class InsertPlayerAsyncTask extends AsyncTask<Player, Void, Void> {
        private PlayerDao playerDao;

        private InsertPlayerAsyncTask(PlayerDao playerDao) { this.playerDao = playerDao; }

        @Override
        protected Void doInBackground(Player... players) {
            playerDao.insert(players[0]);
            return null;
        }
    }
    private static class UpdatePlayerAsyncTask extends AsyncTask<Player, Void, Void> {
        private PlayerDao playerDao;

        private UpdatePlayerAsyncTask(PlayerDao playerDao) { this.playerDao = playerDao; }

        @Override
        protected Void doInBackground(Player... players) {
            playerDao.update(players[0]);
            return null;
        }
    }
    private static class DeletePlayerAsyncTask extends AsyncTask<Player, Void, Void> {
        private PlayerDao playerDao;

        private DeletePlayerAsyncTask(PlayerDao playerDao) { this.playerDao = playerDao; }

        @Override
        protected Void doInBackground(Player... players) {
            playerDao.delete(players[0]);
            return null;
        }
    }







//    private static Repository repo;
//
//    private Universe universe;
//    private Player player;
//
//    public static Repository getInstance() {
//        if (repo == null){
//            repo = new Repository();
//        }
//        return repo;
//    }
//
//    private Repository() {
//        universe = new Universe();
//        // Awkward initialization of player's system and planet bc player is initialized before universe.
//        // Player should be initialized, but just to be safe.
//        if (player != null) {
//            // Sets planet to first planet of first solar system in universe.
//            player.setCurrentSystem(universe.getSolarSystems().get(0));
//        }
//    }
//
//    public Universe getUniverse() {
//        return universe;
//    }
//
//    public void setUniverse(Universe universe) {
//        this.universe = universe;
//    }
//
//    public Player getPlayer() {
//        return player;
//    }
//
//    public void setPlayer(Player player) {
//        this.player = player;
//    }
//
//    public List<SolarSystem> getSolarSystems() {
//        return universe.getSolarSystems();
//    }
//
//    public SolarSystem getSolarSystem(String name) {
//        if (name == null) {
//            return getSolarSystems().get(0);
//        }
//        for (SolarSystem solarSystem : getSolarSystems()) {
//            if (solarSystem.getName().equals(name)) {
//                return solarSystem;
//            }
//        }
//        return null;
//    }
//
//    public Market getMarket(String name) {
//        return getSolarSystem(name).getMarket();
//    }
}
