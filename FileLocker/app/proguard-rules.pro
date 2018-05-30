# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\DaiPhongPC\AppData\Local\Android\Sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript Interface
# class:
#-keepclassmembers class fqcn.of.javascript.Interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-optimizations !code/simplification/arithmetic,!code/simplification/cast,!field/*,!class/merging/*
-optimizationpasses 5
-allowaccessmodification
-dontpreverify
-obfuscationdictionary F:\\key.txt
-classobfuscationdictionary F:\\key.txt
-packageobfuscationdictionary F:\\key.txt

#-classobfuscationdictionary mykey.txt
#-packageobfuscationdictionary mykey.txt


# The remainder of this file is identical to the non-optimized version
# of the Proguard configuration file (except that the other file has
# flags to turn off optimization).
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-verbose

-keepattributes *Annotation*
-keep public class com.google.vending.licensing.ILicensingService
-keep public class com.android.vending.licensing.ILicensingService

# For native methods, see http://proguard.sourceforge.net/manual/examples.html#native
-keepclasseswithmembernames class * {
    native <methods>;
}

# keep setters in Views so that animations can still work.
# see http://proguard.sourceforge.net/manual/examples.html#beans
-keepclassmembers public class * extends android.view.View {
   void set*(***);
   *** get*();
}

# We want to keep methods in Activity that could be used in the XML attribute onClick
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

# For enumeration classes, see http://proguard.sourceforge.net/manual/examples.html#enumerations
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

-keepclassmembers class **.R$* {
    public static <fields>;
}
-keep class com.google.firebase.**{*;}
-keep class com.google.android.gms.**{*;}


##---------------Begin: proguard configuration for Gson ----------
# Gson uses generic type information stored in a class file when working with
#fields. Proguard removes such information by default, so configure it to keep
#all of it.
-keepattributes Signature

# For using GSON @Expose annotation: o tren kia co roi
##-keepattributes *Annotation*

# Gson specific classes
-keep class sun.misc.Unsafe { *; }
#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.facebook.** {*;}
##---------------End: proguard configuration for Gson ----------

# The support library contains references to newer platform versions.
# Don't warn about those in case this app is linking against an older
# platform version.  We know about them, and they are safe.
-keep class android.support.v4.** { *; }
-keep interface android.support.v4.** { *; }
-keep class android.support.v7.** { *; }
-keep interface android.support.v7.** { *; }
-keep class com.squareup.picasso { *; }
-keep interface com.squareup.picasso.** { *; }
-keep class org.jsoup.** {*;}
-keep class com.security.filelocker.objects.**{ *; }
-keep class com.google.code.gson.** { *; }
-keep class org.apache.commons.io.** { *; }
-keep class me.relex.** { *; }
-keep class com.snatik.** { *; }

-dontwarn com.google.code.gson.**
-dontwarn com.squareup.picasso.**
-dontwarn org.jsoup.**
-dontwarn android.support.**
-dontwarn com.google.common.**
-dontwarn com.facebook.ads.internal.**
-dontwarn com.google.android.gms.**
-dontwarn org.apache.commons.io.**
-dontwarn me.relex.**
-dontwarn com.snatik.**
-dontwarn rx.internal.util.unsafe.**
