#!/bin/bash
echo $PWD
clang-format -style=google $1 -i && clang $1 -S -O3 -emit-llvm -o - > $1.ll && source emsdk/emsdk_env.sh && emcc $1 -o $1.html -s NO_EXIT_RUNTIME=0