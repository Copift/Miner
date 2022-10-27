package com.copift;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sun.tools.javac.util.Pair;

public class Block {
    private boolean flagb=false;
    private Texture img;
    private boolean isDead=false;
    private Texture flag;
    private Texture flagFalse;
    private Batch batch;
    public boolean isHiden=true;
    private BitmapFont font24;
    private boolean isMine=false;
    private Vector2 position = new Vector2();
    public int mineNumber;
    public Block(Batch batch,BitmapFont font,float x, float y){
        this.batch  = batch;
        img = new Texture("cell.png");
        flag = new Texture(("1.png"));
        flagFalse = new Texture(("2.png"));
        position.x = x;
        position.y = y;
        font24=font;
    }
    public void setXY(float x, float y){
        position.x = x;
        position.y = y;
    }
    public void getmine(Pair mines)
    {

    }
 public void setFlag()
 {
     flagb=!flagb;
 }
    public void setMine()
    {
        isMine=true;
    }
    public void setDead()
    {
        isDead=true;
    }
    public boolean getMine()
    {
        return isMine;
    }
    public boolean getflag()
    {
        return flagb;
    }
    public void draw() {

        batch.setColor(1, 1, 1, 1);
        batch.draw(img, position.x , position.y );

        if (flagb)

        {
            batch.setColor(1, 1, 0, 1);
            // font24.setColor(Color.RED);
            //   font24.draw(batch, "M", position.x + 20, position.y +30,30, 1, false);
            batch.draw(flag, position.x, position.y);

        if (isDead&&!getMine()&&flagb)
        {
            batch.draw(flagFalse, position.x, position.y);
        }
        }else

        {
            if (isHiden==false) {
                batch.setColor(0.90f, 1, 0.33f, 1);
                batch.draw(img, position.x , position.y );
             if (mineNumber>0) {
                 font24.setColor(Color.BLACK);
                 font24.draw(batch, String.valueOf(mineNumber), position.x + 20, position.y + 30, 30, 1, false);
             }
             if (getMine())
             {font24.setColor(Color.RED);
                 batch.setColor(1, 1, 1, 1);
                 batch.draw(img, position.x , position.y );
                 batch.draw(flag, position.x, position.y);

             }
            }
        }


    //        batch.setColor(1, 1, 1, 1);
          //  gameScreen.getFont24().draw(batch, "1", position.x - dx, position.y + height + 30 - dy,width, 1, false);
        }



}
