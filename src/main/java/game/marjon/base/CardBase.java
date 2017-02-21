package game.marjon.base;

import com.sun.istack.internal.NotNull;
import game.marjon.base.expr.CardValueErrorException;
import game.marjon.base.type.CardStatus;
import game.marjon.base.type.CardType;

/**
 * Created by Administrator on 2017/2/17.
 * 牌超类
 */
public abstract class CardBase {

    protected CardType type;

    protected int val;

    protected CardStatus status;

    protected CardBase(@NotNull CardType type,@NotNull int val)throws CardValueErrorException{
        if (val <= 0 ||val >9 ||type == CardType.FENG && val > 7)throw new CardValueErrorException();
        this.type = type;
        this.val = val;
        this.status = CardStatus.UNUSED;
    }

    
}
