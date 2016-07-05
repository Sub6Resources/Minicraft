/*    */ package com.mojang.ld22.item;
/*    */ 
/*    */ import com.mojang.ld22.entity.Furniture;
/*    */ import com.mojang.ld22.entity.Player;
/*    */ import com.mojang.ld22.gfx.Color;
/*    */ import com.mojang.ld22.gfx.Screen;
/*    */ 
/*    */ public class PowerGloveItem extends Item
/*    */ {
/*    */   public int getColor()
/*    */   {
/* 12 */     return Color.get(-1, 100, 320, 430);
/*    */   }
/*    */   
/*    */   public int getSprite() {
/* 16 */     return 135;
/*    */   }
/*    */   
/*    */   public void renderIcon(Screen screen, int x, int y) {
/* 20 */     screen.render(x, y, getSprite(), getColor(), 0);
/*    */   }
/*    */   
/*    */   public void renderInventory(Screen screen, int x, int y) {
/* 24 */     screen.render(x, y, getSprite(), getColor(), 0);
/* 25 */     com.mojang.ld22.gfx.Font.draw(getName(), screen, x + 8, y, Color.get(-1, 555, 555, 555));
/*    */   }
/*    */   
/*    */   public String getName() {
/* 29 */     return "Pow glove";
/*    */   }
/*    */   
/*    */   public boolean interact(Player player, com.mojang.ld22.entity.Entity entity, int attackDir) {
/* 33 */     if ((entity instanceof Furniture)) {
/* 34 */       Furniture f = (Furniture)entity;
/* 35 */       f.take(player);
/* 36 */       return true;
/*    */     }
/* 38 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\item\PowerGloveItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */