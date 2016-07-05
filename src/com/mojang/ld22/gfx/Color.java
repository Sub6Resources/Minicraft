/*    */ package com.mojang.ld22.gfx;
/*    */ 
/*    */ public class Color
/*    */ {
/*    */   public static int get(int a, int b, int c, int d) {
/*  6 */     return (get(d) << 24) + (get(c) << 16) + (get(b) << 8) + get(a);
/*    */   }
/*    */   
/*    */   public static int get(int d) {
/* 10 */     if (d < 0) return 255;
/* 11 */     int r = d / 100 % 10;
/* 12 */     int g = d / 10 % 10;
/* 13 */     int b = d % 10;
/* 14 */     return r * 36 + g * 6 + b;
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\gfx\Color.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */