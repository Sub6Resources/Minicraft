/*    */ package com.mojang.ld22.item;
/*    */ 
/*    */ import com.mojang.ld22.entity.Furniture;
/*    */ import com.mojang.ld22.entity.ItemEntity;
/*    */ import com.mojang.ld22.entity.Player;
/*    */ import com.mojang.ld22.gfx.Font;
/*    */ import com.mojang.ld22.gfx.Screen;
/*    */ import com.mojang.ld22.level.Level;
/*    */ import com.mojang.ld22.level.tile.Tile;
/*    */ 
/*    */ public class FurnitureItem extends Item
/*    */ {
/*    */   public Furniture furniture;
/* 14 */   public boolean placed = false;
/*    */   
/*    */   public FurnitureItem(Furniture furniture) {
/* 17 */     this.furniture = furniture;
/*    */   }
/*    */   
/*    */   public int getColor() {
/* 21 */     return this.furniture.col;
/*    */   }
/*    */   
/*    */   public int getSprite() {
/* 25 */     return this.furniture.sprite + 320;
/*    */   }
/*    */   
/*    */   public void renderIcon(Screen screen, int x, int y) {
/* 29 */     screen.render(x, y, getSprite(), getColor(), 0);
/*    */   }
/*    */   
/*    */   public void renderInventory(Screen screen, int x, int y) {
/* 33 */     screen.render(x, y, getSprite(), getColor(), 0);
/* 34 */     Font.draw(this.furniture.name, screen, x + 8, y, com.mojang.ld22.gfx.Color.get(-1, 555, 555, 555));
/*    */   }
/*    */   
/*    */   public void onTake(ItemEntity itemEntity) {}
/*    */   
/*    */   public boolean canAttack()
/*    */   {
/* 41 */     return false;
/*    */   }
/*    */   
/*    */   public boolean interactOn(Tile tile, Level level, int xt, int yt, Player player, int attackDir) {
/* 45 */     if (tile.mayPass(level, xt, yt, this.furniture)) {
/* 46 */       this.furniture.x = (xt * 16 + 8);
/* 47 */       this.furniture.y = (yt * 16 + 8);
/* 48 */       level.add(this.furniture);
/* 49 */       this.placed = true;
/* 50 */       return true;
/*    */     }
/* 52 */     return false;
/*    */   }
/*    */   
/*    */   public boolean isDepleted() {
/* 56 */     return this.placed;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 60 */     return this.furniture.name;
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\item\FurnitureItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */