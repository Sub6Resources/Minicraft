/*    */ package com.mojang.ld22;
/*    */ 
/*    */ import java.applet.Applet;
/*    */ import java.awt.BorderLayout;
/*    */ 
/*    */ public class GameApplet extends Applet
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*  9 */   private Game game = new Game();
/*    */   
/*    */   public void init() {
/* 12 */     setLayout(new BorderLayout());
/* 13 */     add(this.game, "Center");
/*    */   }
/*    */   
/*    */   public void start() {
/* 17 */     this.game.start();
/*    */   }
/*    */   
/*    */   public void stop() {
/* 21 */     this.game.stop();
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\GameApplet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */