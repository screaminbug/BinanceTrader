package hr.from.tomislav_strelar.binancetrader.data;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Tomislav on 7.10.2017..
 */
public class DepthTest {

    private JSONObject testObject1;
    private JSONObject testObject2;

    private static final String testJsonStr1 = "{\"e\":\"depthUpdate\",\"E\":1507390597277,\"s\":\"ETHBTC\",\"U\":13635528,\"u\":13635534,\"b\":[[\"0.07092900\",\"23.39200000\",[]],[\"0.07091800\",\"6.03300000\",[]],[\"0.07004400\",\"8.97900000\",[]],[\"0.06489700\",\"0.00000000\",[]]],\"a\":[[\"0.07120200\",\"6.05700000\",[]],[\"0.07121300\",\"0.00000000\",[]],[\"0.07570200\",\"4.90100000\",[]]]}";
    private static final String testJsonStr2 = "{\"e\":\"depthUpdate\",\"E\":1507390597277,\"s\":\"ETHBTC\",\"U\":13635528,\"u\":13635536,\"b\":[[\"0.07092900\",\"23.39200000\",[]],[\"0.07091800\",\"6.03300000\",[]],[\"0.07004400\",\"8.97900000\",[]],[\"0.06489700\",\"0.00000000\",[]]],\"a\":[[\"0.07120200\",\"6.05700000\",[]],[\"0.07121300\",\"0.00000000\",[]],[\"0.07570200\",\"4.90100000\",[]]]}";

    @Before
    public void setUp() throws Exception {
        testObject1 = new JSONObject(testJsonStr1);
        testObject2 = new JSONObject(testJsonStr2);
        System.out.println(testObject1);
        System.out.println(testObject2);
    }

    @Test
    public void testDepthParse() throws Exception {
        Depth testDepth = new Depth();
        testDepth.update(testObject1);
        System.out.println(testDepth.toString());
        assertEquals(13635534, testDepth.getUpdateId());

        testDepth.update(testObject2);
        System.out.println(testDepth.toString());
        assertEquals(13635536, testDepth.getUpdateId());
    }

}