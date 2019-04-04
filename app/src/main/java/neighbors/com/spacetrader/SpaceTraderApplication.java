package neighbors.com.spacetrader;

import android.app.Application;

import neighbors.com.spacetrader.model.Repository;

public class SpaceTraderApplication extends Application {

    static Repository repository;

    @Override
    public void onCreate() {
        super.onCreate();
        repository = Repository.getInstance(this);
    }
}
