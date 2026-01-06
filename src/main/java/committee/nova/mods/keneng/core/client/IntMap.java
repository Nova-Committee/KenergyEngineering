package committee.nova.mods.keneng.core.client;

import java.util.HashMap;

public class IntMap<K> extends HashMap<K, Integer>
{

    public Integer get(Object key)
    {
        Integer v = super.get(key);
        if(v == null) {
            return 0;
        }
        return v;
    }

    public void trs(Object key, int trs)
    {
        put((K) key, get(key) + trs);
    }

}
