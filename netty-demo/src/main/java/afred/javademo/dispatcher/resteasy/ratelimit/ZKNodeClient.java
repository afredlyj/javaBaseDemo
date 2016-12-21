package afred.javademo.dispatcher.resteasy.ratelimit;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Properties;

/**
 * Created by afred on 16/12/21.
 */
public class ZKNodeClient {

    private List<ZKConfigChangedListener> listeners = Lists.newLinkedList();

    private Properties properties;

    public void register(ZKConfigChangedListener listener) {
        listeners.add(listener);
    }

    public Optional<ImmutableMap<String, String>> exportProperties() {

        return Optional.fromNullable(Maps.fromProperties(properties));

    }


    public void changed() {
        for (ZKConfigChangedListener listener : listeners) {
            listener.eventReceived();
        }
    }

}
