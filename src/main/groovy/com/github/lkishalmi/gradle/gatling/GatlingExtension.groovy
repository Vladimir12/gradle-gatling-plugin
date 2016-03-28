package com.github.lkishalmi.gradle.gatling

import org.gradle.api.Project

/**
 *
 * @author Laszlo Kishalmi
 */
class GatlingExtension {

    def toolVersion = '2.1.7'

    def jvmArgs = [
            '-server',
            '-XX:+UseThreadPriorities',
            '-XX:ThreadPriorityPolicy=42',
            '-Xms512M',
            '-Xmx512M',
            '-Xmn100M',
            '-XX:+HeapDumpOnOutOfMemoryError',
            '-XX:+AggressiveOpts',
            '-XX:+OptimizeStringConcat',
            '-XX:+UseFastAccessorMethods',
            '-XX:+UseParNewGC',
            '-XX:+UseConcMarkSweepGC',
            '-XX:+CMSParallelRemarkEnabled',
            '-Djava.net.preferIPv4Stack=true',
            '-Djava.net.preferIPv6Addresses=false'
    ]

    def simulations = {
        include "**/*Simulation.scala"
    }

    private final boolean isGatlingLayout

    GatlingExtension(Project project) {
        this.isGatlingLayout = project.file("src/gatling/simulations").exists() && project.file("src/gatling/data").exists() && project.file("src/gatling/bodies").exists()
    }

    String simulationsDir() {
        "src/gatling/${ isGatlingLayout ? "simulations" : "scala" }"
    }

    String dataDir() {
        "src/gatling/${ isGatlingLayout ? "data" : "resources/data" }"
    }

    String bodiesDir() {
        "src/gatling/${ isGatlingLayout ? "bodies" : "resources/bodies" }"
    }

    List<String> gatlingArgs(Project project) {
        ["-df", dataDir(), "-bdf", bodiesDir()]
    }
}
