/*     */ package com.mojang.ld22.entity;
/*     */ 
/*     */ import com.mojang.ld22.gfx.Color;
/*     */ import com.mojang.ld22.level.Level;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class AirWizard extends Mob
/*     */ {
/*     */   private int xa;
/*     */   private int ya;
/*  11 */   private int randomWalkTime = 0;
/*  12 */   private int attackDelay = 0;
/*  13 */   private int attackTime = 0;
/*  14 */   private int attackType = 0;
/*     */   
/*     */   public AirWizard() {
/*  17 */     this.x = this.random.nextInt(1024);
/*  18 */     this.y = this.random.nextInt(1024);
/*  19 */     this.health = (this.maxHealth = 2000);
/*     */   }
/*     */   
/*     */   public void tick() {
/*  23 */     super.tick();
/*     */     
/*  25 */     if (this.attackDelay > 0) {
/*  26 */       this.dir = ((this.attackDelay - 45) / 4 % 4);
/*  27 */       this.dir = (this.dir * 2 % 4 + this.dir / 2);
/*  28 */       if (this.attackDelay < 45) {
/*  29 */         this.dir = 0;
/*     */       }
/*  31 */       this.attackDelay -= 1;
/*  32 */       if (this.attackDelay == 0) {
/*  33 */         this.attackType = 0;
/*  34 */         if (this.health < 1000) this.attackType = 1;
/*  35 */         if (this.health < 200) this.attackType = 2;
/*  36 */         this.attackTime = 120;
/*     */       }
/*  38 */       return;
/*     */     }
/*     */     
/*  41 */     if (this.attackTime > 0) {
/*  42 */       this.attackTime -= 1;
/*  43 */       double dir = this.attackTime * 0.25D * (this.attackTime % 2 * 2 - 1);
/*  44 */       double speed = 0.7D + this.attackType * 0.2D;
/*  45 */       this.level.add(new Spark(this, Math.cos(dir) * speed, Math.sin(dir) * speed));
/*  46 */       return;
/*     */     }
/*     */     
/*  49 */     if ((this.level.player != null) && (this.randomWalkTime == 0)) {
/*  50 */       int xd = this.level.player.x - this.x;
/*  51 */       int yd = this.level.player.y - this.y;
/*  52 */       if (xd * xd + yd * yd < 1024) {
/*  53 */         this.xa = 0;
/*  54 */         this.ya = 0;
/*  55 */         if (xd < 0) this.xa = 1;
/*  56 */         if (xd > 0) this.xa = -1;
/*  57 */         if (yd < 0) this.ya = 1;
/*  58 */         if (yd > 0) this.ya = -1;
/*  59 */       } else if (xd * xd + yd * yd > 6400) {
/*  60 */         this.xa = 0;
/*  61 */         this.ya = 0;
/*  62 */         if (xd < 0) this.xa = -1;
/*  63 */         if (xd > 0) this.xa = 1;
/*  64 */         if (yd < 0) this.ya = -1;
/*  65 */         if (yd > 0) { this.ya = 1;
/*     */         }
/*     */       }
/*     */     }
/*  69 */     int speed = this.tickTime % 4 == 0 ? 0 : 1;
/*  70 */     if ((!move(this.xa * speed, this.ya * speed)) || (this.random.nextInt(100) == 0)) {
/*  71 */       this.randomWalkTime = 30;
/*  72 */       this.xa = (this.random.nextInt(3) - 1);
/*  73 */       this.ya = (this.random.nextInt(3) - 1);
/*     */     }
/*  75 */     if (this.randomWalkTime > 0) {
/*  76 */       this.randomWalkTime -= 1;
/*  77 */       if ((this.level.player != null) && (this.randomWalkTime == 0)) {
/*  78 */         int xd = this.level.player.x - this.x;
/*  79 */         int yd = this.level.player.y - this.y;
/*  80 */         if ((this.random.nextInt(4) == 0) && (xd * xd + yd * yd < 2500) && 
/*  81 */           (this.attackDelay == 0) && (this.attackTime == 0)) {
/*  82 */           this.attackDelay = 120;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected void doHurt(int damage, int attackDir)
/*     */   {
/*  90 */     super.doHurt(damage, attackDir);
/*  91 */     if ((this.attackDelay == 0) && (this.attackTime == 0)) {
/*  92 */       this.attackDelay = 120;
/*     */     }
/*     */   }
/*     */   
/*     */   public void render(com.mojang.ld22.gfx.Screen screen) {
/*  97 */     int xt = 8;
/*  98 */     int yt = 14;
/*     */     
/* 100 */     int flip1 = this.walkDist >> 3 & 0x1;
/* 101 */     int flip2 = this.walkDist >> 3 & 0x1;
/*     */     
/* 103 */     if (this.dir == 1) {
/* 104 */       xt += 2;
/*     */     }
/* 106 */     if (this.dir > 1)
/*     */     {
/* 108 */       flip1 = 0;
/* 109 */       flip2 = this.walkDist >> 4 & 0x1;
/* 110 */       if (this.dir == 2) {
/* 111 */         flip1 = 1;
/*     */       }
/* 113 */       xt += 4 + (this.walkDist >> 3 & 0x1) * 2;
/*     */     }
/*     */     
/* 116 */     int xo = this.x - 8;
/* 117 */     int yo = this.y - 11;
/*     */     
/* 119 */     int col1 = Color.get(-1, 100, 500, 555);
/* 120 */     int col2 = Color.get(-1, 100, 500, 532);
/* 121 */     if (this.health < 200) {
/* 122 */       if (this.tickTime / 3 % 2 == 0) {
/* 123 */         col1 = Color.get(-1, 500, 100, 555);
/* 124 */         col2 = Color.get(-1, 500, 100, 532);
/*     */       }
/* 126 */     } else if ((this.health < 1000) && 
/* 127 */       (this.tickTime / 5 % 4 == 0)) {
/* 128 */       col1 = Color.get(-1, 500, 100, 555);
/* 129 */       col2 = Color.get(-1, 500, 100, 532);
/*     */     }
/*     */     
/* 132 */     if (this.hurtTime > 0) {
/* 133 */       col1 = Color.get(-1, 555, 555, 555);
/* 134 */       col2 = Color.get(-1, 555, 555, 555);
/*     */     }
/*     */     
/* 137 */     screen.render(xo + 8 * flip1, yo + 0, xt + yt * 32, col1, flip1);
/* 138 */     screen.render(xo + 8 - 8 * flip1, yo + 0, xt + 1 + yt * 32, col1, flip1);
/* 139 */     screen.render(xo + 8 * flip2, yo + 8, xt + (yt + 1) * 32, col2, flip2);
/* 140 */     screen.render(xo + 8 - 8 * flip2, yo + 8, xt + 1 + (yt + 1) * 32, col2, flip2);
/*     */   }
/*     */   
/*     */   protected void touchedBy(Entity entity) {
/* 144 */     if ((entity instanceof Player)) {
/* 145 */       entity.hurt(this, 3, this.dir);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void die() {
/* 150 */     super.die();
/* 151 */     if (this.level.player != null) {
/* 152 */       this.level.player.score += 1000;
/* 153 */       this.level.player.gameWon();
/*     */     }
/* 155 */     com.mojang.ld22.sound.Sound.bossdeath.play();
/*     */   }
/*     */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\entity\AirWizard.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */