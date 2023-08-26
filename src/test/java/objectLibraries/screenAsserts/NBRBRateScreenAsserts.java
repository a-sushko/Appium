package objectLibraries.screenAsserts;

import objectLibraries.screens.NBRBRateScreen;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.*;
import org.testng.Assert;

public class NBRBRateScreenAsserts {

    NBRBRateScreen nbrbRateScreen;

    /**
     * Class initializer
     * @param nbrbRateScreen an instance of the NBRBRateScreen class
     */
    public NBRBRateScreenAsserts(NBRBRateScreen nbrbRateScreen) {
        this.nbrbRateScreen = nbrbRateScreen;
    }

    /**
     * Method to compare the
     * @param expectedRank the expected error message
     * @return an instance of the login page
     */
    public NBRBRateScreen compareTheSwissFrankRate(String expectedRank) {
        assertThat(nbrbRateScreen.getCurrencyRate("CHF/BYN"), is(equalTo(expectedRank)));
        return nbrbRateScreen;
    }


}
