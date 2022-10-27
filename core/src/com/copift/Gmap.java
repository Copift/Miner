package com.copift;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.sun.tools.javac.util.Pair;

import javax.swing.tree.AbstractLayoutCache;
import java.util.Random;

public class Gmap {
    Block  blocks[][];
    Batch batch;
    int countOpenMine=0;
    Sound deathSound = Gdx.audio.newSound(Gdx.files.internal("death.wav"));
    Sound Sound = Gdx.audio.newSound(Gdx.files.internal("click.wav"));
    private BitmapFont font24;
    public static final int numberMine=35;
    public static final int MaxY=10;
    public static final int MaxX=20;
    public static int  widthBlock=50;
    public static int top = 20;
    public static int left = 20;
    public static int distance = 3;
    public boolean isDeath=false;
    public   Pair[] mines = new Pair[numberMine];
    final Random random = new Random();
    public Gmap (Batch batch){
        this.batch=batch;

        font24 = new BitmapFont(Gdx.files.internal("font25.fnt"));
        blocks = new Block[MaxX+2][MaxY+2];
        for (int i = 0; i < MaxY+2 ; i++) {
            for (int j = 0; j < MaxX+2  ; j++) {
                blocks[j][i]=new Block(batch,font24,left+(j-1)*(widthBlock+distance),top+(i-1)*(widthBlock+distance));


            }

        }
        for (int i = 0; i < numberMine; i++) {
            int ranX= random.nextInt(MaxX)+1;
            int ranY= random.nextInt(MaxY)+1;
            while (blocks[ranX][ranY].getMine()) {
                ranX=random.nextInt(MaxX)+1;
                ranY=random.nextInt(MaxY)+1;

            }
            blocks[ranX][ranY].setMine();
            mines[i]= new Pair(ranX,ranY);
            }

        for (int i = 1; i < MaxX+1; i++) {
            for (int j = 1; j < MaxY+1  ; j++) {
                blocks[i][j].mineNumber=getNumber(i,j);
                blocks[i][j].isHiden=true;


            }

        }

    }
    public boolean isDeath()
    {
        return isDeath;
    }
    public int counterOpenMine(int x, int y)

    {
        int count=0;
        if (blocks[x-1][y].getflag())
        {
    count++;
        }

        if (blocks[x+1][y].getflag())
        {
            count++;
        }
        //////
        if (blocks[x-1][y+1].getflag())
        {
            count++;
        }
        if (blocks[x][y+1].getflag())
        {
            count++;
        }
        if (blocks[x+1][y+1].getflag())
        {
        count++;
        }
//////

        //////
        if (blocks[x-1][y-1].getflag())
        {
            count++;
        }
        if (blocks[x][y-1].getflag())
        {
            count++;
        }
        if (blocks[x+1][y-1].getflag())
        {
            count++;
        }

        return count  ;
    }




    public int getNumber(int x, int y)
    {
        int number=0;
         class Pair{
          int x,y;
          public Pair (int x, int y){
             this.y =y ;
             this.x = x ;
          }

             public int getX() {
                 return x;
             }

             public int getY() {
                 return y;
             }
         }
        Pair[] pairs = new Pair[8];
        pairs[0] = new Pair(-1,-1);
        pairs[1] = new Pair(0,-1);
        pairs[2] = new Pair(1,-1);
        pairs[3] = new Pair(1,0);
        pairs[4] = new Pair(1,1);
        pairs[5] = new Pair(0,1);
        pairs[6] = new Pair(-1,1);
        pairs[7] = new Pair(-1,0);

                for (int k = 0; k < 8; k++) {
                 if (blocks[x+pairs[k].getX()][y+pairs[k].getY()].getMine()) {
                     number++;
                 }
            }



return number;
    }

private void walk3(int x, int y)
{
    if ((x==0)||(x>MaxX))
    {
        return;
    }

    if ((y==0)||(y>MaxY))
    {
        return;
    }
    if (blocks[x][y].getMine())
    {
        return;
    }
    if (!blocks[x][y].isHiden) {
        return;
    }
    blocks[x][y].isHiden=false;
    if (blocks[x][y].mineNumber>0) {
        blocks[x][y].isHiden = false;
        return;
    }
    //////////

    ////////
    System.out.println(x+ " " + y);
    if (x>1)
    {
        walk3(x-1,y);
    }

    if (x<MaxX-1)
    {
        System.out.println("maxX " + x);
        walk3(x+1,y);
    }


    if (y>1)
    {
        walk3(x,y-1);
    }

    if (y<MaxY-1)
    {
        walk3(x,y+1);
    }
/*////////////////walk diag

    if (x>1&&y>1)
    {
        walk3(x-1,y-1);
    }

    if (x<MaxX-1&&y<MaxY-1)
    {
        walk3(x+1,y+1);
    }


    if (y>1&&x<MaxX-1)
    {
        walk3(x+1,y-1);
    }

    if (y<MaxY-1&&x>1)
    {
        walk3(x-1,y+1);
    }
*////////////////

}



