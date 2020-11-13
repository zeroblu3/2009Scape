package org.runite.client;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL4bc;

import java.nio.FloatBuffer;


final class Class127_Sub1 extends LoginHandler {

   private static int anInt2682;


   static void method1755() {
      GL2 var0 = HDToolKit.gl;
      if(var0.isExtensionAvailable("GL_ARB_point_parameters")) {
         float[] var1 = new float[]{1.0F, 0.0F, 5.0E-4F};
         var0.glPointParameterfv('\u8129', var1, 0);
         FloatBuffer var2 = FloatBuffer.allocate(1);
         var0.glGetFloatv('\u8127', var2);
         float var3 = var2.get(0);
         if(var3 > 1024.0F) {
            var3 = 1024.0F;
         }

         var0.glPointParameterf('\u8126', 1.0F);
         var0.glPointParameterf('\u8127', var3);
      }

      if(var0.isExtensionAvailable("GL_ARB_point_sprite")) {
      }

   }

   static int method1757() {
      return anInt2682;
   }

   static void method1758(int var0) {
      anInt2682 = var0;
   }

   static {
      new Class128();
      anInt2682 = 2;
      new DataBuffer(131056);
   }
}
