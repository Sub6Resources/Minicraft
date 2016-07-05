/*    */ package com.mojang.ld22.entity;
/*    */ 
/*    */ import com.mojang.ld22.gfx.Color;
/*    */ 
/*    */ public class Torch extends Furniture {
/*    */   public Torch() {
/*  7 */     super("Torch");
/*  8 */     this.col = Color.get(-1, 100, 321, 431);
/*  9 */     this.sprite = 6;
/* 10 */     this.xr = 3;
/* 11 */     this.yr = 2;
/*    */   }
/*    */   
/*    */   public int getLightRadius() {
/* 15 */     return 4;
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\entity\Lantern.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */