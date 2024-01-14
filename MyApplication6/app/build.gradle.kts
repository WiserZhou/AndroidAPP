plugins {
    id("com.android.application") // 使用 com.android.application 插件，标识这是一个 Android 应用项目
}

android {
    namespace = "com.example.myapplication" // 设置应用的命名空间

    compileSdk = 33 // 编译时使用的 SDK 版本

    defaultConfig {
        applicationId = "com.example.myapplication" // 应用的唯一标识符

        minSdk = 31 // 支持的最低 Android 版本
        targetSdk = 33 // 目标 Android 版本

        versionCode = 1 // 应用版本号，用于区分不同版本的应用
        versionName = "1.0" // 应用版本名，可用于显示给用户

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner" // 测试运行器
    }

    buildTypes {
        release {
            isMinifyEnabled = false // 是否开启代码混淆和优化
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            ) // 指定 ProGuard 规则文件
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8 // 源代码兼容性
        targetCompatibility = JavaVersion.VERSION_1_8 // 目标平台兼容性
    }
}

dependencies {
    // 应用所依赖的库

    implementation("androidx.appcompat:appcompat:1.6.1") // AndroidX AppCompat 库，提供向后兼容的界面特性
    implementation("com.google.android.material:material:1.8.0") // Material Design 组件库
    implementation("androidx.constraintlayout:constraintlayout:2.1.4") // 约束布局库
    testImplementation("junit:junit:4.13.2") // JUnit 测试库
    androidTestImplementation("androidx.test.ext:junit:1.1.5") // AndroidX 单元测试库
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1") // Espresso 功能测试库
}
