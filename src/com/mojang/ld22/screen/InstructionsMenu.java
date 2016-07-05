/*    */ package com.mojang.ld22.screen;
/*    */ 
/*    */ import com.mojang.ld22.gfx.Font;
/*    */ 
/*    */ public class InstructionsMenu extends Menu
/*    */ {
/*    */   private Menu parent;
/*    */   
/*    */   public InstructionsMenu(Menu parent)
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
/* 23 */     Font.draw("HOW TO PLAY", screen, 36, 8, com.mojang.ld22.gfx.Color.get(0, 555, 555, 555));
/* 24 */     Font.draw("Move your character", screen, 4, 24, com.mojang.ld22.gfx.Color.get(0, 333, 333, 333));
/* 25 */     Font.draw("with the arrow keys", screen, 4, 32, com.mojang.ld22.gfx.Color.get(0, 333, 333, 333));
/* 26 */     Font.draw("press C to attack", screen, 4, 40, com.mojang.ld22.gfx.Color.get(0, 333, 333, 333));
/* 27 */     Font.draw("and X to open the", screen, 4, 48, com.mojang.ld22.gfx.Color.get(0, 333, 333, 333));
/* 28 */     Font.draw("inventory and to", screen, 4, 56, com.mojang.ld22.gfx.Color.get(0, 333, 333, 333));
/* 29 */     Font.draw("use items.", screen, 4, 64, com.mojang.ld22.gfx.Color.get(0, 333, 333, 333));
/* 30 */     Font.draw("Select an item in", screen, 4, 72, com.mojang.ld22.gfx.Color.get(0, 333, 333, 333));
/* 31 */     Font.draw("the inventory to", screen, 4, 80, com.mojang.ld22.gfx.Color.get(0, 333, 333, 333));
/* 32 */     Font.draw("equip it.", screen, 4, 88, com.mojang.ld22.gfx.Color.get(0, 333, 333, 333));
/* 33 */     Font.draw("Kill the air wizard", screen, 4, 96, com.mojang.ld22.gfx.Color.get(0, 333, 333, 333));
/* 34 */     Font.draw("to win the game!", screen, 4, 104, com.mojang.ld22.gfx.Color.get(0, 333, 333, 333));
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\screen\InstructionsMenu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */