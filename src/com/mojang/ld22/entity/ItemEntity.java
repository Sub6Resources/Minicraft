/*    */ package com.mojang.ld22.entity;
/*    */ 
/*    */ import com.mojang.ld22.gfx.Screen;
/*    */ import com.mojang.ld22.item.Item;
/*    */ import java.util.Random;
/*    */ 
/*    */ public class ItemEntity extends Entity
/*    */ {
/*    */   private int lifeTime;
/* 10 */   protected int walkDist = 0;
/* 11 */   protected int dir = 0;
/* 12 */   public int hurtTime = 0;
/*    */   protected int xKnockback;
/*    */   protected int yKnockback;
/*    */   public double xa;
/*    */   public double ya;
/* 17 */   public double za; public double xx; public double yy; public double zz; public Item item; private int time = 0;
/*    */   
/*    */   public ItemEntity(Item item, int x, int y) {
/* 20 */     this.item = item;
/* 21 */     this.xx = (this.x = x);
/* 22 */     this.yy = (this.y = y);
/* 23 */     this.xr = 3;
/* 24 */     this.yr = 3;
/*    */     
/* 26 */     this.zz = 2.0D;
/* 27 */     this.xa = (this.random.nextGaussian() * 0.3D);
/* 28 */     this.ya = (this.random.nextGaussian() * 0.2D);
/* 29 */     this.za = (this.random.nextFloat() * 0.7D + 1.0D);
/*    */     
/* 31 */     this.lifeTime = (600 + this.random.nextInt(60));
/*    */   }
/*    */   
/*    */   public void tick() {
/* 35 */     this.time += 1;
/* 36 */     if (this.time >= this.lifeTime) {
/* 37 */       remove();
/* 38 */       return;
/*    */     }
/* 40 */     this.xx += this.xa;
/* 41 */     this.yy += this.ya;
/* 42 */     this.zz += this.za;
/* 43 */     if (this.zz < 0.0D) {
/* 44 */       this.zz = 0.0D;
/* 45 */       this.za *= -0.5D;
/* 46 */       this.xa *= 0.6D;
/* 47 */       this.ya *= 0.6D;
/*    */     }
/* 49 */     this.za -= 0.15D;
/* 50 */     int ox = this.x;
/* 51 */     int oy = this.y;
/* 52 */     int nx = (int)this.xx;
/* 53 */     int ny = (int)this.yy;
/* 54 */     int expectedx = nx - this.x;
/* 55 */     int expectedy = ny - this.y;
/* 56 */     move(nx - this.x, ny - this.y);
/* 57 */     int gotx = this.x - ox;
/* 58 */     int goty = this.y - oy;
/* 59 */     this.xx += gotx - expectedx;
/* 60 */     this.yy += goty - expectedy;
/*    */     
/* 62 */     if (this.hurtTime > 0) this.hurtTime -= 1;
/*    */   }
/*    */   
/*    */   public boolean isBlockableBy(Mob mob) {
/* 66 */     return false;
/*    */   }
/*    */   
/*    */   public void render(Screen screen) {
/* 70 */     if ((this.time >= this.lifeTime - 120) && 
/* 71 */       (this.time / 6 % 2 == 0)) { return;
/*    */     }
/* 73 */     screen.render(this.x - 4, this.y - 4, this.item.getSprite(), com.mojang.ld22.gfx.Color.get(-1, 0, 0, 0), 0);
/* 74 */     screen.render(this.x - 4, this.y - 4 - (int)this.zz, this.item.getSprite(), this.item.getColor(), 0);
/*    */   }
/*    */   
/*    */   protected void touchedBy(Entity entity) {
/* 78 */     if (this.time > 30) entity.touchItem(this);
/*    */   }
/*    */   
/*    */   public void take(Player player) {
/* 82 */     com.mojang.ld22.sound.Sound.pickup.play();
/* 83 */     player.score += 1;
/* 84 */     this.item.onTake(this);
/* 85 */     remove();
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\entity\ItemEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */