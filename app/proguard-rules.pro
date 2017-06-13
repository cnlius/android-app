# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\code-soft\android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
#############################################
# 混淆的关键字解析
#############################################

# 保持相关元素不参与混淆的规则相关的几种命令:
#使用格式：[保持命令] [类] {[成员] }
#-keep	防止类和成员被移除或者被重命名
#-keepnames	防止类和成员被重命名
#-keepclassmembers	防止成员被移除或者被重命名
#-keepnames	防止成员被重命名
#-keepclasseswithmembers	防止拥有该成员的类和成员被移除或者被重命名
#-keepclasseswithmembernames	防止拥有该成员的类和成员被重命名

#-dontwarn//不用输出警告

#-dontnote//不用输出通知

#-keep 后跟项目的入口类， 将你不需要混淆的部分申明进来，因为有些类经过混淆会导致程序编译不通过，如：
#-keep public class * extends android.app.Fragment
#-keep 后还可以跟在项目中没有用到的类或方法，但在配置文件中有用到，如果不用该参数保留出来，在做优化时，就会直接的删除掉了，项目运行时会报找不到类的错误。

#############################################


#############################################
# 基本混淆
#############################################

# 指定压缩级别,代码混淆压缩比,在0~7之间，默认为5，一般不做修改
-optimizationpasses 5

# 指定不去忽略非公共库的类成员,即不跳过非公共的库的类成员
-dontskipnonpubliclibraryclassmembers

# 指定混淆时采用的算法，后面的参数是一个过滤器
# 这个过滤器是谷歌推荐的算法，一般不做更改
-optimizations !code/simplification/cast,!field/*,!class/merging/*
#-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

# 抛出异常时保留代码行号
-keepattributes SourceFile,LineNumberTable
# 保持泛型，避免混淆泛型
-keepattributes Signature

# --------------------------

# 将文件来源重命名为“SourceFile”字符串
-renamesourcefileattribute SourceFile

# 优化时允许访问并修改有修饰符的类和类的成员
-allowaccessmodification

# 类和类成员都使用唯一的名字
-useuniqueclassmembernames

#Fragment不需要在AndroidManifest.xml中注册，需要额外保护下
-keep public class * extends android.support.v4.app.Fragment
-keep public class * extends android.app.Fragment

# 保留继承的
-keep public class * extends android.support.v4.**
-keep public class * extends android.support.v7.**
-keep public class * extends android.support.annotation.**

#model vo类防混淆
-keep class com.liusong.app.vo.**{ *;}

#保持所有实现 Serializable 接口的类成员
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# 保持测试相关的代码
-dontnote junit.framework.**
-dontnote junit.runner.**
-dontwarn android.test.**
-dontwarn android.support.test.**
-dontwarn org.junit.**
####################################################

# 保留support下的所有类及其内部类
-keep class android.support.** {*;}

# OkHttp3
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *;}
-dontwarn okio.**