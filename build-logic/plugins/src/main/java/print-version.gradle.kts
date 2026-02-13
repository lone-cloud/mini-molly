/*
 * Copyright 2026 Molly Instant Messenger
 * SPDX-License-Identifier: AGPL-3.0-only
 */

import com.android.build.gradle.AppExtension
import groovy.json.JsonOutput

val android = extensions.getByType<AppExtension>()

tasks.register("printVersion") {
  group = "help"
  description = "Prints app version information as JSON."

  val versionCode = provider { android.defaultConfig.versionCode }
  val versionName = provider { android.defaultConfig.versionName }

  doLast {
    val versionInfo = mapOf(
      "android" to mapOf(
        "versionCode" to versionCode.get(),
        "versionName" to versionName.get(),
      ),
    )

    val json = JsonOutput.prettyPrint(JsonOutput.toJson(versionInfo))
    println(json)
  }
}