    public void draw()
    {
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)&&!isDeath){
            int x=Gdx.input.getX();
            int y=Gdx.input.getY();
            int Posx=(((x-left)/(widthBlock+distance)+1));
            int Posy=MaxY-((y-top+widthBlock/2)/(widthBlock+distance)-1);
            if (blocks[Posx][Posy].mineNumber==counterOpenMine(Posx,Posy)&& counteropenIsMine(counterOpenMine(Posx,Posy),Posx,Posy))
            {
                walk3(Posx-1,Posy-1); walk3(Posx,Posy-1); walk3(Posx+1,Posy-1);
                walk3(Posx-1,Posy);        walk3(Posx+1,Posy);
                walk3(Posx-1,Posy+1); walk3(Posx,Posy+1); walk3(Posx+1,Posy+1);

            }
                if (blocks[Posx][Posy].getMine()|| !counteropenIsMine(counterOpenMine(Posx,Posy),Posx,Posy))

            {


                deathSound.play();
                batch.setColor(1, 0, 0, 1);
                for (int i = 1; i < MaxX+1; i++) {
                    for (int j = 1; j < MaxY+1  ; j++) {
                        blocks[i][j].setDead();
                        blocks[i][j].isHiden = false;
                        if (blocks[i][j].getMine())
                        {
                            blocks[i][j].setFlag();
                        }
                    }
                }
            isDeath=true;
            }else {
                    Sound.play();
                walk3(Posx, Posy);
            }

        }
        if(Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)&&!isDeath){

            int x=Gdx.input.getX();
            int y=Gdx.input.getY();
            int Posx=(((x-left)/(widthBlock+distance)+1));
            int Posy=MaxY-((y-top+widthBlock/2)/(widthBlock+distance)-1);

                blocks[Posx][Posy].setFlag();

        }
        for (int i = 0; i < MaxY; i++) {
            for (int j = 0; j < MaxX; j++) {

                blocks[j+1][i+1].draw();


            }

        }
    }

    private boolean counteropenIsMine(int sum,int x,int y) {
        int count=0;
       if (sum==0)
       {return true;}
       else {
           if (blocks[x - 1][y].getflag() && blocks[x - 1][y].getMine()) {
               count++;
           }

           if (blocks[x + 1][y].getflag() && blocks[x + 1][y].getMine()) {
               count++;
           }
           //////
           if (blocks[x - 1][y + 1].getflag() && blocks[x - 1][y + 1].getMine()) {
               count++;
           }
           if (blocks[x][y + 1].getflag() && blocks[x][y + 1].getMine()) {
               count++;
           }
           if (blocks[x + 1][y + 1].getflag() && blocks[x + 1][y + 1].getMine()) {
               count++;
           }
//////

           //////
           if (blocks[x - 1][y - 1].getflag() && blocks[x - 1][y - 1].getMine()) {
               count++;
           }
           if (blocks[x][y - 1].getflag() && blocks[x][y - 1].getMine()) {
               count++;
           }
           if (blocks[x + 1][y - 1].getflag() && blocks[x + 1][y - 1].getMine()) {
               count++;
           }
           if (count == sum) {
               return true;
           } else {
               return false;
           }
       }
    }
}
