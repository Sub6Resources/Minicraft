/*     */ package com.mojang.ld22.level.tile;
/*     */ 
/*     */ import com.mojang.ld22.entity.Entity;
/*     */ import com.mojang.ld22.entity.ItemEntity;
/*     */ import com.mojang.ld22.entity.Mob;
/*     */ import com.mojang.ld22.entity.Player;
/*     */ import com.mojang.ld22.gfx.Color;
/*     */ import com.mojang.ld22.gfx.Screen;
/*     */ import com.mojang.ld22.item.Item;
/*     */ import com.mojang.ld22.item.ResourceItem;
/*     */ import com.mojang.ld22.item.ToolItem;
/*     */ import com.mojang.ld22.item.resource.Resource;
/*     */ import com.mojang.ld22.level.Level;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class RockTile extends Tile
/*     */ {
/*     */   public RockTile(int id)
/*     */   {
/*  20 */     super(id);
/*     */   }
/*     */   
/*     */   public void render(Screen screen, Level level, int x, int y) {
/*  24 */     int col = Color.get(444, 444, 333, 333);
/*  25 */     int transitionColor = Color.get(111, 444, 555, level.dirtColor);
/*     */     
/*  27 */     boolean u = level.getTile(x, y - 1) != this;
/*  28 */     boolean d = level.getTile(x, y + 1) != this;
/*  29 */     boolean l = level.getTile(x - 1, y) != this;
/*  30 */     boolean r = level.getTile(x + 1, y) != this;
/*     */     
/*  32 */     boolean ul = level.getTile(x - 1, y - 1) != this;
/*  33 */     boolean dl = level.getTile(x - 1, y + 1) != this;
/*  34 */     boolean ur = level.getTile(x + 1, y - 1) != this;
/*  35 */     boolean dr = level.getTile(x + 1, y + 1) != this;
/*     */     
/*  37 */     if ((!u) && (!l)) {
/*  38 */       if (!ul) {
/*  39 */         screen.render(x * 16 + 0, y * 16 + 0, 0, col, 0);
/*     */       } else
/*  41 */         screen.render(x * 16 + 0, y * 16 + 0, 7, transitionColor, 3);
/*     */     } else {
/*  43 */       screen.render(x * 16 + 0, y * 16 + 0, (l ? 6 : 5) + (u ? 2 : 1) * 32, transitionColor, 3);
/*     */     }
/*  45 */     if ((!u) && (!r)) {
/*  46 */       if (!ur) {
/*  47 */         screen.render(x * 16 + 8, y * 16 + 0, 1, col, 0);
/*     */       } else
/*  49 */         screen.render(x * 16 + 8, y * 16 + 0, 8, transitionColor, 3);
/*     */     } else {
/*  51 */       screen.render(x * 16 + 8, y * 16 + 0, (r ? 4 : 5) + (u ? 2 : 1) * 32, transitionColor, 3);
/*     */     }
/*  53 */     if ((!d) && (!l)) {
/*  54 */       if (!dl) {
/*  55 */         screen.render(x * 16 + 0, y * 16 + 8, 2, col, 0);
/*     */       } else
/*  57 */         screen.render(x * 16 + 0, y * 16 + 8, 39, transitionColor, 3);
/*     */     } else
/*  59 */       screen.render(x * 16 + 0, y * 16 + 8, (l ? 6 : 5) + (d ? 0 : 1) * 32, transitionColor, 3);
/*  60 */     if ((!d) && (!r)) {
/*  61 */       if (!dr) {
/*  62 */         screen.render(x * 16 + 8, y * 16 + 8, 3, col, 0);
/*     */       } else
/*  64 */         screen.render(x * 16 + 8, y * 16 + 8, 40, transitionColor, 3);
/*     */     } else
/*  66 */       screen.render(x * 16 + 8, y * 16 + 8, (r ? 4 : 5) + (d ? 0 : 1) * 32, transitionColor, 3);
/*     */   }
/*     */   
/*     */   public boolean mayPass(Level level, int x, int y, Entity e) {
/*  70 */     return false;
/*     */   }
/*     */   
/*     */   public void hurt(Level level, int x, int y, Mob source, int dmg, int attackDir) {
/*  74 */     hurt(level, x, y, dmg);
/*     */   }
/*     */   
/*     */   public boolean interact(Level level, int xt, int yt, Player player, Item item, int attackDir) {
/*  78 */     if ((item instanceof ToolItem)) {
/*  79 */       ToolItem tool = (ToolItem)item;
/*  80 */       if ((tool.type == com.mojang.ld22.item.ToolType.pickaxe) && 
/*  81 */         (player.payStamina(4 - tool.level))) {
/*  82 */         hurt(level, xt, yt, this.random.nextInt(10) + tool.level * 5 + 10);
/*  83 */         return true;
/*     */       }
/*     */     }
/*     */     
/*  87 */     return false;
/*     */   }
/*     */   
/*     */   public void hurt(Level level, int x, int y, int dmg) {
/*  91 */     int damage = level.getData(x, y) + dmg;
/*  92 */     level.add(new com.mojang.ld22.entity.particle.SmashParticle(x * 16 + 8, y * 16 + 8));
/*  93 */     level.add(new com.mojang.ld22.entity.particle.TextParticle(""+dmg, x * 16 + 8, y * 16 + 8, Color.get(-1, 500, 500, 500)));
/*  94 */     if (damage >= 50) {
/*  95 */       int count = this.random.nextInt(4) + 1;
/*  96 */       for (int i = 0; i < count; i++) {
/*  97 */         level.add(new ItemEntity(new ResourceItem(Resource.stone), x * 16 + this.random.nextInt(10) + 3, y * 16 + this.random.nextInt(10) + 3));
/*     */       }
/*  99 */       count = this.random.nextInt(2);
/* 100 */       for (int i = 0; i < count; i++) {
/* 101 */         level.add(new ItemEntity(new ResourceItem(Resource.coal), x * 16 + this.random.nextInt(10) + 3, y * 16 + this.random.nextInt(10) + 3));
/*     */       }
/* 103 */       level.setTile(x, y, Tile.dirt, 0);
/*     */     } else {
/* 105 */       level.setData(x, y, damage);
/*     */     }
/*     */   }
/*     */   
/*     */   public void tick(Level level, int xt, int yt) {
/* 110 */     int damage = level.getData(xt, yt);
/* 111 */     if (damage > 0) level.setData(xt, yt, damage - 1);
/*     */   }
/*     */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\level\tile\RockTile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */