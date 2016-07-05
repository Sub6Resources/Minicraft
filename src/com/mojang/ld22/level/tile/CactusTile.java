/*    */ package com.mojang.ld22.level.tile;
/*    */ 
/*    */ import com.mojang.ld22.entity.Entity;
/*    */ import com.mojang.ld22.entity.Mob;
/*    */ import com.mojang.ld22.gfx.Color;
/*    */ import com.mojang.ld22.gfx.Screen;
/*    */ import com.mojang.ld22.item.ResourceItem;
/*    */ import com.mojang.ld22.item.resource.Resource;
/*    */ import com.mojang.ld22.level.Level;
/*    */ import java.util.Random;
/*    */ 
/*    */ public class CactusTile extends Tile
/*    */ {
/*    */   public CactusTile(int id)
/*    */   {
/* 16 */     super(id);
/* 17 */     this.connectsToSand = true;
/*    */   }
/*    */   
/*    */   public void render(Screen screen, Level level, int x, int y) {
/* 21 */     int col = Color.get(20, 40, 50, level.sandColor);
/* 22 */     screen.render(x * 16 + 0, y * 16 + 0, 72, col, 0);
/* 23 */     screen.render(x * 16 + 8, y * 16 + 0, 73, col, 0);
/* 24 */     screen.render(x * 16 + 0, y * 16 + 8, 104, col, 0);
/* 25 */     screen.render(x * 16 + 8, y * 16 + 8, 105, col, 0);
/*    */   }
/*    */   
/*    */   public boolean mayPass(Level level, int x, int y, Entity e) {
/* 29 */     return false;
/*    */   }
/*    */   
/*    */   public void hurt(Level level, int x, int y, Mob source, int dmg, int attackDir) {
/* 33 */     int damage = level.getData(x, y) + dmg;
/* 34 */     level.add(new com.mojang.ld22.entity.particle.SmashParticle(x * 16 + 8, y * 16 + 8));
/* 35 */     level.add(new com.mojang.ld22.entity.particle.TextParticle(""+dmg, x * 16 + 8, y * 16 + 8, Color.get(-1, 500, 500, 500)));
/* 36 */     if (damage >= 10) {
/* 37 */       int count = this.random.nextInt(2) + 1;
/* 38 */       for (int i = 0; i < count; i++) {
/* 39 */         level.add(new com.mojang.ld22.entity.ItemEntity(new ResourceItem(Resource.cactusFlower), x * 16 + this.random.nextInt(10) + 3, y * 16 + this.random.nextInt(10) + 3));
/*    */       }
/* 41 */       level.setTile(x, y, Tile.sand, 0);
/*    */     } else {
/* 43 */       level.setData(x, y, damage);
/*    */     }
/*    */   }
/*    */   
/*    */   public void bumpedInto(Level level, int x, int y, Entity entity) {
/* 48 */     entity.hurt(this, x, y, 1);
/*    */   }
/*    */   
/*    */   public void tick(Level level, int xt, int yt) {
/* 52 */     int damage = level.getData(xt, yt);
/* 53 */     if (damage > 0) level.setData(xt, yt, damage - 1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\level\tile\CactusTile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */