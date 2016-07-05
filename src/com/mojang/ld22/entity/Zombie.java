/*    */ package com.mojang.ld22.entity;
/*    */ 
/*    */ import com.mojang.ld22.level.Level;
/*    */ import java.util.Random;
/*    */ 
/*    */ public class Zombie extends Mob
/*    */ {
/*    */   private int xa;
/*    */   private int ya;
/*    */   private int lvl;
/* 11 */   private int randomWalkTime = 0;
/*    */   
/*    */   public Zombie(int lvl) {
/* 14 */     this.lvl = lvl;
/* 15 */     this.x = this.random.nextInt(1024);
/* 16 */     this.y = this.random.nextInt(1024);
/* 17 */     this.health = (this.maxHealth = lvl * lvl * 10);
/*    */   }
/*    */   
/*    */   public void tick()
/*    */   {
/* 22 */     super.tick();
/*    */     
/* 24 */     if ((this.level.player != null) && (this.randomWalkTime == 0)) {
/* 25 */       int xd = this.level.player.x - this.x;
/* 26 */       int yd = this.level.player.y - this.y;
/* 27 */       if (xd * xd + yd * yd < 2500) {
/* 28 */         this.xa = 0;
/* 29 */         this.ya = 0;
/* 30 */         if (xd < 0) this.xa = -1;
/* 31 */         if (xd > 0) this.xa = 1;
/* 32 */         if (yd < 0) this.ya = -1;
/* 33 */         if (yd > 0) { this.ya = 1;
/*    */         }
/*    */       }
/*    */     }
/* 37 */     int speed = this.tickTime & 0x1;
/* 38 */     if ((!move(this.xa * speed, this.ya * speed)) || (this.random.nextInt(200) == 0)) {
/* 39 */       this.randomWalkTime = 60;
/* 40 */       this.xa = ((this.random.nextInt(3) - 1) * this.random.nextInt(2));
/* 41 */       this.ya = ((this.random.nextInt(3) - 1) * this.random.nextInt(2));
/*    */     }
/* 43 */     if (this.randomWalkTime > 0) this.randomWalkTime -= 1;
/*    */   }
/*    */   
/*    */   public void render(com.mojang.ld22.gfx.Screen screen) {
/* 47 */     int xt = 0;
/* 48 */     int yt = 14;
/*    */     
/* 50 */     int flip1 = this.walkDist >> 3 & 0x1;
/* 51 */     int flip2 = this.walkDist >> 3 & 0x1;
/*    */     
/* 53 */     if (this.dir == 1) {
/* 54 */       xt += 2;
/*    */     }
/* 56 */     if (this.dir > 1)
/*    */     {
/* 58 */       flip1 = 0;
/* 59 */       flip2 = this.walkDist >> 4 & 0x1;
/* 60 */       if (this.dir == 2) {
/* 61 */         flip1 = 1;
/*    */       }
/* 63 */       xt += 4 + (this.walkDist >> 3 & 0x1) * 2;
/*    */     }
/*    */     
/* 66 */     int xo = this.x - 8;
/* 67 */     int yo = this.y - 11;
/*    */     
/* 69 */     int col = com.mojang.ld22.gfx.Color.get(-1, 10, 252, 40);
/* 70 */     if (this.lvl == 2) col = com.mojang.ld22.gfx.Color.get(-1, 100, 522, 40);
/* 71 */     if (this.lvl == 3) col = com.mojang.ld22.gfx.Color.get(-1, 111, 444, 40);
/* 72 */     if (this.lvl == 4) col = com.mojang.ld22.gfx.Color.get(-1, 0, 111, 16);
/* 73 */     if (this.hurtTime > 0) {
/* 74 */       col = com.mojang.ld22.gfx.Color.get(-1, 555, 555, 555);
/*    */     }
/*    */     
/* 77 */     screen.render(xo + 8 * flip1, yo + 0, xt + yt * 32, col, flip1);
/* 78 */     screen.render(xo + 8 - 8 * flip1, yo + 0, xt + 1 + yt * 32, col, flip1);
/* 79 */     screen.render(xo + 8 * flip2, yo + 8, xt + (yt + 1) * 32, col, flip2);
/* 80 */     screen.render(xo + 8 - 8 * flip2, yo + 8, xt + 1 + (yt + 1) * 32, col, flip2);
/*    */   }
/*    */   
/*    */   protected void touchedBy(Entity entity) {
/* 84 */     if ((entity instanceof Player)) {
/* 85 */       entity.hurt(this, this.lvl + 1, this.dir);
/*    */     }
/*    */   }
/*    */   
/*    */   protected void die() {
/* 90 */     super.die();
/*    */     
/* 92 */     int count = this.random.nextInt(2) + 1;
/* 93 */     for (int i = 0; i < count; i++) {
/* 94 */       this.level.add(new ItemEntity(new com.mojang.ld22.item.ResourceItem(com.mojang.ld22.item.resource.Resource.cloth), this.x + this.random.nextInt(11) - 5, this.y + this.random.nextInt(11) - 5));
/*    */     }
/*    */     
/* 97 */     if (this.level.player != null) {
/* 98 */       this.level.player.score += 50 * this.lvl;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\entity\Zombie.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */