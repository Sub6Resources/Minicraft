/*    */ package com.mojang.ld22.screen;
/*    */ 
/*    */ import com.mojang.ld22.gfx.Color;
/*    */ import com.mojang.ld22.gfx.Font;
/*    */ 
/*    */ public class WonMenu extends Menu
/*    */ {
/*  8 */   private int inputDelay = 60;
/*    */   
/*    */ 
/*    */ 
/*    */   public void tick()
/*    */   {
/* 14 */     if (this.inputDelay > 0) {
/* 15 */       this.inputDelay -= 1;
/* 16 */     } else if ((this.input.attack.clicked) || (this.input.menu.clicked)) {
/* 17 */       this.game.setMenu(new TitleMenu());
/*    */     }
/*    */   }
/*    */   
/*    */   public void render(com.mojang.ld22.gfx.Screen screen) {
/* 22 */     Font.renderFrame(screen, "", 1, 3, 18, 9);
/* 23 */     Font.draw("You won! Yay!", screen, 16, 32, Color.get(-1, 555, 555, 555));
/*    */     
/* 25 */     int seconds = this.game.gameTime / 60;
/* 26 */     int minutes = seconds / 60;
/* 27 */     int hours = minutes / 60;
/* 28 */     minutes %= 60;
/* 29 */     seconds %= 60;
/*    */     
/* 31 */     String timeString = "";
/* 32 */     if (hours > 0) {
/* 33 */       timeString = hours + "h" + (minutes < 10 ? "0" : "") + minutes + "m";
/*    */     } else {
/* 35 */       timeString = minutes + "m " + (seconds < 10 ? "0" : "") + seconds + "s";
/*    */     }
/* 37 */     Font.draw("Time:", screen, 16, 40, Color.get(-1, 555, 555, 555));
/* 38 */     Font.draw(timeString, screen, 56, 40, Color.get(-1, 550, 550, 550));
/* 39 */     Font.draw("Score:", screen, 16, 48, Color.get(-1, 555, 555, 555));
/* 40 */     Font.draw(""+this.game.player.score, screen, 64, 48, Color.get(-1, 550, 550, 550));
/* 41 */     Font.draw("Press C to win", screen, 16, 64, Color.get(-1, 333, 333, 333));
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\screen\WonMenu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */