/*     */ package com.mojang.ld22.entity;
/*     */ 
/*     */ import com.mojang.ld22.Game;
/*     */ import com.mojang.ld22.InputHandler;
/*     */ import com.mojang.ld22.InputHandler.Key;
/*     */ import com.mojang.ld22.entity.particle.TextParticle;
/*     */ import com.mojang.ld22.gfx.Color;
/*     */ import com.mojang.ld22.gfx.Screen;
/*     */ import com.mojang.ld22.item.FurnitureItem;
/*     */ import com.mojang.ld22.item.Item;
/*     */ import com.mojang.ld22.item.PowerGloveItem;
/*     */ import com.mojang.ld22.level.Level;
/*     */ import com.mojang.ld22.level.tile.Tile;
/*     */ import com.mojang.ld22.screen.InventoryMenu;
/*     */ import com.mojang.ld22.sound.Sound;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ public class Player
/*     */   extends Mob
/*     */ {
/*     */   private InputHandler input;
/*     */   private int attackTime;
/*     */   private int attackDir;
/*     */   public Game game;
/*  27 */   public Inventory inventory = new Inventory();
/*     */   public Item attackItem;
/*     */   public Item activeItem;
/*     */   public int stamina;
/*     */   public int staminaRecharge;
/*     */   public int staminaRechargeDelay;
/*     */   public int score;
/*  34 */   public int maxStamina = 10;
/*     */   private int onStairDelay;
/*  36 */   public int invulnerableTime = 0;
/*     */   
/*     */   public Player(Game game, InputHandler input) {
/*  39 */     this.game = game;
/*  40 */     this.input = input;
/*  41 */     this.x = 24;
/*  42 */     this.y = 24;
/*  43 */     this.stamina = this.maxStamina;
/*     */     
/*  45 */     this.inventory.add(new FurnitureItem(new Workbench()));
/*  46 */     this.inventory.add(new PowerGloveItem());
/*     */   }
/*     */   
/*     */   public void tick() {
/*  50 */     super.tick();
/*     */     
/*  52 */     if (this.invulnerableTime > 0) this.invulnerableTime -= 1;
/*  53 */     Tile onTile = this.level.getTile(this.x >> 4, this.y >> 4);
/*  54 */     if ((onTile == Tile.stairsDown) || (onTile == Tile.stairsUp)) {
/*  55 */       if (this.onStairDelay == 0) {
/*  56 */         changeLevel(onTile == Tile.stairsUp ? 1 : -1);
/*  57 */         this.onStairDelay = 10;
/*  58 */         return;
/*     */       }
/*  60 */       this.onStairDelay = 10;
/*     */     }
/*  62 */     else if (this.onStairDelay > 0) { this.onStairDelay -= 1;
/*     */     }
/*     */     
/*  65 */     if ((this.stamina <= 0) && (this.staminaRechargeDelay == 0) && (this.staminaRecharge == 0)) {
/*  66 */       this.staminaRechargeDelay = 40;
/*     */     }
/*     */     
/*  69 */     if (this.staminaRechargeDelay > 0) {
/*  70 */       this.staminaRechargeDelay -= 1;
/*     */     }
/*     */     
/*  73 */     if (this.staminaRechargeDelay == 0) {
/*  74 */       this.staminaRecharge += 1;
/*  75 */       if (isSwimming()) {
/*  76 */         this.staminaRecharge = 0;
/*     */       }
/*  78 */       while (this.staminaRecharge > 10) {
/*  79 */         this.staminaRecharge -= 10;
/*  80 */         if (this.stamina < this.maxStamina) { this.stamina += 1;
/*     */         }
/*     */       }
/*     */     }
/*  84 */     int xa = 0;
/*  85 */     int ya = 0;
/*  86 */     if (this.input.up.down) ya--;
/*  87 */     if (this.input.down.down) ya++;
/*  88 */     if (this.input.left.down) xa--;
/*  89 */     if (this.input.right.down) xa++;
/*  90 */     if ((isSwimming()) && (this.tickTime % 60 == 0)) {
/*  91 */       if (this.stamina > 0) {
/*  92 */         this.stamina -= 1;
/*     */       } else {
/*  94 */         hurt(this, 1, this.dir ^ 0x1);
/*     */       }
/*     */     }
/*     */     
/*  98 */     if (this.staminaRechargeDelay % 2 == 0) {
/*  99 */       move(xa, ya);
/*     */     }
/*     */     
/* 102 */     if ((this.input.attack.clicked) && 
/* 103 */       (this.stamina != 0))
/*     */     {
/*     */ 
/* 106 */       this.stamina -= 1;
/* 107 */       this.staminaRecharge = 0;
/* 108 */       attack();
/*     */     }
/*     */     
/* 111 */     if ((this.input.menu.clicked) && 
/* 112 */       (!use())) {
/* 113 */       this.game.setMenu(new InventoryMenu(this));
/*     */     }
/*     */     
/* 116 */     if (this.attackTime > 0) this.attackTime -= 1;
/*     */   }
/*     */   
/*     */   private boolean use()
/*     */   {
/* 121 */     int yo = -2;
/* 122 */     if ((this.dir == 0) && (use(this.x - 8, this.y + 4 + yo, this.x + 8, this.y + 12 + yo))) return true;
/* 123 */     if ((this.dir == 1) && (use(this.x - 8, this.y - 12 + yo, this.x + 8, this.y - 4 + yo))) return true;
/* 124 */     if ((this.dir == 3) && (use(this.x + 4, this.y - 8 + yo, this.x + 12, this.y + 8 + yo))) return true;
/* 125 */     if ((this.dir == 2) && (use(this.x - 12, this.y - 8 + yo, this.x - 4, this.y + 8 + yo))) { return true;
/*     */     }
/* 127 */     int xt = this.x >> 4;
/* 128 */     int yt = this.y + yo >> 4;
/* 129 */     int r = 12;
/* 130 */     if (this.attackDir == 0) yt = this.y + r + yo >> 4;
/* 131 */     if (this.attackDir == 1) yt = this.y - r + yo >> 4;
/* 132 */     if (this.attackDir == 2) xt = this.x - r >> 4;
/* 133 */     if (this.attackDir == 3) { xt = this.x + r >> 4;
/*     */     }
/* 135 */     if ((xt >= 0) && (yt >= 0) && (xt < this.level.w) && (yt < this.level.h) && 
/* 136 */       (this.level.getTile(xt, yt).use(this.level, xt, yt, this, this.attackDir))) { return true;
/*     */     }
/*     */     
/* 139 */     return false;
/*     */   }
/*     */   
/*     */   private void attack() {
/* 143 */     this.walkDist += 8;
/* 144 */     this.attackDir = this.dir;
/* 145 */     this.attackItem = this.activeItem;
/* 146 */     boolean done = false;
/*     */     
/* 148 */     if (this.activeItem != null) {
/* 149 */       this.attackTime = 10;
/* 150 */       int yo = -2;
/* 151 */       int range = 12;
/* 152 */       if ((this.dir == 0) && (interact(this.x - 8, this.y + 4 + yo, this.x + 8, this.y + range + yo))) done = true;
/* 153 */       if ((this.dir == 1) && (interact(this.x - 8, this.y - range + yo, this.x + 8, this.y - 4 + yo))) done = true;
/* 154 */       if ((this.dir == 3) && (interact(this.x + 4, this.y - 8 + yo, this.x + range, this.y + 8 + yo))) done = true;
/* 155 */       if ((this.dir == 2) && (interact(this.x - range, this.y - 8 + yo, this.x - 4, this.y + 8 + yo))) done = true;
/* 156 */       if (done) { return;
/*     */       }
/* 158 */       int xt = this.x >> 4;
/* 159 */       int yt = this.y + yo >> 4;
/* 160 */       int r = 12;
/* 161 */       if (this.attackDir == 0) yt = this.y + r + yo >> 4;
/* 162 */       if (this.attackDir == 1) yt = this.y - r + yo >> 4;
/* 163 */       if (this.attackDir == 2) xt = this.x - r >> 4;
/* 164 */       if (this.attackDir == 3) { xt = this.x + r >> 4;
/*     */       }
/* 166 */       if ((xt >= 0) && (yt >= 0) && (xt < this.level.w) && (yt < this.level.h)) {
/* 167 */         if (this.activeItem.interactOn(this.level.getTile(xt, yt), this.level, xt, yt, this, this.attackDir)) {
/* 168 */           done = true;
/*     */         }
/* 170 */         else if (this.level.getTile(xt, yt).interact(this.level, xt, yt, this, this.activeItem, this.attackDir)) {
/* 171 */           done = true;
/*     */         }
/*     */         
/* 174 */         if (this.activeItem.isDepleted()) {
/* 175 */           this.activeItem = null;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 180 */     if (done) { return;
/*     */     }
/* 182 */     if ((this.activeItem == null) || (this.activeItem.canAttack())) {
/* 183 */       this.attackTime = 5;
/* 184 */       int yo = -2;
/* 185 */       int range = 20;
/* 186 */       if (this.dir == 0) hurt(this.x - 8, this.y + 4 + yo, this.x + 8, this.y + range + yo);
/* 187 */       if (this.dir == 1) hurt(this.x - 8, this.y - range + yo, this.x + 8, this.y - 4 + yo);
/* 188 */       if (this.dir == 3) hurt(this.x + 4, this.y - 8 + yo, this.x + range, this.y + 8 + yo);
/* 189 */       if (this.dir == 2) { hurt(this.x - range, this.y - 8 + yo, this.x - 4, this.y + 8 + yo);
/*     */       }
/* 191 */       int xt = this.x >> 4;
/* 192 */       int yt = this.y + yo >> 4;
/* 193 */       int r = 12;
/* 194 */       if (this.attackDir == 0) yt = this.y + r + yo >> 4;
/* 195 */       if (this.attackDir == 1) yt = this.y - r + yo >> 4;
/* 196 */       if (this.attackDir == 2) xt = this.x - r >> 4;
/* 197 */       if (this.attackDir == 3) { xt = this.x + r >> 4;
/*     */       }
/* 199 */       if ((xt >= 0) && (yt >= 0) && (xt < this.level.w) && (yt < this.level.h)) {
/* 200 */         this.level.getTile(xt, yt).hurt(this.level, xt, yt, this, this.random.nextInt(3) + 1, this.attackDir);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean use(int x0, int y0, int x1, int y1)
/*     */   {
/* 207 */     List<Entity> entities = this.level.getEntities(x0, y0, x1, y1);
/* 208 */     for (int i = 0; i < entities.size(); i++) {
/* 209 */       Entity e = (Entity)entities.get(i);
/* 210 */       if ((e != this) && (e.use(this, this.attackDir))) return true;
/*     */     }
/* 212 */     return false;
/*     */   }
/*     */   
/*     */   private boolean interact(int x0, int y0, int x1, int y1) {
/* 216 */     List<Entity> entities = this.level.getEntities(x0, y0, x1, y1);
/* 217 */     for (int i = 0; i < entities.size(); i++) {
/* 218 */       Entity e = (Entity)entities.get(i);
/* 219 */       if ((e != this) && (e.interact(this, this.activeItem, this.attackDir))) return true;
/*     */     }
/* 221 */     return false;
/*     */   }
/*     */   
/*     */   private void hurt(int x0, int y0, int x1, int y1) {
/* 225 */     List<Entity> entities = this.level.getEntities(x0, y0, x1, y1);
/* 226 */     for (int i = 0; i < entities.size(); i++) {
/* 227 */       Entity e = (Entity)entities.get(i);
/* 228 */       if (e != this) e.hurt(this, getAttackDamage(e), this.attackDir);
/*     */     }
/*     */   }
/*     */   
/*     */   private int getAttackDamage(Entity e) {
/* 233 */     int dmg = this.random.nextInt(3) + 1;
/* 234 */     if (this.attackItem != null) {
/* 235 */       dmg += this.attackItem.getAttackDamageBonus(e);
/*     */     }
/* 237 */     return dmg;
/*     */   }
/*     */   
/*     */   public void render(Screen screen) {
/* 241 */     int xt = 0;
/* 242 */     int yt = 14;
/*     */     
/* 244 */     int flip1 = this.walkDist >> 3 & 0x1;
/* 245 */     int flip2 = this.walkDist >> 3 & 0x1;
/*     */     
/* 247 */     if (this.dir == 1) {
/* 248 */       xt += 2;
/*     */     }
/* 250 */     if (this.dir > 1) {
/* 251 */       flip1 = 0;
/* 252 */       flip2 = this.walkDist >> 4 & 0x1;
/* 253 */       if (this.dir == 2) {
/* 254 */         flip1 = 1;
/*     */       }
/* 256 */       xt += 4 + (this.walkDist >> 3 & 0x1) * 2;
/*     */     }
/*     */     
/* 259 */     int xo = this.x - 8;
/* 260 */     int yo = this.y - 11;
/* 261 */     if (isSwimming()) {
/* 262 */       yo += 4;
/* 263 */       int waterColor = Color.get(-1, -1, 115, 335);
/* 264 */       if (this.tickTime / 8 % 2 == 0) {
/* 265 */         waterColor = Color.get(-1, 335, 5, 115);
/*     */       }
/* 267 */       screen.render(xo + 0, yo + 3, 421, waterColor, 0);
/* 268 */       screen.render(xo + 8, yo + 3, 421, waterColor, 1);
/*     */     }
/*     */     
/* 271 */     if ((this.attackTime > 0) && (this.attackDir == 1)) {
/* 272 */       screen.render(xo + 0, yo - 4, 422, Color.get(-1, 555, 555, 555), 0);
/* 273 */       screen.render(xo + 8, yo - 4, 422, Color.get(-1, 555, 555, 555), 1);
/* 274 */       if (this.attackItem != null) {
/* 275 */         this.attackItem.renderIcon(screen, xo + 4, yo - 4);
/*     */       }
/*     */     }
/* 278 */     int col = Color.get(-1, 100, 220, 532);
/* 279 */     if (this.hurtTime > 0) {
/* 280 */       col = Color.get(-1, 555, 555, 555);
/*     */     }
/*     */     
/* 283 */     if ((this.activeItem instanceof FurnitureItem)) {
/* 284 */       yt += 2;
/*     */     }
/* 286 */     screen.render(xo + 8 * flip1, yo + 0, xt + yt * 32, col, flip1);
/* 287 */     screen.render(xo + 8 - 8 * flip1, yo + 0, xt + 1 + yt * 32, col, flip1);
/* 288 */     if (!isSwimming()) {
/* 289 */       screen.render(xo + 8 * flip2, yo + 8, xt + (yt + 1) * 32, col, flip2);
/* 290 */       screen.render(xo + 8 - 8 * flip2, yo + 8, xt + 1 + (yt + 1) * 32, col, flip2);
/*     */     }
/*     */     
/* 293 */     if ((this.attackTime > 0) && (this.attackDir == 2)) {
/* 294 */       screen.render(xo - 4, yo, 423, Color.get(-1, 555, 555, 555), 1);
/* 295 */       screen.render(xo - 4, yo + 8, 423, Color.get(-1, 555, 555, 555), 3);
/* 296 */       if (this.attackItem != null) {
/* 297 */         this.attackItem.renderIcon(screen, xo - 4, yo + 4);
/*     */       }
/*     */     }
/* 300 */     if ((this.attackTime > 0) && (this.attackDir == 3)) {
/* 301 */       screen.render(xo + 8 + 4, yo, 423, Color.get(-1, 555, 555, 555), 0);
/* 302 */       screen.render(xo + 8 + 4, yo + 8, 423, Color.get(-1, 555, 555, 555), 2);
/* 303 */       if (this.attackItem != null) {
/* 304 */         this.attackItem.renderIcon(screen, xo + 8 + 4, yo + 4);
/*     */       }
/*     */     }
/* 307 */     if ((this.attackTime > 0) && (this.attackDir == 0)) {
/* 308 */       screen.render(xo + 0, yo + 8 + 4, 422, Color.get(-1, 555, 555, 555), 2);
/* 309 */       screen.render(xo + 8, yo + 8 + 4, 422, Color.get(-1, 555, 555, 555), 3);
/* 310 */       if (this.attackItem != null) {
/* 311 */         this.attackItem.renderIcon(screen, xo + 4, yo + 8 + 4);
/*     */       }
/*     */     }
/*     */     
/* 315 */     if ((this.activeItem instanceof FurnitureItem)) {
/* 316 */       Furniture furniture = ((FurnitureItem)this.activeItem).furniture;
/* 317 */       furniture.x = this.x;
/* 318 */       furniture.y = yo;
/* 319 */       furniture.render(screen);
/*     */     }
/*     */   }
/*     */   
/*     */   public void touchItem(ItemEntity itemEntity)
/*     */   {
/* 325 */     itemEntity.take(this);
/* 326 */     this.inventory.add(itemEntity.item);
/*     */   }
/*     */   
/*     */ 
/* 330 */   public boolean canSwim() { return true; }
/*     */   
/*     */   public boolean findStartPos(Level level) {
/*     */     int x;
/*     */     int y;
/* 335 */     do { x = this.random.nextInt(level.w);
/* 336 */       y = this.random.nextInt(level.h);
/* 337 */     } while (level.getTile(x, y) != Tile.grass);
/* 338 */     this.x = (x * 16 + 8);
/* 339 */     this.y = (y * 16 + 8);
/* 340 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean payStamina(int cost)
/*     */   {
/* 346 */     if (cost > this.stamina) return false;
/* 347 */     this.stamina -= cost;
/* 348 */     return true;
/*     */   }
/*     */   
/*     */   public void changeLevel(int dir) {
/* 352 */     this.game.scheduleLevelChange(dir);
/*     */   }
/*     */   
/*     */   public int getLightRadius() {
/* 356 */     int r = 2;
/* 357 */     if ((this.activeItem != null) && 
/* 358 */       ((this.activeItem instanceof FurnitureItem))) {
/* 359 */       int rr = ((FurnitureItem)this.activeItem).furniture.getLightRadius();
/* 360 */       if (rr > r) { r = rr;
/*     */       }
/*     */     }
/* 363 */     return r;
/*     */   }
/*     */   
/*     */   protected void die() {
/* 367 */     super.die();
/* 368 */     Sound.playerDeath.play();
/*     */   }
/*     */   
/*     */   protected void touchedBy(Entity entity) {
/* 372 */     if (!(entity instanceof Player)) {
/* 373 */       entity.touchedBy(this);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void doHurt(int damage, int attackDir) {
/* 378 */     if ((this.hurtTime > 0) || (this.invulnerableTime > 0)) { return;
/*     */     }
/* 380 */     Sound.playerHurt.play();
/* 381 */     this.level.add(new TextParticle(""+damage, this.x, this.y, Color.get(-1, 504, 504, 504)));
/* 382 */     this.health -= damage;
/* 383 */     if (attackDir == 0) this.yKnockback = 6;
/* 384 */     if (attackDir == 1) this.yKnockback = -6;
/* 385 */     if (attackDir == 2) this.xKnockback = -6;
/* 386 */     if (attackDir == 3) this.xKnockback = 6;
/* 387 */     this.hurtTime = 10;
/* 388 */     this.invulnerableTime = 30;
/*     */   }
/*     */   
/*     */   public void gameWon() {
/* 392 */     this.level.player.invulnerableTime = 300;
/* 393 */     this.game.won();
/*     */   }
/*     */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\entity\Player.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */