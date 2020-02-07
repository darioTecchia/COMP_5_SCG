#!/bin/bash
echo $PWD
clang-format -style=google $1 -i && clang $1 -S -O3 -emit-llvm -o - > $1.ll