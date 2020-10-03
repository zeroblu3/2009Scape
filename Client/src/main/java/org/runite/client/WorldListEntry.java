package org.runite.client;

import org.rs09.client.config.GameConfig;

import java.util.Objects;

final class WorldListEntry extends Class44 {

    static WorldListCountry[] countries;
    RSString activity;
   int worldId;
   static boolean aBoolean2623 = true;
   RSString address;
   static int anInt2626 = 20;
   static Class155 aClass155_2627;


   static void method1076() {
      try {
         Class154.aReferenceCache_1964.clear();
      } catch (RuntimeException var2) {
         throw ClientErrorException.clientError(var2, "ba.C(" + 88 + ')');
      }
   }

   static void parseWorldList(DataBuffer buffer) {
      try {
         int var2 = buffer.getSmart();
         countries = new WorldListCountry[var2];

         int var3;
         for(var3 = 0; var3 < var2; ++var3) {
            countries[var3] = new WorldListCountry();
            countries[var3].flagId = buffer.getSmart();
            countries[var3].name = buffer.getGJString2(105);
         }

         Class53.worldListOffset = buffer.getSmart();
         Class100.worldListArraySize = buffer.getSmart();
         Class57.activeWorldListSize = buffer.getSmart();
         Class117.worldList = new WorldListEntry[-Class53.worldListOffset + Class100.worldListArraySize + 1];

         for(var3 = 0; var3 < Class57.activeWorldListSize; ++var3) {
            int worldId = buffer.getSmart();
            WorldListEntry var5 = Class117.worldList[worldId] = new WorldListEntry();
            var5.countryIndex = buffer.readUnsignedByte();
            var5.settings = buffer.readInt();
            var5.worldId = worldId - -Class53.worldListOffset;
            var5.activity = buffer.getGJString2(98);
            var5.address = buffer.getGJString2(79);
            GameConfig.WORLD = worldId;
//            GameLaunch.SETTINGS.setWorld(worldId);
            System.out.println("Registering to world: " + GameConfig.WORLD);
         }
         Unsorted.updateStamp = buffer.readInt();
         Class30.loadedWorldList = true;
      } catch (RuntimeException var6) {
         throw ClientErrorException.clientError(var6, "hi.B(" + (buffer != null?"{...}":"null") + ',' + -88 + ')');
      }
   }

   final WorldListCountry method1078(int var1) {
      try {
         return countries[this.countryIndex];
      } catch (RuntimeException var3) {
         throw ClientErrorException.clientError(var3, "ba.B(" + var1 + ')');
      }
   }

   static int method1079(int var0) {
      try {
         if(0 > var0) {
            return 0;
         } else {
            Class3_Sub25 var2 = (Class3_Sub25)Class3_Sub2.aHashTable_2220.get((long)var0);
            if(var2 == null) {
               return Objects.requireNonNull(Unsorted.method2069(var0)).size;
            } else {
               int var3 = 0;

               for(int var4 = 0; var4 < var2.anIntArray2547.length; ++var4) {
                  if(var2.anIntArray2547[var4] == -1) {
                     ++var3;
                  }
               }

               var3 += Objects.requireNonNull(Unsorted.method2069(var0)).size + -var2.anIntArray2547.length;
               return var3;
            }
         }
      } catch (RuntimeException var5) {
         throw ClientErrorException.clientError(var5, "ba.D(" + var0 + ',' + (byte) -80 + ')');
      }
   }

}
