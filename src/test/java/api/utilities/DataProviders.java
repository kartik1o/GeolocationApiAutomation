package api.utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "getRadioTypeTestData")
    public Object[] getRadioTypeTestData() {
        Object[] obj = new Object[4];
        obj[0] = "gsm";
        obj[1] = "cdma";
        obj[2] = "wcdma";
        obj[3] = "lte";

        return obj;
    }
}
