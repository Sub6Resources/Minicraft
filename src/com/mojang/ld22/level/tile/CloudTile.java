/*    */ package com.mojang.ld22.level.tile;
/*    */ 
/*    */ import com.mojang.ld22.entity.Entity;
/*    */ import com.mojang.ld22.entity.Player;
/*    */ import com.mojang.ld22.gfx.Color;
/*    */ import com.mojang.ld22.gfx.Screen;
/*    */ import com.mojang.ld22.item.Item;
/*    */ import com.mojang.ld22.item.ToolItem;
/*    */ import com.mojang.ld22.item.ToolType;
/*    */ import com.mojang.ld22.level.Level;
/*    */ import java.util.Random;
/*    */ 
/*    */ public class CloudTile extends Tile
/*    */ {
/*    */   public CloudTile(int id)
/*    */   {
/* 17 */     super(id);
/*    */   }
/*    */   
/*    */   public void render(Screen screen, Level level, int x, int y) {
/* 21 */     int col = Color.get(444, 444, 555, 555);
/* 22 */     int transitionColor = Color.get(333, 444, 555, -1);
/*    */     
/* 24 */     boolean u = level.getTile(x, y - 1) == Tile.infiniteFall;
/* 25 */     boolean d = level.getTile(x, y + 1) == Tile.infiniteFall;
/* 26 */     boolean l = level.getTile(x - 1, y) == Tile.infiniteFall;
/* 27 */     boolean r = level.getTile(x + 1, y) == Tile.infiniteFall;
/*    */     
/* 29 */     boolean ul = level.getTile(x - 1, y - 1) == Tile.infiniteFall;
/* 30 */     boolean dl = level.getTile(x - 1, y + 1) == Tile.infiniteFall;
/* 31 */     boolean ur = level.getTile(x + 1, y - 1) == Tile.infiniteFall;
/* 32 */     boolean dr = level.getTile(x + 1, y + 1) == Tile.infiniteFall;
/*    */     
/* 34 */     if ((!u) && (!l)) {
/* 35 */       if (!ul) {
/* 36 */         screen.render(x * 16 + 0, y * 16 + 0, 17, col, 0);
/*    */       } else
/* 38 */         screen.render(x * 16 + 0, y * 16 + 0, 7, transitionColor, 3);
/*    */     } else {
/* 40 */       screen.render(x * 16 + 0, y * 16 + 0, (l ? 6 : 5) + (u ? 2 : 1) * 32, transitionColor, 3);
/*    */     }
/* 42 */     if ((!u) && (!r)) {
/* 43 */       if (!ur) {
/* 44 */         screen.render(x * 16 + 8, y * 16 + 0, 18, col, 0);
/*    */       } else
/* 46 */         screen.render(x * 16 + 8, y * 16 + 0, 8, transitionColor, 3);
/*    */     } else {
/* 48 */       screen.render(x * 16 + 8, y * 16 + 0, (r ? 4 : 5) + (u ? 2 : 1) * 32, transitionColor, 3);
/*    */     }
/* 50 */     if ((!d) && (!l)) {
/* 51 */       if (!dl) {
/* 52 */         screen.render(x * 16 + 0, y * 16 + 8, 20, col, 0);
/*    */       } else
/* 54 */         screen.render(x * 16 + 0, y * 16 + 8, 39, transitionColor, 3);
/*    */     } else
/* 56 */       screen.render(x * 16 + 0, y * 16 + 8, (l ? 6 : 5) + (d ? 0 : 1) * 32, transitionColor, 3);
/* 57 */     if ((!d) && (!r)) {
/* 58 */       if (!dr) {
/* 59 */         screen.render(x * 16 + 8, y * 16 + 8, 19, col, 0);
/*    */       } else
/* 61 */         screen.render(x * 16 + 8, y * 16 + 8, 40, transitionColor, 3);
/*    */     } else
/* 63 */       screen.render(x * 16 + 8, y * 16 + 8, (r ? 4 : 5) + (d ? 0 : 1) * 32, transitionColor, 3);
/*    */   }
/*    */   
/*    */   public boolean mayPass(Level level, int x, int y, Entity e) {
/* 67 */     return true;
/*    */   }
/*    */   
/*    */   public boolean interact(Level level, int xt, int yt, Player player, Item item, int attackDir) {
/* 71 */     if ((item instanceof ToolItem)) {
/* 72 */       ToolItem tool = (ToolItem)item;
/* 73 */       if ((tool.type == ToolType.shovel) && 
/* 74 */         (player.payStamina(5)))
/*    */       {
/* 76 */         int count = this.random.nextInt(2) + 1;
/* 77 */         for (int i = 0; i < count; i++) {
/* 78 */           level.add(new com.mojang.ld22.entity.ItemEntity(new com.mojang.ld22.item.ResourceItem(com.mojang.ld22.item.resource.Resource.cloud), xt * 16 + this.random.nextInt(10) + 3, yt * 16 + this.random.nextInt(10) + 3));
/*    */         }
/* 80 */         return true;
/*    */       }
/*    */     }
/*    */     
/* 84 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\level\tile\CloudTile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */