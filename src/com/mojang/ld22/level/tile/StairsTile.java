/*    */ package com.mojang.ld22.level.tile;
/*    */ 
/*    */ import com.mojang.ld22.gfx.Screen;
/*    */ 
/*    */ public class StairsTile extends Tile
/*    */ {
/*    */   private boolean leadsUp;
/*    */   
/*    */   public StairsTile(int id, boolean leadsUp)
/*    */   {
/* 11 */     super(id);
/* 12 */     this.leadsUp = leadsUp;
/*    */   }
/*    */   
/*    */   public void render(Screen screen, com.mojang.ld22.level.Level level, int x, int y) {
/* 16 */     int color = com.mojang.ld22.gfx.Color.get(level.dirtColor, 0, 333, 444);
/* 17 */     int xt = 0;
/* 18 */     if (this.leadsUp) xt = 2;
/* 19 */     screen.render(x * 16 + 0, y * 16 + 0, xt + 64, color, 0);
/* 20 */     screen.render(x * 16 + 8, y * 16 + 0, xt + 1 + 64, color, 0);
/* 21 */     screen.render(x * 16 + 0, y * 16 + 8, xt + 96, color, 0);
/* 22 */     screen.render(x * 16 + 8, y * 16 + 8, xt + 1 + 96, color, 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\level\tile\StairsTile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */