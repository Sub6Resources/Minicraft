/*    */ package com.mojang.ld22.gfx;
/*    */ 
/*    */ import java.awt.image.BufferedImage;
/*    */ 
/*    */ public class SpriteSheet {
/*    */   public int width;
/*    */   public int height;
/*    */   public int[] pixels;
/*    */   
/* 10 */   public SpriteSheet(BufferedImage image) { this.width = image.getWidth();
/* 11 */     this.height = image.getHeight();
/* 12 */     this.pixels = image.getRGB(0, 0, this.width, this.height, null, 0, this.width);
/* 13 */     for (int i = 0; i < this.pixels.length; i++) {
/* 14 */       this.pixels[i] = ((this.pixels[i] & 0xFF) / 64);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\gfx\SpriteSheet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */