/*     */ package com.mojang.ld22.entity;
/*     */ 
/*     */ import com.mojang.ld22.level.Level;
/*     */ import com.mojang.ld22.level.tile.Tile;
/*     */ import com.mojang.ld22.sound.Sound;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class Mob extends Entity
/*     */ {
/*  10 */   protected int walkDist = 0;
/*  11 */   protected int dir = 0;
/*  12 */   public int hurtTime = 0;
/*     */   protected int xKnockback;
/*  14 */   protected int yKnockback; public int maxHealth = 10;
/*  15 */   public int health = this.maxHealth;
/*  16 */   public int swimTimer = 0;
/*  17 */   public int tickTime = 0;
/*     */   
/*     */   public Mob() {
/*  20 */     this.x = (this.y = 8);
/*  21 */     this.xr = 4;
/*  22 */     this.yr = 3;
/*     */   }
/*     */   
/*     */   public void tick() {
/*  26 */     this.tickTime += 1;
/*  27 */     if (this.level.getTile(this.x >> 4, this.y >> 4) == Tile.lava) {
/*  28 */       hurt(this, 4, this.dir ^ 0x1);
/*     */     }
/*     */     
/*  31 */     if (this.health <= 0) {
/*  32 */       die();
/*     */     }
/*  34 */     if (this.hurtTime > 0) this.hurtTime -= 1;
/*     */   }
/*     */   
/*     */   protected void die() {
/*  38 */     remove();
/*     */   }
/*     */   
/*     */   public boolean move(int xa, int ya) {
/*  42 */     if ((isSwimming()) && 
/*  43 */       (this.swimTimer++ % 2 == 0)) { return true;
/*     */     }
/*  45 */     if (this.xKnockback < 0) {
/*  46 */       move2(-1, 0);
/*  47 */       this.xKnockback += 1;
/*     */     }
/*  49 */     if (this.xKnockback > 0) {
/*  50 */       move2(1, 0);
/*  51 */       this.xKnockback -= 1;
/*     */     }
/*  53 */     if (this.yKnockback < 0) {
/*  54 */       move2(0, -1);
/*  55 */       this.yKnockback += 1;
/*     */     }
/*  57 */     if (this.yKnockback > 0) {
/*  58 */       move2(0, 1);
/*  59 */       this.yKnockback -= 1;
/*     */     }
/*  61 */     if (this.hurtTime > 0) return true;
/*  62 */     if ((xa != 0) || (ya != 0)) {
/*  63 */       this.walkDist += 1;
/*  64 */       if (xa < 0) this.dir = 2;
/*  65 */       if (xa > 0) this.dir = 3;
/*  66 */       if (ya < 0) this.dir = 1;
/*  67 */       if (ya > 0) this.dir = 0;
/*     */     }
/*  69 */     return super.move(xa, ya);
/*     */   }
/*     */   
/*     */   protected boolean isSwimming() {
/*  73 */     Tile tile = this.level.getTile(this.x >> 4, this.y >> 4);
/*  74 */     return (tile == Tile.water) || (tile == Tile.lava);
/*     */   }
/*     */   
/*     */   public boolean blocks(Entity e) {
/*  78 */     return e.isBlockableBy(this);
/*     */   }
/*     */   
/*     */   public void hurt(Tile tile, int x, int y, int damage) {
/*  82 */     int attackDir = this.dir ^ 0x1;
/*  83 */     doHurt(damage, attackDir);
/*     */   }
/*     */   
/*     */   public void hurt(Mob mob, int damage, int attackDir) {
/*  87 */     doHurt(damage, attackDir);
/*     */   }
/*     */   
/*     */   public void heal(int heal) {
/*  91 */     if (this.hurtTime > 0) { return;
/*     */     }
/*  93 */     this.level.add(new com.mojang.ld22.entity.particle.TextParticle(""+heal, this.x, this.y, com.mojang.ld22.gfx.Color.get(-1, 50, 50, 50)));
/*  94 */     this.health += heal;
/*  95 */     if (this.health > this.maxHealth) this.health = this.maxHealth;
/*     */   }
/*     */   
/*     */   protected void doHurt(int damage, int attackDir) {
/*  99 */     if (this.hurtTime > 0) { return;
/*     */     }
/* 101 */     if (this.level.player != null) {
/* 102 */       int xd = this.level.player.x - this.x;
/* 103 */       int yd = this.level.player.y - this.y;
/* 104 */       if (xd * xd + yd * yd < 6400) {
/* 105 */         Sound.monsterHurt.play();
/*     */       }
/*     */     }
/* 108 */     this.level.add(new com.mojang.ld22.entity.particle.TextParticle(""+damage, this.x, this.y, com.mojang.ld22.gfx.Color.get(-1, 500, 500, 500)));
/* 109 */     this.health -= damage;
/* 110 */     if (attackDir == 0) this.yKnockback = 6;
/* 111 */     if (attackDir == 1) this.yKnockback = -6;
/* 112 */     if (attackDir == 2) this.xKnockback = -6;
/* 113 */     if (attackDir == 3) this.xKnockback = 6;
/* 114 */     this.hurtTime = 10;
/*     */   }
/*     */   
/*     */   public boolean findStartPos(Level level) {
/* 118 */     int x = this.random.nextInt(level.w);
/* 119 */     int y = this.random.nextInt(level.h);
/* 120 */     int xx = x * 16 + 8;
/* 121 */     int yy = y * 16 + 8;
/*     */     
/* 123 */     if (level.player != null) {
/* 124 */       int xd = level.player.x - xx;
/* 125 */       int yd = level.player.y - yy;
/* 126 */       if (xd * xd + yd * yd < 6400) { return false;
/*     */       }
/*     */     }
/* 129 */     int r = level.monsterDensity * 16;
/* 130 */     if (level.getEntities(xx - r, yy - r, xx + r, yy + r).size() > 0) { return false;
/*     */     }
/* 132 */     if (level.getTile(x, y).mayPass(level, x, y, this)) {
/* 133 */       this.x = xx;
/* 134 */       this.y = yy;
/* 135 */       return true;
/*     */     }
/*     */     
/* 138 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\entity\Mob.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */