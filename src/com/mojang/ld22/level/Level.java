/*     */ package com.mojang.ld22.level;
/*     */ 
/*     */ import com.mojang.ld22.entity.AirWizard;
/*     */ import com.mojang.ld22.entity.Entity;
/*     */ import com.mojang.ld22.entity.Mob;
/*     */ import com.mojang.ld22.entity.Player;
/*     */ import com.mojang.ld22.entity.Slime;
/*     */ import com.mojang.ld22.entity.Zombie;
          import com.mojang.ld22.entity.Cow;
/*     */ import com.mojang.ld22.gfx.Screen;
/*     */ import com.mojang.ld22.level.levelgen.LevelGen;
/*     */ import com.mojang.ld22.level.tile.Tile;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class Level
/*     */ {
/*  20 */   private Random random = new Random();
/*     */   
/*     */   public int w;
/*     */   
/*     */   public int h;
/*     */   public byte[] tiles;
/*     */   public byte[] data;
/*     */   public List<Entity>[] entitiesInTiles;
/*  28 */   public int grassColor = 141;
/*  29 */   public int dirtColor = 322;
/*  30 */   public int sandColor = 550;
/*     */   private int depth;
/*  32 */   public int monsterDensity = 8;
/*     */   
/*  34 */   public List<Entity> entities = new ArrayList();
/*  35 */   private Comparator<Entity> spriteSorter = new Comparator<Entity>() {
/*     */     public int compare(Entity e0, Entity e1) {
/*  37 */       if (e1.y < e0.y) return 1;
/*  38 */       if (e1.y > e0.y) return -1;
/*  39 */       return 0;
/*     */     }
/*     */   };
/*     */   
/*     */ 
/*     */   public Level(int w, int h, int level, Level parentLevel)
/*     */   {
/*  46 */     if (level < 0) {
/*  47 */       this.dirtColor = 222;
/*     */     }
/*  49 */     this.depth = level;
/*  50 */     this.w = w;
/*  51 */     this.h = h;
/*     */     
/*     */ 
/*  54 */     if (level == 1)
/*  55 */       this.dirtColor = 444;
/*     */     byte[][] maps;
/*  57 */     if (level == 0) {
/*  58 */       maps = LevelGen.createAndValidateTopMap(w, h);
/*  59 */     } else if (level < 0) {
/*  60 */       maps = LevelGen.createAndValidateUndergroundMap(w, h, -level);
/*  61 */       this.monsterDensity = 4;
/*     */     } else {
/*  63 */       maps = LevelGen.createAndValidateSkyMap(w, h);
/*  64 */       this.monsterDensity = 4;
/*     */     }
/*     */     
/*  67 */     this.tiles = maps[0];
/*  68 */     this.data = maps[1];
/*     */     
/*  70 */     if (parentLevel != null) {
/*  71 */       for (int y = 0; y < h; y++) {
/*  72 */         for (int x = 0; x < w; x++) {
/*  73 */           if (parentLevel.getTile(x, y) == Tile.stairsDown)
/*     */           {
/*  75 */             setTile(x, y, Tile.stairsUp, 0);
/*  76 */             if (level == 0) {
/*  77 */               setTile(x - 1, y, Tile.hardRock, 0);
/*  78 */               setTile(x + 1, y, Tile.hardRock, 0);
/*  79 */               setTile(x, y - 1, Tile.hardRock, 0);
/*  80 */               setTile(x, y + 1, Tile.hardRock, 0);
/*  81 */               setTile(x - 1, y - 1, Tile.hardRock, 0);
/*  82 */               setTile(x - 1, y + 1, Tile.hardRock, 0);
/*  83 */               setTile(x + 1, y - 1, Tile.hardRock, 0);
/*  84 */               setTile(x + 1, y + 1, Tile.hardRock, 0);
/*     */             } else {
/*  86 */               setTile(x - 1, y, Tile.dirt, 0);
/*  87 */               setTile(x + 1, y, Tile.dirt, 0);
/*  88 */               setTile(x, y - 1, Tile.dirt, 0);
/*  89 */               setTile(x, y + 1, Tile.dirt, 0);
/*  90 */               setTile(x - 1, y - 1, Tile.dirt, 0);
/*  91 */               setTile(x - 1, y + 1, Tile.dirt, 0);
/*  92 */               setTile(x + 1, y - 1, Tile.dirt, 0);
/*  93 */               setTile(x + 1, y + 1, Tile.dirt, 0);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 100 */     this.entitiesInTiles = new ArrayList[w * h];
/* 101 */     for (int i = 0; i < w * h; i++) {
/* 102 */       this.entitiesInTiles[i] = new ArrayList();
/*     */     }
/*     */     
/* 105 */     if (level == 1) {
/* 106 */       AirWizard aw = new AirWizard();
/* 107 */       aw.x = (w * 8);
/* 108 */       aw.y = (h * 8);
/* 109 */       add(aw);
/*     */     }
/*     */   }
/*     */   
/*     */   public void renderBackground(Screen screen, int xScroll, int yScroll) {
/* 114 */     int xo = xScroll >> 4;
/* 115 */     int yo = yScroll >> 4;
/* 116 */     int w = screen.w + 15 >> 4;
/* 117 */     int h = screen.h + 15 >> 4;
/* 118 */     screen.setOffset(xScroll, yScroll);
/* 119 */     for (int y = yo; y <= h + yo; y++) {
/* 120 */       for (int x = xo; x <= w + xo; x++) {
/* 121 */         getTile(x, y).render(screen, this, x, y);
/*     */       }
/*     */     }
/* 124 */     screen.setOffset(0, 0);
/*     */   }
/*     */   
/* 127 */   private List<Entity> rowSprites = new ArrayList();
/*     */   public Player player;
/*     */   
/*     */   public void renderSprites(Screen screen, int xScroll, int yScroll)
/*     */   {
/* 132 */     int xo = xScroll >> 4;
/* 133 */     int yo = yScroll >> 4;
/* 134 */     int w = screen.w + 15 >> 4;
/* 135 */     int h = screen.h + 15 >> 4;
/*     */     
/* 137 */     screen.setOffset(xScroll, yScroll);
/* 138 */     for (int y = yo; y <= h + yo; y++) {
/* 139 */       for (int x = xo; x <= w + xo; x++) {
/* 140 */         if ((x >= 0) && (y >= 0) && (x < this.w) && (y < this.h))
/* 141 */           this.rowSprites.addAll(this.entitiesInTiles[(x + y * this.w)]);
/*     */       }
/* 143 */       if (this.rowSprites.size() > 0) {
/* 144 */         sortAndRender(screen, this.rowSprites);
/*     */       }
/* 146 */       this.rowSprites.clear();
/*     */     }
/* 148 */     screen.setOffset(0, 0);
/*     */   }
/*     */   
/*     */   public void renderLight(Screen screen, int xScroll, int yScroll) {
/* 152 */     int xo = xScroll >> 4;
/* 153 */     int yo = yScroll >> 4;
/* 154 */     int w = screen.w + 15 >> 4;
/* 155 */     int h = screen.h + 15 >> 4;
/*     */     
/* 157 */     screen.setOffset(xScroll, yScroll);
/* 158 */     int r = 4;
/* 159 */     for (int y = yo - r; y <= h + yo + r; y++) {
/* 160 */       for (int x = xo - r; x <= w + xo + r; x++)
/* 161 */         if ((x >= 0) && (y >= 0) && (x < this.w) && (y < this.h)) {
/* 162 */           List<Entity> entities = this.entitiesInTiles[(x + y * this.w)];
/* 163 */           for (int i = 0; i < entities.size(); i++) {
/* 164 */             Entity e = (Entity)entities.get(i);
/*     */             
/* 166 */             int lr = e.getLightRadius();
/* 167 */             if (lr > 0) screen.renderLight(e.x - 1, e.y - 4, lr * 8);
/*     */           }
/* 169 */           int lr = getTile(x, y).getLightRadius(this, x, y);
/* 170 */           if (lr > 0) screen.renderLight(x * 16 + 8, y * 16 + 8, lr * 8);
/*     */         }
/*     */     }
/* 173 */     screen.setOffset(0, 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void sortAndRender(Screen screen, List<Entity> list)
/*     */   {
/* 181 */     Collections.sort(list, this.spriteSorter);
/* 182 */     for (int i = 0; i < list.size(); i++) {
/* 183 */       ((Entity)list.get(i)).render(screen);
/*     */     }
/*     */   }
/*     */   
/*     */   public Tile getTile(int x, int y) {
/* 188 */     if ((x < 0) || (y < 0) || (x >= this.w) || (y >= this.h)) return Tile.rock;
/* 189 */     return Tile.tiles[this.tiles[(x + y * this.w)]];
/*     */   }
/*     */   
/*     */   public void setTile(int x, int y, Tile t, int dataVal) {
/* 193 */     if ((x < 0) || (y < 0) || (x >= this.w) || (y >= this.h)) return;
/* 194 */     this.tiles[(x + y * this.w)] = t.id;
/* 195 */     this.data[(x + y * this.w)] = ((byte)dataVal);
/*     */   }
/*     */   
/*     */   public int getData(int x, int y) {
/* 199 */     if ((x < 0) || (y < 0) || (x >= this.w) || (y >= this.h)) return 0;
/* 200 */     return this.data[(x + y * this.w)] & 0xFF;
/*     */   }
/*     */   
/*     */   public void setData(int x, int y, int val) {
/* 204 */     if ((x < 0) || (y < 0) || (x >= this.w) || (y >= this.h)) return;
/* 205 */     this.data[(x + y * this.w)] = ((byte)val);
/*     */   }
/*     */   
/*     */   public void add(Entity entity) {
/* 209 */     if ((entity instanceof Player)) {
/* 210 */       this.player = ((Player)entity);
/*     */     }
/* 212 */     entity.removed = false;
/* 213 */     this.entities.add(entity);
/* 214 */     entity.init(this);
/*     */     
/* 216 */     insertEntity(entity.x >> 4, entity.y >> 4, entity);
/*     */   }
/*     */   
/*     */   public void remove(Entity e) {
/* 220 */     this.entities.remove(e);
/* 221 */     int xto = e.x >> 4;
/* 222 */     int yto = e.y >> 4;
/* 223 */     removeEntity(xto, yto, e);
/*     */   }
/*     */   
/*     */   private void insertEntity(int x, int y, Entity e) {
/* 227 */     if ((x < 0) || (y < 0) || (x >= this.w) || (y >= this.h)) return;
/* 228 */     this.entitiesInTiles[(x + y * this.w)].add(e);
/*     */   }
/*     */   
/*     */   private void removeEntity(int x, int y, Entity e) {
/* 232 */     if ((x < 0) || (y < 0) || (x >= this.w) || (y >= this.h)) return;
/* 233 */     this.entitiesInTiles[(x + y * this.w)].remove(e);
/*     */   }
/*     */   
/*     */   public void trySpawn(int count) {
/* 237 */     for (int i = 0; i < count; i++)
/*     */     {
/*     */ 
/* 240 */       int minLevel = 1;
/* 241 */       int maxLevel = 1;
/* 242 */       if (this.depth < 0) {
/* 243 */         maxLevel = -this.depth + 1;
/*     */       }
/* 245 */       if (this.depth > 0) {
/* 246 */         minLevel = maxLevel = 4;
/*     */       }
/*     */       
/* 249 */       int lvl = this.random.nextInt(maxLevel - minLevel + 1) + minLevel;
			    int tempRandom = this.random.nextInt(3);
/* 250 */        Mob mob = null; if (tempRandom == 0) {
/* 251 */         mob = new Cow(lvl);
/*     */       } 
                  if (tempRandom == 1) {
/* 253 */         mob = new Slime(lvl);
//                Replacing with Cow temporarily. (was zombie)
/*     */       } 
				  if (tempRandom == 2) {
				  mob = new Zombie(lvl);
 				}
					
/* 255 */       if (mob.findStartPos(this)) {
/* 256 */         add(mob);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void tick() {
/* 262 */     trySpawn(1);
/*     */     
/* 264 */     for (int i = 0; i < this.w * this.h / 50; i++) {
/* 265 */       int xt = this.random.nextInt(this.w);
/* 266 */       int yt = this.random.nextInt(this.w);
/* 267 */       getTile(xt, yt).tick(this, xt, yt);
/*     */     }
/* 269 */     for (int i = 0; i < this.entities.size(); i++) {
/* 270 */       Entity e = (Entity)this.entities.get(i);
/* 271 */       int xto = e.x >> 4;
/* 272 */       int yto = e.y >> 4;
/*     */       
/* 274 */       e.tick();
/*     */       
/* 276 */       if (e.removed) {
/* 277 */         this.entities.remove(i--);
/* 278 */         removeEntity(xto, yto, e);
/*     */       } else {
/* 280 */         int xt = e.x >> 4;
/* 281 */         int yt = e.y >> 4;
/*     */         
/* 283 */         if ((xto != xt) || (yto != yt)) {
/* 284 */           removeEntity(xto, yto, e);
/* 285 */           insertEntity(xt, yt, e);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public List<Entity> getEntities(int x0, int y0, int x1, int y1) {
/* 292 */     List<Entity> result = new ArrayList();
/* 293 */     int xt0 = (x0 >> 4) - 1;
/* 294 */     int yt0 = (y0 >> 4) - 1;
/* 295 */     int xt1 = (x1 >> 4) + 1;
/* 296 */     int yt1 = (y1 >> 4) + 1;
/* 297 */     for (int y = yt0; y <= yt1; y++) {
/* 298 */       for (int x = xt0; x <= xt1; x++)
/* 299 */         if ((x >= 0) && (y >= 0) && (x < this.w) && (y < this.h)) {
/* 300 */           List<Entity> entities = this.entitiesInTiles[(x + y * this.w)];
/* 301 */           for (int i = 0; i < entities.size(); i++) {
/* 302 */             Entity e = (Entity)entities.get(i);
/* 303 */             if (e.intersects(x0, y0, x1, y1)) result.add(e);
/*     */           }
/*     */         }
/*     */     }
/* 307 */     return result;
/*     */   }
/*     */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\level\Level.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */