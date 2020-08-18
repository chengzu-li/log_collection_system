package org.example;

import org.apache.curator.shaded.com.google.common.collect.Maps;
import org.apache.curator.shaded.com.google.common.base.Charsets;
import org.apache.flume.Event;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.api.RpcClient;
import org.apache.flume.api.RpcClientFactory;
import org.apache.flume.event.EventBuilder;
import java.util.Map;

public class Connect {
    public static void main(String[] args) {
        String data = "hi, there";
        //ZkClient clientDemo = new ZkClient("192.168.186.100: 19999");
        writeData(data);
    }

    public static void writeData(String data) {
        Map<String, String> headers = Maps.newHashMap();
        headers.put("type", "panda_2");
        Event event = EventBuilder.withBody(data, Charsets.UTF_8);
        RpcClient client = RpcClientFactory.getThriftInstance("192.168.186.100", 19999);
        event.setHeaders(headers);
        try {
            client.append(event);
        } catch (EventDeliveryException e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
    }
}
