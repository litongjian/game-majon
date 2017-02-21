package game.marjon.core.base;

import game.marjon.core.exprs.OtherExpr;
import game.marjon.core.exprs.RoomFullExpr;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/21.
 */
public class MRoom {

    public MRoom(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    private int roomNumber;// 房间号

    public int getRoomNumber() {
        return roomNumber;
    }

    private MPlayer[] players = new MPlayer[4]; // 玩家

    public void joinGame(MPlayer mPlayer) throws RoomFullExpr {
        for (int i = 0; i < 4; i++) {
            if (players[i] == null) {
                players[i] = mPlayer;
                mPlayer.setCurrIdx(i);
                mPlayer.clearCash();
                return;
            }
        }
        throw new RoomFullExpr();
    }

    public void quitGame(MPlayer mPlayer) throws OtherExpr {
        for (int i = 0; i < 4; i++) {
            if (players[i].equals(mPlayer)) {
                players[i] = null;
                mPlayer.clearCash();
                mPlayer.setCurrRoom(-1);
                mPlayer.setCurrStatus(MPlayer.NORMAL);
                return;
            }
            throw new OtherExpr();
        }
    }

    private List<MCard> houseCards = new ArrayList<MCard>();//牌库
    private List<MCard> handCards = new ArrayList<MCard>(); //手牌
    private List<MCard> usedCards = new ArrayList<MCard>();//已使用的牌
    private List<MCard> bandCards = new ArrayList<MCard>();//已弃的牌
    private MCard currKing;//赖子
    private MCard currQueen;//皮子
    private MCard currCard;//当前出的牌
    private int currBanker = 0;//当前庄

    /**
     * 重置
     */
    private void resetCard(){
        houseCards.clear();
        handCards.clear();
        usedCards.clear();
        bandCards.clear();
        currKing = null;
        currQueen = null;
        currCard = null;
    }

    public void startNewGame() throws SQLException {
        resetCard();
        houseCards = arrayToList(MCardWareHouse.createRandomCard());//洗牌
        int tmpBanker = currBanker;
        for (int i = 0; i < 3; i++) {
            for (int j=0;j<4;j++){
                distribution(tmpBanker,4);
                tmpBanker = evalNext(tmpBanker);
            }
        }
        for (int j=0;j<4;j++){
            distribution(tmpBanker,1);
            tmpBanker = evalNext(tmpBanker);
        }
        distribution(currBanker,1);//庄家再起一张
        evalKingAndQueen();
        System.out.println("     done    ");
    }

    private List<MCard> arrayToList(MCard[] cards){
        List<MCard> list = new ArrayList<MCard>();
        for (MCard card:cards){
            list.add(card);
        }
        return list;
    }

    public static void main(String[] args) throws SQLException {
        MRoom room = new MRoom(1);
        room.startNewGame();
    }

    private int evalNext(int idx) {
        int next = idx+1;
        return next == 4 ? 0 : next;
    }

    /**
     * 计算赖子和皮子
     */
    private void evalKingAndQueen() {
        // TODO: 2017/2/21 计算赖子和皮子
        int num = 0;
        for (MCard card:handCards){
            if (card.getOwner()==currBanker){
                num++;
            }
        }
        System.out.println("BANKER CARD NUM IS :"+num);
    }

    /**
     * 起牌
     *
     * @param playerIndex 玩家索引
     * @param cardNum     牌数量
     */
    private void distribution(int playerIndex, int cardNum) {
        List<MCard> cList = getCardFromHouse(cardNum);
        for (MCard card : cList) {
            card.setOwner(playerIndex);
            handCards.add(card);
        }
    }

    private List<MCard> getCardFromHouse(int cardNum) {
        List<MCard> rs = new ArrayList<MCard>();
        while (cardNum > 0) {
            MCard card = houseCards.remove(0).copyNewInstance();
            rs.add(card);
            cardNum--;
        }
        return rs;
    }
}
