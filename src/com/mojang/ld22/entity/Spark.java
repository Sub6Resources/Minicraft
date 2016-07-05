/*    */ package com.mojang.ld22.entity;
/*    */ 
/*    */ import com.mojang.ld22.gfx.Screen;
/*    */ 
/*    */ public class Spark extends Entity
/*    */ {
/*    */   private int lifeTime;
/*    */   public double xa;
/*    */   public double ya;
/*    */   public double xx;
/*    */   public double yy;
/*    */   private int time;
/*    */   private AirWizard owner;
/*    */   
/*    */   public Spark(AirWizard owner, double xa, double ya) {
/* 16 */     this.owner = owner;
/* 17 */     this.xx = (this.x = owner.x);
/* 18 */     this.yy = (this.y = owner.y);
/* 19 */     this.xr = 0;
/* 20 */     this.yr = 0;
/*    */     
/* 22 */     this.xa = xa;
/* 23 */     this.ya = ya;
/*    */     
/* 25 */     this.lifeTime = (600 + this.random.nextInt(30));
/*    */   }
/*    */   
/*    */   public void tick() {
/* 29 */     this.time += 1;
/* 30 */     if (this.time >= this.lifeTime) {
/* 31 */       remove();
/* 32 */       return;
/*    */     }
/* 34 */     this.xx += this.xa;
/* 35 */     this.yy += this.ya;
/* 36 */     this.x = ((int)this.xx);
/* 37 */     this.y = ((int)this.yy);
/* 38 */     java.util.List<Entity> toHit = this.level.getEntities(this.x, this.y, this.x, this.y);
/* 39 */     for (int i = 0; i < toHit.size(); i++) {
/* 40 */       Entity e = (Entity)toHit.get(i);
/* 41 */       if (((e instanceof Mob)) && (!(e instanceof AirWizard))) {
/* 42 */         e.hurt(this.owner, 1, ((Mob)e).dir ^ 0x1);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public boolean isBlockableBy(Mob mob) {
/* 48 */     return false;
/*    */   }
/*    */   
/*    */   public void render(Screen screen) {
/* 52 */     if ((this.time >= this.lifeTime - 120) && 
/* 53 */       (this.time / 6 % 2 == 0)) { return;
/*    */     }
/*    */     
/* 56 */     int xt = 8;
/* 57 */     int yt = 13;
/*    */     
/* 59 */     screen.render(this.x - 4, this.y - 4 - 2, xt + yt * 32, com.mojang.ld22.gfx.Color.get(-1, 555, 555, 555), this.random.nextInt(4));
/* 60 */     screen.render(this.x - 4, this.y - 4 + 2, xt + yt * 32, com.mojang.ld22.gfx.Color.get(-1, 0, 0, 0), this.random.nextInt(4));
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\entity\Spark.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */