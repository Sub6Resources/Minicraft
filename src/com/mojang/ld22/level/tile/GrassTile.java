/*    */ package com.mojang.ld22.level.tile;
/*    */ 
/*    */ import com.mojang.ld22.entity.Player;
/*    */ import com.mojang.ld22.gfx.Screen;
/*    */ import com.mojang.ld22.item.ResourceItem;
/*    */ import com.mojang.ld22.item.ToolItem;
/*    */ import com.mojang.ld22.item.ToolType;
/*    */ import com.mojang.ld22.item.resource.Resource;
/*    */ import com.mojang.ld22.level.Level;
/*    */ import com.mojang.ld22.sound.Sound;
/*    */ import java.util.Random;
/*    */ 
/*    */ public class GrassTile extends Tile
/*    */ {
/*    */   public GrassTile(int id)
/*    */   {
/* 17 */     super(id);
/* 18 */     this.connectsToGrass = true;
/*    */   }
/*    */   
/*    */   public void render(Screen screen, Level level, int x, int y) {
/* 22 */     int col = com.mojang.ld22.gfx.Color.get(level.grassColor, level.grassColor, level.grassColor + 111, level.grassColor + 111);
/* 23 */     int transitionColor = com.mojang.ld22.gfx.Color.get(level.grassColor - 111, level.grassColor, level.grassColor + 111, level.dirtColor);
/*    */     
/* 25 */     boolean u = !level.getTile(x, y - 1).connectsToGrass;
/* 26 */     boolean d = !level.getTile(x, y + 1).connectsToGrass;
/* 27 */     boolean l = !level.getTile(x - 1, y).connectsToGrass;
/* 28 */     boolean r = !level.getTile(x + 1, y).connectsToGrass;
/*    */     
/* 30 */     if ((!u) && (!l)) {
/* 31 */       screen.render(x * 16 + 0, y * 16 + 0, 0, col, 0);
/*    */     } else {
/* 33 */       screen.render(x * 16 + 0, y * 16 + 0, (l ? 11 : 12) + (u ? 0 : 1) * 32, transitionColor, 0);
/*    */     }
/* 35 */     if ((!u) && (!r)) {
/* 36 */       screen.render(x * 16 + 8, y * 16 + 0, 1, col, 0);
/*    */     } else {
/* 38 */       screen.render(x * 16 + 8, y * 16 + 0, (r ? 13 : 12) + (u ? 0 : 1) * 32, transitionColor, 0);
/*    */     }
/* 40 */     if ((!d) && (!l)) {
/* 41 */       screen.render(x * 16 + 0, y * 16 + 8, 2, col, 0);
/*    */     } else
/* 43 */       screen.render(x * 16 + 0, y * 16 + 8, (l ? 11 : 12) + (d ? 2 : 1) * 32, transitionColor, 0);
/* 44 */     if ((!d) && (!r)) {
/* 45 */       screen.render(x * 16 + 8, y * 16 + 8, 3, col, 0);
/*    */     } else
/* 47 */       screen.render(x * 16 + 8, y * 16 + 8, (r ? 13 : 12) + (d ? 2 : 1) * 32, transitionColor, 0);
/*    */   }
/*    */   
/*    */   public void tick(Level level, int xt, int yt) {
/* 51 */     if (this.random.nextInt(40) != 0) { return;
/*    */     }
/* 53 */     int xn = xt;
/* 54 */     int yn = yt;
/*    */     
/* 56 */     if (this.random.nextBoolean()) {
/* 57 */       xn += this.random.nextInt(2) * 2 - 1;
/*    */     } else {
/* 59 */       yn += this.random.nextInt(2) * 2 - 1;
/*    */     }
/* 61 */     if (level.getTile(xn, yn) == Tile.dirt) {
/* 62 */       level.setTile(xn, yn, this, 0);
/*    */     }
/*    */   }
/*    */   
/*    */   public boolean interact(Level level, int xt, int yt, Player player, com.mojang.ld22.item.Item item, int attackDir) {
/* 67 */     if ((item instanceof ToolItem)) {
/* 68 */       ToolItem tool = (ToolItem)item;
/* 69 */       if ((tool.type == ToolType.shovel) && 
/* 70 */         (player.payStamina(4 - tool.level))) {
/* 71 */         level.setTile(xt, yt, Tile.dirt, 0);
/* 72 */         Sound.monsterHurt.play();
/* 73 */         if (this.random.nextInt(5) == 0) {
/* 74 */           level.add(new com.mojang.ld22.entity.ItemEntity(new ResourceItem(Resource.seeds), xt * 16 + this.random.nextInt(10) + 3, yt * 16 + this.random.nextInt(10) + 3));
/* 75 */           return true;
/*    */         }
/*    */       }
/*    */       
/* 79 */       if ((tool.type == ToolType.hoe) && 
/* 80 */         (player.payStamina(4 - tool.level))) {
/* 81 */         Sound.monsterHurt.play();
/* 82 */         if (this.random.nextInt(5) == 0) {
/* 83 */           level.add(new com.mojang.ld22.entity.ItemEntity(new ResourceItem(Resource.seeds), xt * 16 + this.random.nextInt(10) + 3, yt * 16 + this.random.nextInt(10) + 3));
/* 84 */           return true;
/*    */         }
/* 86 */         level.setTile(xt, yt, Tile.farmland, 0);
/* 87 */         return true;
/*    */       }
/*    */     }
/*    */     
/* 91 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\level\tile\GrassTile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */