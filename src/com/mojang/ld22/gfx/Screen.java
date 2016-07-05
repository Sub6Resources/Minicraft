/*     */ package com.mojang.ld22.gfx;
/*     */ 
/*     */ 
/*     */ public class Screen
/*     */ {
/*     */   public int xOffset;
/*     */   
/*     */   public int yOffset;
/*     */   
/*     */   public static final int BIT_MIRROR_X = 1;
/*     */   
/*     */   public static final int BIT_MIRROR_Y = 2;
/*     */   
/*     */   public final int w;
/*     */   public final int h;
/*     */   public int[] pixels;
/*     */   private SpriteSheet sheet;
/*     */   
/*     */   public Screen(int w, int h, SpriteSheet sheet)
/*     */   {
/*  21 */     this.sheet = sheet;
/*  22 */     this.w = w;
/*  23 */     this.h = h;
/*     */     
/*  25 */     this.pixels = new int[w * h];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void clear(int color)
/*     */   {
/*  41 */     for (int i = 0; i < this.pixels.length; i++) {
/*  42 */       this.pixels[i] = color;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void render(int xp, int yp, int tile, int colors, int bits)
/*     */   {
/*  52 */     xp -= this.xOffset;
/*  53 */     yp -= this.yOffset;
/*  54 */     boolean mirrorX = (bits & 0x1) > 0;
/*  55 */     boolean mirrorY = (bits & 0x2) > 0;
/*     */     
/*  57 */     int xTile = tile % 32;
/*  58 */     int yTile = tile / 32;
/*  59 */     int toffs = xTile * 8 + yTile * 8 * this.sheet.width;
/*     */     
/*  61 */     for (int y = 0; y < 8; y++) {
/*  62 */       int ys = y;
/*  63 */       if (mirrorY) ys = 7 - y;
/*  64 */       if ((y + yp >= 0) && (y + yp < this.h))
/*  65 */         for (int x = 0; x < 8; x++)
/*  66 */           if ((x + xp >= 0) && (x + xp < this.w))
/*     */           {
/*  68 */             int xs = x;
/*  69 */             if (mirrorX) xs = 7 - x;
/*  70 */             int col = colors >> this.sheet.pixels[(xs + ys * this.sheet.width + toffs)] * 8 & 0xFF;
/*  71 */             if (col < 255) this.pixels[(x + xp + (y + yp) * this.w)] = col;
/*     */           }
/*     */     }
/*     */   }
/*     */   
/*     */   public void setOffset(int xOffset, int yOffset) {
/*  77 */     this.xOffset = xOffset;
/*  78 */     this.yOffset = yOffset;
/*     */   }
/*     */   
/*  81 */   private int[] dither = { 0, 8, 2, 10, 12, 4, 14, 6, 3, 11, 1, 9, 15, 7, 13, 5 };
/*     */   
/*     */   public void overlay(Screen screen2, int xa, int ya) {
/*  84 */     int[] oPixels = screen2.pixels;
/*  85 */     int i = 0;
/*  86 */     for (int y = 0; y < this.h; y++) {
/*  87 */       for (int x = 0; x < this.w; x++) {
/*  88 */         if (oPixels[i] / 10 <= this.dither[((x + xa & 0x3) + (y + ya & 0x3) * 4)]) this.pixels[i] = 0;
/*  89 */         i++;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void renderLight(int x, int y, int r)
/*     */   {
/*  96 */     x -= this.xOffset;
/*  97 */     y -= this.yOffset;
/*  98 */     int x0 = x - r;
/*  99 */     int x1 = x + r;
/* 100 */     int y0 = y - r;
/* 101 */     int y1 = y + r;
/*     */     
/* 103 */     if (x0 < 0) x0 = 0;
/* 104 */     if (y0 < 0) y0 = 0;
/* 105 */     if (x1 > this.w) x1 = this.w;
/* 106 */     if (y1 > this.h) { y1 = this.h;
/*     */     }
/* 108 */     for (int yy = y0; yy < y1; yy++) {
/* 109 */       int yd = yy - y;
/* 110 */       yd *= yd;
/* 111 */       for (int xx = x0; xx < x1; xx++) {
/* 112 */         int xd = xx - x;
/* 113 */         int dist = xd * xd + yd;
/*     */         
/* 115 */         if (dist <= r * r) {
/* 116 */           int br = 255 - dist * 255 / (r * r);
/* 117 */           if (this.pixels[(xx + yy * this.w)] < br) this.pixels[(xx + yy * this.w)] = br;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\gfx\Screen.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */