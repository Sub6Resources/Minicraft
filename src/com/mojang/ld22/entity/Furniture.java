/*    */ package com.mojang.ld22.entity;
/*    */ 
/*    */ import com.mojang.ld22.gfx.Screen;
/*    */ import com.mojang.ld22.item.FurnitureItem;
/*    */ 
/*    */ public class Furniture extends Entity
/*    */ {
/*  8 */   private int pushTime = 0;
/*  9 */   private int pushDir = -1;
/*    */   public int col;
/*    */   public int sprite;
/*    */   public String name;
/*    */   private Player shouldTake;
/*    */   
/* 15 */   public Furniture(String name) { this.name = name;
/* 16 */     this.xr = 3;
/* 17 */     this.yr = 3;
/*    */   }
/*    */   
/*    */   public void tick() {
/* 21 */     if (this.shouldTake != null) {
/* 22 */       if ((this.shouldTake.activeItem instanceof com.mojang.ld22.item.PowerGloveItem)) {
/* 23 */         remove();
/* 24 */         this.shouldTake.inventory.add(0, this.shouldTake.activeItem);
/* 25 */         this.shouldTake.activeItem = new FurnitureItem(this);
/*    */       }
/* 27 */       this.shouldTake = null;
/*    */     }
/* 29 */     if (this.pushDir == 0) move(0, 1);
/* 30 */     if (this.pushDir == 1) move(0, -1);
/* 31 */     if (this.pushDir == 2) move(-1, 0);
/* 32 */     if (this.pushDir == 3) move(1, 0);
/* 33 */     this.pushDir = -1;
/* 34 */     if (this.pushTime > 0) this.pushTime -= 1;
/*    */   }
/*    */   
/*    */   public void render(Screen screen) {
/* 38 */     screen.render(this.x - 8, this.y - 8 - 4, this.sprite * 2 + 256, this.col, 0);
/* 39 */     screen.render(this.x - 0, this.y - 8 - 4, this.sprite * 2 + 256 + 1, this.col, 0);
/* 40 */     screen.render(this.x - 8, this.y - 0 - 4, this.sprite * 2 + 256 + 32, this.col, 0);
/* 41 */     screen.render(this.x - 0, this.y - 0 - 4, this.sprite * 2 + 256 + 33, this.col, 0);
/*    */   }
/*    */   
/*    */   public boolean blocks(Entity e) {
/* 45 */     return true;
/*    */   }
/*    */   
/*    */   protected void touchedBy(Entity entity) {
/* 49 */     if (((entity instanceof Player)) && (this.pushTime == 0)) {
/* 50 */       this.pushDir = ((Player)entity).dir;
/* 51 */       this.pushTime = 10;
/*    */     }
/*    */   }
/*    */   
/*    */   public void take(Player player) {
/* 56 */     this.shouldTake = player;
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\entity\Furniture.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */