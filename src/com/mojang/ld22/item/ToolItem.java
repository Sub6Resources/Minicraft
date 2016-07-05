/*    */ package com.mojang.ld22.item;
/*    */ 
/*    */ import com.mojang.ld22.entity.Entity;
/*    */ import com.mojang.ld22.entity.ItemEntity;
/*    */ import com.mojang.ld22.gfx.Color;
/*    */ import com.mojang.ld22.gfx.Font;
/*    */ import com.mojang.ld22.gfx.Screen;
/*    */ import java.util.Random;
/*    */ 
/*    */ public class ToolItem extends Item
/*    */ {
/* 12 */   private Random random = new Random();
/*    */   
/*    */   public static final int MAX_LEVEL = 5;
/* 15 */   public static final String[] LEVEL_NAMES = {
/* 16 */     "Wood", "Rock", "Iron", "Gold", "Gem" };
/*    */   
/*    */ 
/* 19 */   public static final int[] LEVEL_COLORS = {
/* 20 */     Color.get(-1, 100, 321, 431), 
/* 21 */     Color.get(-1, 100, 321, 111), 
/* 22 */     Color.get(-1, 100, 321, 555), 
/* 23 */     Color.get(-1, 100, 321, 550), 
/* 24 */     Color.get(-1, 100, 321, 45) };
/*    */   
/*    */   public ToolType type;
/*    */   
/* 28 */   public int level = 0;
/*    */   
/*    */   public ToolItem(ToolType type, int level) {
/* 31 */     this.type = type;
/* 32 */     this.level = level;
/*    */   }
/*    */   
/*    */   public int getColor() {
/* 36 */     return LEVEL_COLORS[this.level];
/*    */   }
/*    */   
/*    */   public int getSprite() {
/* 40 */     return this.type.sprite + 160;
/*    */   }
/*    */   
/*    */   public void renderIcon(Screen screen, int x, int y) {
/* 44 */     screen.render(x, y, getSprite(), getColor(), 0);
/*    */   }
/*    */   
/*    */   public void renderInventory(Screen screen, int x, int y) {
/* 48 */     screen.render(x, y, getSprite(), getColor(), 0);
/* 49 */     Font.draw(getName(), screen, x + 8, y, Color.get(-1, 555, 555, 555));
/*    */   }
/*    */   
/*    */   public String getName() {
/* 53 */     return LEVEL_NAMES[this.level] + " " + this.type.name;
/*    */   }
/*    */   
/*    */   public void onTake(ItemEntity itemEntity) {}
/*    */   
/*    */   public boolean canAttack()
/*    */   {
/* 60 */     return true;
/*    */   }
/*    */   
/*    */   public int getAttackDamageBonus(Entity e) {
/* 64 */     if (this.type == ToolType.axe) {
/* 65 */       return (this.level + 1) * 2 + this.random.nextInt(4);
/*    */     }
/* 67 */     if (this.type == ToolType.sword) {
/* 68 */       return (this.level + 1) * 3 + this.random.nextInt(2 + this.level * this.level * 2);
/*    */     }
/* 70 */     return 1;
/*    */   }
/*    */   
/*    */   public boolean matches(Item item) {
/* 74 */     if ((item instanceof ToolItem)) {
/* 75 */       ToolItem other = (ToolItem)item;
/* 76 */       if (other.type != this.type) return false;
/* 77 */       if (other.level != this.level) return false;
/* 78 */       return true;
/*    */     }
/* 80 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\item\ToolItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */