package hr.from.tomislav_strelar.binancetrader.websocket;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

import hr.from.tomislav_strelar.binancetrader.OkHttp;
import hr.from.tomislav_strelar.binancetrader.data.BinanceData;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

/**
 * Created by Tomislav on 14.10.2017..
 */

public class BinanceWebSocket {
    private static final Map<WebsockCommand, String> commands = initComands();

    private final Request request;
    private WebSocket ws;
    private final WebSocketListener listener;

    private final BinanceData data;

    public static final String URL = "wss://stream.binance.com:9443/ws/";

    private static Map<WebsockCommand, String> initComands() {
        Map<WebsockCommand, String> ret = new HashMap<>();
        ret.put(WebsockCommand.DEPTH, "@depth");
        ret.put(WebsockCommand.KLINE, "@kline");
        ret.put(WebsockCommand.AGG_TRADE, "@aggTrade");

        return ret;
    }

    public BinanceWebSocket(BinanceData data) {
        WebsockCommand cmd = data.getWebSockCommand();
        String symbol = data.getSymbol().toString();
        String listenKey = data.getListenKey();

        if (cmd != WebsockCommand.USER_DATA) {
            request = new Request.Builder().url(URL +
                    symbol + commands.get(cmd)).build();
        } else if (listenKey != null && listenKey.length() == 64) {
            request = new Request.Builder().url(URL + listenKey).build();
        } else throw new InvalidParameterException("Incorrect listen key: " + listenKey);

        this.data = data;
        listener = new BinanceWebSocketListener();
    }

    public void connect() {
        if (ws == null) {
            ws = OkHttp.client.newWebSocket(request, listener);
        }
    }

    public void close() {
        if (ws != null) {
            ws.close(1000, "Bye");

        }
    }

    private final class BinanceWebSocketListener extends WebSocketListener {
        private static final int NORMAL_CLOSE_STATUS = 1000;
        private static final String LOG_TAG = "DepthWebSocketListener";

        @Override
        public void onMessage(WebSocket webSocket, String text) {
            Log.i(LOG_TAG, "Received text message");
            try {
                data.update(new JSONObject(text));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onMessage(WebSocket webSocket, ByteString bytes) {
            Log.i(LOG_TAG, "Received binary message");
        }


        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            webSocket.close(NORMAL_CLOSE_STATUS, null);
            Log.i(LOG_TAG, "Closing socket: " + code + " / " + reason);
        }

        @Override
        public void onClosed(WebSocket webSocket, int code, String reason) {
            ws = null;
            Log.i(LOG_TAG, "Socket closed: " + code + " / " + reason);
            data.onComplete();
        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
            ws = null;
            if (!(t instanceof java.io.EOFException)) {
                Log.e(LOG_TAG, "Error: " + response, t);
            }
            if (t != null) {
                data.onError(t);
            }
        }
    }
}
