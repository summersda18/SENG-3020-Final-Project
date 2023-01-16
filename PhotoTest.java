import static org.testng.Assert.*;
import org.testng.annotations.*;



public class PhotoTest {

    private static Object[][] testData1 = new Object[][] {
            // int fourBySix, int fiveBySeven, int eightByTen, int fourMatte, int fiveMatte, int eightMatte, int today, int tomorrow, String discount
            //today+tomorrow should be equal to the amount of pictures being printed
//            {"EP1", 20,0,0,1,0,0,0,20, "00000", 4.79},          //EP 4x6
//            {"EP2", 55,0,0,0,0,0,1,54, "00000", 12.45},
//            {"EP3", 80,0,0,0,0,0,0,80,"00000" , 8},
//
//            {"EP4", 0,25,0,0,1,0,0,25,"00000", 8.5},          //EP 5x7
//            {"EP5", 0,55,0,0,0,0,0,55,"", 17.05},
//            {"EP6", 0,80,0,0,0,0,0,80,"N56M2", 22.4},
//
//            {"EP7", 0,0,25,0,0,0,0,25,"00000", 17},           //EP 8x10
//            {"EP8", 0,0,55,0,0,0,0,55,"N56M2", 35.2},
//            {"EP9", 0,0,76,0,0,0,0,76,"00000", 45.6},

            {"T1.1", 30,0,0,0,0,0,30,0,"00000", 5.20},
            {"T1.2", 51,0,0,51,0,0,0,51,"00000", 7.14},
            {"T1.3", 100,0,0,100,0,0,0,100,"00000", 12},
            {"T1.4", 0,27,0,0,0,0,0,27,"00000", 9.18},
            {"T1.5", 0,66,0,0,66,0,0,66,"00000", 22.44},
            {"T1.6", 0,79,0,0,79,0,0,79,"00000", 24.49},
            {"T1.7", 0,0,44,0,0,0,0,44,"00000", 22.44},
            {"T1.8", 0,0,61,0,0,0,0,61,"00000", 39.04},
            {"T1.9", 0,0,100,0,0,100,0,100,"00000", 64.00},
            {"T1.10", 16,16,16,0,8,16,66,0,"00000", 25.68},
            {"T1.11", 12,31,14,0,31,0,0,57,"00000", 25.01},
            {"T1.12", 7,21,60,7,21,0,88,0,"00000", 60.96},


    };

    @DataProvider(name="dataset1")
    public Object[][] getTestData() {
        return testData1;
    }

    @Test(dataProvider = "dataset1")
    public void testPhoto(String id, int fourBySix, int fiveBySeven, int eightByTen, int fourMatte, int fiveMatte, int eightMatte, int today, int tomorrow, String discount, double expected)
    {
        Photo photo = new Photo(fourBySix, fiveBySeven, eightByTen, fourMatte, fiveMatte, eightMatte, today, tomorrow, discount);
        assertEquals((photo.Calc()), expected);
    }
}
