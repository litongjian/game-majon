package game.marjon.base;

import com.sun.istack.internal.NotNull;
import game.marjon.base.expr.TableFullException;

/**
 * Created by Administrator on 2017/2/17.
 */
public abstract class CardTable {
    protected CardTable(@NotNull CardRule currRule){
        this.currRule = currRule;
    }
    private CardRule currRule;

    protected final CardRule getCurrRule(){
        return this.currRule;
    }

    protected final CardPlayer[] players = new CardPlayer[4];

    protected final void join(CardPlayer player)throws TableFullException{
        for (int i=0;i<players.length;i++){
            if (players[i]==null){
                players[i]=player;
                return;
            }
        }
        throw new TableFullException();
    }

    /**
     * 当前出的牌
     */
    private CardBase currCard;

    // TODO: 2017/2/17 未完成 
}
