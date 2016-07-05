/*    */ package com.mojang.ld22.level.tile;
/*    */ 
/*    */ import com.mojang.ld22.entity.Entity;
/*    */ import com.mojang.ld22.entity.Mob;
/*    */ import com.mojang.ld22.entity.Player;
/*    */ import com.mojang.ld22.gfx.Color;
/*    */ import com.mojang.ld22.gfx.Screen;
/*    */ import com.mojang.ld22.item.Item;
/*    */ import com.mojang.ld22.item.ResourceItem;
/*    */ import com.mojang.ld22.item.ToolItem;
/*    */ import com.mojang.ld22.item.ToolType;
/*    */ import com.mojang.ld22.item.resource.Resource;
/*    */ import com.mojang.ld22.level.Level;
/*    */ import java.util.Random;
/*    */ 
/*    */ public class OreTile extends Tile
/*    */ {
/*    */   private Resource toDrop;
/*    */   private int color;
/*    */   
/*    */   public OreTile(int id, Resource toDrop)
/*    */   {
/* 23 */     super(id);
/* 24 */     this.toDrop = toDrop;
/* 25 */     this.color = (toDrop.color & 0xFFFF00);
/*    */   }
/*    */   
/*    */   public void render(Screen screen, Level level, int x, int y) {
/* 29 */     this.color = ((this.toDrop.color & 0xFF00) + Color.get(level.dirtColor));
/* 30 */     screen.render(x * 16 + 0, y * 16 + 0, 49, this.color, 0);
/* 31 */     screen.render(x * 16 + 8, y * 16 + 0, 50, this.color, 0);
/* 32 */     screen.render(x * 16 + 0, y * 16 + 8, 81, this.color, 0);
/* 33 */     screen.render(x * 16 + 8, y * 16 + 8, 82, this.color, 0);
/*    */   }
/*    */   
/*    */   public boolean mayPass(Level level, int x, int y, Entity e) {
/* 37 */     return false;
/*    */   }
/*    */   
/*    */   public void hurt(Level level, int x, int y, Mob source, int dmg, int attackDir) {
/* 41 */     hurt(level, x, y, 0);
/*    */   }
/*    */   
/*    */   public boolean interact(Level level, int xt, int yt, Player player, Item item, int attackDir) {
/* 45 */     if ((item instanceof ToolItem)) {
/* 46 */       ToolItem tool = (ToolItem)item;
/* 47 */       if ((tool.type == ToolType.pickaxe) && 
/* 48 */         (player.payStamina(6 - tool.level))) {
/* 49 */         hurt(level, xt, yt, 1);
/* 50 */         return true;
/*    */       }
/*    */     }
/*    */     
/* 54 */     return false;
/*    */   }
/*    */   
/*    */   public void hurt(Level level, int x, int y, int dmg) {
/* 58 */     int damage = level.getData(x, y) + 1;
/* 59 */     level.add(new com.mojang.ld22.entity.particle.SmashParticle(x * 16 + 8, y * 16 + 8));
/* 60 */     level.add(new com.mojang.ld22.entity.particle.TextParticle(""+dmg, x * 16 + 8, y * 16 + 8, Color.get(-1, 500, 500, 500)));
/* 61 */     if (dmg > 0) {
/* 62 */       int count = this.random.nextInt(2);
/* 63 */       if (damage >= this.random.nextInt(10) + 3) {
/* 64 */         level.setTile(x, y, Tile.dirt, 0);
/* 65 */         count += 2;
/*    */       } else {
/* 67 */         level.setData(x, y, damage);
/*    */       }
/* 69 */       for (int i = 0; i < count; i++) {
/* 70 */         level.add(new com.mojang.ld22.entity.ItemEntity(new ResourceItem(this.toDrop), x * 16 + this.random.nextInt(10) + 3, y * 16 + this.random.nextInt(10) + 3));
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public void bumpedInto(Level level, int x, int y, Entity entity) {
/* 76 */     entity.hurt(this, x, y, 3);
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\level\tile\OreTile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */