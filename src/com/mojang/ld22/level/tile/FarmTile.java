/*    */ package com.mojang.ld22.level.tile;
/*    */ 
/*    */ import com.mojang.ld22.entity.Entity;
/*    */ import com.mojang.ld22.entity.Player;
/*    */ import com.mojang.ld22.gfx.Screen;
/*    */ import com.mojang.ld22.item.Item;
/*    */ import com.mojang.ld22.item.ToolItem;
/*    */ import com.mojang.ld22.level.Level;
/*    */ 
/*    */ public class FarmTile extends Tile
/*    */ {
/*    */   public FarmTile(int id)
/*    */   {
/* 14 */     super(id);
/*    */   }
/*    */   
/*    */   public void render(Screen screen, Level level, int x, int y) {
/* 18 */     int col = com.mojang.ld22.gfx.Color.get(level.dirtColor - 121, level.dirtColor - 11, level.dirtColor, level.dirtColor + 111);
/* 19 */     screen.render(x * 16 + 0, y * 16 + 0, 34, col, 1);
/* 20 */     screen.render(x * 16 + 8, y * 16 + 0, 34, col, 0);
/* 21 */     screen.render(x * 16 + 0, y * 16 + 8, 34, col, 0);
/* 22 */     screen.render(x * 16 + 8, y * 16 + 8, 34, col, 1);
/*    */   }
/*    */   
/*    */   public boolean interact(Level level, int xt, int yt, Player player, Item item, int attackDir) {
/* 26 */     if ((item instanceof ToolItem)) {
/* 27 */       ToolItem tool = (ToolItem)item;
/* 28 */       if ((tool.type == com.mojang.ld22.item.ToolType.shovel) && 
/* 29 */         (player.payStamina(4 - tool.level))) {
/* 30 */         level.setTile(xt, yt, Tile.dirt, 0);
/* 31 */         return true;
/*    */       }
/*    */     }
/*    */     
/* 35 */     return false;
/*    */   }
/*    */   
/*    */   public void tick(Level level, int xt, int yt) {
/* 39 */     int age = level.getData(xt, yt);
/* 40 */     if (age < 5) level.setData(xt, yt, age + 1);
/*    */   }
/*    */   
/*    */   public void steppedOn(Level level, int xt, int yt, Entity entity) {
/* 44 */     if (this.random.nextInt(60) != 0) return;
/* 45 */     if (level.getData(xt, yt) < 5) return;
/* 46 */     level.setTile(xt, yt, Tile.dirt, 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\level\tile\FarmTile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */