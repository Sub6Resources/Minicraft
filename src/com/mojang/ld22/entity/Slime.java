/*    */ package com.mojang.ld22.entity;
/*    */ 
/*    */ import com.mojang.ld22.gfx.Screen;
/*    */ import java.util.Random;
/*    */ 
/*    */ public class Slime extends Mob
/*    */ {
/*    */   private int xa;
/*    */   private int ya;
/* 10 */   private int jumpTime = 0;
/*    */   private int lvl;
/*    */   
/*    */   public Slime(int lvl) {
/* 14 */     this.lvl = lvl;
/* 15 */     this.x = this.random.nextInt(1024);
/* 16 */     this.y = this.random.nextInt(1024);
/* 17 */     this.health = (this.maxHealth = lvl * lvl * 5);
/*    */   }
/*    */   
/*    */   public void tick() {
/* 21 */     super.tick();
/*    */     
/* 23 */     int speed = 1;
/* 24 */     if (((!move(this.xa * speed, this.ya * speed)) || (this.random.nextInt(40) == 0)) && 
/* 25 */       (this.jumpTime <= -10)) {
/* 26 */       this.xa = (this.random.nextInt(3) - 1);
/* 27 */       this.ya = (this.random.nextInt(3) - 1);
/*    */       
/* 29 */       if (this.level.player != null) {
/* 30 */         int xd = this.level.player.x - this.x;
/* 31 */         int yd = this.level.player.y - this.y;
/* 32 */         if (xd * xd + yd * yd < 2500) {
/* 33 */           if (xd < 0) this.xa = -1;
/* 34 */           if (xd > 0) this.xa = 1;
/* 35 */           if (yd < 0) this.ya = -1;
/* 36 */           if (yd > 0) { this.ya = 1;
/*    */           }
/*    */         }
/*    */       }
/*    */       
/* 41 */       if ((this.xa != 0) || (this.ya != 0)) { this.jumpTime = 10;
/*    */       }
/*    */     }
/*    */     
/* 45 */     this.jumpTime -= 1;
/* 46 */     if (this.jumpTime == 0) {
/* 47 */       this.xa = (this.ya = 0);
/*    */     }
/*    */   }
/*    */   
/*    */   protected void die() {
/* 52 */     super.die();
/*    */     
/* 54 */     int count = this.random.nextInt(2) + 1;
/* 55 */     for (int i = 0; i < count; i++) {
/* 56 */       this.level.add(new ItemEntity(new com.mojang.ld22.item.ResourceItem(com.mojang.ld22.item.resource.Resource.slime), this.x + this.random.nextInt(11) - 5, this.y + this.random.nextInt(11) - 5));
/*    */     }
/*    */     
/* 59 */     if (this.level.player != null) {
/* 60 */       this.level.player.score += 25 * this.lvl;
/*    */     }
/*    */   }
/*    */   
/*    */   public void render(Screen screen)
/*    */   {
/* 66 */     int xt = 0;
/* 67 */     int yt = 18;
/*    */     
/* 69 */     int xo = this.x - 8;
/* 70 */     int yo = this.y - 11;
/*    */     
/* 72 */     if (this.jumpTime > 0) {
/* 73 */       xt += 2;
/* 74 */       yo -= 4;
/*    */     }
/*    */     
/* 77 */     int col = com.mojang.ld22.gfx.Color.get(-1, 10, 252, 555);
/* 78 */     if (this.lvl == 2) col = com.mojang.ld22.gfx.Color.get(-1, 100, 522, 555);
/* 79 */     if (this.lvl == 3) col = com.mojang.ld22.gfx.Color.get(-1, 111, 444, 555);
/* 80 */     if (this.lvl == 4) { col = com.mojang.ld22.gfx.Color.get(-1, 0, 111, 224);
/*    */     }
/* 82 */     if (this.hurtTime > 0) {
/* 83 */       col = com.mojang.ld22.gfx.Color.get(-1, 555, 555, 555);
/*    */     }
/*    */     
/* 86 */     screen.render(xo + 0, yo + 0, xt + yt * 32, col, 0);
/* 87 */     screen.render(xo + 8, yo + 0, xt + 1 + yt * 32, col, 0);
/* 88 */     screen.render(xo + 0, yo + 8, xt + (yt + 1) * 32, col, 0);
/* 89 */     screen.render(xo + 8, yo + 8, xt + 1 + (yt + 1) * 32, col, 0);
/*    */   }
/*    */   
/*    */   protected void touchedBy(Entity entity) {
/* 93 */     if ((entity instanceof Player)) {
/* 94 */       entity.hurt(this, this.lvl, this.dir);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\entity\Slime.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */