package com.yoolean.client;

import com.yoolean.common.model.HotArea;
import com.yoolean.common.model.HotSpot;
import com.yoolean.common.model.Location;
import com.yoolean.common.util.XMLUtil;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by chenhang on 2015/4/11.
 */
public class HotSpotClient {
    private static final String HOTSPOT_PATH = "/yoolean/hotspot";
    private static final String HOTAREA_PATH = "/yoolean/hotspot/area";
    private String address;

    public HotSpotClient(String address) {
        this.address = address;
    }

    public void
    createHotSpot(HotSpot hotSpot) {
        try {
            String xml = XMLUtil.hotSpotToXML(hotSpot);
            String path = address + HOTSPOT_PATH;
            String result=postResource(path, xml);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HotArea getHotArea(Location center, long radius) {
        HotArea hotArea = new HotArea();
        try {
            String path = new StringBuilder().append(address).append(HOTAREA_PATH).append("?")
                    .append("latitude").append("=").append(center.getLatitude()).append("&")
                    .append("longitude").append("=").append(center.getLongitude()).append("&")
                    .append("radius").append("=").append(radius)
                    .toString();
            String xml = getResource(path);
            hotArea = XMLUtil.hotAreaFromXML(xml);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hotArea;
    }

    private String getResource(String path) {
        String result = null;
        InputStreamReader in = null;
        try {
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/xml");
            connection.setReadTimeout(30000);
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            result = getString(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeQuietly(in);
        }
        return result;
    }

    private String getString(InputStream inputStream) throws IOException {
        String result = null;
        BufferedReader reader = null;
        InputStreamReader in = null;
        try {
            in = new InputStreamReader(inputStream);
            reader = new BufferedReader(in);
            StringBuilder buf = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                buf.append(line).append(System.getProperty("line.separator"));
            }
            result = buf.toString();
        } finally {
            closeQuietly(reader);
            closeQuietly(inputStream);
        }
        return result;
    }


    private String postResource(String path, String content) throws Exception {
        String result = null;
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-Type", "application/xml");
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            outputStream = connection.getOutputStream();
            outputStream.write(content.getBytes());
            inputStream = connection.getInputStream();
            result = getString(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeQuietly(outputStream);
            closeQuietly(inputStream);
        }
        return result;
    }

    private void closeQuietly(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (Exception ignored) {
            }
        }
    }

    private void closeQuietly(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Exception ignored) {
            }
        }
    }


    private void closeQuietly(Reader in) {
        if (in != null) {
            try {
                in.close();
            } catch (Exception e) {

            }
        }
    }
}
