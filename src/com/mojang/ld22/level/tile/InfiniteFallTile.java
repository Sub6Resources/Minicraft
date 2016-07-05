/*    */ package com.mojang.ld22.level.tile;
/*    */ 
/*    */ import com.mojang.ld22.gfx.Screen;
/*    */ import com.mojang.ld22.level.Level;
/*    */ 
/*    */ public class InfiniteFallTile extends Tile
/*    */ {
/*    */   public InfiniteFallTile(int id)
/*    */   {
/* 10 */     super(id);
/*    */   }
/*    */   
/*    */ 
/*    */   public void render(Screen screen, Level level, int x, int y) {}
/*    */   
/*    */   public void tick(Level level, int xt, int yt) {}
/*    */   
/*    */   public boolean mayPass(Level level, int x, int y, com.mojang.ld22.entity.Entity e)
/*    */   {
/* 20 */     if ((e instanceof com.mojang.ld22.entity.AirWizard)) return true;
/* 21 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\level\tile\InfiniteFallTile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */