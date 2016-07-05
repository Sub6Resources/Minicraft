/*     */ package com.mojang.ld22.level.levelgen;
/*     */ 
/*     */ import com.mojang.ld22.level.tile.Tile;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.util.Random;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JOptionPane;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LevelGen
/*     */ {
/*  13 */   private static final Random random = new Random();
/*     */   public double[] values;
/*     */   private int w;
/*     */   private int h;
/*     */   
/*  18 */   public LevelGen(int w, int h, int featureSize) { this.w = w;
/*  19 */     this.h = h;
/*     */     
/*  21 */     this.values = new double[w * h];
/*     */     
/*  23 */     for (int y = 0; y < w; y += featureSize) {
/*  24 */       for (int x = 0; x < w; x += featureSize) {
/*  25 */         setSample(x, y, random.nextFloat() * 2.0F - 1.0F);
/*     */       }
/*     */     }
/*     */     
/*  29 */     int stepSize = featureSize;
/*  30 */     double scale = 1.0D / w;
/*  31 */     double scaleMod = 1.0D;
/*     */     do {
/*  33 */       int halfStep = stepSize / 2;
/*  34 */       for (int y = 0; y < w; y += stepSize) {
/*  35 */         for (int x = 0; x < w; x += stepSize) {
/*  36 */           double a = sample(x, y);
/*  37 */           double b = sample(x + stepSize, y);
/*  38 */           double c = sample(x, y + stepSize);
/*  39 */           double d = sample(x + stepSize, y + stepSize);
/*     */           
/*  41 */           double e = (a + b + c + d) / 4.0D + (random.nextFloat() * 2.0F - 1.0F) * stepSize * scale;
/*  42 */           setSample(x + halfStep, y + halfStep, e);
/*     */         }
/*     */       }
/*  45 */       for (int y = 0; y < w; y += stepSize) {
/*  46 */         for (int x = 0; x < w; x += stepSize) {
/*  47 */           double a = sample(x, y);
/*  48 */           double b = sample(x + stepSize, y);
/*  49 */           double c = sample(x, y + stepSize);
/*  50 */           double d = sample(x + halfStep, y + halfStep);
/*  51 */           double e = sample(x + halfStep, y - halfStep);
/*  52 */           double f = sample(x - halfStep, y + halfStep);
/*     */           
/*  54 */           double H = (a + b + d + e) / 4.0D + (random.nextFloat() * 2.0F - 1.0F) * stepSize * scale * 0.5D;
/*  55 */           double g = (a + c + d + f) / 4.0D + (random.nextFloat() * 2.0F - 1.0F) * stepSize * scale * 0.5D;
/*  56 */           setSample(x + halfStep, y, H);
/*  57 */           setSample(x, y + halfStep, g);
/*     */         }
/*     */       }
/*  60 */       stepSize /= 2;
/*  61 */       scale *= (scaleMod + 0.8D);
/*  62 */       scaleMod *= 0.3D;
/*  63 */     } while (stepSize > 1);
/*     */   }
/*     */   
/*     */   private double sample(int x, int y) {
/*  67 */     return this.values[((x & this.w - 1) + (y & this.h - 1) * this.w)];
/*     */   }
/*     */   
/*     */   private void setSample(int x, int y, double value) {
/*  71 */     this.values[((x & this.w - 1) + (y & this.h - 1) * this.w)] = value;
/*     */   }
/*     */   
/*     */   public static byte[][] createAndValidateTopMap(int w, int h) {
/*  75 */     int attempt = 0;
/*     */     byte[][] result;
/*  77 */     int[] count; do { result = createTopMap(w, h);
/*     */       
/*  79 */       count = new int[256];
/*     */       
/*  81 */       for (int i = 0; i < w * h; i++) {
/*  82 */         count[(result[0][i] & 0xFF)] += 1;
/*     */       }
//changed sand in line 85 to lava
/*  84 */     } while ((count[(Tile.rock.id & 0xFF)] < 100) || 
/*  85 */       (count[(Tile.sand.id & 0xFF)] < 100) || 
/*  86 */       (count[(Tile.grass.id & 0xFF)] < 100) || 
/*  87 */       (count[(Tile.tree.id & 0xFF)] < 100) || 
/*  88 */       (count[(Tile.stairsDown.id & 0xFF)] < 2)
			    );
/*     */     
/*  90 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */   public static byte[][] createAndValidateUndergroundMap(int w, int h, int depth)
/*     */   {
/*  96 */     int attempt = 0;
/*     */     byte[][] result;
/*  98 */     int[] count; do { result = createUndergroundMap(w, h, depth);
/*     */       
/* 100 */       count = new int[256];
/*     */       
/* 102 */       for (int i = 0; i < w * h; i++) {
/* 103 */         count[(result[0][i] & 0xFF)] += 1;
/*     */       }
/* 105 */     } while ((count[(Tile.rock.id & 0xFF)] < 100) || 
/* 106 */       (count[(Tile.dirt.id & 0xFF)] < 100) || 
/* 107 */       (count[((Tile.ironOre.id & 0xFF) + depth - 1)] < 20) || (
/* 108 */       (depth < 3) && (count[(Tile.stairsDown.id & 0xFF)] < 2)));
/*     */     
/* 110 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */   public static byte[][] createAndValidateSkyMap(int w, int h)
/*     */   {
/* 116 */     int attempt = 0;
/*     */     byte[][] result;
/* 118 */     int[] count; do { result = createSkyMap(w, h);
/*     */       
/* 120 */       count = new int[256];
/*     */       
/* 122 */       for (int i = 0; i < w * h; i++) {
/* 123 */         count[(result[0][i] & 0xFF)] += 1;
/*     */       }
/* 125 */     } while ((count[(Tile.cloud.id & 0xFF)] < 2000) || 
/* 126 */       (count[(Tile.stairsDown.id & 0xFF)] < 2));
/*     */     
/* 128 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */   private static byte[][] createTopMap(int w, int h)
/*     */   {
/* 134 */     LevelGen mnoise1 = new LevelGen(w, h, 16);
/* 135 */     LevelGen mnoise2 = new LevelGen(w, h, 16);
/* 136 */     LevelGen mnoise3 = new LevelGen(w, h, 16);
/*     */     
/* 138 */     LevelGen noise1 = new LevelGen(w, h, 32);
/* 139 */     LevelGen noise2 = new LevelGen(w, h, 32);
/*     */     
/* 141 */     byte[] map = new byte[w * h];
/* 142 */     byte[] data = new byte[w * h];
/* 143 */     for (int y = 0; y < h; y++) {
/* 144 */       for (int x = 0; x < w; x++) {
/* 145 */         int i = x + y * w;
/*     */         
/* 147 */         double val = Math.abs(noise1.values[i] - noise2.values[i]) * 3.0D - 2.0D;
/* 148 */         double mval = Math.abs(mnoise1.values[i] - mnoise2.values[i]);
/* 149 */         mval = Math.abs(mval - mnoise3.values[i]) * 3.0D - 2.0D;
/*     */         
/* 151 */         double xd = x / (w - 1.0D) * 2.0D - 1.0D;
/* 152 */         double yd = y / (h - 1.0D) * 2.0D - 1.0D;
/* 153 */         if (xd < 0.0D) xd = -xd;
/* 154 */         if (yd < 0.0D) yd = -yd;
/* 155 */         double dist = xd >= yd ? xd : yd;
/* 156 */         dist = dist * dist * dist * dist;
/* 157 */         dist = dist * dist * dist * dist;
/* 158 */         val = val + 1.0D - dist * 20.0D;
/*     */         
/* 160 */         if (val < -0.5D) {
/* 161 */           map[i] = Tile.water.id;
/* 162 */         } else if ((val > 0.5D) && (mval < -1.5D)) {
/* 163 */           map[i] = Tile.rock.id;
/*     */         } else {
/* 165 */           map[i] = Tile.grass.id;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 170 */     for (int i = 0; i < w * h / 2800; i++) {
/* 171 */       int xs = random.nextInt(w);
/* 172 */       int ys = random.nextInt(h);
/* 173 */       for (int k = 0; k < 10; k++) {
/* 174 */         int x = xs + random.nextInt(21) - 10;
/* 175 */         int y = ys + random.nextInt(21) - 10;
/* 176 */         for (int j = 0; j < 100; j++) {
/* 177 */           int xo = x + random.nextInt(5) - random.nextInt(5);
/* 178 */           int yo = y + random.nextInt(5) - random.nextInt(5);
/* 179 */           for (int yy = yo - 1; yy <= yo + 1; yy++) {
/* 180 */             for (int xx = xo - 1; xx <= xo + 1; xx++) {
/* 181 */               if ((xx >= 0) && (yy >= 0) && (xx < w) && (yy < h) && 
/* 182 */                 (map[(xx + yy * w)] == Tile.grass.id)) {
/* 183 */                 map[(xx + yy * w)] = Tile.sand.id;
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ // changed 183 tile.sand.id into lava
/*     */ 
/*     */ 
/* 194 */     for (int i = 0; i < w * h / 400; i++) {
/* 195 */       int x = random.nextInt(w);
/* 196 */       int y = random.nextInt(h);
/* 197 */       for (int j = 0; j < 200; j++) {
/* 198 */         int xx = x + random.nextInt(15) - random.nextInt(15);
/* 199 */         int yy = y + random.nextInt(15) - random.nextInt(15);
/* 200 */         if ((xx >= 0) && (yy >= 0) && (xx < w) && (yy < h) && 
/* 201 */           (map[(xx + yy * w)] == Tile.grass.id)) {
/* 202 */           map[(xx + yy * w)] = Tile.tree.id;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 208 */     for (int i = 0; i < w * h / 400; i++) {
/* 209 */       int x = random.nextInt(w);
/* 210 */       int y = random.nextInt(h);
/* 211 */       int col = random.nextInt(4);
/* 212 */       for (int j = 0; j < 30; j++) {
/* 213 */         int xx = x + random.nextInt(5) - random.nextInt(5);
/* 214 */         int yy = y + random.nextInt(5) - random.nextInt(5);
/* 215 */         if ((xx >= 0) && (yy >= 0) && (xx < w) && (yy < h) && 
/* 216 */           (map[(xx + yy * w)] == Tile.grass.id)) {
/* 217 */           map[(xx + yy * w)] = Tile.flower.id;
/* 218 */           data[(xx + yy * w)] = ((byte)(col + random.nextInt(4) * 16));
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 224 */     for (int i = 0; i < w * h / 100; i++) {
/* 225 */       int xx = random.nextInt(w);
/* 226 */       int yy = random.nextInt(h);
/* 227 */       if ((xx >= 0) && (yy >= 0) && (xx < w) && (yy < h) && 
/* 228 */         (map[(xx + yy * w)] == Tile.sand.id)) {
/* 229 */         map[(xx + yy * w)] = Tile.cactus.id;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 234 */     int count = 0;
/* 235 */     for (int i = 0; i < w * h / 100; i++) {
/* 236 */       int x = random.nextInt(w - 2) + 1;
/* 237 */       int y = random.nextInt(h - 2) + 1;
/*     */       
/* 239 */       for (int yy = y - 1; yy <= y + 1; yy++) {
/* 240 */         for (int xx = x - 1; xx <= x + 1; xx++)
/* 241 */           if (map[(xx + yy * w)] != Tile.rock.id)
/*     */             break;
/*     */       }
/* 244 */       map[(x + y * w)] = Tile.stairsDown.id;
/* 245 */       count++;
/* 246 */       if (count == 4)
/*     */         break;
/*     */     }
/* 249 */     return new byte[][] { map, data };
/*     */   }
/*     */   
/*     */   private static byte[][] createUndergroundMap(int w, int h, int depth) {
/* 253 */     LevelGen mnoise1 = new LevelGen(w, h, 16);
/* 254 */     LevelGen mnoise2 = new LevelGen(w, h, 16);
/* 255 */     LevelGen mnoise3 = new LevelGen(w, h, 16);
/*     */     
/* 257 */     LevelGen nnoise1 = new LevelGen(w, h, 16);
/* 258 */     LevelGen nnoise2 = new LevelGen(w, h, 16);
/* 259 */     LevelGen nnoise3 = new LevelGen(w, h, 16);
/*     */     
/* 261 */     LevelGen wnoise1 = new LevelGen(w, h, 16);
/* 262 */     LevelGen wnoise2 = new LevelGen(w, h, 16);
/* 263 */     LevelGen wnoise3 = new LevelGen(w, h, 16);
/*     */     
/* 265 */     LevelGen noise1 = new LevelGen(w, h, 32);
/* 266 */     LevelGen noise2 = new LevelGen(w, h, 32);
/*     */     
/* 268 */     byte[] map = new byte[w * h];
/* 269 */     byte[] data = new byte[w * h];
/* 270 */     for (int y = 0; y < h; y++) {
/* 271 */       for (int x = 0; x < w; x++) {
/* 272 */         int i = x + y * w;
/*     */         
/* 274 */         double val = Math.abs(noise1.values[i] - noise2.values[i]) * 3.0D - 2.0D;
/*     */         
/* 276 */         double mval = Math.abs(mnoise1.values[i] - mnoise2.values[i]);
/* 277 */         mval = Math.abs(mval - mnoise3.values[i]) * 3.0D - 2.0D;
/*     */         
/* 279 */         double nval = Math.abs(nnoise1.values[i] - nnoise2.values[i]);
/* 280 */         nval = Math.abs(nval - nnoise3.values[i]) * 3.0D - 2.0D;
/*     */         
/* 282 */         double wval = Math.abs(wnoise1.values[i] - wnoise2.values[i]);
/* 283 */         wval = Math.abs(nval - wnoise3.values[i]) * 3.0D - 2.0D;
/*     */         
/* 285 */         double xd = x / (w - 1.0D) * 2.0D - 1.0D;
/* 286 */         double yd = y / (h - 1.0D) * 2.0D - 1.0D;
/* 287 */         if (xd < 0.0D) xd = -xd;
/* 288 */         if (yd < 0.0D) yd = -yd;
/* 289 */         double dist = xd >= yd ? xd : yd;
/* 290 */         dist = dist * dist * dist * dist;
/* 291 */         dist = dist * dist * dist * dist;
/* 292 */         val = val + 1.0D - dist * 20.0D;
/*     */         
/* 294 */         if ((val > -2.0D) && (wval < -2.0D + depth / 2 * 3)) {
/* 295 */           if (depth > 2) {
/* 296 */             map[i] = Tile.lava.id;
/*     */           } else
/* 298 */             map[i] = Tile.water.id;
/* 299 */         } else if ((val > -2.0D) && ((mval < -1.7D) || (nval < -1.4D))) {
/* 300 */           map[i] = Tile.dirt.id;
/*     */         } else {
/* 302 */           map[i] = Tile.rock.id;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 308 */     int r = 2;
/* 309 */     for (int i = 0; i < w * h / 400; i++) {
/* 310 */       int x = random.nextInt(w);
/* 311 */       int y = random.nextInt(h);
/* 312 */       for (int j = 0; j < 30; j++) {
/* 313 */         int xx = x + random.nextInt(5) - random.nextInt(5);
/* 314 */         int yy = y + random.nextInt(5) - random.nextInt(5);
/* 315 */         if ((xx >= r) && (yy >= r) && (xx < w - r) && (yy < h - r) && 
/* 316 */           (map[(xx + yy * w)] == Tile.rock.id)) {
/* 317 */           map[(xx + yy * w)] = ((byte)((Tile.ironOre.id & 0xFF) + depth - 1));
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 324 */     if (depth < 3) {
/* 325 */       int count = 0;
/* 326 */       for (int i = 0; i < w * h / 100; i++) {
/* 327 */         int x = random.nextInt(w - 20) + 10;
/* 328 */         int y = random.nextInt(h - 20) + 10;
/*     */         
/* 330 */         for (int yy = y - 1; yy <= y + 1; yy++) {
/* 331 */           for (int xx = x - 1; xx <= x + 1; xx++)
/* 332 */             if (map[(xx + yy * w)] != Tile.rock.id)
/*     */               break;
/*     */         }
/* 335 */         map[(x + y * w)] = Tile.stairsDown.id;
/* 336 */         count++;
/* 337 */         if (count == 4)
/*     */           break;
/*     */       }
/*     */     }
/* 341 */     return new byte[][] { map, data };
/*     */   }
/*     */   
/*     */   private static byte[][] createSkyMap(int w, int h) {
/* 345 */     LevelGen noise1 = new LevelGen(w, h, 8);
/* 346 */     LevelGen noise2 = new LevelGen(w, h, 8);
/*     */     
/* 348 */     byte[] map = new byte[w * h];
/* 349 */     byte[] data = new byte[w * h];
/* 350 */     for (int y = 0; y < h; y++) {
/* 351 */       for (int x = 0; x < w; x++) {
/* 352 */         int i = x + y * w;
/*     */         
/* 354 */         double val = Math.abs(noise1.values[i] - noise2.values[i]) * 3.0D - 2.0D;
/*     */         
/* 356 */         double xd = x / (w - 1.0D) * 2.0D - 1.0D;
/* 357 */         double yd = y / (h - 1.0D) * 2.0D - 1.0D;
/* 358 */         if (xd < 0.0D) xd = -xd;
/* 359 */         if (yd < 0.0D) yd = -yd;
/* 360 */         double dist = xd >= yd ? xd : yd;
/* 361 */         dist = dist * dist * dist * dist;
/* 362 */         dist = dist * dist * dist * dist;
/* 363 */         val = -val * 1.0D - 2.2D;
/* 364 */         val = val + 1.0D - dist * 20.0D;
/*     */         
/* 366 */         if (val < -0.25D) {
/* 367 */           map[i] = Tile.infiniteFall.id;
/*     */         } else {
/* 369 */           map[i] = Tile.cloud.id;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 374 */     for (int i = 0; i < w * h / 50; i++) {
/* 375 */       int x = random.nextInt(w - 2) + 1;
/* 376 */       int y = random.nextInt(h - 2) + 1;
/*     */       
/* 378 */       for (int yy = y - 1; yy <= y + 1; yy++) {
/* 379 */         for (int xx = x - 1; xx <= x + 1; xx++)
/* 380 */           if (map[(xx + yy * w)] != Tile.cloud.id)
/*     */             break;
/*     */       }
/* 383 */       map[(x + y * w)] = Tile.cloudCactus.id;
/*     */     }
/*     */     
/* 386 */     int count = 0;
/* 387 */     for (int i = 0; i < w * h; i++) {
/* 388 */       int x = random.nextInt(w - 2) + 1;
/* 389 */       int y = random.nextInt(h - 2) + 1;
/*     */       
/* 391 */       for (int yy = y - 1; yy <= y + 1; yy++) {
/* 392 */         for (int xx = x - 1; xx <= x + 1; xx++)
/* 393 */           if (map[(xx + yy * w)] != Tile.cloud.id)
/*     */             break;
/*     */       }
/* 396 */       map[(x + y * w)] = Tile.stairsDown.id;
/* 397 */       count++;
/* 398 */       if (count == 2)
/*     */         break;
/*     */     }
/* 401 */     return new byte[][] { map, data };
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/* 405 */     int d = 0;
/*     */     for (;;) {
/* 407 */       int w = 128;
/* 408 */       int h = 128;
/*     */       
/* 410 */       byte[] map = createAndValidateTopMap(w, h)[0];
/*     */       
/*     */ 
/*     */ 
/* 414 */       BufferedImage img = new BufferedImage(w, h, 1);
/* 415 */       int[] pixels = new int[w * h];
/* 416 */       for (int y = 0; y < h; y++) {
/* 417 */         for (int x = 0; x < w; x++) {
/* 418 */           int i = x + y * w;
/*     */           
/* 420 */           if (map[i] == Tile.water.id) pixels[i] = 128;
/* 421 */           if (map[i] == Tile.grass.id) pixels[i] = 2129952;
/* 422 */           if (map[i] == Tile.rock.id) pixels[i] = 10526880;
/* 423 */           if (map[i] == Tile.dirt.id) pixels[i] = 6307904;
/* 424 */           if (map[i] == Tile.sand.id) pixels[i] = 10526784;
/* 425 */           if (map[i] == Tile.tree.id) pixels[i] = 12288;
/* 426 */           if (map[i] == Tile.lava.id) pixels[i] = 16719904;
/* 427 */           if (map[i] == Tile.cloud.id) pixels[i] = 10526880;
/* 428 */           if (map[i] == Tile.stairsDown.id) pixels[i] = 16777215;
/* 429 */           if (map[i] == Tile.stairsUp.id) pixels[i] = 16777215;
/* 430 */           if (map[i] == Tile.cloudCactus.id) pixels[i] = 16711935;
/*     */         }
/*     */       }
/* 433 */       img.setRGB(0, 0, w, h, pixels, 0, w);
/* 434 */       JOptionPane.showMessageDialog(null, null, "Another", 0, new ImageIcon(img.getScaledInstance(w * 4, h * 4, 16)));
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\level\levelgen\LevelGen.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */