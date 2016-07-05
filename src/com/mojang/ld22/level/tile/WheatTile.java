/*    */ package com.mojang.ld22.level.tile;
/*    */ 
/*    */ import com.mojang.ld22.entity.ItemEntity;
/*    */ import com.mojang.ld22.entity.Mob;
/*    */ import com.mojang.ld22.entity.Player;
/*    */ import com.mojang.ld22.gfx.Color;
/*    */ import com.mojang.ld22.gfx.Screen;
/*    */ import com.mojang.ld22.item.ResourceItem;
/*    */ import com.mojang.ld22.item.ToolItem;
/*    */ import com.mojang.ld22.item.resource.Resource;
/*    */ import com.mojang.ld22.level.Level;
/*    */ import java.util.Random;
/*    */ 
/*    */ public class WheatTile extends Tile
/*    */ {
/*    */   public WheatTile(int id)
/*    */   {
/* 18 */     super(id);
/*    */   }
/*    */   
/*    */   public void render(Screen screen, Level level, int x, int y) {
/* 22 */     int age = level.getData(x, y);
/* 23 */     int col = Color.get(level.dirtColor - 121, level.dirtColor - 11, level.dirtColor, 50);
/* 24 */     int icon = age / 10;
/* 25 */     if (icon >= 3) {
/* 26 */       col = Color.get(level.dirtColor - 121, level.dirtColor - 11, 50 + icon * 100, 40 + (icon - 3) * 2 * 100);
/* 27 */       if (age == 50) {
/* 28 */         col = Color.get(0, 0, 50 + icon * 100, 40 + (icon - 3) * 2 * 100);
/*    */       }
/* 30 */       icon = 3;
/*    */     }
/*    */     
/* 33 */     screen.render(x * 16 + 0, y * 16 + 0, 100 + icon, col, 0);
/* 34 */     screen.render(x * 16 + 8, y * 16 + 0, 100 + icon, col, 0);
/* 35 */     screen.render(x * 16 + 0, y * 16 + 8, 100 + icon, col, 1);
/* 36 */     screen.render(x * 16 + 8, y * 16 + 8, 100 + icon, col, 1);
/*    */   }
/*    */   
/*    */   public void tick(Level level, int xt, int yt) {
/* 40 */     if (this.random.nextInt(2) == 0) { return;
/*    */     }
/* 42 */     int age = level.getData(xt, yt);
/* 43 */     if (age < 50) level.setData(xt, yt, age + 1);
/*    */   }
/*    */   
/*    */   public boolean interact(Level level, int xt, int yt, Player player, com.mojang.ld22.item.Item item, int attackDir) {
/* 47 */     if ((item instanceof ToolItem)) {
/* 48 */       ToolItem tool = (ToolItem)item;
/* 49 */       if ((tool.type == com.mojang.ld22.item.ToolType.shovel) && 
/* 50 */         (player.payStamina(4 - tool.level))) {
/* 51 */         level.setTile(xt, yt, Tile.dirt, 0);
/* 52 */         return true;
/*    */       }
/*    */     }
/*    */     
/* 56 */     return false;
/*    */   }
/*    */   
/*    */   public void steppedOn(Level level, int xt, int yt, com.mojang.ld22.entity.Entity entity) {
/* 60 */     if (this.random.nextInt(60) != 0) return;
/* 61 */     if (level.getData(xt, yt) < 2) return;
/* 62 */     harvest(level, xt, yt);
/*    */   }
/*    */   
/*    */   public void hurt(Level level, int x, int y, Mob source, int dmg, int attackDir)
/*    */   {
/* 67 */     harvest(level, x, y);
/*    */   }
/*    */   
/*    */   private void harvest(Level level, int x, int y) {
/* 71 */     int age = level.getData(x, y);
/*    */     
/* 73 */     int count = this.random.nextInt(2);
/* 74 */     for (int i = 0; i < count; i++) {
/* 75 */       level.add(new ItemEntity(new ResourceItem(Resource.seeds), x * 16 + this.random.nextInt(10) + 3, y * 16 + this.random.nextInt(10) + 3));
/*    */     }
/*    */     
/* 78 */     count = 0;
/* 79 */     if (age == 50) {
/* 80 */       count = this.random.nextInt(3) + 2;
/* 81 */     } else if (age >= 40) {
/* 82 */       count = this.random.nextInt(2) + 1;
/*    */     }
/* 84 */     for (int i = 0; i < count; i++) {
/* 85 */       level.add(new ItemEntity(new ResourceItem(Resource.wheat), x * 16 + this.random.nextInt(10) + 3, y * 16 + this.random.nextInt(10) + 3));
/*    */     }
/*    */     
/* 88 */     level.setTile(x, y, Tile.dirt, 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\level\tile\WheatTile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */