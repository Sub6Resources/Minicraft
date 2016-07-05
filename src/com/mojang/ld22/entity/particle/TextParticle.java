/*    */ package com.mojang.ld22.entity.particle;
/*    */ 
/*    */ import com.mojang.ld22.gfx.Font;
/*    */ import com.mojang.ld22.gfx.Screen;
/*    */ import java.util.Random;
/*    */ 
/*    */ public class TextParticle extends com.mojang.ld22.entity.Entity
/*    */ {
/*    */   private String msg;
/*    */   private int col;
/* 11 */   private int time = 0;
/*    */   public double xa;
/*    */   public double ya;
/*    */   public double za;
/*    */   
/* 16 */   public TextParticle(String msg, int x, int y, int col) { this.msg = msg;
/* 17 */     this.x = x;
/* 18 */     this.y = y;
/* 19 */     this.col = col;
/* 20 */     this.xx = x;
/* 21 */     this.yy = y;
/* 22 */     this.zz = 2.0D;
/* 23 */     this.xa = (this.random.nextGaussian() * 0.3D);
/* 24 */     this.ya = (this.random.nextGaussian() * 0.2D);
/* 25 */     this.za = (this.random.nextFloat() * 0.7D + 2.0D); }
/*    */   
/*    */   public double xx;
/*    */   
/* 29 */   public void tick() { this.time += 1;
/* 30 */     if (this.time > 60) {
/* 31 */       remove();
/*    */     }
/* 33 */     this.xx += this.xa;
/* 34 */     this.yy += this.ya;
/* 35 */     this.zz += this.za;
/* 36 */     if (this.zz < 0.0D) {
/* 37 */       this.zz = 0.0D;
/* 38 */       this.za *= -0.5D;
/* 39 */       this.xa *= 0.6D;
/* 40 */       this.ya *= 0.6D;
/*    */     }
/* 42 */     this.za -= 0.15D;
/* 43 */     this.x = ((int)this.xx);
/* 44 */     this.y = ((int)this.yy);
/*    */   }
/*    */   
/*    */   public double yy;
/*    */   public double zz;
/* 49 */   public void render(Screen screen) { Font.draw(this.msg, screen, this.x - this.msg.length() * 4 + 1, this.y - (int)this.zz + 1, com.mojang.ld22.gfx.Color.get(-1, 0, 0, 0));
/* 50 */     Font.draw(this.msg, screen, this.x - this.msg.length() * 4, this.y - (int)this.zz, this.col);
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\entity\particle\TextParticle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */