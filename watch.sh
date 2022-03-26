#!/bin/bash
watchman-make -p 'shared/src/**/*.kt' 'shared/build.gradle.kts' --run ./build.sh
