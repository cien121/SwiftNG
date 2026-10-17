package com.v2ray.ang.util

import android.os.SystemClock
import com.v2ray.ang.AppConfig

/**
 * SwiftNG diagnostic performance timer.
 *
 * Used in the -diag build to pinpoint where latency comes from:
 * DNS resolution vs TCP connect vs config build vs Go core measurement.
 *
 * All timings use [SystemClock.elapsedRealtime] (monotonic, ms precision).
 * Output goes to logcat under tag "SwiftNG-Diag" so it can be grepped with:
 *   adb logcat -s SwiftNG-Diag
 */
object DiagTimer {
    const val TAG = "SwiftNG-Diag"

    /**
     * Runs [block] and logs how long it took, with a phase label.
     * Returns the block's result.
     */
    inline fun <T> phase(label: String, extra: String = "", block: () -> T): T {
        val start = SystemClock.elapsedRealtime()
        return try {
            block()
        } finally {
            val elapsed = SystemClock.elapsedRealtime() - start
            val suffix = if (extra.isNotBlank()) " [$extra]" else ""
            LogUtil.i(TAG, "DIAG phase=$label elapsed=${elapsed}ms$suffix")
        }
    }

    /**
     * Simple one-shot log with a timestamp. Useful for marking event points.
     */
    fun mark(label: String, extra: String = "") {
        val suffix = if (extra.isNotBlank()) " [$extra]" else ""
        LogUtil.i(TAG, "DIAG mark=$label at=${SystemClock.elapsedRealtime()}ms$suffix")
    }

    /**
     * Logs a summary line for a completed multi-phase operation.
     */
    fun summary(label: String, totalMs: Long, breakdown: String) {
        LogUtil.i(TAG, "DIAG summary=$label total=${totalMs}ms breakdown={$breakdown}")
    }
}
