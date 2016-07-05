/*    */ package com.mojang.ld22.screen;
/*    */ 
/*    */ import com.mojang.ld22.InputHandler;
/*    */ import com.mojang.ld22.InputHandler.Key;
/*    */ import com.mojang.ld22.gfx.Screen;
/*    */ 
/*    */ public class TitleMenu extends Menu
/*    */ {
/*  9 */   private int selected = 0;
/*    */   
/* 11 */   private static final String[] options = { "Start game", "How to play", "About" };
/*    */   
/*    */ 
/*    */ 
/*    */   public void tick()
/*    */   {
/* 17 */     if (this.input.up.clicked) this.selected -= 1;
/* 18 */     if (this.input.down.clicked) { this.selected += 1;
/*    */     }
/* 20 */     int len = options.length;
/* 21 */     if (this.selected < 0) this.selected += len;
/* 22 */     if (this.selected >= len) { this.selected -= len;
/*    */     }
/* 24 */     if ((this.input.attack.clicked) || (this.input.menu.clicked)) {
/* 25 */       if (this.selected == 0) {
/* 26 */         com.mojang.ld22.sound.Sound.test.play();
/* 27 */         this.game.resetGame();
/* 28 */         this.game.setMenu(null);
/*    */       }
/* 30 */       if (this.selected == 1) this.game.setMenu(new InstructionsMenu(this));
/* 31 */       if (this.selected == 2) this.game.setMenu(new AboutMenu(this));
/*    */     }
/*    */   }
/*    */   
/*    */   public void render(Screen screen) {
/* 36 */     screen.clear(0);
/*    */     
/* 38 */     int h = 2;
/* 39 */     int w = 13;
/* 40 */     int titleColor = com.mojang.ld22.gfx.Color.get(0, 8, 131, 551);
/* 41 */     int xo = (screen.w - w * 8) / 2;
/* 42 */     int yo = 24;
/* 43 */     for (int y = 0; y < h; y++) {
/* 44 */       for (int x = 0; x < w; x++) {
/* 45 */         screen.render(xo + x * 8, yo + y * 8, x + (y + 6) * 32, titleColor, 0);
/*    */       }
/*    */     }
/*    */     
/* 49 */     for (int i = 0; i < 3; i++) {
/* 50 */       String msg = options[i];
/* 51 */       int col = com.mojang.ld22.gfx.Color.get(0, 222, 222, 222);
/* 52 */       if (i == this.selected) {
/* 53 */         msg = "> " + msg + " <";
/* 54 */         col = com.mojang.ld22.gfx.Color.get(0, 555, 555, 555);
/*    */       }
/* 56 */       com.mojang.ld22.gfx.Font.draw(msg, screen, (screen.w - msg.length() * 8) / 2, (8 + i) * 8, col);
/*    */     }
/*    */     
/* 59 */     com.mojang.ld22.gfx.Font.draw("(Arrow keys,X and C)", screen, 0, screen.h - 8, com.mojang.ld22.gfx.Color.get(0, 111, 111, 111));
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\screen\TitleMenu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */