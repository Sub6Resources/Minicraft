/*    */ package com.mojang.ld22.level.tile;
/*    */ 
/*    */ import com.mojang.ld22.entity.Player;
/*    */ import com.mojang.ld22.gfx.Screen;
/*    */ import com.mojang.ld22.item.Item;
/*    */ import com.mojang.ld22.item.ResourceItem;
/*    */ import com.mojang.ld22.item.ToolItem;
/*    */ import com.mojang.ld22.item.ToolType;
/*    */ import com.mojang.ld22.level.Level;
/*    */ import com.mojang.ld22.sound.Sound;
/*    */ import java.util.Random;
/*    */ 
/*    */ public class DirtTile extends Tile
/*    */ {
/*    */   public DirtTile(int id)
/*    */   {
/* 17 */     super(id);
/*    */   }
/*    */   
/*    */   public void render(Screen screen, Level level, int x, int y) {
/* 21 */     int col = com.mojang.ld22.gfx.Color.get(level.dirtColor, level.dirtColor, level.dirtColor - 111, level.dirtColor - 111);
/* 22 */     screen.render(x * 16 + 0, y * 16 + 0, 0, col, 0);
/* 23 */     screen.render(x * 16 + 8, y * 16 + 0, 1, col, 0);
/* 24 */     screen.render(x * 16 + 0, y * 16 + 8, 2, col, 0);
/* 25 */     screen.render(x * 16 + 8, y * 16 + 8, 3, col, 0);
/*    */   }
/*    */   
/*    */   public boolean interact(Level level, int xt, int yt, Player player, Item item, int attackDir) {
/* 29 */     if ((item instanceof ToolItem)) {
/* 30 */       ToolItem tool = (ToolItem)item;
/* 31 */       if ((tool.type == ToolType.shovel) && 
/* 32 */         (player.payStamina(4 - tool.level))) {
/* 33 */         level.setTile(xt, yt, Tile.hole, 0);
/* 34 */         level.add(new com.mojang.ld22.entity.ItemEntity(new ResourceItem(com.mojang.ld22.item.resource.Resource.dirt), xt * 16 + this.random.nextInt(10) + 3, yt * 16 + this.random.nextInt(10) + 3));
/* 35 */         Sound.monsterHurt.play();
/* 36 */         return true;
/*    */       }
/*    */       
/* 39 */       if ((tool.type == ToolType.hoe) && 
/* 40 */         (player.payStamina(4 - tool.level))) {
/* 41 */         level.setTile(xt, yt, Tile.farmland, 0);
/* 42 */         Sound.monsterHurt.play();
/* 43 */         return true;
/*    */       }
/*    */     }
/*    */     
/* 47 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\level\tile\DirtTile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */