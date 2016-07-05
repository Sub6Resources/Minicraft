/*    */ package com.mojang.ld22.level.tile;
/*    */ 
/*    */ import com.mojang.ld22.gfx.Screen;
/*    */ import com.mojang.ld22.level.Level;
/*    */ 
/*    */ public class StoneTile extends Tile
/*    */ {
/*    */   public StoneTile(int id)
/*    */   {
/* 10 */     super(id);
/*    */   }
/*    */   
/*    */   public void render(Screen screen, Level level, int x, int y) {
/* 14 */     int rc1 = 111;
/* 15 */     int rc2 = 333;
/* 16 */     int rc3 = 555;
/* 17 */     screen.render(x * 16 + 0, y * 16 + 0, 32, com.mojang.ld22.gfx.Color.get(rc1, level.dirtColor, rc2, rc3), 0);
/* 18 */     screen.render(x * 16 + 8, y * 16 + 0, 32, com.mojang.ld22.gfx.Color.get(rc1, level.dirtColor, rc2, rc3), 0);
/* 19 */     screen.render(x * 16 + 0, y * 16 + 8, 32, com.mojang.ld22.gfx.Color.get(rc1, level.dirtColor, rc2, rc3), 0);
/* 20 */     screen.render(x * 16 + 8, y * 16 + 8, 32, com.mojang.ld22.gfx.Color.get(rc1, level.dirtColor, rc2, rc3), 0);
/*    */   }
/*    */   
/*    */   public boolean mayPass(Level level, int x, int y, com.mojang.ld22.entity.Entity e) {
/* 24 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\level\tile\StoneTile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */