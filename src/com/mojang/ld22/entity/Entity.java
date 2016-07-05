/*     */ package com.mojang.ld22.entity;
/*     */ 
/*     */ import com.mojang.ld22.gfx.Screen;
/*     */ import com.mojang.ld22.item.Item;
/*     */ import com.mojang.ld22.level.Level;
/*     */ import com.mojang.ld22.level.tile.Tile;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class Entity
/*     */ {
/*  12 */   protected final Random random = new Random();
/*     */   public int x;
/*  14 */   public int y; public int xr = 6;
/*  15 */   public int yr = 6;
/*     */   
/*     */   public boolean removed;
/*     */   public Level level;
/*     */   
/*     */   public void render(Screen screen) {}
/*     */   
/*     */   public void tick() {}
/*     */   
/*     */   public void remove()
/*     */   {
/*  26 */     this.removed = true;
/*     */   }
/*     */   
/*     */   public final void init(Level level) {
/*  30 */     this.level = level;
/*     */   }
/*     */   
/*     */   public boolean intersects(int x0, int y0, int x1, int y1) {
/*  34 */     return (this.x + this.xr >= x0) && (this.y + this.yr >= y0) && (this.x - this.xr <= x1) && (this.y - this.yr <= y1);
/*     */   }
/*     */   
/*     */   public boolean blocks(Entity e) {
/*  38 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void hurt(Mob mob, int dmg, int attackDir) {}
/*     */   
/*     */   public void hurt(Tile tile, int x, int y, int dmg) {}
/*     */   
/*     */   public boolean move(int xa, int ya)
/*     */   {
/*  48 */     if ((xa != 0) || (ya != 0)) {
/*  49 */       boolean stopped = true;
/*  50 */       if ((xa != 0) && (move2(xa, 0))) stopped = false;
/*  51 */       if ((ya != 0) && (move2(0, ya))) stopped = false;
/*  52 */       if (!stopped) {
/*  53 */         int xt = this.x >> 4;
/*  54 */         int yt = this.y >> 4;
/*  55 */         this.level.getTile(xt, yt).steppedOn(this.level, xt, yt, this);
/*     */       }
/*  57 */       return !stopped;
/*     */     }
/*  59 */     return true;
/*     */   }
/*     */   
/*     */   protected boolean move2(int xa, int ya) {
/*  63 */     if ((xa != 0) && (ya != 0)) { throw new IllegalArgumentException("Move2 can only move along one axis at a time!");
/*     */     }
/*  65 */     int xto0 = this.x - this.xr >> 4;
/*  66 */     int yto0 = this.y - this.yr >> 4;
/*  67 */     int xto1 = this.x + this.xr >> 4;
/*  68 */     int yto1 = this.y + this.yr >> 4;
/*     */     
/*  70 */     int xt0 = this.x + xa - this.xr >> 4;
/*  71 */     int yt0 = this.y + ya - this.yr >> 4;
/*  72 */     int xt1 = this.x + xa + this.xr >> 4;
/*  73 */     int yt1 = this.y + ya + this.yr >> 4;
/*  74 */     boolean blocked = false;
/*  75 */     for (int yt = yt0; yt <= yt1; yt++)
/*  76 */       for (int xt = xt0; xt <= xt1; xt++)
/*  77 */         if ((xt < xto0) || (xt > xto1) || (yt < yto0) || (yt > yto1)) {
/*  78 */           this.level.getTile(xt, yt).bumpedInto(this.level, xt, yt, this);
/*  79 */           if (!this.level.getTile(xt, yt).mayPass(this.level, xt, yt, this)) {
/*  80 */             blocked = true;
/*  81 */             return false;
/*     */           }
/*     */         }
/*  84 */     if (blocked) { return false;
/*     */     }
/*  86 */     List<Entity> wasInside = this.level.getEntities(this.x - this.xr, this.y - this.yr, this.x + this.xr, this.y + this.yr);
/*  87 */     List<Entity> isInside = this.level.getEntities(this.x + xa - this.xr, this.y + ya - this.yr, this.x + xa + this.xr, this.y + ya + this.yr);
/*  88 */     for (int i = 0; i < isInside.size(); i++) {
/*  89 */       Entity e = (Entity)isInside.get(i);
/*  90 */       if (e != this)
/*     */       {
/*  92 */         e.touchedBy(this); }
/*     */     }
/*  94 */     isInside.removeAll(wasInside);
/*  95 */     for (int i = 0; i < isInside.size(); i++) {
/*  96 */       Entity e = (Entity)isInside.get(i);
/*  97 */       if (e != this)
/*     */       {
/*  99 */         if (e.blocks(this)) {
/* 100 */           return false;
/*     */         }
/*     */       }
/*     */     }
/* 104 */     this.x += xa;
/* 105 */     this.y += ya;
/* 106 */     return true;
/*     */   }
/*     */   
/*     */   protected void touchedBy(Entity entity) {}
/*     */   
/*     */   public boolean isBlockableBy(Mob mob)
/*     */   {
/* 113 */     return true;
/*     */   }
/*     */   
/*     */   public void touchItem(ItemEntity itemEntity) {}
/*     */   
/*     */   public boolean canSwim()
/*     */   {
/* 120 */     return false;
/*     */   }
/*     */   
/*     */   public boolean interact(Player player, Item item, int attackDir) {
/* 124 */     return item.interact(player, this, attackDir);
/*     */   }
/*     */   
/*     */   public boolean use(Player player, int attackDir) {
/* 128 */     return false;
/*     */   }
/*     */   
/*     */   public int getLightRadius() {
/* 132 */     return 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\entity\Entity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */