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
/*     */ public class TreeTile extends Tile
/*     */ {
/*     */   public TreeTile(int id)
/*     */   {
/*  20 */     super(id);
/*  21 */     this.connectsToGrass = true;
/*     */   }
/*     */   
/*     */   public void render(Screen screen, Level level, int x, int y) {
/*  25 */     int col = Color.get(10, 30, 151, level.grassColor);
/*  26 */     int barkCol1 = Color.get(10, 30, 430, level.grassColor);
/*  27 */     int barkCol2 = Color.get(10, 30, 320, level.grassColor);
/*     */     
/*  29 */     boolean u = level.getTile(x, y - 1) == this;
/*  30 */     boolean l = level.getTile(x - 1, y) == this;
/*  31 */     boolean r = level.getTile(x + 1, y) == this;
/*  32 */     boolean d = level.getTile(x, y + 1) == this;
/*  33 */     boolean ul = level.getTile(x - 1, y - 1) == this;
/*  34 */     boolean ur = level.getTile(x + 1, y - 1) == this;
/*  35 */     boolean dl = level.getTile(x - 1, y + 1) == this;
/*  36 */     boolean dr = level.getTile(x + 1, y + 1) == this;
/*     */     
/*  38 */     if ((u) && (ul) && (l)) {
/*  39 */       screen.render(x * 16 + 0, y * 16 + 0, 42, col, 0);
/*     */     } else {
/*  41 */       screen.render(x * 16 + 0, y * 16 + 0, 9, col, 0);
/*     */     }
/*  43 */     if ((u) && (ur) && (r)) {
/*  44 */       screen.render(x * 16 + 8, y * 16 + 0, 74, barkCol2, 0);
/*     */     } else {
/*  46 */       screen.render(x * 16 + 8, y * 16 + 0, 10, col, 0);
/*     */     }
/*  48 */     if ((d) && (dl) && (l)) {
/*  49 */       screen.render(x * 16 + 0, y * 16 + 8, 74, barkCol2, 0);
/*     */     } else {
/*  51 */       screen.render(x * 16 + 0, y * 16 + 8, 41, barkCol1, 0);
/*     */     }
/*  53 */     if ((d) && (dr) && (r)) {
/*  54 */       screen.render(x * 16 + 8, y * 16 + 8, 42, col, 0);
/*     */     } else {
/*  56 */       screen.render(x * 16 + 8, y * 16 + 8, 106, barkCol2, 0);
/*     */     }
/*     */   }
/*     */   
/*     */   public void tick(Level level, int xt, int yt) {
/*  61 */     int damage = level.getData(xt, yt);
/*  62 */     if (damage > 0) level.setData(xt, yt, damage - 1);
/*     */   }
/*     */   
/*     */   public boolean mayPass(Level level, int x, int y, Entity e) {
/*  66 */     return false;
/*     */   }
/*     */   
/*     */   public void hurt(Level level, int x, int y, Mob source, int dmg, int attackDir) {
/*  70 */     hurt(level, x, y, dmg);
/*     */   }
/*     */   
/*     */   public boolean interact(Level level, int xt, int yt, Player player, Item item, int attackDir) {
/*  74 */     if ((item instanceof ToolItem)) {
/*  75 */       ToolItem tool = (ToolItem)item;
/*  76 */       if ((tool.type == com.mojang.ld22.item.ToolType.axe) && 
/*  77 */         (player.payStamina(4 - tool.level))) {
/*  78 */         hurt(level, xt, yt, this.random.nextInt(10) + tool.level * 5 + 10);
/*  79 */         return true;
/*     */       }
/*     */     }
/*     */     
/*  83 */     return false;
/*     */   }
/*     */   
/*     */   private void hurt(Level level, int x, int y, int dmg)
/*     */   {
/*  88 */     int count = this.random.nextInt(10) == 0 ? 1 : 0;
/*  89 */     for (int i = 0; i < count; i++) {
/*  90 */       level.add(new ItemEntity(new ResourceItem(Resource.apple), x * 16 + this.random.nextInt(10) + 3, y * 16 + this.random.nextInt(10) + 3));
/*     */     }
/*     */     
/*  93 */     int damage = level.getData(x, y) + dmg;
/*  94 */     level.add(new com.mojang.ld22.entity.particle.SmashParticle(x * 16 + 8, y * 16 + 8));
/*  95 */     level.add(new com.mojang.ld22.entity.particle.TextParticle(""+dmg, x * 16 + 8, y * 16 + 8, Color.get(-1, 500, 500, 500)));
/*  96 */     if (damage >= 20) {
/*  97 */       count = this.random.nextInt(2) + 1;
/*  98 */       for (int i = 0; i < count; i++) {
/*  99 */         level.add(new ItemEntity(new ResourceItem(Resource.wood), x * 16 + this.random.nextInt(10) + 3, y * 16 + this.random.nextInt(10) + 3));
/*     */       }
/* 101 */       count = this.random.nextInt(this.random.nextInt(4) + 1);
/* 102 */       for (int i = 0; i < count; i++) {
/* 103 */         level.add(new ItemEntity(new ResourceItem(Resource.acorn), x * 16 + this.random.nextInt(10) + 3, y * 16 + this.random.nextInt(10) + 3));
/*     */       }
/* 105 */       level.setTile(x, y, Tile.grass, 0);
/*     */     } else {
/* 107 */       level.setData(x, y, damage);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\level\tile\TreeTile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */