/*    */ package com.mojang.ld22.sound;
/*    */ 
/*    */ import java.applet.AudioClip;
/*    */ 
/*    */ public class Sound
/*    */ {
/*  7 */   public static final Sound playerHurt = new Sound("/playerhurt.wav");
/*  8 */   public static final Sound playerDeath = new Sound("/death.wav");
/*  9 */   public static final Sound monsterHurt = new Sound("/monsterhurt.wav");
/* 10 */   public static final Sound test = new Sound("/test.wav");
/* 11 */   public static final Sound pickup = new Sound("/pickup.wav");
/* 12 */   public static final Sound bossdeath = new Sound("/bossdeath.wav");
/* 13 */   public static final Sound craft = new Sound("/craft.wav");
/*    */   private AudioClip clip;
/*    */   
/*    */   private Sound(String name)
/*    */   {
/*    */     try {
/* 19 */       this.clip = java.applet.Applet.newAudioClip(Sound.class.getResource(name));
/*    */     } catch (Throwable e) {
/* 21 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void play()
/*    */   {
/*    */     try
/*    */     {
/* 31 */       new Thread()
/*    */       {
/*    */         public void run()
/*    */         {
/* 29 */           Sound.this.clip.play();
/*    */         }
/*    */       }.start();
/*    */     } catch (Throwable e) {
/* 33 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Matthew\Downloads\Minicraft.jar!\com\mojang\ld22\sound\Sound.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */