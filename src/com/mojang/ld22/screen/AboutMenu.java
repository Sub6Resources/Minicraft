/*    */ package com.mojang.ld22.screen;
/*    */ 
/*    */ import com.mojang.ld22.gfx.Font;
/*    */ 
/*    */ public class AboutMenu extends Menu
/*    */ {
/*    */   private Menu parent;
/*    */   
/*    */   public AboutMenu(Menu parent)
/*    */   {
/* 11 */     this.parent = parent;
/*    */   }
/*    */   
/*    */   public void tick() {
/* 15 */     if ((this.input.attack.clicked) || (this.input.menu.clicked)) {
/* 16 */       this.game.setMenu(this.parent);
/*    */     }
/*    */   }
/*    */   
/*    */   public void render(com.mojang.ld22.gfx.Screen screen) {
/* 21 */     screen.clear(0);
/*    */     
/* 23 */     Font.draw("About Minicraft", screen, 20, 8, com.mojang.ld22.gfx.Color.get(0, 555, 555, 555));
/* 24 */     Font.draw("Minicraft was made", screen, 4, 24, com.mojang.ld22.gfx.Color.get(0, 333, 333, 333));
/* 25 */     Font.draw("by Markus Persson", screen, 4, 32, com.mojang.ld22.gfx.Color.get(0, 333, 333, 333));
/* 26 */     Font.draw("For the 22'nd ludum", screen, 4, 40, com.mojang.ld22.gfx.Color.get(0, 333, 333, 333));
/* 27 */     Font.draw("dare competition in", screen, 4, 48, com.mojang.ld22.gfx.Color.get(0, 333, 333, 333));
/* 28 */     Font.draw("december 2011.", screen, 4, 56, com.mojang.ld22.gfx.Color.get(0, 333, 333, 333));
/* 29 */     Font.draw("Modded by:", screen, 4, 72, com.mojang.ld22.gfx.Color.get(0, 333, 333, 333));
/* 30 */     Font.draw("Matthew Whitaker", screen, 4, 80, com.mojang.ld22.gfx.Color.get(0, 333, 333, 333));
			 Font.draw("Version 1.0.1", screen, 4, 96, com.mojang.ld22.gfx.Color.get(0, 555, 555, 555));
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\screen\AboutMenu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */