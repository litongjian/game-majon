package game.marjon.mapper;

import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/21.
 * 数据中心，用于MyBatis读写数据库
 */
@Repository
public interface DBCenter {

    /**
     * 获取所有基础牌类型
     * @return
     * @throws SQLException
     */
    List<Map<String,Object>> selectBaseCardAll()throws SQLException;
}
