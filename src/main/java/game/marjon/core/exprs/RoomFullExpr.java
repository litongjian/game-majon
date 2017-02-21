package game.marjon.core.exprs;

/**
 * Created by Administrator on 2017/2/21.
 */
public class RoomFullExpr extends Exception{
    @Override
    public String getMessage() {
        return "当前房间已满员";
    }
}
