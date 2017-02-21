package game.commons.dbcp;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/2/21.
 */
public class PoMap extends HashMap<String,Object> {

    private boolean ignoreCaseMode = false;

    public void setIgnoreCaseMode(boolean mode){
        ignoreCaseMode = mode;
    }

    @Override
    public Object get(Object key) {
        Object obj = null;
        if (ignoreCaseMode&&key!=null){
            for (Object k:this.keySet()){
                String str = k.toString();
                if (str.equalsIgnoreCase(key.toString())){
                    obj=super.get(k);
                    break;
                }
            }
        }else {
            obj = super.get(key);
        }
        return obj;
    }

    public Integer getInt(Object key){
        Integer rs = null;
        Object obj = this.get(key);
        if (obj!=null){
            rs = rs.valueOf(obj.toString());
        }
        return rs;
    }

    public String getString(Object key){
        return this.get(key).toString();
    }
}
