package objectLibraries.screenAsserts;

import objectLibraries.screens.NBRBRateScreen;
import objectLibraries.screens.HomeScreen;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class HomeScreenAsserts {

    HomeScreen homeScreen;

    /**
     * Class initializer
     * @param homeScreen an instance of the HomeScreen class
     */
    public HomeScreenAsserts(HomeScreen homeScreen) {
        this.homeScreen = homeScreen;
    }



}
