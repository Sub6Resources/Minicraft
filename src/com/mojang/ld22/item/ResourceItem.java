/*    */ package com.mojang.ld22.item;
/*    */ 
/*    */ import com.mojang.ld22.entity.ItemEntity;
/*    */ import com.mojang.ld22.entity.Player;
/*    */ import com.mojang.ld22.gfx.Color;
/*    */ import com.mojang.ld22.gfx.Font;
/*    */ import com.mojang.ld22.gfx.Screen;
/*    */ import com.mojang.ld22.item.resource.Resource;
/*    */ import com.mojang.ld22.level.Level;
/*    */ 
/*    */ public class ResourceItem extends Item
/*    */ {
/*    */   public Resource resource;
/* 14 */   public int count = 1;
/*    */   
/*    */   public ResourceItem(Resource resource) {
/* 17 */     this.resource = resource;
/*    */   }
/*    */   
/*    */   public ResourceItem(Resource resource, int count) {
/* 21 */     this.resource = resource;
/* 22 */     this.count = count;
/*    */   }
/*    */   
/*    */   public int getColor() {
/* 26 */     return this.resource.color;
/*    */   }
/*    */   
/*    */   public int getSprite() {
/* 30 */     return this.resource.sprite;
/*    */   }
/*    */   
/*    */   public void renderIcon(Screen screen, int x, int y) {
/* 34 */     screen.render(x, y, this.resource.sprite, this.resource.color, 0);
/*    */   }
/*    */   
/*    */   public void renderInventory(Screen screen, int x, int y) {
/* 38 */     screen.render(x, y, this.resource.sprite, this.resource.color, 0);
/* 39 */     Font.draw(this.resource.name, screen, x + 32, y, Color.get(-1, 555, 555, 555));
/* 40 */     int cc = this.count;
/* 41 */     if (cc > 999) cc = 999;
/* 42 */     Font.draw(""+cc, screen, x + 8, y, Color.get(-1, 444, 444, 444));
/*    */   }
/*    */   
/*    */   public String getName() {
/* 46 */     return this.resource.name;
/*    */   }
/*    */   
/*    */   public void onTake(ItemEntity itemEntity) {}
/*    */   
/*    */   public boolean interactOn(com.mojang.ld22.level.tile.Tile tile, Level level, int xt, int yt, Player player, int attackDir)
/*    */   {
/* 53 */     if (this.resource.interactOn(tile, level, xt, yt, player, attackDir)) {
/* 54 */       this.count -= 1;
/* 55 */       return true;
/*    */     }
/* 57 */     return false;
/*    */   }
/*    */   
/*    */   public boolean isDepleted() {
/* 61 */     return this.count <= 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\item\ResourceItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */