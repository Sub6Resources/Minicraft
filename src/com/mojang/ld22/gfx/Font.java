/*    */ package com.mojang.ld22.gfx;
/*    */ 
/*    */ public class Font {
/*  4 */   private static String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ      0123456789.,!?'\"-+=/\\%()<>:;     ";
/*    */   
/*    */ 
/*    */ 
/*    */   public static void draw(String msg, Screen screen, int x, int y, int col)
/*    */   {
/* 10 */     msg = msg.toUpperCase();
/* 11 */     for (int i = 0; i < msg.length(); i++) {
/* 12 */       int ix = chars.indexOf(msg.charAt(i));
/* 13 */       if (ix >= 0) {
/* 14 */         screen.render(x + i * 8, y, ix + 960, col, 0);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public static void renderFrame(Screen screen, String title, int x0, int y0, int x1, int y1) {
/* 20 */     for (int y = y0; y <= y1; y++) {
/* 21 */       for (int x = x0; x <= x1; x++) {
/* 22 */         if ((x == x0) && (y == y0)) {
/* 23 */           screen.render(x * 8, y * 8, 416, Color.get(-1, 1, 5, 445), 0);
/* 24 */         } else if ((x == x1) && (y == y0)) {
/* 25 */           screen.render(x * 8, y * 8, 416, Color.get(-1, 1, 5, 445), 1);
/* 26 */         } else if ((x == x0) && (y == y1)) {
/* 27 */           screen.render(x * 8, y * 8, 416, Color.get(-1, 1, 5, 445), 2);
/* 28 */         } else if ((x == x1) && (y == y1)) {
/* 29 */           screen.render(x * 8, y * 8, 416, Color.get(-1, 1, 5, 445), 3);
/* 30 */         } else if (y == y0) {
/* 31 */           screen.render(x * 8, y * 8, 417, Color.get(-1, 1, 5, 445), 0);
/* 32 */         } else if (y == y1) {
/* 33 */           screen.render(x * 8, y * 8, 417, Color.get(-1, 1, 5, 445), 2);
/* 34 */         } else if (x == x0) {
/* 35 */           screen.render(x * 8, y * 8, 418, Color.get(-1, 1, 5, 445), 0);
/* 36 */         } else if (x == x1) {
/* 37 */           screen.render(x * 8, y * 8, 418, Color.get(-1, 1, 5, 445), 1);
/*    */         } else {
/* 39 */           screen.render(x * 8, y * 8, 418, Color.get(5, 5, 5, 5), 1);
/*    */         }
/*    */       }
/*    */     }
/* 43 */     draw(title, screen, x0 * 8 + 8, y0 * 8, Color.get(5, 5, 5, 550));
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\gfx\Font.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */