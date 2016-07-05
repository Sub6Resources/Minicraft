/*     */ package com.mojang.ld22;
/*     */ 
/*     */ import com.mojang.ld22.entity.Player;
/*     */ import com.mojang.ld22.gfx.Color;
/*     */ import com.mojang.ld22.gfx.Font;
/*     */ import com.mojang.ld22.gfx.Screen;
/*     */ import com.mojang.ld22.gfx.SpriteSheet;
/*     */ import com.mojang.ld22.level.Level;
/*     */ import com.mojang.ld22.level.tile.Tile;
/*     */ import com.mojang.ld22.screen.DeadMenu;
/*     */ import com.mojang.ld22.screen.LevelTransitionMenu;
/*     */ import com.mojang.ld22.screen.Menu;
/*     */ import com.mojang.ld22.screen.WonMenu;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Canvas;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.image.BufferStrategy;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.DataBufferInt;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Random;
/*     */ import javax.imageio.ImageIO;
/*     */ import javax.swing.JFrame;
/*     */ 
/*     */ public class Game extends Canvas implements Runnable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  31 */   private Random random = new Random();
/*     */   
/*     */   public static final String NAME = "Minicraft";
// changed from 120, 160 to 300, 400
/*     */   public static final int HEIGHT = 120;
/*     */   public static final int WIDTH = 160;
/*     */   private static final int SCALE = 3;
/*  37 */   private BufferedImage image = new BufferedImage(160, 120, 1);
/*  38 */   private int[] pixels = ((DataBufferInt)this.image.getRaster().getDataBuffer()).getData();
/*  39 */   private boolean running = false;
/*     */   private Screen screen;
/*     */   private Screen lightScreen;
/*  42 */   private InputHandler input = new InputHandler(this);
/*     */   
/*  44 */   private int[] colors = new int[256];
/*  45 */   private int tickCount = 0;
/*  46 */   public int gameTime = 0;
/*     */   
/*     */   private Level level;
/*  49 */   private Level[] levels = new Level[5];
/*  50 */   private int currentLevel = 3;
/*     */   
/*     */   public Player player;
/*     */   public Menu menu;
/*     */   private int playerDeadTime;
/*     */   private int pendingLevelChange;
/*  56 */   private int wonTimer = 0;
/*  57 */   public boolean hasWon = false;
/*     */   
/*     */   public void setMenu(Menu menu) {
/*  60 */     this.menu = menu;
/*  61 */     if (menu != null) menu.init(this, this.input);
/*     */   }
/*     */   
/*     */   public void start() {
/*  65 */     this.running = true;
/*  66 */     new Thread(this).start();
/*     */   }
/*     */   
/*     */   public void stop() {
/*  70 */     this.running = false;
/*     */   }
/*     */   
/*     */   public void resetGame() {
/*  74 */     this.playerDeadTime = 0;
/*  75 */     this.wonTimer = 0;
/*  76 */     this.gameTime = 0;
/*  77 */     this.hasWon = false;
/*     */     
/*  79 */     this.levels = new Level[5];
/*  80 */     this.currentLevel = 3;
/*     */     
/*  82 */     this.levels[4] = new Level(128, 128, 1, null);
/*  83 */     this.levels[3] = new Level(128, 128, 0, this.levels[4]);
/*  84 */     this.levels[2] = new Level(128, 128, -1, this.levels[3]);
/*  85 */     this.levels[1] = new Level(128, 128, -2, this.levels[2]);
/*  86 */     this.levels[0] = new Level(128, 128, -3, this.levels[1]);
/*     */     
/*  88 */     this.level = this.levels[this.currentLevel];
/*  89 */     this.player = new Player(this, this.input);
/*  90 */     this.player.findStartPos(this.level);
/*     */     
/*  92 */     this.level.add(this.player);
/*     */     
/*  94 */     for (int i = 0; i < 5; i++) {
/*  95 */       this.levels[i].trySpawn(5000);
/*     */     }
/*     */   }
/*     */   
/*     */   private void init() {
/* 100 */     int pp = 0;
/* 101 */     for (int r = 0; r < 6; r++) {
/* 102 */       for (int g = 0; g < 6; g++) {
/* 103 */         for (int b = 0; b < 6; b++) {
/* 104 */           int rr = r * 255 / 5;
/* 105 */           int gg = g * 255 / 5;
/* 106 */           int bb = b * 255 / 5;
/* 107 */           int mid = (rr * 30 + gg * 59 + bb * 11) / 100;
/*     */           
/* 109 */           int r1 = (rr + mid * 1) / 2 * 230 / 255 + 10;
/* 110 */           int g1 = (gg + mid * 1) / 2 * 230 / 255 + 10;
/* 111 */           int b1 = (bb + mid * 1) / 2 * 230 / 255 + 10;
/* 112 */           this.colors[(pp++)] = (r1 << 16 | g1 << 8 | b1);
/*     */         }
/*     */       }
/*     */     }
/*     */     try
/*     */     {
/* 118 */       this.screen = new Screen(160, 120, new SpriteSheet(ImageIO.read(Game.class.getResourceAsStream("/icons.png"))));
/* 119 */       this.lightScreen = new Screen(160, 120, new SpriteSheet(ImageIO.read(Game.class.getResourceAsStream("/icons.png"))));
/*     */     } catch (IOException e) {
/* 121 */       e.printStackTrace();
/*     */     }
/*     */     
/* 124 */     resetGame();
/* 125 */     setMenu(new com.mojang.ld22.screen.TitleMenu());
/*     */   }
/*     */   
/*     */   public void run() {
/* 129 */     long lastTime = System.nanoTime();
/* 130 */     double unprocessed = 0.0D;
/* 131 */     double nsPerTick = 1.6666666666666666E7D;
/* 132 */     int frames = 0;
/* 133 */     int ticks = 0;
/* 134 */     long lastTimer1 = System.currentTimeMillis();
/*     */     
/* 136 */     init();
/*     */     
/* 138 */     while (this.running) {
/* 139 */       long now = System.nanoTime();
/* 140 */       unprocessed += (now - lastTime) / nsPerTick;
/* 141 */       lastTime = now;
/* 142 */       boolean shouldRender = true;
/* 143 */       while (unprocessed >= 1.0D) {
/* 144 */         ticks++;
/* 145 */         tick();
/* 146 */         unprocessed -= 1.0D;
/* 147 */         shouldRender = true;
/*     */       }
/*     */       try
/*     */       {
/* 151 */         Thread.sleep(2L);
/*     */       } catch (InterruptedException e) {
/* 153 */         e.printStackTrace();
/*     */       }
/*     */       
/* 156 */       if (shouldRender) {
/* 157 */         frames++;
/* 158 */         render();
/*     */       }
/*     */       
/* 161 */       if (System.currentTimeMillis() - lastTimer1 > 1000L) {
/* 162 */         lastTimer1 += 1000L;
/* 163 */         System.out.println(ticks + " ticks, " + frames + " fps");
/* 164 */         frames = 0;
/* 165 */         ticks = 0;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void tick() {
/* 171 */     this.tickCount += 1;
/* 172 */     if (!hasFocus()) {
/* 173 */       this.input.releaseAll();
/*     */     } else {
/* 175 */       if ((!this.player.removed) && (!this.hasWon)) { this.gameTime += 1;
/*     */       }
/* 177 */       this.input.tick();
/* 178 */       if (this.menu != null) {
/* 179 */         this.menu.tick();
/*     */       } else {
/* 181 */         if (this.player.removed) {
/* 182 */           this.playerDeadTime += 1;
/* 183 */           if (this.playerDeadTime > 60) {
/* 184 */             setMenu(new DeadMenu());
/*     */           }
/*     */         }
/* 187 */         else if (this.pendingLevelChange != 0) {
/* 188 */           setMenu(new LevelTransitionMenu(this.pendingLevelChange));
/* 189 */           this.pendingLevelChange = 0;
/*     */         }
/*     */         
/* 192 */         if ((this.wonTimer > 0) && 
/* 193 */           (--this.wonTimer == 0)) {
/* 194 */           setMenu(new WonMenu());
/*     */         }
/*     */         
/* 197 */         this.level.tick();
/* 198 */         Tile.tickCount += 1;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void changeLevel(int dir) {
/* 204 */     this.level.remove(this.player);
/* 205 */     this.currentLevel += dir;
/* 206 */     this.level = this.levels[this.currentLevel];
/* 207 */     this.player.x = ((this.player.x >> 4) * 16 + 8);
/* 208 */     this.player.y = ((this.player.y >> 4) * 16 + 8);
/* 209 */     this.level.add(this.player);
/*     */   }
/*     */   
/*     */   public void render()
/*     */   {
/* 214 */     BufferStrategy bs = getBufferStrategy();
/* 215 */     if (bs == null) {
/* 216 */       createBufferStrategy(3);
/* 217 */       requestFocus();
/* 218 */       return;
/*     */     }
/*     */     
/* 221 */     int xScroll = this.player.x - this.screen.w / 2;
/* 222 */     int yScroll = this.player.y - (this.screen.h - 8) / 2;
/* 223 */     if (xScroll < 16) xScroll = 16;
/* 224 */     if (yScroll < 16) yScroll = 16;
/* 225 */     if (xScroll > this.level.w * 16 - this.screen.w - 16) xScroll = this.level.w * 16 - this.screen.w - 16;
/* 226 */     if (yScroll > this.level.h * 16 - this.screen.h - 16) yScroll = this.level.h * 16 - this.screen.h - 16;
/* 227 */     if (this.currentLevel > 3) {
/* 228 */       int col = Color.get(20, 20, 121, 121);
/* 229 */       for (int y = 0; y < 14; y++) {
/* 230 */         for (int x = 0; x < 24; x++) {
/* 231 */           this.screen.render(x * 8 - (xScroll / 4 & 0x7), y * 8 - (yScroll / 4 & 0x7), 0, col, 0);
/*     */         }
/*     */       }
/*     */     }
/* 235 */     this.level.renderBackground(this.screen, xScroll, yScroll);
/* 236 */     this.level.renderSprites(this.screen, xScroll, yScroll);
/*     */     
/* 238 */     if (this.currentLevel < 3) {
/* 239 */       this.lightScreen.clear(0);
/* 240 */       this.level.renderLight(this.lightScreen, xScroll, yScroll);
/* 241 */       this.screen.overlay(this.lightScreen, xScroll, yScroll);
/*     */     }
/*     */     
/* 244 */     renderGui();
/*     */     
/* 246 */     if (!hasFocus()) { renderFocusNagger();
/*     */     }
/* 248 */     for (int y = 0; y < this.screen.h; y++) {
/* 249 */       for (int x = 0; x < this.screen.w; x++) {
/* 250 */         int cc = this.screen.pixels[(x + y * this.screen.w)];
/* 251 */         if (cc < 255) { this.pixels[(x + y * 160)] = this.colors[cc];
/*     */         }
/*     */       }
/*     */     }
/* 255 */     Graphics g = bs.getDrawGraphics();
/* 256 */     g.fillRect(0, 0, getWidth(), getHeight());
/*     */     
/* 258 */     int ww = 480;
/* 259 */     int hh = 360;
/* 260 */     int xo = (getWidth() - ww) / 2;
/* 261 */     int yo = (getHeight() - hh) / 2;
/* 262 */     g.drawImage(this.image, xo, yo, ww, hh, null);
/* 263 */     g.dispose();
/* 264 */     bs.show();
/*     */   }
/*     */   
/*     */   private void renderGui() {
/* 268 */     for (int y = 0; y < 2; y++) {
/* 269 */       for (int x = 0; x < 20; x++) {
/* 270 */         this.screen.render(x * 8, this.screen.h - 16 + y * 8, 384, Color.get(0, 0, 0, 0), 0);
/*     */       }
/*     */     }
/*     */     
/* 274 */     for (int i = 0; i < 10; i++) {
/* 275 */       if (i < this.player.health) {
/* 276 */         this.screen.render(i * 8, this.screen.h - 16, 384, Color.get(0, 200, 500, 533), 0);
/*     */       } else {
/* 278 */         this.screen.render(i * 8, this.screen.h - 16, 384, Color.get(0, 100, 0, 0), 0);
/*     */       }
/* 280 */       if (this.player.staminaRechargeDelay > 0) {
/* 281 */         if (this.player.staminaRechargeDelay / 4 % 2 == 0) {
/* 282 */           this.screen.render(i * 8, this.screen.h - 8, 385, Color.get(0, 555, 0, 0), 0);
/*     */         } else {
/* 284 */           this.screen.render(i * 8, this.screen.h - 8, 385, Color.get(0, 110, 0, 0), 0);
/*     */         }
/* 286 */       } else if (i < this.player.stamina) {
/* 287 */         this.screen.render(i * 8, this.screen.h - 8, 385, Color.get(0, 220, 550, 553), 0);
/*     */       } else {
/* 289 */         this.screen.render(i * 8, this.screen.h - 8, 385, Color.get(0, 110, 0, 0), 0);
/*     */       }
/*     */     }
/* 292 */     if (this.player.activeItem != null) {
/* 293 */       this.player.activeItem.renderInventory(this.screen, 80, this.screen.h - 16);
/*     */     }
/*     */     
/* 296 */     if (this.menu != null) {
/* 297 */       this.menu.render(this.screen);
/*     */     }
/*     */   }
/*     */   
/*     */   private void renderFocusNagger() {
/* 302 */     String msg = "Click to focus!";
/* 303 */     int xx = (160 - msg.length() * 8) / 2;
/* 304 */     int yy = 56;
/* 305 */     int w = msg.length();
/* 306 */     int h = 1;
/*     */     
/* 308 */     this.screen.render(xx - 8, yy - 8, 416, Color.get(-1, 1, 5, 445), 0);
/* 309 */     this.screen.render(xx + w * 8, yy - 8, 416, Color.get(-1, 1, 5, 445), 1);
/* 310 */     this.screen.render(xx - 8, yy + 8, 416, Color.get(-1, 1, 5, 445), 2);
/* 311 */     this.screen.render(xx + w * 8, yy + 8, 416, Color.get(-1, 1, 5, 445), 3);
/* 312 */     for (int x = 0; x < w; x++) {
/* 313 */       this.screen.render(xx + x * 8, yy - 8, 417, Color.get(-1, 1, 5, 445), 0);
/* 314 */       this.screen.render(xx + x * 8, yy + 8, 417, Color.get(-1, 1, 5, 445), 2);
/*     */     }
/* 316 */     for (int y = 0; y < h; y++) {
/* 317 */       this.screen.render(xx - 8, yy + y * 8, 418, Color.get(-1, 1, 5, 445), 0);
/* 318 */       this.screen.render(xx + w * 8, yy + y * 8, 418, Color.get(-1, 1, 5, 445), 1);
/*     */     }
/*     */     
/* 321 */     if (this.tickCount / 20 % 2 == 0) {
/* 322 */       Font.draw(msg, this.screen, xx, yy, Color.get(5, 333, 333, 333));
/*     */     } else {
/* 324 */       Font.draw(msg, this.screen, xx, yy, Color.get(5, 555, 555, 555));
/*     */     }
/*     */   }
/*     */   
/*     */   public void scheduleLevelChange(int dir) {
/* 329 */     this.pendingLevelChange = dir;
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/* 333 */     Game game = new Game();
/* 334 */     game.setMinimumSize(new Dimension(480, 360));
/* 335 */     game.setMaximumSize(new Dimension(480, 360));
/* 336 */     game.setPreferredSize(new Dimension(480, 360));
/*     */     
/* 338 */     JFrame frame = new JFrame("Minicraft");
/* 339 */     frame.setDefaultCloseOperation(3);
/* 340 */     frame.setLayout(new BorderLayout());
/* 341 */     frame.add(game, "Center");
/* 342 */     frame.pack();
/* 343 */     frame.setResizable(false);
/* 344 */     frame.setLocationRelativeTo(null);
/* 345 */     frame.setVisible(true);
/*     */     
/* 347 */     game.start();
/*     */   }
/*     */   
/*     */   public void won() {
/* 351 */     this.wonTimer = 180;
/* 352 */     this.hasWon = true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\Game.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */