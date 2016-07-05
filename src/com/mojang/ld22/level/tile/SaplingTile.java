/*    */ package com.mojang.ld22.level.tile;
/*    */ 
/*    */ import com.mojang.ld22.gfx.Screen;
/*    */ import com.mojang.ld22.level.Level;
/*    */ 
/*    */ public class SaplingTile extends Tile
/*    */ {
/*    */   private Tile onType;
/*    */   private Tile growsTo;
/*    */   
/*    */   public SaplingTile(int id, Tile onType, Tile growsTo)
/*    */   {
/* 13 */     super(id);
/* 14 */     this.onType = onType;
/* 15 */     this.growsTo = growsTo;
/* 16 */     this.connectsToSand = onType.connectsToSand;
/* 17 */     this.connectsToGrass = onType.connectsToGrass;
/* 18 */     this.connectsToWater = onType.connectsToWater;
/* 19 */     this.connectsToLava = onType.connectsToLava;
/*    */   }
/*    */   
/*    */   public void render(Screen screen, Level level, int x, int y) {
/* 23 */     this.onType.render(screen, level, x, y);
/* 24 */     int col = com.mojang.ld22.gfx.Color.get(10, 40, 50, -1);
/* 25 */     screen.render(x * 16 + 4, y * 16 + 4, 107, col, 0);
/*    */   }
/*    */   
/*    */   public void tick(Level level, int x, int y) {
/* 29 */     int age = level.getData(x, y) + 1;
/* 30 */     if (age > 100) {
/* 31 */       level.setTile(x, y, this.growsTo, 0);
/*    */     } else {
/* 33 */       level.setData(x, y, age);
/*    */     }
/*    */   }
/*    */   
/*    */   public void hurt(Level level, int x, int y, com.mojang.ld22.entity.Mob source, int dmg, int attackDir) {
/* 38 */     level.setTile(x, y, this.onType, 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\level\tile\SaplingTile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */