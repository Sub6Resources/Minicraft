/*    */ package com.mojang.ld22.entity.particle;
/*    */ 
/*    */ import com.mojang.ld22.gfx.Color;
/*    */ import com.mojang.ld22.gfx.Screen;
/*    */ import com.mojang.ld22.sound.Sound;
/*    */ 
/*    */ public class SmashParticle extends com.mojang.ld22.entity.Entity
/*    */ {
/*  9 */   private int time = 0;
/*    */   
/*    */   public SmashParticle(int x, int y) {
/* 12 */     this.x = x;
/* 13 */     this.y = y;
/* 14 */     Sound.monsterHurt.play();
/*    */   }
/*    */   
/*    */   public void tick() {
/* 18 */     this.time += 1;
/* 19 */     if (this.time > 10) {
/* 20 */       remove();
/*    */     }
/*    */   }
/*    */   
/*    */   public void render(Screen screen) {
/* 25 */     int col = Color.get(-1, 555, 555, 555);
/* 26 */     screen.render(this.x - 8, this.y - 8, 389, col, 2);
/* 27 */     screen.render(this.x - 0, this.y - 8, 389, col, 3);
/* 28 */     screen.render(this.x - 8, this.y - 0, 389, col, 0);
/* 29 */     screen.render(this.x - 0, this.y - 0, 389, col, 1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\entity\particle\SmashParticle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */