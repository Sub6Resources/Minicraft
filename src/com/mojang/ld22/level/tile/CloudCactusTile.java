/*    */ package com.mojang.ld22.level.tile;
/*    */ 
/*    */ import com.mojang.ld22.entity.AirWizard;
/*    */ import com.mojang.ld22.entity.Entity;
/*    */ import com.mojang.ld22.entity.Mob;
/*    */ import com.mojang.ld22.entity.Player;
/*    */ import com.mojang.ld22.entity.particle.SmashParticle;
/*    */ import com.mojang.ld22.entity.particle.TextParticle;
/*    */ import com.mojang.ld22.gfx.Color;
/*    */ import com.mojang.ld22.gfx.Screen;
/*    */ import com.mojang.ld22.item.Item;
/*    */ import com.mojang.ld22.item.ToolItem;
/*    */ import com.mojang.ld22.item.ToolType;
/*    */ import com.mojang.ld22.level.Level;
/*    */ 
/*    */ public class CloudCactusTile extends Tile
/*    */ {
/*    */   public CloudCactusTile(int id)
/*    */   {
/* 20 */     super(id);
/*    */   }
/*    */   
/*    */   public void render(Screen screen, Level level, int x, int y) {
/* 24 */     int color = Color.get(444, 111, 333, 555);
/* 25 */     screen.render(x * 16 + 0, y * 16 + 0, 49, color, 0);
/* 26 */     screen.render(x * 16 + 8, y * 16 + 0, 50, color, 0);
/* 27 */     screen.render(x * 16 + 0, y * 16 + 8, 81, color, 0);
/* 28 */     screen.render(x * 16 + 8, y * 16 + 8, 82, color, 0);
/*    */   }
/*    */   
/*    */   public boolean mayPass(Level level, int x, int y, Entity e) {
/* 32 */     if ((e instanceof AirWizard)) return true;
/* 33 */     return false;
/*    */   }
/*    */   
/*    */   public void hurt(Level level, int x, int y, Mob source, int dmg, int attackDir) {
/* 37 */     hurt(level, x, y, 0);
/*    */   }
/*    */   
/*    */   public boolean interact(Level level, int xt, int yt, Player player, Item item, int attackDir) {
/* 41 */     if ((item instanceof ToolItem)) {
/* 42 */       ToolItem tool = (ToolItem)item;
/* 43 */       if ((tool.type == ToolType.pickaxe) && 
/* 44 */         (player.payStamina(6 - tool.level))) {
/* 45 */         hurt(level, xt, yt, 1);
/* 46 */         return true;
/*    */       }
/*    */     }
/*    */     
/* 50 */     return false;
/*    */   }
/*    */   
/*    */   public void hurt(Level level, int x, int y, int dmg) {
/* 54 */     int damage = level.getData(x, y) + 1;
/* 55 */     level.add(new SmashParticle(x * 16 + 8, y * 16 + 8));
/* 56 */     level.add(new TextParticle(""+dmg, x * 16 + 8, y * 16 + 8, Color.get(-1, 500, 500, 500)));
/* 57 */     if (dmg > 0) {
/* 58 */       if (damage >= 10) {
/* 59 */         level.setTile(x, y, Tile.cloud, 0);
/*    */       } else {
/* 61 */         level.setData(x, y, damage);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public void bumpedInto(Level level, int x, int y, Entity entity) {
/* 67 */     if ((entity instanceof AirWizard)) return;
/* 68 */     entity.hurt(this, x, y, 3);
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\level\tile\CloudCactusTile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */