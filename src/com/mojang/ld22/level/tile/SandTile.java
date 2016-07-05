/*    */ package com.mojang.ld22.level.tile;
/*    */ 
/*    */ import com.mojang.ld22.entity.Entity;
/*    */ import com.mojang.ld22.entity.Player;
/*    */ import com.mojang.ld22.gfx.Color;
/*    */ import com.mojang.ld22.gfx.Screen;
/*    */ import com.mojang.ld22.item.Item;
/*    */ import com.mojang.ld22.item.ResourceItem;
/*    */ import com.mojang.ld22.item.ToolItem;
/*    */ import com.mojang.ld22.item.ToolType;
/*    */ import com.mojang.ld22.level.Level;
/*    */ import java.util.Random;
/*    */ 
/*    */ public class SandTile extends Tile
/*    */ {
/*    */   public SandTile(int id)
/*    */   {
/* 18 */     super(id);
/* 19 */     this.connectsToSand = true;
/*    */   }
/*    */   
/*    */   public void render(Screen screen, Level level, int x, int y) {
/* 23 */     int col = Color.get(level.sandColor + 2, level.sandColor, level.sandColor - 110, level.sandColor - 110);
/* 24 */     int transitionColor = Color.get(level.sandColor - 110, level.sandColor, level.sandColor - 110, level.dirtColor);
/*    */     
/* 26 */     boolean u = !level.getTile(x, y - 1).connectsToSand;
/* 27 */     boolean d = !level.getTile(x, y + 1).connectsToSand;
/* 28 */     boolean l = !level.getTile(x - 1, y).connectsToSand;
/* 29 */     boolean r = !level.getTile(x + 1, y).connectsToSand;
/*    */     
/* 31 */     boolean steppedOn = level.getData(x, y) > 0;
/*    */     
/* 33 */     if ((!u) && (!l)) {
/* 34 */       if (!steppedOn) {
/* 35 */         screen.render(x * 16 + 0, y * 16 + 0, 0, col, 0);
/*    */       } else
/* 37 */         screen.render(x * 16 + 0, y * 16 + 0, 35, col, 0);
/*    */     } else {
/* 39 */       screen.render(x * 16 + 0, y * 16 + 0, (l ? 11 : 12) + (u ? 0 : 1) * 32, transitionColor, 0);
/*    */     }
/* 41 */     if ((!u) && (!r)) {
/* 42 */       screen.render(x * 16 + 8, y * 16 + 0, 1, col, 0);
/*    */     } else {
/* 44 */       screen.render(x * 16 + 8, y * 16 + 0, (r ? 13 : 12) + (u ? 0 : 1) * 32, transitionColor, 0);
/*    */     }
/* 46 */     if ((!d) && (!l)) {
/* 47 */       screen.render(x * 16 + 0, y * 16 + 8, 2, col, 0);
/*    */     } else
/* 49 */       screen.render(x * 16 + 0, y * 16 + 8, (l ? 11 : 12) + (d ? 2 : 1) * 32, transitionColor, 0);
/* 50 */     if ((!d) && (!r)) {
/* 51 */       if (!steppedOn) {
/* 52 */         screen.render(x * 16 + 8, y * 16 + 8, 3, col, 0);
/*    */       } else {
/* 54 */         screen.render(x * 16 + 8, y * 16 + 8, 35, col, 0);
/*    */       }
/*    */     } else
/* 57 */       screen.render(x * 16 + 8, y * 16 + 8, (r ? 13 : 12) + (d ? 2 : 1) * 32, transitionColor, 0);
/*    */   }
/*    */   
/*    */   public void tick(Level level, int x, int y) {
/* 61 */     int d = level.getData(x, y);
/* 62 */     if (d > 0) level.setData(x, y, d - 1);
/*    */   }
/*    */   
/*    */   public void steppedOn(Level level, int x, int y, Entity entity) {
/* 66 */     if ((entity instanceof com.mojang.ld22.entity.Mob)) {
/* 67 */       level.setData(x, y, 10);
/*    */     }
/*    */   }
/*    */   
/*    */   public boolean interact(Level level, int xt, int yt, Player player, Item item, int attackDir) {
/* 72 */     if ((item instanceof ToolItem)) {
/* 73 */       ToolItem tool = (ToolItem)item;
/* 74 */       if ((tool.type == ToolType.shovel) && 
/* 75 */         (player.payStamina(4 - tool.level))) {
/* 76 */         level.setTile(xt, yt, Tile.dirt, 0);
/* 77 */         level.add(new com.mojang.ld22.entity.ItemEntity(new ResourceItem(com.mojang.ld22.item.resource.Resource.sand), xt * 16 + this.random.nextInt(10) + 3, yt * 16 + this.random.nextInt(10) + 3));
/* 78 */         return true;
/*    */       }
/*    */     }
/*    */     
/* 82 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\level\tile\SandTile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */