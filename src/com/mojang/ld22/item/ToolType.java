/*    */ package com.mojang.ld22.item;
/*    */ 
/*    */ public class ToolType {
/*  4 */   public static ToolType shovel = new ToolType("Shvl", 0);
/*  5 */   public static ToolType hoe = new ToolType("Hoe", 1);
/*  6 */   public static ToolType sword = new ToolType("Swrd", 2);
/*  7 */   public static ToolType pickaxe = new ToolType("Pick", 3);
/*  8 */   public static ToolType axe = new ToolType("Axe", 4);
/*    */   public final String name;
/*    */   public final int sprite;
/*    */   
/*    */   private ToolType(String name, int sprite)
/*    */   {
/* 14 */     this.name = name;
/* 15 */     this.sprite = sprite;
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\item\ToolType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */