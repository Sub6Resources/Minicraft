/*    */ package com.mojang.ld22.level.tile;
/*    */ 
/*    */ import com.mojang.ld22.gfx.Screen;
/*    */ import com.mojang.ld22.level.Level;
/*    */ 
/*    */ public class HoleTile extends Tile
/*    */ {
/*    */   public HoleTile(int id)
/*    */   {
/* 10 */     super(id);
/* 11 */     this.connectsToSand = true;
/* 12 */     this.connectsToWater = true;
/* 13 */     this.connectsToLava = true;
/*    */   }
/*    */   
/*    */   public void render(Screen screen, Level level, int x, int y) {
/* 17 */     int col = com.mojang.ld22.gfx.Color.get(111, 111, 110, 110);
/* 18 */     int transitionColor1 = com.mojang.ld22.gfx.Color.get(3, 111, level.dirtColor - 111, level.dirtColor);
/* 19 */     int transitionColor2 = com.mojang.ld22.gfx.Color.get(3, 111, level.sandColor - 110, level.sandColor);
/*    */     
/* 21 */     boolean u = !level.getTile(x, y - 1).connectsToLiquid();
/* 22 */     boolean d = !level.getTile(x, y + 1).connectsToLiquid();
/* 23 */     boolean l = !level.getTile(x - 1, y).connectsToLiquid();
/* 24 */     boolean r = !level.getTile(x + 1, y).connectsToLiquid();
/*    */     
/* 26 */     boolean su = (u) && (level.getTile(x, y - 1).connectsToSand);
/* 27 */     boolean sd = (d) && (level.getTile(x, y + 1).connectsToSand);
/* 28 */     boolean sl = (l) && (level.getTile(x - 1, y).connectsToSand);
/* 29 */     boolean sr = (r) && (level.getTile(x + 1, y).connectsToSand);
/*    */     
/* 31 */     if ((!u) && (!l)) {
/* 32 */       screen.render(x * 16 + 0, y * 16 + 0, 0, col, 0);
/*    */     } else {
/* 34 */       screen.render(x * 16 + 0, y * 16 + 0, (l ? 14 : 15) + (u ? 0 : 1) * 32, (su) || (sl) ? transitionColor2 : transitionColor1, 0);
/*    */     }
/* 36 */     if ((!u) && (!r)) {
/* 37 */       screen.render(x * 16 + 8, y * 16 + 0, 1, col, 0);
/*    */     } else {
/* 39 */       screen.render(x * 16 + 8, y * 16 + 0, (r ? 16 : 15) + (u ? 0 : 1) * 32, (su) || (sr) ? transitionColor2 : transitionColor1, 0);
/*    */     }
/* 41 */     if ((!d) && (!l)) {
/* 42 */       screen.render(x * 16 + 0, y * 16 + 8, 2, col, 0);
/*    */     } else
/* 44 */       screen.render(x * 16 + 0, y * 16 + 8, (l ? 14 : 15) + (d ? 2 : 1) * 32, (sd) || (sl) ? transitionColor2 : transitionColor1, 0);
/* 45 */     if ((!d) && (!r)) {
/* 46 */       screen.render(x * 16 + 8, y * 16 + 8, 3, col, 0);
/*    */     } else
/* 48 */       screen.render(x * 16 + 8, y * 16 + 8, (r ? 16 : 15) + (d ? 2 : 1) * 32, (sd) || (sr) ? transitionColor2 : transitionColor1, 0);
/*    */   }
/*    */   
/*    */   public boolean mayPass(Level level, int x, int y, com.mojang.ld22.entity.Entity e) {
/* 52 */     return e.canSwim();
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\level\tile\HoleTile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */