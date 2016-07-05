/*    */ package com.mojang.ld22.item;
/*    */ 
/*    */ import com.mojang.ld22.entity.Entity;
/*    */ import com.mojang.ld22.entity.ItemEntity;
/*    */ import com.mojang.ld22.entity.Player;
/*    */ import com.mojang.ld22.gfx.Screen;
/*    */ import com.mojang.ld22.level.Level;
/*    */ 
/*    */ public class Item implements com.mojang.ld22.screen.ListItem
/*    */ {
/*    */   public int getColor()
/*    */   {
/* 13 */     return 0;
/*    */   }
/*    */   
/*    */   public int getSprite() {
/* 17 */     return 0;
/*    */   }
/*    */   
/*    */ 
/*    */   public void onTake(ItemEntity itemEntity) {}
/*    */   
/*    */   public void renderInventory(Screen screen, int x, int y) {}
/*    */   
/*    */   public boolean interact(Player player, Entity entity, int attackDir)
/*    */   {
/* 27 */     return false;
/*    */   }
/*    */   
/*    */   public void renderIcon(Screen screen, int x, int y) {}
/*    */   
/*    */   public boolean interactOn(com.mojang.ld22.level.tile.Tile tile, Level level, int xt, int yt, Player player, int attackDir)
/*    */   {
/* 34 */     return false;
/*    */   }
/*    */   
/*    */   public boolean isDepleted() {
/* 38 */     return false;
/*    */   }
/*    */   
/*    */   public boolean canAttack() {
/* 42 */     return false;
/*    */   }
/*    */   
/*    */   public int getAttackDamageBonus(Entity e) {
/* 46 */     return 0;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 50 */     return "";
/*    */   }
/*    */   
/*    */   public boolean matches(Item item) {
/* 54 */     return item.getClass() == getClass();
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\item\Item.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */