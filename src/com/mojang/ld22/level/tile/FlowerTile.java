/*    */ package com.mojang.ld22.level.tile;
/*    */ 
/*    */ import com.mojang.ld22.entity.ItemEntity;
/*    */ import com.mojang.ld22.entity.Mob;
/*    */ import com.mojang.ld22.entity.Player;
/*    */ import com.mojang.ld22.gfx.Screen;
/*    */ import com.mojang.ld22.item.ResourceItem;
/*    */ import com.mojang.ld22.item.ToolItem;
/*    */ import com.mojang.ld22.item.resource.Resource;
/*    */ import com.mojang.ld22.level.Level;
/*    */ import java.util.Random;
/*    */ 
/*    */ public class FlowerTile extends GrassTile
/*    */ {
/*    */   public FlowerTile(int id)
/*    */   {
/* 17 */     super(id);
/* 18 */     tiles[id] = this;
/* 19 */     this.connectsToGrass = true;
/*    */   }
/*    */   
/*    */   public void render(Screen screen, Level level, int x, int y) {
/* 23 */     super.render(screen, level, x, y);
/*    */     
/* 25 */     int data = level.getData(x, y);
/* 26 */     int shape = data / 16 % 2;
/* 27 */     int flowerCol = com.mojang.ld22.gfx.Color.get(10, level.grassColor, 555, 440);
/*    */     
/* 29 */     if (shape == 0) screen.render(x * 16 + 0, y * 16 + 0, 33, flowerCol, 0);
/* 30 */     if (shape == 1) screen.render(x * 16 + 8, y * 16 + 0, 33, flowerCol, 0);
/* 31 */     if (shape == 1) screen.render(x * 16 + 0, y * 16 + 8, 33, flowerCol, 0);
/* 32 */     if (shape == 0) screen.render(x * 16 + 8, y * 16 + 8, 33, flowerCol, 0);
/*    */   }
/*    */   
/*    */   public boolean interact(Level level, int x, int y, Player player, com.mojang.ld22.item.Item item, int attackDir) {
/* 36 */     if ((item instanceof ToolItem)) {
/* 37 */       ToolItem tool = (ToolItem)item;
/* 38 */       if ((tool.type == com.mojang.ld22.item.ToolType.shovel) && 
/* 39 */         (player.payStamina(4 - tool.level))) {
/* 40 */         level.add(new ItemEntity(new ResourceItem(Resource.flower), x * 16 + this.random.nextInt(10) + 3, y * 16 + this.random.nextInt(10) + 3));
/* 41 */         level.add(new ItemEntity(new ResourceItem(Resource.flower), x * 16 + this.random.nextInt(10) + 3, y * 16 + this.random.nextInt(10) + 3));
/* 42 */         level.setTile(x, y, Tile.grass, 0);
/* 43 */         return true;
/*    */       }
/*    */     }
/*    */     
/* 47 */     return false;
/*    */   }
/*    */   
/*    */   public void hurt(Level level, int x, int y, Mob source, int dmg, int attackDir) {
/* 51 */     int count = this.random.nextInt(2) + 1;
/* 52 */     for (int i = 0; i < count; i++) {
/* 53 */       level.add(new ItemEntity(new ResourceItem(Resource.flower), x * 16 + this.random.nextInt(10) + 3, y * 16 + this.random.nextInt(10) + 3));
/*    */     }
/* 55 */     level.setTile(x, y, Tile.grass, 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\level\tile\FlowerTile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */