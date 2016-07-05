/*    */ package com.mojang.ld22.entity;
/*    */ 
/*    */ import com.mojang.ld22.gfx.Color;
/*    */ 
/*    */ public class Lantern extends Furniture {
/*    */   public Lantern() {
/*  7 */     super("Lantern");
/*  8 */     this.col = Color.get(-1, 0, 111, 555);
/*  9 */     this.sprite = 5;
/* 10 */     this.xr = 3;
/* 11 */     this.yr = 2;
/*    */   }
/*    */   
/*    */   public int getLightRadius() {
/* 15 */     return 8;
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\entity\Lantern.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */