/*    */ package com.mojang.ld22.level.tile;
/*    */ 
/*    */ import com.mojang.ld22.gfx.Color;
/*    */ import com.mojang.ld22.gfx.Screen;
/*    */ import com.mojang.ld22.level.Level;
/*    */ import java.util.Random;
/*    */ 
/*    */ public class WaterTile extends Tile
/*    */ {
/*    */   public WaterTile(int id)
/*    */   {
/* 12 */     super(id);
/* 13 */     this.connectsToSand = true;
/* 14 */     this.connectsToWater = true;
/*    */   }
/*    */   
/* 17 */   private Random wRandom = new Random();
/*    */   
/*    */   public void render(Screen screen, Level level, int x, int y) {
/* 20 */     this.wRandom.setSeed((tickCount + (x / 2 - y) * 4311) / 10 * 54687121L + x * 3271612L + y * 3412987161L);
/* 21 */     int col = Color.get(5, 5, 115, 115);
/* 22 */     int transitionColor1 = Color.get(3, 5, level.dirtColor - 111, level.dirtColor);
/* 23 */     int transitionColor2 = Color.get(3, 5, level.sandColor - 110, level.sandColor);
/*    */     
/* 25 */     boolean u = !level.getTile(x, y - 1).connectsToWater;
/* 26 */     boolean d = !level.getTile(x, y + 1).connectsToWater;
/* 27 */     boolean l = !level.getTile(x - 1, y).connectsToWater;
/* 28 */     boolean r = !level.getTile(x + 1, y).connectsToWater;
/*    */     
/* 30 */     boolean su = (u) && (level.getTile(x, y - 1).connectsToSand);
/* 31 */     boolean sd = (d) && (level.getTile(x, y + 1).connectsToSand);
/* 32 */     boolean sl = (l) && (level.getTile(x - 1, y).connectsToSand);
/* 33 */     boolean sr = (r) && (level.getTile(x + 1, y).connectsToSand);
/*    */     
/* 35 */     if ((!u) && (!l)) {
/* 36 */       screen.render(x * 16 + 0, y * 16 + 0, this.wRandom.nextInt(4), col, this.wRandom.nextInt(4));
/*    */     } else {
/* 38 */       screen.render(x * 16 + 0, y * 16 + 0, (l ? 14 : 15) + (u ? 0 : 1) * 32, (su) || (sl) ? transitionColor2 : transitionColor1, 0);
/*    */     }
/* 40 */     if ((!u) && (!r)) {
/* 41 */       screen.render(x * 16 + 8, y * 16 + 0, this.wRandom.nextInt(4), col, this.wRandom.nextInt(4));
/*    */     } else {
/* 43 */       screen.render(x * 16 + 8, y * 16 + 0, (r ? 16 : 15) + (u ? 0 : 1) * 32, (su) || (sr) ? transitionColor2 : transitionColor1, 0);
/*    */     }
/* 45 */     if ((!d) && (!l)) {
/* 46 */       screen.render(x * 16 + 0, y * 16 + 8, this.wRandom.nextInt(4), col, this.wRandom.nextInt(4));
/*    */     } else
/* 48 */       screen.render(x * 16 + 0, y * 16 + 8, (l ? 14 : 15) + (d ? 2 : 1) * 32, (sd) || (sl) ? transitionColor2 : transitionColor1, 0);
/* 49 */     if ((!d) && (!r)) {
/* 50 */       screen.render(x * 16 + 8, y * 16 + 8, this.wRandom.nextInt(4), col, this.wRandom.nextInt(4));
/*    */     } else
/* 52 */       screen.render(x * 16 + 8, y * 16 + 8, (r ? 16 : 15) + (d ? 2 : 1) * 32, (sd) || (sr) ? transitionColor2 : transitionColor1, 0);
/*    */   }
/*    */   
/*    */   public boolean mayPass(Level level, int x, int y, com.mojang.ld22.entity.Entity e) {
/* 56 */     return e.canSwim();
/*    */   }
/*    */   
/*    */   public void tick(Level level, int xt, int yt) {
/* 60 */     int xn = xt;
/* 61 */     int yn = yt;
/*    */     
/* 63 */     if (this.random.nextBoolean()) {
/* 64 */       xn += this.random.nextInt(2) * 2 - 1;
/*    */     } else {
/* 66 */       yn += this.random.nextInt(2) * 2 - 1;
/*    */     }
/* 68 */     if (level.getTile(xn, yn) == Tile.hole) {
/* 69 */       level.setTile(xn, yn, this, 0);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\level\tile\WaterTile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */