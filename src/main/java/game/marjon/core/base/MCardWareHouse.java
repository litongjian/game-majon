package game.marjon.core.base;

import game.commons.dbcp.ApacheDbcp;
import game.commons.dbcp.PoMap;
import game.marjon.mapper.DBCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017/2/21.
 */
@Component
public class MCardWareHouse {

    private static final Random rd = new Random();

    @Autowired
    private DBCenter center;


    private static List<MCard> castToMCard(List<PoMap> list){
        List<MCard> rs = new ArrayList<MCard>();
        for (PoMap map:list){
            map.setIgnoreCaseMode(true);
            String name = map.getString("name");
            Integer type = map.getInt("type");
            Integer value = map.getInt("val");
            MCard card = new MCard(name,type,value);
            rs.add(card);
        }
        return rs;
    }

    public static MCard[] createRandomCard() throws SQLException {
        MCard[] rs = new MCard[136];
        List<MCard> allCard = castToMCard( ApacheDbcp.executeQuerySQL("SELECT * FROM T_META_CARD"));
        List<MCard> usedCard = new ArrayList<MCard>();
        int i = rs.length;
        long startTime = System.currentTimeMillis();
        while (allCard.size()>0){
            int tmpIdx = rd.nextInt(allCard.size());
            if (allCard.get(tmpIdx).getTimes()<4){
                allCard.get(tmpIdx).addTimes();
                rs[i-1] = allCard.get(tmpIdx).copyNewInstance();
                i--;
            }else {
                MCard used = allCard.remove(tmpIdx);
                usedCard.add(used);
            }
        }
        long executeTime = System.currentTimeMillis() - startTime;
        System.out.println("使用时间："+executeTime/1000+"秒");
        return rs;
    }

}
