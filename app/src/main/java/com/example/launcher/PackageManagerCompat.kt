package com.example.launcher

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager

object PackageManagerCompat {

    fun getInstalledApps(
        context: Context
    ): List<PackageInformation> {

        val packs: List<PackageInfo> = context.packageManager.getInstalledPackages(PackageManager.GET_META_DATA)
            .filter { isUserApp(it.applicationInfo, context) }

        val res: ArrayList<PackageInformation> = ArrayList()

        for (i in packs.indices) {

            val p = packs[i]
            if (p.versionName == null) {
                continue
            }

            val newInfo = PackageInformation(
                appName = p.applicationInfo.loadLabel(context.packageManager).toString(),
                packageName = p.packageName,
                icon = p.applicationInfo.loadIcon(context.packageManager)
            )

            res += newInfo
        }

        return res.toList()
    }

    private fun isUserApp(ai: ApplicationInfo, context: Context): Boolean {
        return context.packageManager.getLaunchIntentForPackage(ai.packageName) != null
    }
}